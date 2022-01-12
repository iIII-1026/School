package com.aizhixin.school.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.poi.excel.ExcelReader;
import com.aizhixin.school.domain.User;
import com.aizhixin.school.dto.UserDTO;
import com.aizhixin.school.dto.UserLoginDTO;
import com.aizhixin.school.dto.UserPageDTO;
import com.aizhixin.school.dto.VisitorDTO;
import com.aizhixin.school.service.UserService;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.vo.NumberVO;
import com.aizhixin.school.vo.UserPageVO;
import com.aizhixin.school.vo.UserVO;
import com.aizhixin.school.vo.VisitorVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Api(tags = "人员管理-人员信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @ApiOperation(value = "人数查询")
//    @GetMapping(value = "/selectNumber")
//    public Result<NumberVO> selectNumber() {
//        return Result.ok(userService.selectNumber());
//    }

    @ApiOperation(value = "教职工/学生登记接口")
    @GetMapping(value = "/login")
    public Result<UserVO> login(String code,UserLoginDTO userLoginDTO) {
        return Result.ok(userService.login(code,userLoginDTO));
    }
    @ApiOperation(value = "访客登记登陆接口")
    @GetMapping(value = "/visitorLogin")
    public Result<VisitorVO> visitorLogin(VisitorDTO visitorDTO) {
        return Result.ok(userService.visitorLogin(visitorDTO));
    }

    @ApiOperation(value = "人员信息批量导入")
    @PostMapping("/importInfo")
    public Result<?> insertUsers(@RequestPart @RequestParam("file") MultipartFile file){
        try {
            ExcelReader excelReader = new ExcelReader(file.getInputStream(), 0);
            //直接把Excel中的内容映射到实体类中
            List<User> users = excelReader.read(0, 0, User.class);
            //把映射的Excel中的数据添加到数据库中
            int i = userService.insertExcel(users);
            //返回影响的记录数
            return Result.ok(i);
        } catch ( IOException e) {
            e.printStackTrace();
            return Result.ok("失败");
        }
    }

    @ApiOperation(value = "人员信息批量导入（poi）")
    @PostMapping("/readInfo")
    public void readExcel(MultipartFile file){
        userService.readExcel(file);
    }
//    @ApiOperation(value = "人员信息批量导入")
//    @PostMapping("/importInfoFile")
//    public String importExcel(@RequestPart  MultipartFile file ,UserDTO userDTO) throws Exception {
//        log.info("文件名：[{}]",file.getOriginalFilename());
//        //excel导入
//        ImportParams params = new ImportParams();
//        params.setHeadRows(1);//header占几行
//        params.setTitleRows(1);//标题占几行
//        params.setImportFields(now String[]{"编号"，"人员类型id"...})
//        List<User> users = ExcelImportUtil.importExcel(file.getInputStream(), User.class, params);
//        userService.save(userDTO);
//        return "redirect:/user/findByPage";
//    }

    @ApiOperation(value = "人员信息增添接口")
    @PostMapping("/addUser")
    public Result<String> save(@RequestBody @Validated UserDTO dto) {
        userService.save(dto);
        return Result.ok("添加成功");
    }


    @ApiOperation(value = "人员信息修改接口")
    @PutMapping("/updateUser")
    public Result<?> updateById(@RequestBody @Validated UserDTO dto) {
        userService.updateById(dto);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "人员信息分页模糊查询")
    @GetMapping("/findByPage")
    public Result<Page<UserPageVO>> findAllByPage(@Validated UserPageDTO userPageDTO) {
        Page<UserPageVO> allByPage = userService.findAllByPage(userPageDTO);
        return Result.ok(allByPage);
    }

    @ApiOperation(value = "人员信息删除接口")
    @DeleteMapping("/deleteById")
    public Result<?> deleteById(@RequestParam("deleteById") @ApiParam(value = "要删除的人员信息id", required = true) Integer userId) {
        userService.deleteById(userId);
        return Result.ok("删除成功");
    }

    @ApiOperation(value = "人员信息批量删除接口")
    @DeleteMapping("/deleteByIds")
    public boolean removeUserBatch(@RequestParam("ids[]") @ApiParam(value = "要删除的人员信息id", required = true) Integer[] ids){
        return userService.removeBatch1(ids);
    }

    @ApiOperation(value = "访客信息24h删除")
    @DeleteMapping(value = "/delete")
    public void timerRun() {
        userService.timerRun();
    }

//    @ApiOperation(value = "获得openId")
//    @GetMapping(value = "/getOpenId")
//    public void getOpenId(String code){
//        userService.getOpenId(wx7fa962c9e357d54a,code,29b8712293dc85c4513a5df2271152ec);
//    }

}
