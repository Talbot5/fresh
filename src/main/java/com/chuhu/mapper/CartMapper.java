package com.chuhu.mapper;

import com.chuhu.pojo.Cart;
import com.chuhu.pojo.CommoditCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartMapper{
    @Select("select * from cart c inner join commodity co on c.coid=co.coid where uid=#{uid}")
    List<CommoditCart> selCartAll(int uid);

    @Insert("insert into cart(coid,num,uid) value(#{coid},#{num},#{uid})")
    int addCart(int coid, int num, int uid);

    @Delete("delete from cart where cid=#{id}")
    int delCart(int id);

    @Update("update cart set num=#{num} where uid=#{uid} and cid=#{cid}")
    int updateCart(int cid,int uid,int num);

    @Select("select * from cart where coid=#{coid} and uid=#{uid}")
    Cart selCart(int coid, int uid);

    @Update("update cart set selected=#{selected} where uid=#{uid} and coid=#{coid}")
    void Upselected(int uid, int coid, int selected);

    @Update("update cart set selected=#{selectedAll} where uid=#{uid}")
    void UpselectedALL(int uid, int selectedAll);
}
