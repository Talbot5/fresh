package com.chuhu.service;

import com.chuhu.pojo.Address;

import java.util.List;

public interface AddrService {
    void getaddAddress(int uid, String uname, String phone, String addr,String detailedaddr,int istrue,String uploaderurl);

    void getupAddress(int uid, String uname, String phone,String address ,String addr,int addrid,int istrue,String uploaderurl);

    List<Address> getselAddr(int uid);

    void getdelAddr(int addrid);

    void getupAddrDef(int addr,int def);

    Address getselAddrDef(int uid, int istrue);

    Address getselOneAddr(int addrid);
}
