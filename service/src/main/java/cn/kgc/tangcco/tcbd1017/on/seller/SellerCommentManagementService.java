package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  下午1:36:48<br>
*类说明:		卖方评论管理 
*/
public interface SellerCommentManagementService {
	/**
	 * 查看评论
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> queryComments(Map<String,Object> map);
	/**
	 * 删除评论
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> removeComments(Map<String,Object> map);
	/**
	 * 商家回复评论
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> addSellerComments(Map<String,Object> map);
}
