package com.sixi.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.sixi.interceptor.AuthInterceptor;
import com.sixi.interceptor.DebugInterceptor;
import com.sixi.interceptor.PermissionInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.Charset;
import java.util.List;

@Slf4j
@SpringBootApplication
public class MvcConfig extends WebMvcConfigurerAdapter {

    public MvcConfig() {
        System.out.println("new：MvcConfig");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("MvcConfig.configureMessageConverters");
        //定义一个转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setCharset(Charset.forName("utf-8"));
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteNullListAsEmpty);
        //在转换器中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //将转换器添加到converters中
        converters.add(fastConverter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).excludePathPatterns("/sys/**");
        registry.addInterceptor(new DebugInterceptor()).excludePathPatterns("/sys/**");
        registry.addInterceptor(permissionInterceptor());
    }

    @Bean
    public PermissionInterceptor permissionInterceptor() {
        return new PermissionInterceptor();
    }
}
