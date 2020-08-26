package com.bjsxt.backend.item.service;

import com.bjsxt.utils.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.backend.item.service
 * @version: 1.0
 */
public interface FileUploadService {

    Result fileUpload(MultipartFile file);
}
