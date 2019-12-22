package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月18日上午11:06:13
	 * </br>
	 **/

public interface OperationService {

	/**
	 * 查询所有待审核卖家
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectSellers(Map<String, Object> map)throws SQLException;
	
	/**
	 * 分配卖家入住商城的所有信息
	 * (添加020102_seller_principal表)
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> allocationSeller(Map<String, Object> map)throws SQLException;
	
	/**
	 * 对卖家入住商城的信息的审核
	 * (修改0201_seller表 )
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> auditSeller(Map<String, Object> map) throws SQLException;
	/**
	 * 查询某员工待审核的卖家
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectEmpSellers(Map<String, Object> map)throws SQLException;
		
	
	
	
	/**
	 * 查询所有待上架商品
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectGoods(Map<String, Object> map)throws SQLException;
	/**
	 * 分配上架商品的所有信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> allocationGoods(Map<String, Object> map)throws SQLException;
	/**
	 * 对商家要上架的商品的审核
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> auditGoods(Map<String, Object> map) throws SQLException;
	/**
	 * 查询某员工待上架商品信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectEmpGoods(Map<String, Object> map)throws SQLException;
	
}
