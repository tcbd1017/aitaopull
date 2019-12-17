package cn.kgc.tangcco.tcbd1017.text;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsService;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.RewardPointsServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月14日<dr>
 *	下午2:00:25<dr>
   * 类描述： 
 */
public class RewardPointsServiceText {
	private static RewardPointsService rewardPointsService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			rewardPointsService = (RewardPointsService) context.getBean(RewardPointsService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void findRewardPoints() {
		Map<String,Object> info = new HashMap<String, Object>();
		info=rewardPointsService.findRewardPoints(info);
		System.out.println(info);
	}
	@Test
	public void addRewardPoints(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 47);
		map.put("reward_points_value_change", 19);
		map.put("reward_points_create_time", new Date());
		map.put("reward_points_update_time", new Date());
		Map<String, Object> map2 = rewardPointsService.addRewardPoints(map);
		switch (map2.get("status").toString()) {
		case "success":
			System.out.println("添加成功");
			break;
		default:
			System.out.println("添加失败");
			break;
		}
	}
	
}
