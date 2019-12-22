package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.buyer.PostageInfoService;
import cn.kgc.tangcco.tcbd1017.on.pojo.PostageInfo;

/**
 * @author LIU KAI
 * @version 1.0 2019年12月14日 下午3:32:50 </br>
 */

public class PostageInfoServiceTest {
	private static PostageInfoService postageInfoService;
	private static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			postageInfoService = (PostageInfoService) path.getBean(PostageInfoService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postage_info_name", "333");
		map.put("postage_info_mobile", "333");
		map.put("postage_info_province_id", 13);
		map.put("postage_info_city_id", 1306);
		map.put("postage_info_district_id", 130624);
		map.put("postage_info_address", "333");
		map.put("postage_info_status", 3);
		map.put("postage_info_id", 4);
		map.put("postage_info_postcode", "123456");
		map.put("buyer_id", 1);
		String jsonString = JSON.toJSONString(map);
		System.out.println(jsonString);
	}

	@Test
	public void queryPostageInfosByBuyerId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 1);
		Map<String, Object> id = postageInfoService.queryPostageInfosByBuyerId(map);
		List<PostageInfo> list = (List<PostageInfo>) id.get("data");
		ListIterator<PostageInfo> it = list.listIterator();
		while (it.hasNext()) {
			PostageInfo postageInfo = it.next();
			System.out.println(postageInfo);
		}
	}

	@Test
	public void addPostageInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postage_info_name", "333");
		map.put("postage_info_mobile", "333");
		map.put("postage_info_province_id", 13);
		map.put("postage_info_city_id", 1306);
		map.put("postage_info_district_id", 130624);
		map.put("postage_info_address", "333");
		map.put("postage_info_status", 3);
		map.put("postage_info_uuid", BaseUUID.generate());
		map.put("postage_info_postcode", "123456");
		map.put("buyer_id", 1);
		Map<String, Object> addPostageInfo = postageInfoService.addPostageInfo(map);
		System.out.println(addPostageInfo.get("status"));
	}

	@Test
	public void modifyPostageInfo() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("postage_info_name", "333");
		map.put("postage_info_mobile", "333");
		map.put("postage_info_province_id", 13);
		map.put("postage_info_city_id", 1306);
		map.put("postage_info_district_id", 130624);
		map.put("postage_info_address", "333");
		map.put("postage_info_status", 3);
		map.put("postage_info_id", 4);
		map.put("postage_info_postcode", "123456");
		map.put("buyer_id", 1);
		Map<String, Object> modifyPostageInfo = postageInfoService.modifyPostageInfo(map);
		System.out.println(modifyPostageInfo.get("status"));
	}

	@Test
	public void modifyPostageInfosByStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 1);
		map.put("postage_info_id", 1);
		map.put("postage_info_status", 3);
		Map<String, Object> infosByStatus = postageInfoService.modifyPostageInfosByStatus(map);
		System.out.println(infosByStatus.get("status"));
	}

	@Test
	public void removePostageInfosByStatus() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 1);
		map.put("postage_info_id", 2);
		map.put("postage_info_status", 1);
		Map<String, Object> status = postageInfoService.removePostageInfosByStatus(map);
		System.out.println(status.get("status"));
	}
}
