package com.aizhixin.school.service;

import com.aizhixin.school.domain.Type;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.dto.TypePageDTO;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface TypeService extends IService<Type> {

    /**
     * 人员类型新增
     * @param typeDTO
     */
    void save(TypeDTO typeDTO);

    /**
     * 人员了欸选哪个修改
     * @param typeDTO
     */
    void updateById(TypeDTO typeDTO);

    /**
     * 人员类型分页查询
     * @param typePageDTO
     * @return
     */
    Page<TypeVO> findAllByPage(TypePageDTO typePageDTO);

    /**
     * 人员类型删除
     * @param typeId
     */
    void deleteById(Integer typeId);

}
