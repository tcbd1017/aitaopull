package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午9:07:10
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Comment;


public interface CommentDao {
	/**
	 * 添加评论
	 * @param map 存放添加的信息
	 * @return 添加状态   0失败   1成功
	 * @throws SQLException
	 */
	public int insertComment(Map<String, Object> map) throws SQLException;
	
	/**
	 * 查询评论信息
	 * @return 评论详细信息
	 * @throws SQLException
	 */
	public List<Map<String ,Object>> selectComment(Map<String, Object> map) throws SQLException;
}
