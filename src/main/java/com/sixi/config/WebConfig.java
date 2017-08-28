package com.sixi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    public WebConfig() {
        System.out.println("new WebConfig");
    }

    /**
     * 设置web视图模板
     *
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        System.out.println("viewResolver");
        FreeMarkerViewResolver v = new FreeMarkerViewResolver();
        v.setPrefix("");
        v.setSuffix(".ftl");
        v.setCache(false);
        v.setContentType("text/html;charset=UTF-8");
        v.setExposeSpringMacroHelpers(true);
        return v;
    }

    /**
     * 设置视图存放目录
     *
     * @return
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        System.out.println("freeMarkerConfigurer");
        FreeMarkerConfigurer f = new FreeMarkerConfigurer();

        f.setTemplateLoaderPath("WEB-INF/views/");
        f.setDefaultEncoding("UTF-8");
        return f;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("configureDefaultServletHandling");
        configurer.enable();
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

//	@Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
//    }

}
