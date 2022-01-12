package com.aizhixin.school.mapper;

import com.aizhixin.school.domain.Type;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.dto.TypePageDTO;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.aizhixin.school.domain.Type
 */
@Mapper
@Repository
public interface TypeMapper extends BaseMapper<Type> {
    Page<TypeVO> getTypeList(Page<Type> typePage, TypePageDTO typePageDTO);

}




