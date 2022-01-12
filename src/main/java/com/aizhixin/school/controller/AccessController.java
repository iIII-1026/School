package com.aizhixin.school.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.aizhixin.school.dto.AccessDTO;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.mapper.AccessMapper;
import com.aizhixin.school.service.AccessService;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.vo.AccessVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Api(tags = "出入管理-出入记录")
@RestController
@RequestMapping("/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @Autowired
    private AccessMapper accessMapper;

    @ApiOperation(value = "出入记录导出")
    @PostMapping("exportExcel")
    public void exportExcel(AccessDTO accessDTO) {
        Page<AccessDTO> page = new Page<>(accessDTO.getPageNumber(), accessDTO.getPageSize());
        Page<AccessVO> accessPage = accessMapper.findAccessPage(page, accessDTO);
        List<AccessVO> rows = accessPage.getRecords();
        //通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("G:/java/Excel/出入信息表.xlsx");
        writer.addHeaderAlias("id","编号");
        writer.addHeaderAlias("pip","打卡地点");
        writer.addHeaderAlias("cType","打卡类型");
        writer.addHeaderAlias("name","姓名");
        writer.addHeaderAlias("card","学号/工号/身份证号");
        writer.addHeaderAlias("remark","备注");
        writer.addHeaderAlias("phone","联系电话");
        writer.addHeaderAlias("scanCodeTime","扫码时间");
        writer.addHeaderAlias("typeName","类型名称");
        //只输入指定字段
        writer.setOnlyAlias(true);

        writer.setSheet("出入记录1");
        //合并单元格后的标题行，使用默认标题样式
        writer.merge(8, "出入记录");
        //一次性写出内容，强制输出标题
        writer.write(rows, true);
        //关闭writer，释放内存
        writer.close();
    }

    @ApiOperation(value = "出入记录分页查询")
    @GetMapping("findByPage")
    public Result<Page<AccessVO>> findByPage(@Validated AccessDTO accessDTO) {
//        AccessDTO accessDTO = new AccessDTO();
//        accessDTO.setPageNumber(accessDTO.getPageNumber());
//        accessDTO.setPageSize(accessDTO.getPageSize());
//        accessDTO.setName(accessDTO.getName());
        Page<AccessVO> allByPage = accessService.findByPage(accessDTO);
        return Result.ok(allByPage);
    }
}
