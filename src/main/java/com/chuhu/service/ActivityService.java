package com.chuhu.service;

import com.chuhu.pojo.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getselactivity(String name);

    Activity getselactivityDetails(int coid);
}
