package com.aizhixin.school.controller;

import com.aizhixin.school.domain.Access;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.UserLoginDTO;
import com.aizhixin.school.exception.CommonException;
import com.aizhixin.school.mapper.AccessMapper;
import com.aizhixin.school.mapper.UserMapper;
import com.aizhixin.school.service.QrService;
import com.aizhixin.school.vo.UserVO;
import com.alibaba.fastjson.JSONObject;
import com.aizhixin.school.dto.LoginDTO;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.utils.WeChatUtil;
import com.aizhixin.school.vo.BackVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

//@Api(tags = "登录")
//@RestController
//@RequestMapping("/login")
public class LoginController {


//    @Autowired
//    private UserMapper userMapper;
//
//    @Autowired
//    private QrService qrService;
//
//    @Autowired
//    private AccessMapper accessMapper;
//    /**
//     * 微信小程序登录
//     * @param loginDTO
//     * @return
//     */
//    @ApiOperation(value = "小程序登录")
//    @PostMapping("login")
//    public BackVO login(@RequestBody LoginDTO loginDTO) throws IOException {
//
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx7fa962c9e357d54a&secret=29b8712293dc85c4513a5df2271152ec&js_code="+loginDTO.getCode()+"&grant_type=authorization_code";
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder().url(url).build();
//        Response response = client.newCall(request).execute();
//        if (response.isSuccessful()){
//            String body = response.body().string();
//            System.out.println(body);
//            //1、入库，把openid存起来
//
//            //返回一个token
//        }
//        return new BackVO();
//    }
//    /**
//     * 登录
//     * @param
//     */
//    @ApiOperation(value = "小程序登录")
//    @GetMapping(value="/login")
//    public void login(String code,UserLoginDTO userLoginDTO) {
//        // 微信小程序ID
//        String appid = "wx7fa962c9e357d54a";
//        // 微信小程序秘钥
//        String secret = "29b8712293dc85c4513a5df2271152ec";
//
//        // 根据小程序穿过来的code想这个url发送请求
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
//        // 发送请求，返回Json字符串
//        String str = WeChatUtil.httpRequest(url, "GET", null);
//        // 转成Json对象 获取openid
//        JSONObject jsonObject = JSONObject.parseObject(str);
//
//        // 我们需要的openid，在一个小程序中，openid是唯一的
//        String openid = jsonObject.get("openid").toString();
//        //将获取到的openid与数据库的openid进行对比
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda()
//                .eq(User::getOpenId,openid);
//        if (userMapper.selectCount(lambda) == 0) {
//            LambdaQueryWrapper<User> userlambda = new QueryWrapper<User>().lambda()
//                    .eq(User::getCard, userLoginDTO.getCard())
//                    .eq(User::getName, userLoginDTO.getName())
//                    .eq(User::getPhone, userLoginDTO.getPhone());
//            if (userMapper.selectCount(userlambda) == 0) {
//                throw new CommonException("查无此人！");
//            }
//            User user = userMapper.selectOne(userlambda);
//            user.setOpenId(userLoginDTO.getOpenId());
//            userMapper.updateById(user);
//            Access access = new Access();
//            access.setQrId(userLoginDTO.getQrId());
//            access.setUserId(user.getId());
//            access.setScanCodeTime(LocalDateTime.now());
//            accessMapper.insert(access);
//
//            //返回信息
//            UserVO userVO = new UserVO();
//            userVO.setCard(user.getCard());
//            userVO.setPhone(user.getPhone());
//            userVO.setName(user.getName());
//            userVO.setCurTime(LocalDateTime.now());
//            userVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
//        }else{
//            //如果openId存在，就在出入记录表增加一条记录，然后返回openId对应的人的信息，并显示扫码时间和扫码的出入口名称
//            User user = new User();
//            Access access = new Access();
//            access.setQrId(userLoginDTO.getQrId());
//            access.setUserId(user.getId());
//            access.setScanCodeTime(LocalDateTime.now());
//            accessMapper.insert(access);
//
//            //返回信息
//            UserVO userVO = new UserVO();
//            userVO.setCard(user.getCard());
//            userVO.setPhone(user.getPhone());
//            userVO.setName(user.getName());
//            userVO.setCurTime(LocalDateTime.now());
//            userVO.setPip(qrService.getById(userLoginDTO.getQrId()).getPip());
//
//        }
//    }
}



