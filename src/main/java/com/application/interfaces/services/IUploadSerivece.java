package com.application.interfaces.services;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadSerivece {
    String uploadImage(MultipartFile file); 
}
