package cn.kgc.tangcco.service;

import java.util.Map;

public interface GuanLiYuanRuKuAndChuKu {
	
	
	/**
	 *   (出货员和管理员，商铺都可查看)     
	 * 传入account 做来判断
	 * 管理员查询 入库表 观察货物状态  确认是否可以入库
	 * 商家也可用这个方法    
	 *   JianCeDaoImpl  selectZhuCe（）                                         还得查询全部个数  分页用
	 * @param map 
	 * @return 
	 */
	public Map<String,Object> ChaKanRuKuBiao(Map<String,Object>map);
	
	/**
	 *   (出货员和管理员都可操作)
	 * 修改入库状态（如果满足提示可以入库 点击入库按钮
					判断  GoodsDaoImpl  selectHuoWu（） 返回值  如果是true 直接添加   反之  修改数量
					如果没有先在goods插  有的话修改数据 
					并且 在 入库记录表添加一条数据  传入入库表的信息
					并且  仓库容量减少  传入仓库uuid  和
					
					该减少的数字）
	 * @param map
	 * @return
	 */
	public Map<String,Object> UpdateRuKuZhuangTai(Map<String,Object>map);
	
	
	/**
	 *   查询所有仓库简略（下拉框）                             chakanxiangqingAllCangKu
	 */
	public Map<String,Object> ChaKanAllCangKuUUid(Map<String,Object>map);
	

	/**
	 * 查询所有商铺的简略，包括id  uuid（在1处的下拉框所用）
	 * @param map    ShopDao  selectShop  
	 * @return
	 */
	public Map<String,Object> ChaKanShopJianLue();
	
	
	/**
	 * 查询 所有仓库的简略  包括仓库 uuid id （在1处的下拉框所用）   可以按商铺id查自家仓库  
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunCangKuJianLue(Map<String,Object>map);
	
	
//	/**
//	 * 按入库订单编号 精确查询         
//	 * @param map
//	 * @return
//	 */
//	public Map<String,Object> RuKuJingQUeChaXun(Map<String,Object>map);
	
	
	
	/**
	 * 查询月份 与 每月的 出货量                           
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunYueFenYuMeiYuChuHuoLiang();

	
	
	/**
	 * 查询类别  与  每种类别的 出货量 
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunLeiBieYuLeiBieChuHuoLiang();

	/**
	 * 出货员/管理员  查询 出库表的所有  包括 所有自家商品的剩余库存  ChuKuDaoImpl  selectChuKu（）  以确保库存充足 可以出库   分页 
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunSuoYouChuHuo(Map<String,Object>map);
	
	/**
	 * 管理员/出库员  修改 出库货物的状态 在前台判断 库存是否充足   
					如果满足提示可以出库 点击出库按钮
					如果没有先在goods插  有的话修改数据 
					在出库记录添加一条记录    传入出库表的信息
					并且  仓库容量增加 传入仓库uuid  和 该增加的数字
	 * @param map
	 * @return
	 */
	public Map<String,Object> XiuGaiChuKuZhuangTai(Map<String,Object>map);
	
	/**
	 * 查询月份 与 每月的 进货量
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunJinHuoZheXianTu();
	
	/**
	 * 查询类别  与  每种类别的 进货量 
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaZunJinHuoBingZhuangTu();

	

	/**
	 * 查询 进货 记录表  
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaJinHuoJiLuBiao(Map<String,Object>map);

	/**
	 * 查询 出货 记录表  
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaChuHuoJiLuBiao(Map<String,Object>map);
	
}
