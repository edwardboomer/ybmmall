package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * Created by hasee on 2018/6/13.
 */
public interface ICartService {

    ServerResponse<CartVo> list (Integer userId);

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);

    ServerResponse<CartVo> update(Integer userId,Integer productId,Integer count);

    ServerResponse<CartVo> deleteProduct(Integer userId,String productIds);

    //封装成一个公用的方法，选或不选
    ServerResponse<CartVo> selectOrUnSelect (Integer userId,Integer productId,Integer checked);

    //获取车中产品数量
    ServerResponse<Integer> getCartProductCount(Integer userId);


}
