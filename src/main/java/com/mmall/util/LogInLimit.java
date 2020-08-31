package com.mmall.util;

import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据总部平台安全漏洞需求：i按需输入错误密码5次之后，账号进入锁定状态，不可登陆。锁定状态将于1小时后，或者下一个自然日（0点）解除。
 *  1.建一张表来记录用户id，错误登录次数，上次登录时间。（也可以在user表上添加这三个字段，这样会省去一个初始化的麻烦）
 *  2.登录失败后，进行校验：若已经错误登录5次，返回特定编码以告知前台显示错误信息。若次数不足5次，错误登录次数+1，并返回正常错误信息。若距离上一次错误登录时间已经过了1小时或者到了第二天，错误次数置为1
 *  3.若密码正确，并可以正常登录，将错误次数置为0。若处于锁定中，返回指定错误信息。
 *  建表代码：
 *  create table log_pwd_err(
 *      login_id number primary key,
 *      err_num number default 0,
 *      last_date date default sysdate);
 */
//public class LogInLimit {
//    //设置参数
//    private static int maxTime = 5;//连续输入五次
//    private static double hour = 1;//一小时内不可登陆
//    /**
//     * 登录错误5次，一小时后不可登录
//     */
//    public Map<String,Object> validateByLoginIdLoginErr(String loginId,String password,
//            boolean isJiami) throws DataAccessException{
//      Map<String,Object> resultMap = new HashMap(2);
//
//
//
//    }
//}
