package com.shijie.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PageHelperConfig {

    public PageHelper createPageHelper(){
        return new PageHelper();
    }
}
