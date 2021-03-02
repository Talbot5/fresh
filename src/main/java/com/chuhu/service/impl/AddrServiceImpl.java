package com.chuhu.service.impl;

import com.chuhu.mapper.AddrMapper;
import com.chuhu.pojo.Address;
import com.chuhu.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {
    @Autowired
    private AddrMapper addrMapper;
    @Override
    public void getaddAddress(int uid, String uname,String phone, String addr,String detailedaddr,int istrue,String uploaderurl) {
        addrMapper.addAddress(uid,uname,phone,addr,detailedaddr,istrue,uploaderurl);
    }

    @Override
    public void getupAddress(int uid, String uname, String phone,String address, String addr,int addrid,int istrue,String uploaderurl) {
        addrMapper.upAddress(uid,uname,phone,address,addr,addrid,istrue,uploaderurl);
    }

    @Override
    public List<Address> getselAddr(int uid) {
        return addrMapper.selAddr(uid);
    }

    @Override
    public void getdelAddr(int addrid) {
        addrMapper.delAddr(addrid);
    }

    @Override
    public void getupAddrDef(int addr,int def) {
        addrMapper.upAddrDef(addr,def);
    }

    @Override
    public Address getselAddrDef(int uid, int istrue) {
        return addrMapper.selAddrDef(uid,istrue);
    }

    @Override
    public Address getselOneAddr(int addrid) {
        return addrMapper.selOneAddr(addrid);
    }

}
