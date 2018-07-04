package test.com;


import com.ConsumerBootStrap;
import com.Config.PostUtil;
import com.alibaba.fastjson.JSONObject;
import com.util.APIutil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerBootStrap.class ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class payTest {
    private static Logger log = LoggerFactory.getLogger(payTest.class);
    @Autowired
    PostUtil post ;

    /**
     * 支付接口测试
     */
    @Test
    public void test(){
        String  Key="D7WjbnpDsgbEregOFwLA7PIViE2v23lJ";
        HashMap<String,String> params = new HashMap<String,String>();
        params.put("agentCode","201806193145"); //合作商户的应用编号
        params.put("merchantCode","2018062714225"); //商户号，支付平台分配
        //TODO
        params.put("channelCode","M005"); //渠道编号

        params.put("payType","T003");   //支付方式
        params.put("interfaceType","P006");  //接口类型
        params.put("successUrl","http://www.tianlepay.com");  //前台回调
        params.put("describe","P005");  //商品描述
        params.put("goodsName","P005"); //商品名称
        params.put("downOrderNum","1145d1as5d15sa1q15"); //订单号
        params.put("callBackUrl","http://www.tianlepay.com:7852/async");  //回调地址
        params.put("totalAmount","2.01"); //金额
        params.put("ip","1");  //IP地址
        //验签
        params.put("sign",APIutil.crypt(params.get("totalAmount")+params.get("downOrderNum"),Key));
        log.info("请求 {}",params);
        String str = post.get(params,"https://m.coobpay.net/pay/mipay/pay");

        log.info("结果 {}",str);
        JSONObject respjson =  JSONObject.parseObject(str);
        log.info("调用接口状态 {}","状态码: "+respjson.getString("code")+" 描述： "+respjson.getString("desc"));
        JSONObject datajson = JSONObject.parseObject(respjson.getString("data"));
        log.info("datajson {}",datajson);
        log.info("报文状态 {}","状态码: "+datajson.getString("responseCode")+" 描述： "+datajson.getString("responseMsg"));
    }
}