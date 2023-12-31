package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    ImageVO upload(MultipartFile file) throws IOException;
    void fileRemove(String virtualPath);
}
