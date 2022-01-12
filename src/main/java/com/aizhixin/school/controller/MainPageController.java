package com.aizhixin.school.controller;


import com.aizhixin.school.dto.HourDTO;
import com.aizhixin.school.dto.MonthDTO;
import com.aizhixin.school.dto.TypeSelectDTO;
import com.aizhixin.school.service.UserService;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.vo.HourVO;
import com.aizhixin.school.vo.MonthVO;
import com.aizhixin.school.vo.NumberVO;
import com.aizhixin.school.vo.TypeSelectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "首页")
@RestController
@RequestMapping("/mainPage")
public class MainPageController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "人数查询")
    @GetMapping(value = "/findByData")
    public Result<NumberVO> selectNumber() {
        return Result.ok(userService.selectNumber());
    }

    @ApiOperation(value = "按月查询")
    @GetMapping(value = "/findMonths")
    public Result<MonthVO> findMonth(MonthDTO monthDTO) {
        return Result.ok(userService.findMonth(monthDTO));
    }

    @ApiOperation(value = "按人员类型查询")
    @GetMapping(value = "/findRole")
    public Result<TypeSelectVO> findTypeSelect(TypeSelectDTO typeSelectDTO) {
        return Result.ok(userService.findTypeSelect(typeSelectDTO));
    }

    @ApiOperation(value = "按小时查询")
    @GetMapping(value = "/findHours")
    public Result<HourVO> findHour(HourDTO hourDTO) {
        return Result.ok(userService.findHour(hourDTO));
    }
}
