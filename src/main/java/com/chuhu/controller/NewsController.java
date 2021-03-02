package com.chuhu.controller;

import com.chuhu.pojo.News;
import com.chuhu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class NewsController {
    @Autowired
    private NewsService newsService;

    @RequestMapping("selNews")
    @ResponseBody
    public List<News> selNews(int uid){
        List<News> news=newsService.getselNews(uid);
        return news;
    }

    @RequestMapping("upNews")
    @ResponseBody
    public void upNews(int newsid){
        newsService.getupNews(newsid);
    }
}
