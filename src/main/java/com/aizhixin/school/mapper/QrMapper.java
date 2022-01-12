package com.aizhixin.school.mapper;

import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.domain.Type;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.dto.QrPageDTO;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.vo.QrVO;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity com.aizhixin.school.domain.Qr
 */
@Mapper
@Repository
public interface QrMapper extends BaseMapper<Qr> {
    Page<QrVO> getQrList(Page<Qr> qrPage, QrPageDTO qrPageDTO);

}




