package com.aizhixin.school.service;

import com.aizhixin.school.domain.Access;
import com.aizhixin.school.dto.AccessDTO;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.vo.AccessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface AccessService extends IService<Access> {

    /**
     * 出入记录新增
     * @param accessDTO
     */
    void save(AccessDTO accessDTO);

    /**
     * 出入记录分页查询
     * @param accessDTO
     * @return
     */
    Page<AccessVO> findByPage(AccessDTO accessDTO);
}
