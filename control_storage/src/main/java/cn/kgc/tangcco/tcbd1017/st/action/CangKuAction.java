package cn.kgc.tangcco.tcbd1017.st.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.service.CangKuService;
import cn.kgc.tangcco.service.impl.CangKuServiceImpl;

@WebServlet(urlPatterns = "/CangKuAction.action")
public class CangKuAction extends BaseServlet {

	private static final long serialVersionUID = -459492159165477325L;

	CangKuService CangKuService = new CangKuServiceImpl();

	// 查询所有未卖仓库 WarehouseDaoImpl selectCangKuLeixing() 在买卖仓库页面显示（条件 查询 不同类型 的详细信息
	// 在页面上只显示价格，图片，类型 点击图片 显示，详情）
	public void ChaXunSuoYouWeiMaiCangKu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));

		map.put("warehouse_id", request.getParameter("warehouse_id"));

		// 调Service
		Map<String, Object> chaXunSuoYouWeiMaiCangKu = CangKuService.ChaXunSuoYouWeiMaiCangKu(map);

		if (chaXunSuoYouWeiMaiCangKu.get("status").equals("success")) {

			// layui,数据参数格式
			Map<String, Object> responseMap = new HashMap<String, Object>();
			responseMap.put("code", 0);
			responseMap.put("msg", "");
			responseMap.put("count", 100);
			responseMap.put("data", chaXunSuoYouWeiMaiCangKu);

			// 响应请求
			printJson(response, responseMap);
		} else {
			printJson(response, "没有匹配到您查询的数据!");
		}

	}

	// 买家查询自家所有仓库的详情 warehouse_shopDaoImpl selectCangKuGenjuShangJiao() 在商家已购仓库页面
	// 以表格显示（包括剩余容量等详情）
	public void ShopChaXunZiJiaCangKu(HttpServletRequest request, HttpServletResponse response) {

		  // 参数
		  Map<String, Object> map = new HashMap<String, Object>();
		  // 页码,每页条数
		  map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
		    Integer.parseInt(request.getParameter("limit"))));

		  map.put("shop_uuid", request.getParameter("shop_uuid"));

		  // 调Service
		  Map<String, Object> ShopChaXunZiJiaCangKu = CangKuService.ShopChaXunZiJiaCangKu(map);
		  Map<String, Object> mapss = (Map<String, Object>) ShopChaXunZiJiaCangKu.get("selectCangKuLeixing");
		  List<Map<String, Object>> list = (List<Map<String, Object>>) mapss.get("chakanxiangqing");
		  // layui,数据参数格式
		  Map<String, Object> responseMap = new HashMap<String, Object>();
		  responseMap.put("code", 0);
		  responseMap.put("msg", "");
		  responseMap.put("count", list.size());
		  responseMap.put("data", list);

		  // 响应请求
		  printJson(response, responseMap);

		 }

	// 购买仓库（1） 购买仓库 选中要买的仓库 点击购买（每次限定买一个）
	// 调用 warehouseresourDao updateCangKuShnegYuCount（） 修改对应仓库的剩余个数 （2） 在仓库与商铺的记录表加
	// 一条记录 warehouse_shopDaoImpl goumaiCangKu()
	public void GouMaiCnagKu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));

		map.put("number", request.getParameter("number"));
		map.put("warehouse_shop_warehousesize", request.getParameter("warehouse_shop_warehousesize"));
		map.put("warehouse_shop_buyyear", request.getParameter("warehouse_shop_buyyear"));
		map.put("warehouse_shop_shop_id", request.getParameter("warehouse_shop_shop_id"));
		map.put("warehouse_shop_warehouse_id", request.getParameter("warehouse_shop_warehouse_id"));
		map.put("warehouseresour_id", request.getParameter("warehouseresour_id"));

		// 调Service
		Map<String, Object> GouMaiCnagKu = CangKuService.GouMaiCnagKu(map);

		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", GouMaiCnagKu);

		// 响应请求
		printJson(response, responseMap);

	}

	// 查询 未卖仓库的总个数 （在首页的头显示）
	public void ChaKanWeiMaiGeShu(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入个数");
		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(1,1));

		// 调Service
		Map<String, Object> ChaKanWeiMaiGeShu = CangKuService.ChaKanWeiMaiGeShu(map);

		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", ChaKanWeiMaiGeShu);

		// 响应请求
		printJson(response, responseMap);

	}

	// 查询 仓库记录表 每月 买的仓库数量 首页 折线图 warehouse_shopDao YiYueFenChaXunYiMaiCnagKuGeShu()
	public void CangKuZheXianTu(HttpServletRequest request, HttpServletResponse response) {
		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(1,1)
				);
		// 调Service
		Map<String, Object> CangKuZheXianTu = CangKuService.CangKuZheXianTu(map);
		List<Map<String, Object>>list=(List<Map<String, Object>>) CangKuZheXianTu.get("selectWeiMaiZongGeShu");
		int size = list.size();
		int []aa=new int[size];
		int i=0;
		for (Map<String, Object> map2 : list) {
			aa[i]=(int) map2.get("yue");
			System.out.println( map2.get("yue"));
			i++;
		}
		int []bb=new int[size];
		int i2=0;
		for (Map<String, Object> map2 : list) {
			bb[i2]=(int) map2.get("count");
			System.out.println( map2.get("count"));
			i2++;
		}
		
		
		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("yue", aa);
		responseMap.put("count",bb);
		
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		
		//responseMap.put("data", CangKuZheXianTu);
		// 响应请求
		printJson(response, responseMap);
	}

	// 查询 仓库记录表 类型 所购买数量 首页 饼状图 warehouse_shopDao YiFenLeiChaXunYiMaiCnagKuGeShu()
	public void CangKuBingZhuangTu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));

		// 调Service
		Map<String, Object> CangKuBingZhuangTu = CangKuService.CangKuBingZhuangTu(map);

		List<Map<String, Object>>list=(List<Map<String, Object>>) CangKuBingZhuangTu.get("YiFenLeiChaXunShuYuCangKuDeShuLiang");
		int size = list.size();
		int []aa=new int[size];
		int i=0;
		for (Map<String, Object> map2 : list) {
			aa[i]=(int) map2.get("warehouse_name");
			System.out.println( map2.get("warehouse_name"));
			i++;
		}
		int []bb=new int[size];
		int i2=0;
		for (Map<String, Object> map2 : list) {
			bb[i2]=(int) map2.get("warehouseresour_count");
			System.out.println( map2.get("warehouseresour_count"));
			i2++;
		}
		
		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("warehouse_name", aa);
		responseMap.put("warehouseresour_count", bb);
		responseMap.put("data",CangKuBingZhuangTu);
		// 响应请求
		printJson(response, responseMap);

	}

	// 查询 仓库记录表 类型 未卖数量 首页 柱状图 warehouse_shopDao YiFenLeiChaXunShuYuCangKuDeShuLiang
	public void CangKuZhuZHuangTu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));

		// 调Service
		Map<String, Object> CangKuZhuZHuangTu = CangKuService.CangKuZhuZHuangTu(map);

		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", CangKuZhuZHuangTu);

		// 响应请求
		printJson(response, responseMap);

	}

	// 查询 仓库记录表 所有仓库的详情 首页 表格 warehouse_shopDao selectCnagKuYuDianPuGuanLianBiao

	public void AllCangKu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		
		  map.put("PageRang", new
		  PageRang(Integer.parseInt(request.getParameter("page")),
		  Integer.parseInt(request.getParameter("limit"))));
		 
		map.put("shop_uuid", request.getParameter("shop_uuid"));
		map.put("warehouse_shop_warehouse_id", request.getParameter("warehouse_shop_warehouse_id"));
		map.put("month", request.getParameter("month"));
		
		//调Service
		Map<String, Object> AllCangKu = CangKuService.AllCangKu(map);
		// layui,数据参数格式
		// 响应请求
		printJson(response, AllCangKu);
	}
	
	// 查询仓库所有分类 首页 表格那块的下拉框 WarehouseDaoImpl selectCangKuLeixing（）
	public void AllCangKuFenLei(HttpServletRequest request, HttpServletResponse response) {
		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		/*
		 * map.put("PageRang", new
		 * PageRang(Integer.parseInt(request.getParameter("page")),
		 * Integer.parseInt(request.getParameter("limit"))));
		 */
		map.put("warehouse_id", request.getParameter("warehouse_id"));

		// 调Service
		Map<String, Object> AllCangKuFenLei = CangKuService.AllCangKuFenLei(map);

		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", AllCangKuFenLei);

		// 响应请求
		printJson(response, responseMap);
	}

	
	
	// 查询 购买仓库的所有商家的 uuid 和id 首页 表格那块的下拉框
	public void AllGouMaiCangKuShop(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
//		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
//				Integer.parseInt(request.getParameter("limit"))));
		

		String parameter = request.getParameter("warehouse_shop_shop_id");
		map.put("warehouse_shop_shop_id", parameter);
		System.out.println(parameter);
		// 调Service
		Map<String, Object> AllGouMaiCangKuShop = CangKuService.AllGouMaiCangKuShop(map);
		
		
		
		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", AllGouMaiCangKuShop);

		// 响应请求
		printJson(response, responseMap);

	}

	// 查询商家所有购买的仓库详情（在商家查询进度的页面 此处是下拉框）
	public void ChaKanShopGouMaiDeAllCangKu(HttpServletRequest request, HttpServletResponse response) {

		// 参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 页码,每页条数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouse_shop_warehouseuuid", request.getParameter("warehouse_shop_warehouseuuid"));

		// 调Service
		Map<String, Object> ChaKanShopGouMaiDeAllCangKu = CangKuService.ChaKanShopGouMaiDeAllCangKu(map);

		// layui,数据参数格式
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("code", 0);
		responseMap.put("msg", "");
		responseMap.put("count", 100);
		responseMap.put("data", ChaKanShopGouMaiDeAllCangKu);

		// 响应请求
		printJson(response, responseMap);

	}
}
