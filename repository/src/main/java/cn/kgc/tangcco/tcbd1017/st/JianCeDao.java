package cn.kgc.tangcco.tcbd1017.st;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.Jiance;



/**
 * 大表   入货员和商家公用一张表
 * 业务   主要检测入库状态
 * @author 孙伊泽
 * 
 */
public interface JianCeDao {
	
	
	
	
	//商家添加页面   查看JianCe页面   每次读取 都先根据货物类型id   品牌  id   型号id  确认到是那个。。。 然后读出来 各自new成对象  加到JianCe这个类  在
	//读取JianCe这个库的时候在页面显示的是   类型的名字  品牌的名字   型号的名字
	
	/**
	 * jiance    type   model  brand 
	 *   读取入库检测表      
	 * 
	 * 若是管理员  和入货员   参数为
	 * 1  按商铺     2 按订单编号 查询  3  按状态  
	 * 
	 * 若时商家   参数是 商家uuid
	 * 
	 * 注  （）是动态SQL      【】是必要参数
	 * 1  按状态          2按仓库    3 按订单编号 查询  
	 * @param map   登陆者       先判断 角色   1 如是管理员或入货员（ 查询条件  1  按商铺     2 按订单编号 查询  3  按状态）  和【物类型id   品牌  id   型号id 】     
	 * 或   2 若是商家（查询条件1  按状态          2按仓库    3 按订单编号 查询  ）【物类型id   品牌  id   型号id 】 
	 * 
	 * @return
	 */
	public List<Jiance> selectZhuCe(Map<String,Object>map);
	
	
	/**
	 *  
	 * 	插入  jiance表 
	 * 此表插入  是商家进行插入的
	 *       默认状态为待入库     自动生成uuid
	 *       （1 库满 2 正在等待入库 3 入库成功 4到期了）
	 * @param map  
	 * @return
	 */
	public int insertJianCeBiao(Map<String,Object>map);
	
	
	/**
	 * 修改状态    此功能是管理员  或者  入货员进行操作的  
	 *       （1 库满 2 正在等待入库 3 入库成功 4到期了）
	 * @param map 
	 * @return
	 */
	public int XiuGaiZhuangTaiRuKu(Map<String,Object>map);
	
	
//	/**
//	 * 精确  查询  按照订单编号
//	 * @param map  订单uuid
//	 * @return
//	 */
//	public Jiance JingQueRuKuselect(Map<String,Object>map);
//	
	/**
	 * 根据商铺的uuid查询  商铺的id
	 */
	public int selectShangPuid(String uuid);
	
	
	/**
	 * 检测 个数   动态sql
	 * @param map
	 * @return
	 */
	public int selectJianCeNum(Map<String,Object>map);
	
}
