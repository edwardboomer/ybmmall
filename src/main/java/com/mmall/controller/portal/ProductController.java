package com.mmall.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServerResponse;
import com.mmall.service.IProductService;
import com.mmall.vo.ProductDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//前台用户的商品管理
@Controller
@RequestMapping("/product/")
public class ProductController {

    @Autowired
    private IProductService iProductService;


    @RequestMapping("detail.do")
    @ResponseBody
    //前台用户查看商品详情
    public ServerResponse<ProductDetailVo> detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }


    @RequestMapping("list.do")
    @ResponseBody
    //前端用户搜索时候的请求
    //需要用到分页，多个参数
    public ServerResponse<PageInfo> list(
             @RequestParam(value = "keyword",required = false)String keyword, //keyword 不是必须传的
             @RequestParam(value = "categoryId",required = false)Integer categoryId,//categoryId 不是必须传的
             @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
             @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
             @RequestParam(value = "orderBy",defaultValue = "") String orderBy){ //orderby，标志动态排序处理
        return iProductService.getProductByKeywordCategory(keyword,categoryId,pageNum,pageSize,orderBy);
    }
}