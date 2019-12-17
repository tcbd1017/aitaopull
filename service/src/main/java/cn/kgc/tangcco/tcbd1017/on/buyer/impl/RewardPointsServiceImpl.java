package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsService;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月13日<dr>
 *	下午4:04:58<dr>
   * 类描述： 
 */
public class RewardPointsServiceImpl implements RewardPointsService{
	private static RewardPointsDao rewardPointsDao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	 static {
	  try {
		  rewardPointsDao = (RewardPointsDao) context.getBean(RewardPointsDao.class.getSimpleName());
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }
	 /**
	  * 买家id，积分id
	  */
	@Override
	public Map<String, Object> findRewardPoints(Map<String, Object> map) {
		 Map<String,Object> info = new HashMap<String, Object>();
		 info.put("data",new ArrayList<RewardPoints>() );
		 info.put("status", "failed");
		 info.put("msg", " ");
		 info.put("code", 0);
		 try {
			 //調用dao層方法
			List<RewardPoints> list=rewardPointsDao.selectRewardPoints(map);
			
			if (list!=null&&list.size()>0) {
				info.put("status", "success");
				info.put("data", list);
			}
			BaseDBUtils.closeAll();
			return info;
		} catch (SQLException e) {
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		return info;
	}
	 /**
	  * 添加积分
	  * @param map
	  * @return
	  * @throws SQLException
	  */
	@Override
	public Map<String, Object> addRewardPoints(Map<String, Object> map){
		Map<String,Object> info = new HashMap<String, Object>();
		 info.put("status", "failed");
		 info.put("msg", " ");
		 info.put("code", 0);
		 info.put("data",new ArrayList<RewardPoints>() );
		int count;
		try {
			 //开启事务
			 BaseDBUtils.startTransaction();
			// 调用持久层接口执行查询
			count =rewardPointsDao.insertRewardPoints(map);
			if (count>0) {
				info.put("status", "success");
				List<RewardPoints> list;
				int sum = 0;
				try {
					list= (List<RewardPoints>) rewardPointsDao.selectAllRewardPoints(map);
					Iterator<RewardPoints> iterator = list.iterator();
					while (iterator.hasNext()) {
						RewardPoints rewardPoints = (RewardPoints) iterator.next();
						System.out.println(rewardPoints);
						sum += rewardPoints.getReward_points_value_change();
						map.put("reward_points_value_change", sum);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					info.put("data", sum);
			}
			//关闭事务
			BaseDBUtils.commitAndClose();
			// 将连接归还给数据源
			return info;
		} catch (Exception e) {
			
			try {
					BaseDBUtils.rollbackAndClose();
				}catch (SQLException e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			
		}
		
		return info;
	}

}
