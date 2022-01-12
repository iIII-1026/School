package com.aizhixin.school.service.impl;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.aizhixin.school.domain.Access;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.*;
import com.aizhixin.school.exception.CommonException;
import com.aizhixin.school.mapper.AccessMapper;
import com.aizhixin.school.mapper.UserMapper;
import com.aizhixin.school.service.QrService;
import com.aizhixin.school.service.UserService;
import com.aizhixin.school.utils.WeChatUtil;
import com.aizhixin.school.vo.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AccessMapper accessMapper;

    @Autowired
    private QrService qrService;

    @Autowired
    private UserService userService;

    /**
     * 教职工/学生登记
     * @param userLoginDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserVO login(String code,UserLoginDTO userLoginDTO) {
        // 微信小程序ID
        String appid = "wx7fa962c9e357d54a";
        // 微信小程序秘钥
        String secret = "29b8712293dc85c4513a5df2271152ec";

        // 根据小程序穿过来的code想这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);

        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        //将获取到的openid与数据库的openid进行对比
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
                .eq(User::getOpenId, openid);
        if (userMapper.selectCount(lambda) == 0) {
            LambdaQueryWrapper<User> userlambda = new QueryWrapper<User>().lambda()
                    .eq(User::getCard, userLoginDTO.getCard())
                    .eq(User::getName, userLoginDTO.getName())
                    .eq(User::getPhone, userLoginDTO.getPhone());
            if (userMapper.selectCount(userlambda) == 0) {
                throw new CommonException("查无此人！");
            }
            User user = userMapper.selectOne(userlambda);
            user.setOpenId(userLoginDTO.getOpenId());
            userMapper.updateById(user);
            Access access = new Access();
            access.setQrId(userLoginDTO.getQrId());
            access.setUserId(user.getId());
            access.setScanCodeTime(LocalDateTime.now());
            accessMapper.insert(access);

            //返回信息
            UserVO userVO = new UserVO();
            userVO.setCard(user.getCard());
            userVO.setPhone(user.getPhone());
            userVO.setName(user.getName());
            userVO.setCurTime(LocalDateTime.now());
            userVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
            return userVO;
        }else{
            //如果openId存在，就在出入记录表增加一条记录，然后返回openId对应的人的信息，并显示扫码时间和扫码的出入口名称
            User user = new User();
            Access access = new Access();
            access.setQrId(userLoginDTO.getQrId());
            access.setUserId(user.getId());
            access.setScanCodeTime(LocalDateTime.now());
            accessMapper.insert(access);

            //返回信息
            UserVO userVO = new UserVO();
            userVO.setCard(user.getCard());
            userVO.setPhone(user.getPhone());
            userVO.setName(user.getName());
            userVO.setCurTime(LocalDateTime.now());
            userVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
            return userVO;
        }
    }

    /**
     * 访客登记
     * @param visitorDTO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public VisitorVO visitorLogin(VisitorDTO visitorDTO) {
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
                .eq(User::getCard, visitorDTO.getCard())
                .eq(User::getName, visitorDTO.getName())
                .eq(User::getPhone, visitorDTO.getPhone());
        if (userMapper.selectCount(lambda) == 0) {
            throw new CommonException("查无此人！");
        }
        User user = userMapper.selectOne(lambda);
        Access access = new Access();
        access.setQrId(visitorDTO.getQrId());
        access.setUserId(user.getId());
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);

        //存入user表的信息,24h后删除
        User users = new User();
        users.setCard(visitorDTO.getVisitorCard());
        users.setName(visitorDTO.getVisitorName());
        users.setPhone(visitorDTO.getVisitorPhone());
        users.setTypeId(4);
        users.setExpiry("24h");
        users.setState(1);
//        users.setQrId(visitorDTO.getQrId());
        users.setCreateTime(LocalDateTime.now());
        users.setInfo("访客事由："+visitorDTO.getVisitInfo()+",访客身份证号："+visitorDTO.getCard()+",访客手机号："+visitorDTO.getPhone()+",访客姓名："+visitorDTO.getName());
        userMapper.insert(users);

        //返回信息
        VisitorVO visitorVO = new VisitorVO();
//        visitorVO.setVisitorCard(visitorDTO.getVisitorCard());
        visitorVO.setVisitorPhone(visitorDTO.getVisitorPhone());
        visitorVO.setVisitorName(visitorDTO.getVisitorName());
        visitorVO.setCreateTime(LocalDateTime.now());
        visitorVO.setPip(qrService.getById(visitorDTO.getQrId()).getPip());
        visitorVO.setVisitInfo(visitorDTO.getVisitInfo());
        visitorVO.setName(visitorDTO.getName());
        visitorVO.setCard(visitorDTO.getCard());
        visitorVO.setPhone(visitorDTO.getPhone());
        return visitorVO;
    }

    /**
     * 人员信息增添
     * @param userDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserDTO userDTO) {
        LambdaQueryWrapper<User> eq = new QueryWrapper<User>().lambda()
                .eq(User::getCard, userDTO.getCard());
        if (userMapper.selectCount(eq) > 0){
            throw new CommonException("添加失败，输入的学号/工号/身份证号已存在！");
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setState(1);
        user.setModifyTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    /**
     * 人员信息修改
     * @param userDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(UserDTO userDTO) {
        LambdaQueryWrapper<User> useWrapper = new QueryWrapper<User>().lambda()
                .eq(User::getId,userDTO.getId());
        if (userMapper.selectCount(useWrapper) == 0){
            throw new CommonException("修改失败，输入的人员信息id不存在！");
        }
        User user = userMapper.selectById(userDTO.getId());
        BeanUtils.copyProperties(userDTO,user);
        user.setModifyTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * 人员信息分页查询
     * @param userPageDTO
     * @return
     */
    @Override
    public Page<UserPageVO> findAllByPage(UserPageDTO userPageDTO) {
        Page<User> userPage = new Page<>(userPageDTO.getPageNumber(), userPageDTO.getPageSize());
        Page<UserPageVO> userPageVOPage = userMapper.getUserList(userPage, userPageDTO);
//        LambdaQueryWrapper<User> lambda =new QueryWrapper<User>().lambda()
//                .like(StrUtil.isNotBlank(name),User::getName,name)
//                .orderByDesc(User::getModifyTime);
//        return userMapper.selectPage(userPage, lambda);
        return userPageVOPage;
    }

    /**
     * 人员信息删除
     * @param userId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer userId) {
        LambdaQueryWrapper<User> useQueryWrapper = new QueryWrapper<User>().lambda()
                .eq(User::getId,userId);
        if (userMapper.selectCount(useQueryWrapper) == 0){
            throw new CommonException("删除失败，输入的人员信息id不存在！");
        }
        userMapper.deleteById(userId);
    }

    /**
     * 人员信息批量删除
     * @param ids
     * @return
     */
    @Override
    public boolean removeBatch1(Integer[] ids) {
        return userMapper.removeBatch(ids);
    }

    /**
     *人员信息批量导入
     * @param users
     * @return
     */
    @Override
    public Integer insertExcel(List<User> users) {
        Integer insert= 0;
        for (User user : users){
            insert += userMapper.insert(user);
        }
        return insert;
    }

    @Override
    public NumberVO selectNumber() {
        return userMapper.selectNumber();
    }

    @Override
    public void readExcel(MultipartFile file) {
        ExcelReader reader = ExcelUtil.getReader("C:/Users/Kiven/Desktop/file1.xlsx");
        List<List<Object>> read = reader.read(1);
        System.out.println(read);
        List<User> userList=new ArrayList<>();
        for (List<Object> objects : read) {
            User user = new User();
//            user.setId(Integer.parseInt(objects.get(0).toString()));
            user.setOpenId(Integer.parseInt(objects.get(0).toString()));
            user.setTypeId(Integer.parseInt(objects.get(1).toString()));
            user.setName((String) objects.get(2));
            user.setCard(objects.get(3).toString());
            user.setPhone(objects.get(4).toString());
            user.setInfo((String) objects.get(5));
            user.setExpiry((String) objects.get(6));
            if("离校".equals(objects.get(7))){
                user.setState(0);
            }else {
                user.setState(1);
            }
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime ldt=LocalDateTime.parse(objects.get(8).toString(),df);
            LocalDateTime parse = LocalDateTime.parse(objects.get(9).toString(), df);
            user.setCreateTime(ldt);
            user.setModifyTime(parse);
            userList.add(user);
        }
        for (User user:userList) {
            userMapper.insert(user);
        }
    }

    /**
     * 图3：按月查询
     * @param monthDTO
     * @return
     */
    @Override
    public Page<MonthVO> findMonth(MonthDTO monthDTO) {
        Page<MonthDTO> monthVOPage = new Page<>(monthDTO.getPageNumber(), monthDTO.getPageSize());
        Page<MonthVO> monthList = userMapper.getMonthList(monthVOPage, monthDTO);
        return monthList;
    }

    @Override
    public Page<TypeSelectVO> findTypeSelect(TypeSelectDTO typeSelectDTO) {
        Page<TypeSelectDTO> typeSelectDTOPage = new Page<>(typeSelectDTO.getPageNumber(), typeSelectDTO.getPageSize());
        Page<TypeSelectVO> typeSelectList = userMapper.getTypeSelectList(typeSelectDTOPage, typeSelectDTO);
        return typeSelectList;
    }

    @Override
    public Page<HourVO> findHour(HourDTO hourDTO) {
        Page<HourDTO> hourDTOPage = new Page<>(hourDTO.getPageNumber(), hourDTO.getPageSize());
        Page<HourVO> hourVOPage = userMapper.getHourList(hourDTOPage, hourDTO);
        return hourVOPage;
    }


    /**
     * 访客信息24h后删除
     */
    public void timerRun() {
        // 一天的毫秒数
        long daySpan = 24 * 60 * 60 * 1000;
        // 规定的每天几点运行
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 15:16:00");
        // 首次运行时间
        try {
            Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sdf.format(new Date()));
            // 如果今天的已经过了 首次运行时间就改为明天
            if (System.currentTimeMillis() > startTime.getTime()){
                startTime = new Date(startTime.getTime() + daySpan);
            }
            Timer t = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    LambdaQueryWrapper<User> userWrapper = new QueryWrapper<User>().lambda()
                            .eq(User::getTypeId,4);
                    if (userMapper.selectCount(userWrapper) == 0){
                        System.out.println("无访客信息");
                    }
                    List<User> userList = userService.list(userWrapper);
                    for (User user : userList) {
                        //把LocalDateTime转换成Date类型
                        Instant instant = user.getCreateTime().atZone( ZoneId.systemDefault()).toInstant();
                        Date start = Date.from(instant);

                        //获取当前时间
                        Date end = new Date();
                        long cha = end.getTime() - start.getTime();
                        double result = cha * 1.0 / (1000 * 60 * 60);
                        if (result < 24) {
                            System.out.println("访客信息不符合");
                        } else {
                            userMapper.deleteById(user.getId());
                        }
                    }
                }
            };
            // 以每24小时执行一次
            t.schedule(task, startTime, daySpan);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getOpenId(String code) throws IOException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx7fa962c9e357d54a&secret=29b8712293dc85c4513a5df2271152ec&js_code="+code+"&grant_type=authorization_code";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()){
            String body = response.body().string();
            System.out.println(body);
            //1、入库，把openid存起来
            //返回一个token
        }
    }


}




