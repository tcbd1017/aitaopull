package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月13日<dr>
 *	下午3:59:54<dr>
   * 类描述： 
 */
public interface RewardPointsService {
	/**
	 * 查询所有积分
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> findRewardPoints(Map<String,Object> map) ;
	
	 /**
	  * 添加积分
	  * @param map
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object> addRewardPoints(Map<String,Object> map) ;
	 /**
	  * 提供一个总积分数据
	  * @param map
	  * @return
	  * @throws SQLException
	  */
	public Map<String,Object>  findSumRewardPoints(Map<String, Object> map);
}
