package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月13日	上午8:57:33<br>
 * 	类描述:
 */


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.CashVoucher;
import cn.kgc.tangcco.tcbd1017.on.pojo.Store;
import cn.kgc.tangcco.tcbd1017.on.pojo.VoucherType;

public interface CashVoucherDao {
	/**
	 * 								查询某状态的所有优惠券信息
	 * @param map					查询的信息集
	 * @return						list集合
	 * @throws SQLException			sql异常
	 */
	public List<Map<String, Object>> selectByBuyerId(Map<String, Object>map)throws SQLException;
	/**
	 * 								查询总量
	 * @param map					查询的条件的集合
	 * @return						总量
	 * @throws SQLException			sql异常
	 */
	public int selectByNumber(Map<String, Object>map) throws SQLException;
	/**
	 * 								删除一个购物券
	 * @param map					删除条件的集合
	 * @return						改变的条目
	 * @throws SQLException			SQL异常
	 */
	public int deleteByUuid(Map<String, Object>map) throws SQLException;
	/**
	 * 								查询加分页
	 * @param map					查询条件的集合
	 * @return						信息的集合
	 * @throws SQLException			sql异常
	 */
	public List<Map<String, Object>> selectByBuerIdAndStatus(Map<String, Object> map)throws SQLException;
	/**
	 * 								查询通过店名
	 * @param map					查询条件的集合
	 * @return						返回信息的集合
	 * @throws SQLException			sql异常
	 */
	public List<Map<String, Object>> selectByIdAndStatusAndStoreName(Map<String, Object> map) throws SQLException;
	
}
