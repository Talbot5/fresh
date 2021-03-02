package com.chuhu.mapper;

import com.chuhu.pojo.Advertisement;
import com.chuhu.pojo.Commodity;
import com.chuhu.pojo.Comtype;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommodityMapper {
    @Select("select * from commodity where status='2' and stock>0 order by salenum desc")
    List<Commodity> selCommodityAll();

    @Select("select * from commodity where coname  like concat('%', #{name},'%')  and status='2' and  stock>0 order by salenum desc")
    List<Commodity> selSearchCommodity(String name);

    @Select("select * from commodity c left join comtype t on c.tid=t.typeid where typename=#{typename} and c.status='2' and  stock>0")
    List<Comtype> selCommodityTypeAll(String typename);

    @Select("select * from commodity where coid=#{coid} and  stock>0")
    Commodity selCommoditydEtails(int coid);

    @Select("select * from comtype order by sort desc limit 0,8")
    List<Comtype> CommodityType();

    @Select("select * from comtype order by sort desc")
    List<Comtype> commodityTypeAllName();

    @Select("select * from advertisement")
    List<Advertisement> advertPhoto();
}
