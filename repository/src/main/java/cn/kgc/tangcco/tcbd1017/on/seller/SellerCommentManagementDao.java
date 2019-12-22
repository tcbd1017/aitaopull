package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  上午8:39:26<br>
*类说明:
*/
public interface SellerCommentManagementDao {
	/**
	 * 查看评论
	 * @return
	 * @throws SQLException
	 */
	List<Map<String,Object>> sellectComments(Map<String,Object> map)throws SQLException;
	/**
	 * 删除评论
	 * @return
	 * @throws SQLException
	 */
	int updateComments(Map<String,Object> map)throws SQLException;
	/**
	 * 商家回复评论
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int insertSellerComments(Map<String,Object> map)throws SQLException;
}
