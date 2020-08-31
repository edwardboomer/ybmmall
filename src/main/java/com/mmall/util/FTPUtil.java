package com.mmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

//文件上传工具类
public class FTPUtil {

    private static  final Logger logger = LoggerFactory.getLogger(FTPUtil.class);
    //和配置文件mmall.properties相关
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd){//构造器
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    //批量的参数，判断是否有文件
    public static boolean uploadFile(List<File> fileList) throws IOException {
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("img",fileList);
        logger.info("开始连接ftp服务器,结束上传,上传结果:{}");
        return result;
    }

   //私有方法，remotePath 远程路径
    private boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if(connectServer(this.ip,this.port,this.user,this.pwd)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);//工作目录
                ftpClient.setBufferSize(1024);//设置缓冲区
                ftpClient.setControlEncoding("UTF-8");//设置encoding
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File fileItem : fileList){
                    //用for循环上传
                    fis = new FileInputStream(fileItem);
                    //存储文件，利用input流
                    ftpClient.storeFile(fileItem.getName(),fis);
                }

            } catch (IOException e) {
                logger.error("上传文件异常",e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                //释放连接
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }

    //是否连接到服务器
    private boolean connectServer(String ip,int port,String user,String pwd){

        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            //连接这个ip
            ftpClient.connect(ip);
            //登陆
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }

    //需要的参数
    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
