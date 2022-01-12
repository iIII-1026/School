package com.aizhixin.school.service.impl;

import cn.hutool.core.io.FileUtil;
import com.aizhixin.school.service.PhotoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Override
    public void uploadPhoto(MultipartFile file) {
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID() + "_" +oldName;
        String filePath = "C:\\Users\\Kiven\\Desktop\\test";
        boolean exists = FileUtil.exists(new File(filePath).toPath(),false);
        if (!exists){
            FileUtil.mkdir(filePath);
        }
        File file1 = FileUtil.file(filePath, newName);
        try{
            file.transferTo(file1);
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    @Override
    public void deletePhoto(MultipartFile file) {

    }
}
