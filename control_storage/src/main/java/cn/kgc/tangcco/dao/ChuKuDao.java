package cn.kgc.tangcco.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.Chuku;

public interface ChuKuDao {
	
	
	//商家出货页面表单   查看ChuKu页面   每次读取 都先根据货物类型id   品牌  id   型号id  确认到是那个。。。 然后读出来 各自new成对象  加到JianCe这个类   然后把   对象放在集合里放到页面上   在
		//读取ChuKu这个biao的时候在页面显示的是   类型的名字  品牌的名字   型号的名字
	/**
	 *     出货员  或管理员  查询chuku 表、
	 *    当时商铺时只能查自家商铺      也可 按照仓库查询 
	*  chuku   
	 *     条件查询    【】为比有参数
	 * @param map    1 按照商铺查询   2  按照仓库查询              动态SQL  【类型id   品牌  id   型号id】
	 * @return
	 */
	public List<Chuku> selectChuKu(Map<String,Object>map);
	
//	/**
//	 * 根据商铺的uuid查询  商铺的id
//	 * @param map   参数 uuid
//	 */
//	public Map<String,Object> selectShangPuid(Map<String,Object>map);
	
	
	/**
	 * 商家选好商品 添加到chuku   
	 * @param map   
	 * @return   jiance对象     从页面上获取到  自动生成uuid
	 */ 
	public int insertChuKu(Map<String,Object>map);
	
	/**
	 * 精确  查询  按照订单编号
	 * @param map  订单uuid
	 * @return
	 */
	//public Map<String,Object> JingQueselectChuKu(String uuid);
	
	/**
	 * 修改状态    此功能是管理员  或者  入货员进行操作的  
	 *       （1 库满 2 正在等待入库 3 入库成功 4到期了）
	 * @param map 
	 * @return
	 */
	public int XiuGaiZhuangTaiChuKu(Map<String,Object>map);
	
	
	/**
	 * 查询 总个数   动态SQL
	 * @param map
	 * @return
	 */
	public int selectJianCeNum(Map<String,Object>map);
}
