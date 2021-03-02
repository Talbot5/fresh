package com.chuhu.mapper;

import com.chuhu.pojo.Activity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ActivityMapper {
    @Select("select * from commodity c inner join activity a on c.actid=a.id where actname=#{name}")
    List<Activity> selactivity(String name);

    @Select("select * from commodity c inner join activity a on c.actid=a.id where c.coid=#{coid}")
    Activity selactivityDetails(int coid);
}
