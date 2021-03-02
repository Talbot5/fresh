package com.chuhu.pojo;


import lombok.Data;

@Data
public class Coupon {

  private Integer id;
  private String title;
  private Integer coupon;
  private Integer num;
  private String starttime;
  private String stoptime;
  private Integer uid;

}
