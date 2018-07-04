package test.com;


import com.Config.PostUtil;
import com.ConsumerBootStrap;
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
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerBootStrap.class ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CashPay {


    private static Logger log = LoggerFactory.getLogger(payTest.class);
    @Autowired
    PostUtil post ;

    @Test
    public void test(){
//        String  Key="0Mc4DZcXgXdAGKvK90FhXQVH54HIoKVl";
        String Key =  "D7WjbnpDsgbEregOFwLA7PIViE2v23lJ";
//        HashMap<String,String> params = new HashMap<String,String>();
        JSONObject params = new JSONObject();


        params.put("agentCode","201806193145"); //合作商户的应用编号
        params.put("merchantCode","2018062714225"); //商户号，支付平台分配


        params.put("channelCode","M003N001");
        params.put("tradeAmount","1");

        params.put("downOrderNo","CH00000000062817060002"); //渠道编号
        params.put("amountIo","O1");   //支付方式


        ///-------------------------------
        TreeMap<String,String> paramsmap = new TreeMap<String,String>();
//        JSONObject params = new JSONObject();


        paramsmap.put("agentCode","201806193145"); //合作商户的应用编号
        paramsmap.put("merchantCode","2018062714225"); //商户号，支付平台分配


        paramsmap.put("channelCode","M001");
        paramsmap.put("tradeAmount","1");

        paramsmap.put("downOrderNo","CH00000000062817060001"); //渠道编号
        paramsmap.put("amountIo","O1");


        //--------------------------------



        //Todo  计算sign
//        String signStr = APIutil.crypt(Key,params.get("agentCode").toString()+params.get("amountIo")
//                +params.get("channelCode")+params.get("downOrderNo")+params.get("merchantCode")+
//                params.get("tradeAmount"));
        log.info("JSON string {}",JSONObject.toJSONString(paramsmap));
        String signStr = APIutil.crypt(JSONObject.toJSONString(paramsmap),Key);

        params.put("sign",signStr);
        log.info("sing {}",signStr);


//        HashMap<String,Object> transactionLogMap = new HashMap<String,Object>();
//        transactionLogMap.put("subjectCard","6214830216958206"); //银行卡号
//        transactionLogMap.put("subjectName","程浩"); //姓名
//        transactionLogMap.put("certNo","342626199109086416"); //身份证号
//        transactionLogMap.put("toPrivate","0"); //对公对私
//        transactionLogMap.put("bankCode","CMB");//银行编号
//        transactionLogMap.put("bankGeneralName","招商银行");//银行名称
//        transactionLogMap.put("subjectPhone","18521555459"); //手机号
//        transactionLogMap.put("remarks","测试"); //打款备注

        JSONObject transactionLogMap = new JSONObject();
        transactionLogMap.put("subjectCard","6214830216958206"); //银行卡号
        transactionLogMap.put("subjectName","程浩"); //姓名
        transactionLogMap.put("certNo","342626199109086416"); //身份证号
        transactionLogMap.put("toPrivate","0"); //对公对私
        transactionLogMap.put("bankCode","CMB");//银行编号
        transactionLogMap.put("bankGeneralName","招商银行");//银行名称
        transactionLogMap.put("subjectPhone","18521555459"); //手机号
        transactionLogMap.put("remarks","测试"); //打款备注


//        params.put("aisleType","9");  //接口类型
//        params.put("callback","http://www.tianlepay.com");  //前台回调
//        params.put("totalAmount","1");  //商品描述
//        params.put("accountNo","6214830216958206"); //商品名称
//        params.put("accountName","程浩"); //订单号
//        params.put("bankNo","CMB");  //回调地址
//        params.put("bankName","招商银行"); //金额
//        params.put("accountType","B2C");  //IP地址
//        params.put("certificateType","ID");
//        params.put("IdCard","342626199109086416");
//        params.put("currency","156");
//        params.put("mobile","18900535306");
//        params.put("remark","cs");

        //验签
//        params.put("sign", APIutil.crypt(params.get("totalAmount")+params.get("downOrderNum"),Key));

        params.put("transactionLog",transactionLogMap.toString());
        log.info("请求 {}",params);
        String str = post.get(params,"https://m.coobpay.net/Dfpay/dfPay/dfPayTransactionOutAmount");

        log.info("结果 {}",str);
        JSONObject respjson =  JSONObject.parseObject(str);
        log.info("调用接口状态 {}","状态码: "+respjson.getString("code")+" 描述： "+respjson.getString("desc"));
        JSONObject datajson = JSONObject.parseObject(respjson.getString("data"));
        log.info("datajson {}",datajson);
    }


}
