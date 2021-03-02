package com.chuhu.mapper;

import com.chuhu.pojo.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AddrMapper {
    @Insert("insert into address(addrname,addrphone,address,detailedaddr,uid,adddefault,photo) value(#{uname},#{phone},#{addr},#{detailedaddr},#{uid},#{istrue}," +
            "#{uploaderurl})")
    void addAddress(int uid, String uname, String phone, String addr,String detailedaddr,int istrue,String uploaderurl);

    @Update("update address set addrname=#{uname},addrphone=#{phone},address=#{address},detailedaddr=#{addr},adddefault=#{istrue},photo=#{uploaderurl} where " +
            "uid=#{uid} and addrid=#{addrid}")
    void upAddress(int uid, String uname, String phone,String address, String addr,int addrid,int istrue,String uploaderurl);

    @Select("select * from address where uid=#{uid}")
    List<Address> selAddr(int uid);

    @Delete("delete from address where addrid=#{addrid}")
    void delAddr(int addrid);

    @Update("update address set adddefault=#{def} where addrid=#{addr}")
    void upAddrDef(int addr,int def);

    @Select("select addrid,address,detailedaddr,addrname,addrphone,shopname,uid,addefault,photo from address where uid=#{uid} and adddefault=#{istrue}")
    Address selAddrDef(int uid, int istrue);

    @Select("select addrid,address,detailedaddr,addrname,addrphone,shopname,uid,addefault,photo from address where addrid=#{addrid}")
    Address selOneAddr(int addrid);
}
