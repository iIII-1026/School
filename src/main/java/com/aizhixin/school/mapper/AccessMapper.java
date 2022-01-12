package com.aizhixin.school.mapper;

import com.aizhixin.school.domain.Access;
import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.dto.AccessDTO;
import com.aizhixin.school.vo.AccessVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Entity com.aizhixin.school.domain.Access
 */
@Mapper
@Repository
public interface AccessMapper extends BaseMapper<Access> {

    Page<AccessVO> findAccessPage(Page<AccessDTO> accessDTOPage, AccessDTO accessDTO);

//    List<Qr> findQrByAccessId(Integer id);
}




