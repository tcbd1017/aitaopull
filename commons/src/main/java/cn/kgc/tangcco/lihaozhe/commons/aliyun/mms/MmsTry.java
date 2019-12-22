package cn.kgc.tangcco.lihaozhe.commons.aliyun.mms;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

	
/**
	 * @author DU MING
	 * @version 1.0	2019年10月13日	下午1:15:13
	 */

public class MmsTry {
//    public static void main(String[] args) {
//        MmsTry m = new MmsTry();
//        Map<String, String> map = new HashMap<String, String>();
//        String phoneNumber = "17640184019";
//        String ranCode = m.ranCode();
//        map.put("phoneNumber", phoneNumber);
//        map.put("ranCode", ranCode);
//        m.sendMms(map);
//        System.out.println(ranCode);
//        }
    
    public void sendMms(Map map) {
        String phoneNumber = (String) map.get("phoneNumber");
        String ranCode = (String) map.get("ranCode");
        String code = "code:" + ranCode;
        String host = "http://dingxin.market.alicloudapi.com";
        String path = "/dx/sendSms";
        String method = "POST";
        String appcode = "ea1fe8c891654de1a7fd7bd830cd770e";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phoneNumber);
        querys.put("param", code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
            * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            *
            * 相应的依赖请参照
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
            */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String ranCode() {
        Random ran = new Random();
        int num = ran.nextInt(9999);
        return String.format("%04d", num);
    }
    

}


