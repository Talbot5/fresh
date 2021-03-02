package com.chuhu.pojo;


import lombok.Data;

@Data

public class Withdrawal {

  private Integer id;
  private Integer userid;
  private double withdmoney;
  private double beforewmoney;
  private double afterwmoney;
  private Integer wtime;

}
