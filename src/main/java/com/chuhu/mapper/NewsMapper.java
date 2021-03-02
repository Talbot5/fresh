package com.chuhu.mapper;

import com.chuhu.pojo.News;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface NewsMapper {
    @Select("select * from news where uid=#{uid} order by time desc")
    List<News> selNews(int uid);

    @Update("update news set status=1 where id=#{newsid}")
    void upNews(int newsid);
}
