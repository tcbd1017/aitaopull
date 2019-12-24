package cn.kgc.tangcco.tcbd1017.st;

import java.util.List;
import java.util.Map;



public interface ShangJiChuKuService {
	
	
	

	/**
	 *  调用查询货物品牌  的方法 查询出所有品牌  (下拉框)      根据上一级所选的id传入    selectBrandByid（） 某类型下 得到所有品牌
	 * @param map
	 * @return
	 */
	public Map<String, Object> ChaXunPinPai(Map<String, Object> map);
	
	
	/**
	 *  调用查询货物型号  的方法 查询出所有型号  (下拉框)      根据上一级所选的id传入    selectXingHao（） 某品牌下 得到所有型号
	 * @param map
	 * @return
	 */
	public Map<String, Object> ChaXunXingHao(Map<String, Object> map);
	
	
	/**
	 * 查询 自家所购买的所有仓库简略 uuid id （下拉框）    
	 * @param map           warehouse_shopDaoImpl        selectShengYuRongLiang()
	 * @return
	 */
	public Map<String, Object> ShopGaouMaiDeCangKu(Map<String, Object> map);
	
	
	/**
	 * 开始检测出库    把所填信息 添加到 ChuKu表              ChuKuDaoImpl()         insertChuKu（）
	 * @param map 
	 * @return
	 */
	public Map<String, Object> KaiShiChuKu(Map<String,Object>map);


	public Map<String, Object> ChaXunLeiXing() ;
	
	
	/**
	  * 
	  * @方法名：查询所有出库
	  * @param map
	  * @return
	  */
	 public Map<String, Object> selectAllChuku(Map<String,Object>map);
}
