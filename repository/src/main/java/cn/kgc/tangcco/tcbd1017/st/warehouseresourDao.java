package cn.kgc.tangcco.tcbd1017.st;

import java.util.Map;

/**
 * 仓库记录表    主要记录某个类型仓库未买的个数
 * 
 * @author 孙伊泽
 *
 */
public interface warehouseresourDao {
	
	
	/**
	  * 查看未卖仓库总个数
	  * @return
	  */
	 public int selectWeiMaiZongGeShu();
	
	
	/**
	 * 查看某类仓库剩余数量
	*跟仓库类型表相连 
	 * @param map  参数 仓库类型
	 * @return
	 * 
	 * key= warehouse_id (仓库类型id)
	 */
	public Map<String,Object> selectXingHao( Map<String,Object>map);
	
	
	/**
	 * 购买仓库成功
	 *    某个类型下的 剩余数量减少
	 * 
	 * 在这张表是各个仓库类型的剩余数量
	 * 得加一块去
	 * @param map  参数 仓库类型
	 * 
	 * 
	 * key= number (购买仓库数量)
	 * key= warehouseresour_id (仓库类型id)
	 */
	public Map<String,Object> updateCangKuShnegYuCount( Map<String,Object>map);
}
