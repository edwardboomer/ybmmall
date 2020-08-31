package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService{

    //控制台日志文件
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    //得到一个文件名
    public String upload(MultipartFile file, String path){
        //原始文件名
        String fileName = file.getOriginalFilename();
        //扩展名   如：abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);//从后到前获取
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;//上传文件的名字，UUID防止文件重名
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
         //新的文件夹
        File fileDir = new File(path);
        if(!fileDir.exists()){ //这个文件夹不存在
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        //目录文件的创建
        File targetFile = new File(path,uploadFileName);

        try {
            //文件已经上传成功了
            file.transferTo(targetFile);

          //已经上传到ftp服务器上
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

          //上传成功后删除upload下面的文件夹
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        //目标文件的文件名
        return targetFile.getName();
    }
}
