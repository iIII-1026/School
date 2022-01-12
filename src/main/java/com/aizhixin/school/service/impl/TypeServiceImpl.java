package com.aizhixin.school.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.aizhixin.school.domain.Type;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.dto.TypePageDTO;
import com.aizhixin.school.exception.CommonException;
import com.aizhixin.school.mapper.TypeMapper;
import com.aizhixin.school.service.TypeService;
import com.aizhixin.school.vo.AccessVO;
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

/**
 *
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(TypeDTO typeDTO) {
        LambdaQueryWrapper<Type> typeWrapper = new QueryWrapper<Type>().lambda()
                .eq(Type::getTypeName,typeDTO.getTypeName());
        if (typeMapper.selectCount(typeWrapper) > 0){
            throw new CommonException("添加失败，输入的人员类型名称已存在！");
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeDTO,type);
        type.setCreateTime(LocalDateTime.now());
        typeMapper.insert(type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateById(TypeDTO typeDTO) {
        LambdaQueryWrapper<Type> typeWrapper = new QueryWrapper<Type>().lambda()
                .eq(Type::getId,typeDTO.getId());
        if (typeMapper.selectCount(typeWrapper) == 0){
            throw new CommonException("修改失败");
        }
        Type type = new Type();
        BeanUtils.copyProperties(typeDTO,type);
        type.setCreateTime(LocalDateTime.now());
        typeMapper.updateById(type);
    }

    @Override
    public Page<TypeVO> findAllByPage(TypePageDTO typePageDTO) {
        Page<Type> typePage = new Page<>(typePageDTO.getPageNumber(),typePageDTO.getPageSize());
        Page<TypeVO> accessPage = typeMapper.getTypeList(typePage, typePageDTO);
//        LambdaQueryWrapper<Type> typeWrapper = new QueryWrapper<Type>().lambda()
//                .like(StrUtil.isNotBlank(typeName),Type::getTypeName,typeName)
//                .orderByDesc(Type::getCreateTime);
//        return typeMapper.selectPage(typePage,typeWrapper);
        return accessPage;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(Integer typeId) {
        LambdaQueryWrapper<Type> typeWrapper = new QueryWrapper<Type>().lambda()
                .eq(Type::getId,typeId);
        if (typeMapper.selectCount(typeWrapper) == 0){
            throw new CommonException("删除失败，输入的人员类型id不存在！");
        }
        typeMapper.deleteById(typeId);
    }
}




