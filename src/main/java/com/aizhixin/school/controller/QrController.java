package com.aizhixin.school.controller;

import com.aizhixin.school.domain.Qr;
import com.aizhixin.school.dto.QrDTO;
import com.aizhixin.school.dto.QrPageDTO;
import com.aizhixin.school.dto.TypeDTO;
import com.aizhixin.school.service.PhotoService;
import com.aizhixin.school.service.QrService;
import com.aizhixin.school.utils.Result;
import com.aizhixin.school.vo.QrVO;
import com.aizhixin.school.vo.TypeVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Api(tags = "出入管理-出入配置")
@RestController
@RequestMapping("/qr")
public class QrController {

    @Autowired
    private QrService qrService;

    @Autowired
    private PhotoService photoService;

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    public void uploadPhoto(@RequestBody MultipartFile file){
        photoService.uploadPhoto(file);
    }

//    @ApiOperation("二维码生成")
//    @PostMapping("/qrCreate")
//    public void qrCreate() {
//        qrService.qrCreate();
//    }

    @ApiOperation(value = "出入配置添加接口")
    @PostMapping("/addQr")
    public Result<String> save(@RequestBody @Validated QrDTO dto) {
        qrService.save(dto);
        return Result.ok("添加成功");
    }


    @ApiOperation(value = "出入配置修改接口")
    @PutMapping("/updateQr")
    public Result<?> updateById(@RequestBody @Validated QrDTO dto) {
        qrService.updateById(dto);
        return Result.ok("修改成功");
    }

    @ApiOperation(value = "出入配置分页查询")
    @GetMapping("/findByPage")
    public Result<Page<QrVO>> findAllByPage(@Validated QrPageDTO qrPageDTO) {
        Page<QrVO> allByPage = qrService.findAllByPage(qrPageDTO);
        return Result.ok(allByPage);
    }

    @ApiOperation(value = "出入配置删除接口")
    @DeleteMapping("/deleteById")
    public Result<?> deleteById(@RequestParam("deleteById") @ApiParam(value = "要删除的出入配置id", required = true) Integer QrId) {
        qrService.deleteById(QrId);
        return Result.ok("删除成功");
    }

    /**
     * @param path     指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @ApiOperation(value = "图片下载")
    @GetMapping("/downloadLocal")
    public void downloadLocal(HttpServletResponse response) throws IOException {
        // 读到流中
        InputStream inputStream = new FileInputStream("G:/java/Qr/qrcode1.jpg");// 文件的存放路径
        response.reset();
        response.setContentType("application/octet-stream");
        String filename = new File("G:/java/Qr/qrcode1.jpg").getName();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
        while ((len = inputStream.read(b)) > 0) {
            outputStream.write(b, 0, len);
        }
        inputStream.close();
    }


}
