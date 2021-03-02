package com.chuhu.pojo;

import lombok.Data;

import java.util.List;

@Data
public class PlaceOrder {
    private List<Commodity> commodityList;
    private Address address;

}
