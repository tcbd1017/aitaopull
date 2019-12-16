package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.hutool.db.sql.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月13日<dr>
 *	上午9:36:01<dr>
   * 类描述： 积分明细
 */
public interface RewardPointsDao {
	/**
	 * 查询所有积分
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<RewardPoints> selectRewardPoints(Map<String, Object> map)throws SQLException;
	/**
	 * 查询积分
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<RewardPoints> selectAllRewardPoints(Map<String, Object> map)throws SQLException;
	
	 /**
	  * 添加积分
	  * @param map
	  * @return
	  * @throws SQLException
	  */
	 public int insertRewardPoints (Map<String, Object> map) throws SQLException;
	 /**
	  * 获取订单实际积分
	  */
	 public int dollar(Map<String, Object> map)throws SQLException;
	
}