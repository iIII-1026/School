package com.aizhixin.school.service;

import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.*;
import com.aizhixin.school.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {

    /**
     * 学生/教职工登记
     * @param userLoginDTO
     * @return
     */
    UserVO login(String code,UserLoginDTO userLoginDTO);

    /**
     * 访客登记
     * @param visitorDTO
     * @return
     */
    VisitorVO visitorLogin(VisitorDTO visitorDTO);
    /**
     * 人员信息新增
     * @param userDTO
     */
    void save(UserDTO userDTO);
    /**
     * 人员信息修改
     * @param userDTO
     */
    void updateById(UserDTO userDTO);

    /**
     * 人员信息分页查询
     */
    Page<UserPageVO> findAllByPage(UserPageDTO userPageDTO);

    /**
     * 人员信息删除
     * @param userId
     */
    void deleteById(Integer userId);

    /**
     * 人员信息批量删除
     * @param ids
     * @return
     */
    boolean removeBatch1(Integer[] ids);

    /**
     * excel导入
     * @param users
     * @return
     */
    Integer insertExcel(List<User> users);

    /**
     * 人数查询
     */
    NumberVO selectNumber();

    /**
     * excel导入（poi）
     * @param file
     */
    void readExcel(MultipartFile file);

    /**
     * 按月查询
     * @param monthDTO
     * @return
     */
    Page<MonthVO> findMonth(MonthDTO monthDTO);

    /**
     * 按类型名称查询
     * @param typeSelectDTO
     * @return
     */
    Page<TypeSelectVO> findTypeSelect(TypeSelectDTO typeSelectDTO);

    /**
     * 按小时查询
     * @param hourDTO
     * @return
     */
    Page<HourVO> findHour(HourDTO hourDTO);

    /**
     * 访客信息24h定时删除
     */
    void timerRun();

    void getOpenId(String code) throws IOException;
}
