package com.chuhu.pojo;

import lombok.Data;
import lombok.Setter;

@Data
public class Activity {

    private Integer id;
    private String actname;
    private String starttime;
    private String stoptime;
    private String discount;
    private Integer coid;
    private String coname;
    private String photo;
    private String synopsis;
    private Integer tid;
    private String amount;
    private String unit;
    private Integer stock;
    private String status;
    private Integer salenum;
    private Address address;
}
