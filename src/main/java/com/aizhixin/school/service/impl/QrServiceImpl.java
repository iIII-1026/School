package com.aizhixin.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.domain.Type;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.dto.QrPageDTO;
import com.aizhixin.school.exception.CommonException;
import com.aizhixin.school.mapper.QrMapper;
import com.aizhixin.school.service.QrService;
import com.aizhixin.school.vo.QrVO;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 */
@Service
public class QrServiceImpl extends ServiceImpl<QrMapper, Qr> implements QrService {

    @Autowired
    private QrMapper qrMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(QrDTO qrDTO) {
        LambdaQueryWrapper<Qr> qrWrapper = new QueryWrapper<Qr>().lambda()
                .eq(Qr::getPip,qrDTO.getPip());
        if (qrMapper.selectCount(qrWrapper) > 0){
            throw new CommonException("添加失败，输入的打卡地点已存在！");
        }
        // 生成指定url对应的二维码到文件，宽和高都是300像素
        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("G:/java/Qr/qrcode1.jpg"));
        Qr qr = new Qr();
        BeanUtils.copyProperties(qrDTO,qr);
        qr.setCreateTime(LocalDateTime.now());
        qr.setPhoto("G:/java/Qr/qrCode1.jpg");
        qrMapper.insert(qr);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(QrDTO qrDTO) {
        LambdaQueryWrapper<Qr> qrWrapper = new QueryWrapper<Qr>().lambda()
                .eq(Qr::getId,qrDTO.getId());
        if (qrMapper.selectCount(qrWrapper) == 0){
            throw new CommonException("修改失败，输入的人员信息id不存在！");
        }
        Qr qr = qrMapper.selectById(qrDTO.getId());
        BeanUtils.copyProperties(qrDTO,qr);
        qrMapper.updateById(qr);

    }

    @Override
    public Page<QrVO> findAllByPage(QrPageDTO qrPageDTO) {
        Page<Qr> qrPage = new Page<>(qrPageDTO.getPageNumber(), qrPageDTO.getPageSize());
        Page<QrVO> accessPage = qrMapper.getQrList(qrPage, qrPageDTO);
//        LambdaQueryWrapper<Qr> lambda =new QueryWrapper<Qr>().lambda()
//                .like(StrUtil.isNotBlank(pipName),Qr::getPip,pipName);
//        return qrMapper.selectPage(qrPage, lambda);
        return accessPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer QrId) {
        LambdaQueryWrapper<Qr> useQueryWrapper = new QueryWrapper<Qr>().lambda()
                .eq(Qr::getId,QrId);
        if (qrMapper.selectCount(useQueryWrapper) == 0){
            throw new CommonException("删除失败，输入的人员信息id不存在！");
        }
        qrMapper.deleteById(QrId);

    }

//    @Override
//    public void qrCreate() {
//        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        QrCodeUtil.generate("https://hutool.cn/", 300, 300, FileUtil.file("G:/java/Qr/qrcode.jpg"));
//        System.out.println("ok");
//    }
}




