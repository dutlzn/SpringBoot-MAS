package com.project.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {
    // 绑定数据源配置
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }
//
//    /**
//     * 配置Druid监控
//     * 配置一个管理后台的Servlet
//     * 配置一个监控的filer
//     */
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        // statViewServlet是配置管理后台的servlet
//        ServletRegistrationBean<StatViewServlet> bean =
//                new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        Map<String, String> param = new HashMap<>();
//        param.put(StatViewServlet.PARAM_NAME_USERNAME, "admin");
//        param.put(StatViewServlet.PARAM_NAME_PASSWORD, "admin");
//        // 允许所有ip可以访问
//        param.put(StatViewServlet.PARAM_NAME_ALLOW, "");
//        bean.setInitParameters(param);
//        return bean;
//    }
//
//    // 配置一个监控的Filter
//    @Bean
//    public FilterRegistrationBean filter() {
//        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
//        bean.setFilter(new WebStatFilter());
//        // 配置初始化参数
//        Map<String, String> param = new HashMap<>();
//        param.put(WebStatFilter.PARAM_NAME_EXCLUSIONS, "*.js,*.css,/druid/*");
//        // 拦截所有请求
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        return bean;
//    }
}
