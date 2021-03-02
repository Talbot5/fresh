package com.chuhu.pojo;



import lombok.Data;

import java.util.List;

@Data

public class Order {

  private Integer orid;
  private Integer comnum;
  private String ordertime;
  private String status;
  private Integer addrid;
  private String payamount;
  private Integer payid;
  private String businphone;
  private Integer uid;
  private String nickname;
  private String headportrait;
  private String openid;
  private List<Commodity> commodityList;
  private String address;
  private String username;
  private String phone;
  private String shopname;
  private String paytype;

}
