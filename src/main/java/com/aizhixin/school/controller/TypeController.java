package com.aizhixin.school.controller;

import com.aizhixin.school.domain.Type;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.AccessDTO;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.dto.TypePageDTO;
import com.aizhixin.school.service.TypeService;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "人员管理-人员类型")
@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @ApiOperation(value = "人员类型添加接口")
    @PostMapping("/addType")
    public Result<String> save(@RequestBody @Validated TypeDTO dto) {
        typeService.save(dto);
        return Result.ok("添加成功");
    }


    @ApiOperation(value = "人员类型修改接口")
    @PutMapping("/updateType")
    public Result<?> updateById(@RequestBody @Validated TypeDTO dto) {
        typeService.updateById(dto);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "人员类型分页查询")
    @GetMapping("/findByPage")
    public Result<Page<TypeVO>> findAllByPage(@Validated TypePageDTO typePageDTO) {
        Page<TypeVO> allByPage = typeService.findAllByPage(typePageDTO);
        return Result.ok(allByPage);
    }

    @ApiOperation(value = "人员类型删除接口")
    @DeleteMapping("/deleteById")
    public Result<?> deleteById(@RequestParam("deleteById") @ApiParam(value = "要删除的人员类型id", required = true) Integer typeId) {
        typeService.deleteById(typeId);
        return Result.ok("删除成功");
    }
}
