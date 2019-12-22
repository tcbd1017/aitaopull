package cn.kgc.tangcco.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.BingZhuangTuDuiXiang;
import cn.kgc.tangcco.pojo.WarehouseShop;

/**
 * 仓库与店铺的关联表  里面有仓库的信息  
 * 业务  对仓库的购买做记录
 * @author 孙伊泽
 *
 */
public interface warehouse_shopDao {

	/**
	 * 查询仓库与店铺的关联表    条件    按商家 查询         按购买年月   查询
	 *  
	    *   查看warehouse_shop 的详情 包括商家简略信息 （商家uuid 商家）   以表格显示到页面上
	 * 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> selectCnagKuYuDianPuGuanLianBiao(Map<String,Object>map);
	
	
	/**
	   *      查 看 详 情  管理员对以卖仓库查询详情
	 * warehouse_shop  与   shop  与    warehouse   
	 * @param map  参数   1 按照类别查询   2 按时间段查询  3按商铺uuid查询
	 * @return
	 */
	public Map<String,Object> chakanxiangqing(Map<String,Object>map);
	
	
	/**
	 * 查询所有仓库   简略     动态SQL   根据店铺uuid查询自家仓库简略
	 * @param map
	 * @return
	 */
	public List<WarehouseShop> chakanxiangqingAllCangKu(Map<String, Object> map);
	 
	/**
	 * 商家查看自家仓库剩余容量等详细信息    条件 
	 * @param map  参数  商铺uuid  这个参数是从session中获取的
	 * @return
	 */
	public Map<String,Object> selectCangKuGenjuShangJiao (Map<String,Object>map);
	
	/**
	 * chuku   warehouse_shop
	 * 商家查看自家仓库剩余容量    条件   1   按仓库uuid 
	 * @param map  参数  商铺uuid  这个参数是从session中获取的
	 * @return
	 */
	public Map<String,Object> selectShengYuRongLiang(Map<String,Object>map);
	
	/**
	 * chuku   warehouse_shop
	 *   查看仓库剩余时间    条件     
	 * @param map    仓库uuid  
	 * @return
	 */
	public Map<String,Object> selectShengYuTime(Map<String,Object>map);
	
	/**
	 * 购买仓库  插入
	 * @param map    warehouse_shop 这张表的信息 全是从页面上获取的     包括商铺 id 仓库类型   购买时间，时长，到期时间是计算出的
	 * @return
	 */
	public Map<String,Object> goumaiCangKu(Map<String,Object>map);
	
	
	/**
	 *    查询以卖仓库个数  以类别作为条件查询 
	 * @param map  类别  
	 * @return
	 */
	public List<BingZhuangTuDuiXiang>  YiFenLeiChaXunYiMaiCnagKuGeShu(Map<String,Object>map);
	
	
	/**
	 * 查询不同类别未卖仓库个数   warehouse  和   waerhouseresour 
	 * @param map    
	 * @return
	 */
	public Map<String,Object> YiFenLeiChaXunShuYuCangKuDeShuLiang(Map<String,Object>map);
	
	/**
	 * 查询所有以卖仓库个数 无条件
	 * @param map
	 * @return
	 */
	public Map<String,Object> Count(Map<String,Object>map);
	
	/**
	 * 查询  不同月份以卖仓库个数          一时间顺寻拍        sql语句--》    以时间为条件  在把条数整合成个数 
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> YiYueFenChaXunYiMaiCnagKuGeShu(Map<String,Object>map);
	
	/**
	 * 对月份做统计 一时间顺寻拍  
	 * @param map  
	 * @return  
	 */
	public Map<String,Object>  ChaXunYueFen(Map<String,Object>map);
	
	
	/**
	 *        修改剩余容量    减少  入库所用
	 */
	public int updateJianCangKuRongLiang(Map<String,Object>map);

	/**
	 * 查询所有剩余容量    在  入库检测表显示
	 * @param map
	 * @return
	 */
	public int ChaXunShengYuRongLiang(String uuid);
	
	
	
	/**
	 * 查询所有到期时间   在  入库检测表显示
	 * @param map
	 * @return
	 */
	public Date ChaXunDaoQiTime(String uuid);
	/**
	 *        修改剩余容量    增加  出库所用
	 */
	int updateaddCangKuRongLiang(Map<String, Object> map);

	
	
}
