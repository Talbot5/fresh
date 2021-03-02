package com.chuhu.service.impl;

import com.chuhu.mapper.CommodityMapper;
import com.chuhu.pojo.Advertisement;
import com.chuhu.pojo.Commodity;
import com.chuhu.pojo.Comtype;
import com.chuhu.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public List<Commodity> getCommodityAll() {
        return commodityMapper.selCommodityAll();
    }

    @Override
    public List<Commodity> getSearchCommodity(String name) {
        return commodityMapper.selSearchCommodity(name);
    }

    @Override
    public List<Comtype> getCommodityTypeAll(String typename) {
        return commodityMapper.selCommodityTypeAll(typename);
    }

    @Override
    public Commodity getcommoditydEtails(int coid) {
        return commodityMapper.selCommoditydEtails(coid) ;
    }

    @Override
    public List<Comtype> getCommodityType() {
        return commodityMapper.CommodityType();
    }

    @Override
    public List<Comtype> getCommodityTypeAllName() {
        return commodityMapper.commodityTypeAllName();
    }

    @Override
    public List<Advertisement> getadvertPhoto() {
        return commodityMapper.advertPhoto();
    }

}
