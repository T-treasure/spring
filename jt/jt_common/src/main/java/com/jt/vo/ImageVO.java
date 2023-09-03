package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ImageVO {

    private String virtualPath; //图片的存储路径  不包含磁盘信息
    private String urlPath;     //图片访问的网络路径
    private String fileName;    //图片名称

}
