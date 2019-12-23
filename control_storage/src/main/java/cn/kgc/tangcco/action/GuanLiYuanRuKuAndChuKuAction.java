package cn.kgc.tangcco.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSON;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.service.GuanLiYuanRuKuAndChuKu;
import cn.kgc.tangcco.service.impl.GuanLiYuanRuKuAndChuKuImpl;
@WebServlet(urlPatterns = "/GuanLiYuanRuKuAndChuKuAction.action")
public class GuanLiYuanRuKuAndChuKuAction extends BaseServlet {
	GuanLiYuanRuKuAndChuKu guanLiYuanRuKuAndChuKu = new GuanLiYuanRuKuAndChuKuImpl();
	private static final long serialVersionUID = -3686623152548391609L;

	public void ChaKanRuKuBiao(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(request.getParameter("page"));
		System.out.println(request.getParameter("limit"));
		map.put("page", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
//		map.put("page", new PageRang(1, 20));
		
		map.put("account", request.getParameter("account"));
		//jiance_shangpuuuid
		map.put("Shop", request.getParameter("Shop"));
		//jiance_cangkuuuid
		map.put("cangku", request.getParameter("cangku"));
		//jiance_flag
		map.put("flag", request.getParameter("flag"));
		//jiance_rukujiluuuid
		map.put("uuid", request.getParameter("uuid"));
		
		//operator
//		map.put("operator", request.getParameter("operator"));
		map.put("chuku_shangpuuuid", request.getParameter("chuku_shangpuuuid"));
		Map<String, Object> chaKanRuKuBiao = guanLiYuanRuKuAndChuKu.ChaKanRuKuBiao(map);
		printJson(response, chaKanRuKuBiao);
	}

	public void UpdateRuKuZhuangTai(HttpServletRequest request, HttpServletResponse response) {
		//
		  Map<String, Object> map = new HashMap<String, Object>();
		  map.put("goods_w_s_uuid", request.getParameter("goods_w_s_uuid"));
		  map.put("goods_model_id", request.getParameter("goods_model_id"));
		  map.put("goods_shop_uuid", request.getParameter("goods_shop_uuid"));
		  map.put("goods_count", request.getParameter("goods_count"));
		  map.put("goods_type_id", request.getParameter("goods_type_id"));
		  map.put("goods_brand_id", request.getParameter("goods_brand_id"));
		  Goods goods=new Goods();
		  
		  Model mo=new Model();
		  String str = map.get("goods_model_id").toString();
		  mo.setModelId(Integer.parseInt(str));
		  
		  goods.setModel(mo);
		  goods.setGoodsWSUuid((String)map.get("goods_w_s_uuid"));
		  map.put("goods",goods);
		  
		  map.put("jiance_rukujiluuuid", request.getParameter("jiance_rukujiluuuid"));
		  Jiance a=new Jiance();
		  a.setJianceRukujiluuuid((String)map.get("jiance_rukujiluuuid"));
		  
		  map.put("jiance",a);
		  map.put("empid", request.getParameter("empid"));
		  System.out.println(request.getParameter("empid")+"jjjjjjjjjjjj");
		  map.put("goods_count", request.getParameter("goods_count"));
		  map.put("zhuangtai", request.getParameter("zhuangtai"));
		  System.out.println(map.get("zhuangtai"));
		  Map<String, Object> updateRuKuZhuangTai = guanLiYuanRuKuAndChuKu.UpdateRuKuZhuangTai(map);
		 
		  printJson(response, updateRuKuZhuangTai);
		  
		 }

	public void ChaKanAllCangKuUUid(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouse_shop_shop_id", request.getParameter("warehouse_shop_shop_id"));
		Map<String, Object> chaKanAllCangKuUUid = guanLiYuanRuKuAndChuKu.ChaKanAllCangKuUUid(map);
		printJson(response, chaKanAllCangKuUUid);
	}

	public void ChaKanShopJianLue(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> chaKanShopJianLue = guanLiYuanRuKuAndChuKu.ChaKanShopJianLue();
		printJson(response, chaKanShopJianLue);
	}

	public void ChaXunCangKuJianLue(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("warehouse_shop_shop_id", request.getParameter("warehouse_shop_shop_id"));
		Map<String, Object> chaXunCangKuJianLue = guanLiYuanRuKuAndChuKu.ChaXunCangKuJianLue(map);
		printJson(response, chaXunCangKuJianLue);
	}

	public void ChaXunYueFenYuMeiYuChuHuoLiang(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> chaXunYueFenYuMeiYuChuHuoLiang = guanLiYuanRuKuAndChuKu.ChaXunYueFenYuMeiYuChuHuoLiang();
		printJson(response, chaXunYueFenYuMeiYuChuHuoLiang);
	}

	public void ChaXunSuoYouChuHuo(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
		map.put("account", request.getParameter("account"));
		
		map.put("Shop", request.getParameter("Shop"));
		map.put("cangku", request.getParameter("cangku"));
		map.put("flag", request.getParameter("flag"));
		map.put("uuid", request.getParameter("uuid"));
		Map<String, Object> chaXunSuoYouChuHuo = guanLiYuanRuKuAndChuKu.ChaXunSuoYouChuHuo(map);
		printJson(response, chaXunSuoYouChuHuo);
	}

	public void XiuGaiChuKuZhuangTai(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_shop_uuid", request.getParameter("goods_shop_uuid"));
		map.put("goods_count", request.getParameter("goods_count"));
		
		Goods goods=new Goods();
		Model mo=new Model();
		mo.setModelId(Integer.parseInt(request.getParameter("goods_model_id")));
		goods.setModel(mo);
		goods.setGoodsWSUuid(request.getParameter("goods_w_s_uuid"));//仓库uuid
		map.put("goods",goods);
		
		map.put("goods_w_s_uuid",request.getParameter("goods_w_s_uuid") );
		
		
		Chuku chuku =new Chuku();
		chuku.setChukuChukujiluuuid(request.getParameter("gogoodsrecord_uuid"));//出库记录
		map.put("chuku", chuku);
		map.put("empid", request.getParameter("empid"));
//		map.put("gogoodsrecord_w_s_uuid", request.getParameter("goods_w_s_uuid"));
		map.put("zhuangtai", request.getParameter("zhuangtai"));
		Map<String, Object> xiuGaiChuKuZhuangTai = guanLiYuanRuKuAndChuKu.XiuGaiChuKuZhuangTai(map);
		printJson(response, xiuGaiChuKuZhuangTai);
	}

	public void ChaXunJinHuoZheXianTu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> chaXunJinHuoZheXianTu = guanLiYuanRuKuAndChuKu.ChaXunJinHuoZheXianTu();
		printJson(response, chaXunJinHuoZheXianTu);
	}

	public void ChaZunJinHuoBingZhuangTu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> chaZunJinHuoBingZhuangTu = guanLiYuanRuKuAndChuKu.ChaZunJinHuoBingZhuangTu();
		printJson(response, chaZunJinHuoBingZhuangTu);
	}

	public void ChaJinHuoJiLuBiao(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageRang", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouseuuid", request.getParameter("warehouseuuid"));
		map.put("shopuuid", request.getParameter("shopuuid"));
		map.put("empname", request.getParameter("empname"));
		map.put("year", request.getParameter("year"));
		map.put("month", request.getParameter("month"));
		Map<String, Object> chaJinHuoJiLuBiao = guanLiYuanRuKuAndChuKu.ChaJinHuoJiLuBiao(map);
		printJson(response, chaJinHuoJiLuBiao);
	}

	public void ChaChuHuoJiLuBiao(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageRang", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouseuuid", request.getParameter("warehouseuuid"));
		map.put("shopuuid", request.getParameter("shopuuid"));
		map.put("empname", request.getParameter("empname"));
		map.put("year", request.getParameter("year"));
		map.put("month", request.getParameter("month"));
		Map<String, Object> chaChuHuoJiLuBiao = guanLiYuanRuKuAndChuKu.ChaChuHuoJiLuBiao(map);
		printJson(response, chaChuHuoJiLuBiao);
	}

}
