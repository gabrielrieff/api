package com.application.implementations.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.interfaces.services.IUploadSerivece;

@Service
public class UploadSerivece implements IUploadSerivece{
    public  String uploadImage(MultipartFile file){
        return "https://shre.ink/31c7";
    }
}
