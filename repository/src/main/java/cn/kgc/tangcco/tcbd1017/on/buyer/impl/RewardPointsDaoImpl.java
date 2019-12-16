package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.nio.channels.NonReadableChannelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.ObjectUtils;

import cn.hutool.db.sql.Order;
import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月13日<dr>
 *	上午9:41:12<dr>
   * 类描述： 
 */
public class RewardPointsDaoImpl implements RewardPointsDao{
	QueryRunner qr=new QueryRunner();
	
	/**
	 * 查询所有积分
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public  List<RewardPoints> selectRewardPoints(Map<String, Object> map) throws SQLException {
		StringBuilder sql=new StringBuilder("SELECT reward_points_id,buyer_id,reward_points_value_change,reward_points_create_time,"
											+ "reward_points_update_time,reward_points_status FROM `0107_reward_points`");
		sql.append(" where 1 = 1 ");
		
		List<Object> list=new ArrayList<Object>();
		Object[] param=list.toArray();
		// 从数据源中获取连		
		Connection conn = BaseDBUtils.getConnection();
		// 预编译SQL语句
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		// 执行查询获取结果集
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		List<RewardPoints> rewardPointsList=null;
		RewardPoints rewardPoints = null;
		if (rs.first()) {
			rs.previous();
			rewardPointsList=new ArrayList<RewardPoints>();
			while (rs.next()) {
				//实例化添加
				rewardPoints = new RewardPoints();
				rewardPoints.setBuyer_id(rs.getInt("buyer_id"));
				rewardPoints.setReward_points_create_time(rs.getDate("reward_points_create_time"));
				rewardPoints.setReward_points_id(rs.getInt("reward_points_id"));
				rewardPoints.setReward_points_status(rs.getInt("reward_points_status"));
				rewardPoints.setReward_points_update_time(rs.getDate("reward_points_update_time"));
				rewardPoints.setReward_points_value_change(rs.getInt("reward_points_value_change"));
				rewardPointsList.add(rewardPoints);
			}
		}	 
		return rewardPointsList;
	}
	@Override
	public List<RewardPoints> selectAllRewardPoints(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder(" SELECT reward_points_value_change FROM 0107_reward_points WHERE 1 = 1 and buyer_id = ? ");
		List<RewardPoints>list=new ArrayList<RewardPoints>();
		RewardPoints rewardPoint = null;
		Object[] param = { Integer.parseInt(map.get("buyer_id").toString()) };
		// 从数据源中获取连		
		Connection conn = BaseDBUtils.getConnection();
		// 预编译SQL语句
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		// 执行查询获取结果集
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				rewardPoint = new RewardPoints();
				rewardPoint.setReward_points_value_change(rs.getInt("reward_points_value_change"));
				list.add(rewardPoint);			
			}
		}	 
		return list;
	}
	 /**
	  * 添加积分
	  * @param map
	  * @return
	  * @throws SQLException
	  */
	@Override
	public int insertRewardPoints(Map<String, Object> map) throws SQLException {
		if (map!=null&&map.size()>0) {
				StringBuilder sql = new StringBuilder(" insert into 0107_reward_points(buyer_id,reward_points_value_change,reward_points_create_time,reward_points_update_time,reward_points_status) ");
				sql.append(" values(?,?,?,?,2)");
				List<Object> list = new ArrayList<Object>();
				list.add(map.get("buyer_id"));
				list.add(map.get("reward_points_value_change"));
				list.add(map.get("reward_points_create_time"));
				list.add(map.get("reward_points_update_time"));
				
				Object[] param = list.toArray();
				System.out.println(sql.toString());
				
				return qr.update(BaseDBUtils.getConnection(), sql.toString(), param);
		}
		return 0;
	}
	
	public int dollar(Map<String, Object> map) throws SQLException {
		int count = 0;
		StringBuilder sql=new StringBuilder("SELECT o.buyer_id,o.order_payment,r.buyer_id,r.reward_points_value_change");
		sql.append(" FROM `0109_order` as o,0107_reward_points as r where 1 = 1 ");
		sql.append(" AND o.buyer_id = ? ");
		sql.append(" AND r.buyer_id = o.buyer_id");
		// 参数数组
		Object[] param = { (Integer) (map.get("buyer_id")) };
		// 从数据源中获取连		
		Connection conn = BaseDBUtils.getConnection();
		// 预编译SQL语句
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		// 执行查询获取结果集
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.first()) {
			rs.previous();
			if (rs.next()) {
				count = rs.getInt("order_payment");
				count=count/10;
			}
		}
		return count;
	}
	 /**
	  * 用来处理结果集的工具
	  * @param rs 用SQL语句查询出的结果集
	  * @return 包含每个字段值的一个list
	  */
	 private List<Map<String ,Object>> rsToList(ResultSet rs){
	  List<Map<String ,Object>> list = null;
	  if(rs==null) {
	   return list;
	  }
	  try {
	   list = new ArrayList<Map<String ,Object>>();
	   //获取总列数
	   int columnCount = rs.getMetaData().getColumnCount();
	   while(rs.next()){
	    Map<String, Object> map = new HashMap<String, Object>();
	    // 遍历每一列,拿出列名和数据
	    for (int i = 1; i <= columnCount; i++) {
	     String columnLabel = rs.getMetaData().getColumnLabel(i);
	     Object value = rs.getObject(i);
	     map.put(columnLabel,value);
	    }
	       list.add(map);
	   }
	  } catch (SQLException e) {
	   e.printStackTrace();
	  }
	  return list;
	 }
	



}
