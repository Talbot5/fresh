package com.chuhu.service;

import com.chuhu.pojo.Advertisement;
import com.chuhu.pojo.Cart;
import com.chuhu.pojo.CommoditCart;

import java.util.List;

public interface CartService {
    List<CommoditCart> getCartAll(int uid);
    int getAddCart(int coid, int num, int uid);
    int getDelCart(int id);

    int getUpdateCart(int cid,int uid,int num);

    Cart getselCart(int coid, int uid);

    Cart getselOneCart(int coid, int uid);

    void getUpselected(int uid, int coid, int selected);

    void getUpselectedALL(int uid, int selectedAll);
}
