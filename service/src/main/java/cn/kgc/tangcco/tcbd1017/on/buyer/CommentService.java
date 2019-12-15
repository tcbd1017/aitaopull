package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午10:51:55
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.SQLException;
import java.util.Map;


public interface CommentService {
	/**
	 * 添加评论
	 * @param map
	 * @return 添加状态
	 * @throws SQLException
	 */
	public Map<String, Object> addComment(Map<String, Object> map);
	
	/**
	 * 查询评论
	 * @param map
	 * @return 评论详细信息
	 */
	public Map<String, Object> queryComment(Map<String, Object> map);
}
