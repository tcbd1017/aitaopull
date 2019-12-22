package cn.kgc.tangcco.tcbd1017.on.buyer;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;
import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.RewardPointsDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Logistics;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

public class Ontest {
	private static RewardPointsDao logisticsDao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			logisticsDao = (RewardPointsDao) context.getBean(RewardPointsDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 胡文举的dao层测试方法
	 */
	@Test
	public void selectRewardPoints() {
		Map<String,Object> map = new HashMap<String, Object>();
		
			List<RewardPoints> list;
			try {
				list = (List<RewardPoints>) logisticsDao.selectRewardPoints(map);
				Iterator<RewardPoints> iterator = list.iterator();
				while (iterator.hasNext()) {
					RewardPoints rewardPoints = (RewardPoints) iterator.next();
					System.out.println(rewardPoints);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	@Test
	public void selectAllRewardPoints() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("buyer_id",47);
		List<RewardPoints> list;
		int count = 0;
		try {
			list= (List<RewardPoints>) logisticsDao.selectAllRewardPoints(map);
			Iterator<RewardPoints> iterator = list.iterator();
			while (iterator.hasNext()) {
				RewardPoints rewardPoints = (RewardPoints) iterator.next();
				System.out.println(rewardPoints);
				count += rewardPoints.getReward_points_value_change();
			}
			System.out.println("count"+count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertRewardPoints() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 5);
		map.put("reward_points_value_change", 22);
		map.put("reward_points_create_time", new Date());
		map.put("reward_points_update_time", new Date());
//		map.put("order_payment", 20);
		try {
			int i = logisticsDao.insertRewardPoints(map);
			System.out.println(i);
			System.out.println(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
