package com.chuhu.controller;

import com.chuhu.pojo.Cart;
import com.chuhu.pojo.CommoditCart;
import com.chuhu.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 购物车
 */
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    private static List<CommoditCart> cartlist;

    /**
     *查询购物车所有数据
     */
    @RequestMapping("cartAll")
    @ResponseBody
    public List<CommoditCart> cartAll(HttpSession session,HttpServletResponse response,int uid,int type){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        if (type==0) {
            this.cartlist = cartService.getCartAll(uid);
        }
        return this.cartlist;
    }


    /**
     *修改选中状态
     */
    @RequestMapping("selectedCart")
    @ResponseBody
    public void selectedCart(HttpSession session,HttpServletResponse response, int uid, int selected, int coid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        for (int i=0;i<cartlist.size();i++){
            if (cartlist.get(i).getCoid()==coid){
                cartService.getUpselected(uid,coid,selected);
            }
        }
    }


    /**
     *选中所有
     */
    @RequestMapping("selectedAllCart")
    @ResponseBody
    public void selectedAllCart(HttpSession session,HttpServletResponse response, int uid, int selectedAll){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        cartService.getUpselectedALL(uid,selectedAll);
    }

    /**
     * 添加购物车
     */
    @RequestMapping("addCart")
    @ResponseBody
    public String addCart(int coid,int uid,int num){
        Cart selcount=cartService.getselCart(coid,uid);
        if (selcount!=null){
            int cid=selcount.getCid();
            cartService.getUpdateCart(cid,uid,num);
            return "修改成功";
        }else {
            int count = cartService.getAddCart(coid, num, uid);
            if (count > 0) {
                return "添加成功！";

            } else {
                return "添加失败！";
            }
        }

    }

    /**
     * 删除购物车和批量删除购物车
     */
    @RequestMapping("delCart")
    @ResponseBody
    public void delCart(String[] idlist){
        for (int i = idlist.length - 1; i >= 0; i--) {
            int count = cartService.getDelCart(Integer.parseInt(idlist[i].replaceAll("\\[", "").replaceAll("]", "")));
        }
    }

    /**
     * 修改购物车
     */
    @RequestMapping("updateCart")
    @ResponseBody
    public void updateCart(HttpServletResponse response,int cid, int num,int uid){
        response.setCharacterEncoding("utf8");
        response.setContentType("html/text;charset=utf-8");
        int count=cartService.getUpdateCart(cid,uid,num);
    }

    /**
     * 查询一个购物车
     */
    @RequestMapping("selOneCart")
    @ResponseBody
    public Cart selOneCart(int coid,int uid){
       Cart cart= cartService.getselOneCart(coid,uid);
       return cart;
    }
}
