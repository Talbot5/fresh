package com.chuhu.pojo;


import lombok.Data;

@Data

public class Profits {

  private Integer id;
  private Integer userid;
  private double money;
  private Integer orderid;
  private double beforemoney;
  private double aftermoney;
  private Integer isagent;
  private Integer ptime;

}
