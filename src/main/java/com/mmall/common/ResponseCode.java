package com.mmall.common;


//相应的枚举类
//吧常量进行归类，是ServerResponce里的status的对应
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),//status=0 表示成功
    ERROR(1,"ERROR"),//status=1 表示失败
    NEED_LOGIN(10,"NEED_LOGIN"),//status=10 表示需要登陆
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");//status=2 表示非法的请求

    private final int code;//code对应0
    private final String desc;//desc对应success

    //构造器
    //这里使用default的修饰，只允许类内部及本包调用
    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    //暴漏获取属性的方法（开放出去）
    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
