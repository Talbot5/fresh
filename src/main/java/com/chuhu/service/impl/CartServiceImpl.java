package com.chuhu.service.impl;

import com.chuhu.mapper.CartMapper;
import com.chuhu.pojo.Cart;
import com.chuhu.pojo.CommoditCart;
import com.chuhu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CommoditCart>  getCartAll(int uid) {
        return cartMapper.selCartAll(uid);
    }

    @Override
    public int getAddCart(int coid, int num, int uid) {
        return cartMapper.addCart(coid,num,uid);
    }

    @Override
    public int getDelCart(int id) {
        return cartMapper.delCart(id);
    }

    @Override
    public int getUpdateCart(int cid,int uid,int num) {
        return cartMapper.updateCart(cid,uid,num);
    }

    @Override
    public Cart getselCart(int coid, int uid) {
        return cartMapper.selCart(coid,uid);
    }

    @Override
    public Cart getselOneCart(int coid, int uid) {
        return cartMapper.selCart(coid,uid);
    }

    @Override
    public void getUpselected(int uid, int coid, int selected) {
        cartMapper.Upselected(uid,coid,selected);
    }

    @Override
    public void getUpselectedALL(int uid, int selectedAll) {
        cartMapper.UpselectedALL(uid,selectedAll);
    }


}
