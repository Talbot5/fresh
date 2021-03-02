package com.chuhu;

import com.chuhu.pojo.Commodity;
import com.chuhu.service.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@MapperScan("com.chuhu.mapper")
public class FreshApplication{



    public static void main(String[] args) {
        SpringApplication.run(FreshApplication.class, args);


    }




}

