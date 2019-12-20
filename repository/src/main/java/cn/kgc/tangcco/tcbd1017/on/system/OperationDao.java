package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;





/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月16日下午6:50:52
	 * </br>
	 **/

public interface OperationDao {

	/**
	 * 查询所有运营部员工
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<Emp> selectoperation(Map<String, Object> map)throws SQLException;
	/**
	 * 查询所有待审核卖家
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<Seller> selectSellers(Map<String, Object> map)throws SQLException;
	/**
	 * 查询待审核卖家的总记录数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectCountSeller(Map<String, Object> map) throws SQLException;
	/**
	 * 分配卖家入住商城的所有信息
	 * (添加020102_seller_principal表)
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int allocationSeller(Map<String, Object> map)throws SQLException;	
	/**
	 * 对卖家入住商城的信息的审核
	 * (修改0201_seller表 )
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int auditSeller(Map<String, Object> map) throws SQLException;
	
	
	
	/**
	 * 查询所有待上架商品
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<Goods> selectGoods(Map<String, Object> map)throws SQLException;
	/**
	 * 查询待上架商品的总记录数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectCountGoods(Map<String, Object> map) throws SQLException;
	/**
	 * 分配上架商品的所有信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int allocationGoods(Map<String, Object> map)throws SQLException;
	/**
	 * 对商家要上架的商品的审核
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int auditGoods(Map<String, Object> map) throws SQLException;
}
