package com.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author：nbfujx
 * @description：
 * @date：下午2:07 2018/5/11
 */
@Configuration
public class RestTempleConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("simpleClientHttpRequestFactory") ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //读取超时单位为ms
        factory.setReadTimeout(20*1000);
        //建立连接超时单位为ms
        factory.setConnectTimeout(20*1000);
        return factory;
    }

}
