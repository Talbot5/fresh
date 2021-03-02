package com.chuhu.service;

import com.chuhu.pojo.Advertisement;
import com.chuhu.pojo.Commodity;
import com.chuhu.pojo.Comtype;

import java.util.List;

public interface CommodityService {
    List<Commodity> getCommodityAll();

    List<Commodity> getSearchCommodity(String name);

    List<Comtype> getCommodityTypeAll(String typename);

    Commodity getcommoditydEtails(int coid);

    List<Comtype> getCommodityType();

    List<Comtype> getCommodityTypeAllName();

    List<Advertisement> getadvertPhoto();
}
