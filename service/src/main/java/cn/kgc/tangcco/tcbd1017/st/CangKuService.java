package cn.kgc.tangcco.tcbd1017.st;

import java.util.Map;

public interface CangKuService {
	
	/**
	 * 查询所有未卖仓库          WarehouseDaoImpl    selectCangKuLeixing()     
	 *  在买卖仓库页面显示            
	 *  （条件    查询  不同类型 的详细信息                  在页面上只显示价格，图片，类型     点击图片 显示，详情）
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaXunSuoYouWeiMaiCangKu(Map<String,Object>map);
	
	
	
	/**
	 * 买家查询自家所有仓库的详情         warehouse_shopDaoImpl     selectCangKuGenjuShangJiao()
	 *  
	 *在商家已购仓库页面  以表格显示（包括剩余容量等详情） 
	 * @param map
	 * @return
	 */
	public Map<String,Object> ShopChaXunZiJiaCangKu(Map<String,Object>map);
	
	
	/**
	 * 购买仓库                        
	 * （1） 购买仓库 选中要买的仓库 点击购买（每次限定买一个） 调用 warehouseresourDao  updateCangKuShnegYuCount（） 修改对应仓库的剩余个数
	*（2） 在仓库与商铺的记录表加 一条记录          warehouse_shopDaoImpl      goumaiCangKu()
	 * 
	 * @param map
	 * @return
	 */
	public Map<String,Object>GouMaiCnagKu(Map<String,Object>map);
	
	/**
	 *  查询	未卖仓库的总个数 （在首页的头显示）    
	 * @param map
	 * @return
	 */
	public Map<String,Object>ChaKanWeiMaiGeShu(Map<String,Object>map);
	
	/**
	 * 查询 仓库记录表  	每月 买的仓库数量   首页 折线图        warehouse_shopDao          YiYueFenChaXunYiMaiCnagKuGeShu()
	 * @param map
	 * @return
	 */
	public Map<String,Object>CangKuZheXianTu(Map<String,Object>map);

	/**
	 *  查询  仓库记录表  	类型  所购买数量    首页 饼状图       warehouse_shopDao    YiFenLeiChaXunYiMaiCnagKuGeShu() 
	 * @param map  
	 * @return
	 */
	public Map<String,Object>CangKuBingZhuangTu(Map<String,Object>map);
	
	/**
	 * 查询  仓库记录表   	类型   未卖数量      首页  柱状图             warehouse_shopDao             YiFenLeiChaXunShuYuCangKuDeShuLiang
	 * @param map                                                       
	 * @return
	 */
	public Map<String,Object>CangKuZhuZHuangTu(Map<String,Object>map);
	
	/**
	 *  查询   仓库记录表    所有仓库的详情      首页   表格      warehouse_shopDao           selectCnagKuYuDianPuGuanLianBiao
	 * @param map
	 * @return
	 */
	public Map<String,Object>AllCangKu(Map<String,Object>map);

	/**
	 * 查询仓库所有分类     首页	表格那块的下拉框     WarehouseDaoImpl    selectCangKuLeixing（）
	 * @param map
	 * @return
	 */
	public Map<String,Object> AllCangKuFenLei(Map<String,Object>map);

	/**
	 *  查询 购买仓库的所有商家的 uuid 和id    首页   表格那块的下拉框 
	 * @param map
	 * @return
	 */
	public Map<String,Object> AllGouMaiCangKuShop(Map<String,Object>map);
	
	/**
	 * 查询商家所有购买的仓库详情（在商家查询进度的页面   此处是下拉框）
	 */
	public Map<String,Object> ChaKanShopGouMaiDeAllCangKu(Map<String,Object>map);
	
}
