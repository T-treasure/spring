package com.jt.serviceimpl;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    //为属性动态赋值 注解@Value
    @Value("${image.localPathDir}")
    private String localPathDir;
    @Value("${image.localUrlPath}")
    private String localUrlPath;

    /*
    * 1.参数  2.干什么 实现文件上传  3.返回值: ImageVO  有效返回
    *
    * 文件上传案例实现
    * 1.保证前端上传的数据是有效的
    * 1.1校验文件的名称 是否为图片
    * 1.2校验是否为恶意程序
    *
    * 2.创建一个本地文件夹(本地 + 时间)将图片存入
    * 2.1存入时需要更改名称uuid
    *
    * 3.创建imageVO对象返回
    * 3.1注意每一个参数的赋值
    * */

    @Override
    public ImageVO upload(MultipartFile file) throws IOException {
        //1.1校验是否为图片类型 abc.jpg ABC.APG  文件大小写
        String fileName = file.getOriginalFilename();
        //将所有的文件名称转化为小写
        fileName = fileName.toLowerCase();
        if (!fileName.matches("^.+\\.(jpg|png|gif)$")){
            return null;
        }
        //1.2校验图片类型是否为木马
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width==0 || height==0){
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        //2.目录结构
        //2.1实现分目录进行存储  可以以时间的维度进行分割  /yyyy/MM/dd
        String dataPath = new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        //2.2进行目录的拼接 "D:/JT_IMAGE/2023/8/12";
        String localDir = localPathDir + dataPath;
        //2.3需要创建目录
        File dirFile = new File(localDir);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        //3.文件名称重复  采用UUID防止文件重名        UUID.jpg
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String realFileName = uuid + fileType;
        String filePathAll = localDir +realFileName;
        //4.实现文件上传
        File realFile = new File(filePathAll);
        file.transferTo(realFile);
        //2023/8/12/uuid.jpg
        String virtualPath = dataPath + realFileName;
        //http://image.jt.com/2023/8/12/uuid.jpg
        String urlPath = localUrlPath +virtualPath;
        return new ImageVO(virtualPath,urlPath,realFileName);
    }
    //如果实现文件删除  virtualPath: 2023/08/12/uuid.jpg
    @Override
    public void fileRemove(String virtualPath) {
        String filePath = localPathDir + virtualPath;
        File file = new File(filePath);
        //删除文件
        file.delete();
    }

//    @Override
//    public ImageVO upload(MultipartFile file) throws IOException {
//        //1.1校验是否图片类型  abc.jpg
//        String fileName = file.getOriginalFilename();
//        fileName = fileName.toLowerCase();
//        if(!fileName.matches("^.+\\.(jpg|png|gif)$")) {
//            return null;
//        }
//        //2.校验图片类型是否为木马
//        BufferedImage bufferedImage = null;
//        try {
//             bufferedImage = ImageIO.read(file.getInputStream());
//             int width = bufferedImage.getWidth();
//             int height = bufferedImage.getHeight();
//             if(width == 0 || height == 0) {
//                 return null;
//             }
//        } catch (IOException e) {
//            return null;
//        }
//
//
//        //2.目录结构
//        //2.1实现目录进行存储  可以以时间的维度进行分割   /yyyy/MM/dd
//        String dataPath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
//        //2.2进行目录的拼接 "D:/IDEA-projects/hkjWang/JT_IMAGE/2023/8/12"
//        String localDir = localPathDir + dataPath;
//        //2.3需要创建目录
//        File fileDir = new File(localDir);
//        if(!fileDir.exists()) {
//            fileDir.mkdirs();
//        }
//
//        //3.文件名称重复  采用UUID防止文件重名  UUID.jpg
//        //重新拼接一个文件名
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        //获取文件类型
//        String fileType = fileName.substring(fileName.lastIndexOf("."));
//        //拼接
//        String readFileName = uuid + fileType;
//        //D:/IDEA-projects/hkjWang/JT_IMAGE/2023/8/12 + uuid + 文件类型
//        String filePathAll = localDir + "/" + readFileName;
//
//        //4.实现文件上传
//        //传入本地磁盘
//        File readFile = new File(filePathAll);
//        file.transferTo(readFile);
//
//        //2023/8/12/uuid.jpg
//        String virtualPath = dataPath + "/" + readFileName;
//        //http://image.jt.com/2023/8/12/uuid.jpg
//        String urlPath = localUrlPath + virtualPath;
//        //封装ImageVO对象返回
//        return new ImageVO(virtualPath, urlPath, fileName);
//    }
//
//    //实现文件删除    virtualPath : 2023/8/12/uuid.jpg
//    @Override
//    public void fileRemove(String virtualPath) {
//        String filePath = localPathDir + virtualPath;
//        System.out.println("filePath = " + filePath);
//        File file = new File(virtualPath);
//        file.delete();
//    }
}
