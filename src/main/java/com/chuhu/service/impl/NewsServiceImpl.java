package com.chuhu.service.impl;

import com.chuhu.mapper.NewsMapper;
import com.chuhu.pojo.News;
import com.chuhu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getselNews(int uid) {
        return newsMapper.selNews(uid);
    }

    @Override
    public void getupNews(int newsid) {
        newsMapper.upNews(newsid);
    }
}
