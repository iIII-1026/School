package com.aizhixin.school.service;

import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.dto.QrPageDTO;
import com.aizhixin.school.vo.QrVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface QrService extends IService<Qr> {


    /**
     * 出入配置新增
     * @param qrDTO
     */
    void save(QrDTO qrDTO);
    /**
     * 出入配置修改
     * @param qrDTO
     */
    void updateById(QrDTO qrDTO);

    /**
     * 出入配置分页查询
     * @param pageNumber
     * @param pageSize
     * @param pipName
     * @return
     */
//    Page<Qr> findAllByPage(Integer pageNumber, Integer pageSize, String pipName);
    Page<QrVO> findAllByPage(QrPageDTO qrPageDTO);

    /**
     * 出入配置启用禁用
     * @param QrId
     */
    void deleteById(Integer QrId);

//    void qrCreate();
}
