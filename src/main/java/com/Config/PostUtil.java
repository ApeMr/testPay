package com.Config;



import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;

/**
 * @author：lq
 * @description：
 * @date：15:22 2018/5/18
 */
@Component
public class PostUtil {
    @Autowired
    RestTemplate restTemplate;

    public   String get(HashMap<String,String> params,String url ){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        String jsonStr = JSONObject.toJSONString(params);
        System.out.println("jsonStr:"+jsonStr);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,formEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();

        int code = statusCode.value();
        System.out.print(code);
        String list = responseEntity.getBody();


        return  list;
    }

    public   String get(JSONObject params,String url ){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
//        String jsonStr = JSONObject.toJSONString(params);
        String jsonStr = params.toJSONString();
        System.out.println("jsonStr:"+jsonStr);
        jsonStr = jsonStr.replaceAll("\\\\","");
        System.out.println("jsonStr replace after:"+jsonStr);
        jsonStr = "{\"sign\":\"7cb2f14af32144b7234898bc2c23551d\",\"downOrderNo\":\"CH00000000062817060002\",\"channelCode\":\"M003N001\",\"amountIo\":\"O1\",\"transactionLog\":{\"bankGeneralName\":\"招商银行\",\"subjectName\":\"程浩\",\"certNo\":\"342626199109086416\",\"remarks\":\"测试\",\"toPrivate\":\"0\",\"subjectCard\":\"6214830216958206\",\"subjectPhone\":\"18521555459\",\"bankCode\":\"CMB\"},\"tradeAmount\":\"1\",\"merchantCode\":\"2018062714225\",\"agentCode\":\"201806193145\"}";
        System.out.println("jsonStr last:"+jsonStr);
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST,formEntity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();

        int code = statusCode.value();
        System.out.print(code);
        String list = responseEntity.getBody();


        return  list;
    }
}