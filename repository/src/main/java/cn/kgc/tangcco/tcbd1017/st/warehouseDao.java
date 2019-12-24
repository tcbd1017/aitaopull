package cn.kgc.tangcco.tcbd1017.st;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.Warehouse;


/**
 * 购买仓库 显示的页面
 * @author 孙伊泽
 *
 */
public interface warehouseDao {
	
	
	/**
	 * 购买仓库时显示仓库的信息
	 * 读出  每种仓库剩余个数和 详情
	 * 1按类型查询 
	 * 
	 * 	
	 * 以图片的形式显示
	 * @param map  
	 * @return
	 * 
	 * key= warehouse_id (仓库类型id)
	 */
	public Map<String,Object> selectCangKuLeixing(Map<String,Object>map);
	
	
	/**
	 * 查询所有类型
	 */
	
	public List<Warehouse> selectCangKuLeixing();
}
