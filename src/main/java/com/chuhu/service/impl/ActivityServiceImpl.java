package com.chuhu.service.impl;

import com.chuhu.mapper.ActivityMapper;
import com.chuhu.pojo.Activity;
import com.chuhu.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public List<Activity> getselactivity(String name) {
        return activityMapper.selactivity(name);
    }

    @Override
    public Activity getselactivityDetails(int coid) {
        return activityMapper.selactivityDetails(coid);
    }
}
