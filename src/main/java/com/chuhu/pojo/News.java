package com.chuhu.pojo;

import lombok.Data;



@Data

public class News {

  private Integer id;
  private String title;
  private String content;
  private String time;
  private Integer status;
  private Integer uid;

}
