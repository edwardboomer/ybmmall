package com.mmall.common;

import com.google.common.collect.Sets;

import java.util.Set;


//自我声明一个常量类
public class Const {
    public static final String CURRENT_USER = "currentUser"; //当前用户

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    //内部接口类，进行常量的分组
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }

    //动态排序，根据价格升序或降序
    public interface ProductListOrderBy{
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_desc","price_asc");
    }

    //产品状态，枚举类
    public enum ProductStatusEnum{
        ON_SALE(1,"在线");
        private String value;
        private int code;
        //构造器
        ProductStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
    //购物车枚举类
    public interface Cart{
        int CHECKED = 1;//即购物车选中状态
        int UN_CHECKED = 0;//购物车中未选中状态

        String LIMIT_NUM_FAIL = "LIMIT_NUM_FAIL"; //限制失败
        String LIMIT_NUM_SUCCESS = "LIMIT_NUM_SUCCESS";//限制成功
    }

    //订单确认信息枚举类
    public enum OrderStatusEnum{
        //订单状态常量
        CANCELED(0,"已取消"),
        NO_PAY(10,"未支付"),
        PAID(20,"已付款"),
        SHIPPED(40,"已发货"),
        ORDER_SUCCESS(50,"订单完成"),
        ORDER_CLOSE(60,"订单关闭");

        //构造器
        OrderStatusEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        //根据code返回详情描述
        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }
    }

    //支付宝回掉关键数据接口
    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";//等待买家付款
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";//交易成功

        String RESPONSE_SUCCESS = "success";//成功
        String RESPONSE_FAILED = "failed";//失败
    }

   //支付宝订单枚举
    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

       //构造器
        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    //付款方式，只有在线支付可选择
    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");
        //构造器
        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        //根据我们传的code（就是1），把详情的描述返回回去
        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }

    }
}
