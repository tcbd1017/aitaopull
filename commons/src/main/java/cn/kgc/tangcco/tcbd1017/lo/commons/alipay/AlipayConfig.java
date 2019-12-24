
﻿package cn.kgc.tangcco.tcbd1017.lo.commons.alipay;

import java.io.FileWriter;
import java.io.IOException;


/**
 * @author Administrator
 * 类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 * 
 *
 */
public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101700707084";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJxjvLS3a6SY2sl57sOOk/Vz3Sa4CHySAdwBzj9T5WyXIhHWy/nIbr6XuLarVOr55Um/uHMa/UlpB1wcdb2/mFox4odJlTC4xrkLdYlqbtLIK2LVrjWtAAxytNdiNvSJfkug0xZoRoJB7x4O+w5GLE17ZuZ0TdXUCn1wPCzYWcW2BgKosYEbLmv6SDhKl8/6uSwD9oyJJ70WetR+29ofMbZBvNabgoJft1nRRwKOoYNd0gHalByH0JlS4pGA8EUR1TQeeffgfHK7T/V79zXQn5JLZrASWdgCdGX+v81UrQsHYNIN3Nb5/7Dpn4FK3ROSxIEAWwmZDKwkZvdYVAGjIfAgMBAAECggEAcQszwtDzoqjEy/ZiXjwASbFp/HKoHusKqEBSr1Bd7aeM3oqqu6tJV/CGe/R7warYjNSbFgI7XWJBawf21jsmz700jzvMDt/Aex8n6WT1/pjaXpCIhCoDsPvlAc/3SEimeMnXzF6APgxGngMEptk9kMS9+5q11UOCjsYC4GfevFriqRgmk00mA3WyDc8T1tlaf+Aq4jO8q708UUq565IwOPhE+GFwY2gUNT/Dt2xbJR6HnLM+SM3Hxjc7W37HrSllEFg5Gj3qkn/kAzzr2rplm1a4mCvHVUtKRsS5xe+Hgs3Ii4MuMl3dl22iL2nScJXzhbMzd9GWNmlNKrJ3L1+CwQKBgQDoRJg8CZgMKTb1Ac6jLOeQ0/7Y88aME1qj/RPvTATq0k6hh0Y2xrvHJACNmxLTSZ/Hel9czpZ1ABKLlu1GV85A8qgvMgo3u4+3xWJkY4HdsPLk79GzGte3hhXEZsXXL4YorSdpWQ5q5yxEz4xZDId33Z8ru2h3uM/hb0WHLpPTPwKBgQDeZAV+HbYj7gcmzidhxLJZXH2uMuuuC6LV6PpRaVYq+nDEA9DZ3AL4D/YAdHbhfm25Xj50oz9qARSviMZI4w34NJUqF8UzdbBYNNPKaUJvxKr38Nw+dH/8jiUvGsgtogG/zSXV1QkZbj/1bA0+fTnUhLidlb+RkIKuYG2O53FJIQKBgBpWBMjx8YqXdcLMJKj943fZgdEJux6Q5zsm7Aa7IOebVw0bsItvd0U6QNHNnpHUjEhrCLsWNZsomfG3+El8lUdp/EX1r5DoNICK4gAuBECl2rS4uJdNWN5XVsqBOLeQjO65y3k3ftoCQlO/ENPcBtRBjdXAlzTUAMY3/rM4RcyzAoGBAKwBjqIB1vupQczIzHr5CLzHJcoTRmhCRbrPTLSlFWl+dLOXA/VNDtrQdYPakIxOHg2uj/3kucqCdX5i+oIrY4WjHfAFnJf1oeRHT7wRd7o80cOdfqurrcYoJyhsvYFV+aZgxK/D6xrJRBzNatz3PEBcOvJffWDX9ctrd7F83wkhAoGAcAMZmW34Mv1xsSYtxV67aY2bJagpEDRWOiCFsqMBsveRb+kXntE9TwRupRww5ozZ6hxBnRq/ZtXj8XyX+qMdgZaEAsf2Zf7XeVx1OBG8JQmMSOpSjMWHP4Mv+OOrFcD6BjRp6RuFvVPo1IXiQCTSs+5qLfF2pMM061uTbUZWeb4=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4hdsDR81Glw8eXFKKWQ3XXvYIFMU7UlBlHncEzvrDc2c1TW0IxKeVyVq8/6u5m0tCFtwEA5rNbHPOmmtovnnHBQw6KXHvLKvHz/Dud5j82wLJAR9xBC2IE6mdiUvL3HIMsvi7H5yK810lpIQV4wORS49pPYS4uGnyjGDwprlx9zxb/tqDtnS55m4QUiCOiK7IY3MWD7OtxVbxOh9z1NPHArTlxQhnmaNgvF3TRBwgWDLVpHQV0wd81KgC/9BEIhlxFhijL2FEoS6HPRMYDBCJDyhLpHW3gfO/ClaOEcZF8l0pVa/PhwQaTnE0j2MsX9/XE2Y5WjZ5c6RQwfrMTXlnwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 
	public static String notify_url = "http://localhost:8080/control_OnlineRetailers/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问8080/control_OnlineRetailers/querypay.action
	public static String return_url = "http://localhost:8080/wl/pay.action?methodName=callBack";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


