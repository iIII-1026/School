package com.aizhixin.school.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    /**
     * 照片上传
     * @param file
     */
    void uploadPhoto(MultipartFile file);

    void deletePhoto(MultipartFile file);

}
