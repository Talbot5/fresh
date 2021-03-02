package com.chuhu.service;

import com.chuhu.pojo.News;

import java.util.List;

public interface NewsService {

    List<News> getselNews(int uid);

    void getupNews(int newsid);
}
