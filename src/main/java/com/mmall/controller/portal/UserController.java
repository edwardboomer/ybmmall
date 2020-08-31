package com.mmall.controller.portal;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

        @Autowired
        private IUserService iUserService;

        //登录接口
        //高复用服务响应对象ServerResponse
        @RequestMapping(value = "login.do",method = RequestMethod.POST)//请求（value表示请求的url，method表示请求的method类型）
        @ResponseBody//表示该方法的返回值直接写入HTTP responsebody中
        // （和RequestMapping）联合使用，返回值不会解析为调转路径，直接反回json数据
        public ServerResponse<User> login(String username, String password, HttpSession session){
            ServerResponse<User> response = iUserService.login(username,password);
            if(response.isSuccess()){
                //把这个用户属性放进来，需要用到常量类
                session.setAttribute(com.mmall.common.Const.CURRENT_USER,response.getData());
            }
            return response;
        }

        //登出接口
        @RequestMapping(value = "logout.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> logout(HttpSession session){
            //把用户session信息remove掉
            session.removeAttribute(com.mmall.common.Const.CURRENT_USER);
            return ServerResponse.createBySuccess();
        }

        //注册接口
        @RequestMapping(value = "register.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> register(User user){
            return iUserService.register(user);
        }

        //校验用户名，邮箱是否可以使用
        @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> checkValid(String str,String type){
            return iUserService.checkValid(str,type);
        }

        //获取用户信息
        @RequestMapping(value = "get_user_info.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<User> getUserInfo(HttpSession session){
            User user = (User) session.getAttribute(com.mmall.common.Const.CURRENT_USER);
            if(user != null){
                return ServerResponse.createBySuccess(user);
            }
            return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
        }

        //忘记密码，获取密码问题
        @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> forgetGetQuestion(String username){
            return iUserService.selectQuestion(username);
        }

        //提交密码提示问题的答案
        @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> forgetCheckAnswer(String username,String question,String answer){ //参数部分就是request
            return iUserService.checkAnswer(username,question,answer);
        }
        //忘记密码的重置密码功能
        @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
            //forgettoken如果不用的话，就会越权，也就是会重置任意一个密码，任意一个用户名。
            //使用token之后，只有正确的密码和提示问题才可以使用。
            return iUserService.forgetResetPassword(username,passwordNew,forgetToken);
        }

        //登陆成功下重置密码
        @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<String> resetPassword(HttpSession session,String passwordOld,String passwordNew){
            //用户可以从session获取
            User user = (User)session.getAttribute(com.mmall.common.Const.CURRENT_USER);
            if(user == null){
                return ServerResponse.createByErrorMessage("用户未登录");
            }
            return iUserService.resetPassword(passwordOld,passwordNew,user);
        }
        //更新用户详细信息
        @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<User> update_information(HttpSession session,User user){
            User currentUser = (User)session.getAttribute(com.mmall.common.Const.CURRENT_USER);
            if(currentUser == null){
                return ServerResponse.createByErrorMessage("用户未登录");
            }
            //username和userId是唯一不被更新的
            user.setId(currentUser.getId());
            user.setUsername(currentUser.getUsername());
            ServerResponse<User> response = iUserService.updateInformation(user);
            if(response.isSuccess()){
                response.getData().setUsername(currentUser.getUsername());
                session.setAttribute(com.mmall.common.Const.CURRENT_USER,response.getData());
            }
            return response;
        }

        //获取用户的详细信息并强制登陆
        @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
        @ResponseBody
        public ServerResponse<User> get_information(HttpSession session){
            User currentUser = (User)session.getAttribute(com.mmall.common.Const.CURRENT_USER);
            if(currentUser == null){
                //status=10 对应NEED_LOGIN(10,"NEED_LOGIN"),
                return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
            }
            return iUserService.getInformation(currentUser.getId());
        }


    }


