package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;



/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月16日上午9:42:55
	 * </br>
	 **/

public interface ViewSellerDao {

	/**
	 * 查看所有卖家信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<Seller> selectViewSeller(Map<String, Object> map) throws SQLException;
	/**
	 * 查询总记录数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectCountSeller(Map<String, Object> map) throws SQLException;
}
