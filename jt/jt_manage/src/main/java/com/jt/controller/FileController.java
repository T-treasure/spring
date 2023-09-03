package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    //实现文件删除
    @DeleteMapping("deleteFile")
    public SysResult fileRemove(String virtualPath) {
        fileService.fileRemove(virtualPath);
        return SysResult.success();
    }


    /*
    * URL:file/upload
    * 参数: file:二进制信息
    * 返回值: SysResult
    * MultipartFile: SpringMVC对外提供的接口 专门实现文件上传操作
    * 高级API 默认文件大小  最大  1M
    * 如果需要优化 我们需要编辑配置类 查询定义大小(一般不会这么做)
    * 优化: 1.防止文件重名
    *      2.防止恶意程序  jpg|png|gif
    * */
    @PostMapping("/upload")
    public SysResult upload(MultipartFile file) throws IOException {
        ImageVO imageVO = fileService.upload(file);
        if(imageVO == null) {
            return SysResult.fail();
        }
        return SysResult.success(imageVO);
//        //1.获取文件名称
////        String fileName = file.getName();
////        //2.准备文件上传的本地目录
////        String fileDir = "D:/IDEA-projects/hkjWang/JT_IMAGE/";
////        //3.是否需要判断目录是否存在
////        File filePath = new File(fileDir);
////        if(!filePath.exists()) {
////            //创建一个文件夹
////            filePath.mkdirs();
////            //创建一级目录
////            //filePath.mkdir();
////        }
////        //4.准备输出对象  文件的全路径="文件的目录" + / + "文件的名称"
////        String readFilePath = fileDir + fileName;
////        File readFile = new File(readFilePath);
////        //实现文件的上传
////        file.transferTo(readFile);
////        //1.获取其中的字节信息
////        //2.定义文件上传位置
////        //3.循环遍历将字节信息依次输出
////        //4.关流
////        return SysResult.success();
    }


}
