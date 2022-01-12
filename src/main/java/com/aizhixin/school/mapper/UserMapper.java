package com.aizhixin.school.mapper;

import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.HourDTO;
import com.aizhixin.school.dto.MonthDTO;
import com.aizhixin.school.dto.TypeSelectDTO;
import com.aizhixin.school.dto.UserPageDTO;
import com.aizhixin.school.vo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.aizhixin.school.domain.User
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 人员信息分页查询
     * @param typePage
     * @param userPageDTO
     * @return
     */
    Page<UserPageVO> getUserList(Page<User> typePage, UserPageDTO userPageDTO);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    boolean removeBatch(Integer[] ids);

    /**
     * 人数查询
     * @return
     */
    NumberVO selectNumber();

    /**
     * 按月查询
     * @param monthDTOPage
     * @param monthDTO
     * @return
     */
    Page<MonthVO> getMonthList(Page<MonthDTO> monthDTOPage, MonthDTO monthDTO);

    /**
     * 按类型查询
     * @param typeSelectDTOPage
     * @param typeSelectDTO
     * @return
     */
    Page<TypeSelectVO> getTypeSelectList(Page<TypeSelectDTO> typeSelectDTOPage, TypeSelectDTO typeSelectDTO);

    /**
     * 按小时查询
     * @param typeSelectDTOPage
     * @param typeSelectDTO
     * @return
     */
    Page<HourVO> getHourList(Page<HourDTO> hourDTOPage, HourDTO hourDTO);
}




