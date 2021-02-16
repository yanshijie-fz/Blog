package com.shijie.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix="spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    //后台监控功能
    @Bean
    public ServletRegistrationBean StatViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean=new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        //后台需要有人登录
        HashMap<String , String> initParameters=new HashMap<>();
        //增加配置
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","aaaaaa");

        //允许谁能访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);//设置初始化参数
        return bean;
    }


    @Bean
    public FilterRegistrationBean webStartFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();

        bean.setFilter(new WebStatFilter());

        //可以过滤哪些请求
        Map<String, String> initParameters=new HashMap<>();
        initParameters.put("exclusions","*.js,*.css,/druid/**");//这些东西不进行统计

        bean.setInitParameters(initParameters);

        return bean;
    }
}