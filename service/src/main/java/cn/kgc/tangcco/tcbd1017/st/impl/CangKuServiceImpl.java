package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.CangKuService;
import cn.kgc.tangcco.tcbd1017.st.warehouseDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.BingZhuangTuDuiXiang;
import cn.kgc.tangcco.tcbd1017.st.pojo.WarehouseShop;



/**
 * @author ZHOUxq<br>
 * @version v1.0<br>
 * @创建时间：2019年12月17日  下午4:35:19<br>
 * @类描述：	
 */
public class CangKuServiceImpl implements CangKuService {
	
	warehouseDao warehouseDaoImpl=new WarehouseDaoImpl();
	warehouse_shopDaoImpl warehouse_shopDao=new warehouse_shopDaoImpl();
	WarehouseresourDaoImpl warehouseresourDao= new WarehouseresourDaoImpl();
	
	//查询所有未卖仓库
	//在页面上只显示价格，图片，类型
	// 点击图片 显示，详情
	@Override
	public Map<String, Object> ChaXunSuoYouWeiMaiCangKu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		
		
		statusMap.put("status", "failed");
		Map<String, Object> selectCangKuLeixing = warehouseDaoImpl.selectCangKuLeixing(map);
		if (selectCangKuLeixing != null) {
			statusMap.put("status", "success");
			statusMap.put("selectCangKuLeixing", selectCangKuLeixing);
		}
		return statusMap;
	}
	//买家查询自家所有仓库的详情
	@Override
	public Map<String, Object> ShopChaXunZiJiaCangKu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		Map<String, Object> selectCangKuGenjuShangJiao = warehouse_shopDao.selectCangKuGenjuShangJiao(map);
		if (selectCangKuGenjuShangJiao != null) {
			statusMap.put("status", "success");
			statusMap.put("selectCangKuLeixing", selectCangKuGenjuShangJiao);
		}
		return statusMap;
	}
	//购买仓库 
	@Override
	public Map<String, Object> GouMaiCnagKu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			Map<String, Object> updateCangKuShnegYuCount = warehouseresourDao.updateCangKuShnegYuCount(map);
			if (updateCangKuShnegYuCount != null) {
//				statusMap.put("status", "failed");
//				statusMap.put("selectCangKuLeixing", updateCangKuShnegYuCount);
				Map<String, Object> goumaiCangKu = warehouse_shopDao.goumaiCangKu(map);
				if (goumaiCangKu!=null && goumaiCangKu.size()>0) {
					statusMap.put("status", "success");
					BaseDBUtils.commitAndClose();
					statusMap.put("goumaiCangKu", goumaiCangKu);
				}
			}
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			return statusMap;
		}
		return statusMap;
	}
	//查询	未卖仓库的总个数
	@Override
	public Map<String, Object> ChaKanWeiMaiGeShu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		int selectWeiMaiZongGeShu = warehouseresourDao.selectWeiMaiZongGeShu();
		if (selectWeiMaiZongGeShu >=0) {
			statusMap.put("status", "success");
			statusMap.put("selectWeiMaiZongGeShu", selectWeiMaiZongGeShu);
		}
		return statusMap;
	}
	//查询 仓库记录表  	每月 买的仓库数量   首页 折线图
	@Override
	public Map<String, Object> CangKuZheXianTu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		List<Map<String, Object>> yiYueFenChaXunYiMaiCnagKuGeShu = warehouse_shopDao.YiYueFenChaXunYiMaiCnagKuGeShu(map);
		if (yiYueFenChaXunYiMaiCnagKuGeShu !=null) {
			statusMap.put("status", "success");
			statusMap.put("selectWeiMaiZongGeShu", yiYueFenChaXunYiMaiCnagKuGeShu);
		}
		return statusMap;
	}
	//查询  仓库记录表  	类型  所购买数量    首页 饼状图
	@Override
	public Map<String, Object> CangKuBingZhuangTu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		List<BingZhuangTuDuiXiang> yiFenLeiChaXunYiMaiCnagKuGeShu = warehouse_shopDao.YiFenLeiChaXunYiMaiCnagKuGeShu(map);
		if (yiFenLeiChaXunYiMaiCnagKuGeShu !=null) {
			statusMap.put("status", "success");
			statusMap.put("yiFenLeiChaXunYiMaiCnagKuGeShu", yiFenLeiChaXunYiMaiCnagKuGeShu);
		}
		return statusMap;
	}
	//查询  仓库记录表   	类型   未卖数量      首页  柱状图
	@Override
	public Map<String, Object> CangKuZhuZHuangTu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		Map<String, Object> yiFenLeiChaXunShuYuCangKuDeShuLiang = warehouse_shopDao.YiFenLeiChaXunShuYuCangKuDeShuLiang(map);
		if (yiFenLeiChaXunShuYuCangKuDeShuLiang !=null) {
			statusMap.put("status", "success");
			statusMap.put("yiFenLeiChaXunYiMaiCnagKuGeShu", yiFenLeiChaXunShuYuCangKuDeShuLiang);
		}
		return statusMap;
	}
	//查询   仓库记录表    所有仓库的详情      首页   表格 
	@Override
	public Map<String, Object> AllCangKu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("code", 0);
		statusMap.put("msg", "");
		statusMap.put("count", 0);
		statusMap.put("data", "");
	
		List<Map<String, Object>> selectCnagKuYuDianPuGuanLianBiao = warehouse_shopDao.selectCnagKuYuDianPuGuanLianBiao(map);
		if (selectCnagKuYuDianPuGuanLianBiao !=null) {
			statusMap.put("data", selectCnagKuYuDianPuGuanLianBiao);
			statusMap.put("count",selectCnagKuYuDianPuGuanLianBiao.size() );
			
		}
		return statusMap;
	}
	//查询仓库所有分类   首页	表格那块的下拉框
	@Override
	public Map<String, Object> AllCangKuFenLei(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		Map<String, Object> selectCangKuLeixing = warehouseDaoImpl.selectCangKuLeixing(map);
		if (selectCangKuLeixing !=null) {
			statusMap.put("status", "success");
			statusMap.put("selectCangKuLeixing", selectCangKuLeixing);
		}
		return statusMap;
	}
	//查询 购买仓库的所有商家的 uuid 和id    首页   表格那块的下拉框
	@Override
	public Map<String, Object> AllGouMaiCangKuShop(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		List<WarehouseShop> chakanxiangqingAllCangKu = warehouse_shopDao.chakanxiangqingAllCangKu(map);
		if (chakanxiangqingAllCangKu !=null) {
			statusMap.put("status", "success");
			statusMap.put("chakanxiangqingAllCangKu", chakanxiangqingAllCangKu);
		}
		return statusMap;
	}
	//查询商家所有购买的仓库详情（在商家查询进度的页面   此处是下拉框）
	@Override
	public Map<String, Object> ChaKanShopGouMaiDeAllCangKu(Map<String, Object> map) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		statusMap.put("status", "failed");
		Map<String, Object> selectCangKuGenjuShangJiao = warehouse_shopDao.selectCangKuGenjuShangJiao(map);
		if (selectCangKuGenjuShangJiao !=null) {
			statusMap.put("status", "success");
			statusMap.put("selectCangKuGenjuShangJiao", selectCangKuGenjuShangJiao);
		}
		return statusMap;
	}

}
