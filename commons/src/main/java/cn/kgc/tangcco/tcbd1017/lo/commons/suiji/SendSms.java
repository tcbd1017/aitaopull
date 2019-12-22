package cn.kgc.tangcco.tcbd1017.lo.commons.suiji;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 *	 短信发送功能  
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年10月21日 下午7:21:18 类说明
 */
public class SendSms {
	// AK参数
	static final String accessKeyId = "LTAI4FouCjysLuCifjNQHXAy";
	static final String accessKeySecret = "m6morhZR5mEwlW21r5Y2t5rK96CWzT";
	// 短信API域名
	static final String Domain = "dysmsapi.aliyuncs.com";
	// 地域ID
	static final String RegionId = "cn-hangzhou";
	static final String SignName ="LK超市";
	static final String TemplateCode="SMS_175573444";
	/**
	 * @return 随机生成六位随机验证码
	 */
	public static String temp() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
	}

	/**
	 * 发送短信
	 * 
	 * @param phoneNumbe 接收短信的号码
	 * @return 返回 短信 验证码
	 */
	public static String smsSending(String phoneNumbe) {
		//初始化默认配置文件
		DefaultProfile profile = DefaultProfile.getProfile(RegionId, accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		String temp = temp();
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain(Domain);
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", RegionId);
		request.putQueryParameter("PhoneNumbers", phoneNumbe);
		request.putQueryParameter("SignName", SignName);
		request.putQueryParameter("TemplateCode", TemplateCode);
		request.putQueryParameter("TemplateParam", "{\"code\":\"" + temp + "\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return temp;
	}
}
