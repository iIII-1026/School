package com.aizhixin.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.aizhixin.school.domain.Access;
import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.domain.Type;
import com.aizhixin.school.dto.AccessDTO;
import com.aizhixin.school.mapper.AccessMapper;
import com.aizhixin.school.service.AccessService;
import com.aizhixin.school.vo.AccessVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 */
@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access> implements AccessService {

    @Autowired
    private AccessMapper accessMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(AccessDTO accessDTO) {
        Access access = new Access();
        BeanUtils.copyProperties(accessDTO,access);
        access.setScanCodeTime(LocalDateTime.now());
        accessMapper.insert(access);
    }

    @Override
    public Page<AccessVO> findByPage(AccessDTO accessDTO) {
        Page<AccessDTO> page = new Page<>(accessDTO.getPageNumber(), accessDTO.getPageSize());
        Page<AccessVO> accessPage = accessMapper.findAccessPage(page, accessDTO);
        return accessPage;
//        LambdaQueryWrapper<AccessVO> accessWrapper = new QueryWrapper<AccessVO>().lambda()
//                .like(StrUtil.isNotBlank(accessDTO.getName()), AccessVO::getName, accessDTO.getName())
//                .orderByDesc(AccessVO::getCurrentTime);

//        return accessMapper.selectPage(accessPage, accessWrapper);
    }
}
//        List<AccessVO> records = accessPage.getRecords();
//        for (AccessVO record : records){
//            List<Qr> qrs = accessMapper.findQrByAccessId(record.getId());
//            if (qrs != null){
//                record.setQrs(qrs);
//            }
//        }

//        Page<UserRoleDTO> page = new Page<>(userRoleDTO.getPageNumber(), userRoleDTO.getPageSize());//创建分页对象
//        Page<UserRoleVO> userRoleVOPage = userRoleMapper.findUserRolePage(page,userRoleDTO);//分页的所有用户信息
//        List<UserRoleVO> records = userRoleVOPage.getRecords();//集合所有用户信息
//        //遍历用户信息
//        for (UserRoleVO record : records) {
//            //根据用户id来填入角色信息
//            List<RoleVO> roleVOS = userRoleMapper.findRoleByUserId(record.getId());
//            if (roleVOS != null) {
//                record.setRoleVOS(roleVOS);
//            }
//        }
//        return userRoleVOPage;






