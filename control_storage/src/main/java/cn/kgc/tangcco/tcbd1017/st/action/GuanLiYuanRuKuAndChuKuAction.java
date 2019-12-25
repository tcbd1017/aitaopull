package cn.kgc.tangcco.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
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
		
		String parameter = request.getParameter("data");
		
		String substring = parameter.substring(1,parameter.length()-1);
		System.out.println(substring);
		
		JSONObject parseObject = JSONObject.parseObject(substring);
		JSONObject parseObjecta= (JSONObject) parseObject.get("brand");
		int s =  Integer.parseInt(parseObjecta.get("brandId").toString());
		System.out.println("品牌"+s);
		
		
		
		String jianceCangkuuuid= (String) parseObject.get("jianceCangkuuuid");
		System.out.println("仓库uuid"+jianceCangkuuuid.toString());
		
		
		int parseInt= (Integer) parseObject.get("jianceComegoodsrecoredCount");
		//int parseInt = Integer.parseInt(jianceComegoodsrecoredCount);
		System.out.println("入库数量"+parseInt);
		
		int jianceFlag=3;
		
		String parameter2 = request.getParameter("zhuangtai");
		
		System.out.println(parameter2);
		
		int jiancePrice=(Integer)parseObject.get("jiancePrice");
		System.out.println(jiancePrice);
		
		String jianceRukujiluuuid=(String)parseObject.get("jianceRukujiluuuid");
		System.out.println(jianceRukujiluuuid);
		
		String jianceShangpuuuid=(String)parseObject.get("jianceShangpuuuid");
		System.out.println(jianceShangpuuuid);
		
		JSONObject model= (JSONObject) parseObject.get("model");
		int modelId =  Integer.parseInt(model.get("modelId").toString());
		System.out.println(""+modelId);
		
		
		JSONObject type= (JSONObject) parseObject.get("type");
		int typeId =  Integer.parseInt(type.get("typeId").toString());
		System.out.println(""+typeId);
		 Map<String, Object> map = new HashMap<String, Object>();
		  map.put("goods_w_s_uuid", jianceCangkuuuid);
		  System.out.println("   sds"+request.getParameter("goods_w_s_uuid"));
		  map.put("goods_model_id", modelId);
		  map.put("goods_shop_uuid", jianceShangpuuuid);
		  map.put("goods_count", parseInt);
		  map.put("goods_type_id",typeId);
		  map.put("goods_brand_id", s);
		  Goods goods=new Goods();
		  Model mo=new Model();
		  String str = map.get("goods_model_id").toString();
		  mo.setModelId(Integer.parseInt(str));
		  goods.setModel(mo);
		  goods.setGoodsWSUuid((String)map.get("goods_w_s_uuid"));
		  map.put("goods",goods);
		  
		  map.put("jiance_rukujiluuuid", jianceRukujiluuuid);
		  Jiance a=new Jiance();
		  a.setJianceRukujiluuuid((String)map.get("jiance_rukujiluuuid"));
		  map.put("jiance",a);
		  map.put("empid", 1);
		  map.put("zhuangtai",Integer.parseInt(parameter2));
		  System.out.println(map.get("zhuangtai"));
		  Map<String, Object> updateRuKuZhuangTai = guanLiYuanRuKuAndChuKu.UpdateRuKuZhuangTai(map);
		  printJson(response, updateRuKuZhuangTai);
	}
	
		public void ChaKanAllCangKuUUid(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouse_shop_shop_id",1 );
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> chaXunYueFenYuMeiYuChuHuoLiang = guanLiYuanRuKuAndChuKu.ChaXunYueFenYuMeiYuChuHuoLiang();
		List<RecordMonthAndSum>list=(List<RecordMonthAndSum>) chaXunYueFenYuMeiYuChuHuoLiang.get("data");
		int aa[]=new int[list.size()];
		int i=0;
		for (RecordMonthAndSum a : list) {
			aa[i]=a.getSum();
			System.out.println(aa[i]);
			i++;
		}
		int []bb=new int[list.size()];
		int i2=0;
		for (RecordMonthAndSum s : list) {
			bb[i2]=Integer.parseInt(s.getMonth());
			System.out.println( bb[i2]);
			i2++;
		}
		map.put("yue", bb);
		map.put("count",aa);
		
		printJson(response, map);
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
		String parameter = request.getParameter("data");
		String substring = parameter.substring(1,parameter.length()-1);
		System.out.println(substring);
		
		
		JSONObject parseObject = JSONObject.parseObject(substring);
		JSONObject parseObjecta= (JSONObject) parseObject.get("brand");
		int s =  Integer.parseInt(parseObjecta.get("brandId").toString());
		System.out.println("品牌"+s);
		
		String chukuCangkuuuid= (String) parseObject.get("chukuCangkuuuid");
		System.out.println("仓库uuid"+chukuCangkuuuid);
		
		String chukuChukujiluuuid= (String) parseObject.get("chukuChukujiluuuid");
		System.out.println("出库uuid"+chukuChukujiluuuid);
		
		int  chukuFlag=1;
		String zt = request.getParameter("zhuangtai");
		
		
		int chukuGogoodsrecoredCount= (Integer) parseObject.get("chukuGogoodsrecoredCount");
		System.out.println("出库数量"+chukuGogoodsrecoredCount);
		
		String chukuShangpuuuid= (String) parseObject.get("chukuShangpuuuid");
		System.out.println("商铺uuid"+chukuShangpuuuid);
		
		
		JSONObject goods1= (JSONObject) parseObject.get("goods");
		int good =(Integer)goods1.get("count");
		System.out.println("库存数量"+good);
		
		JSONObject model= (JSONObject) parseObject.get("model");
		int modelId =(Integer)model.get("modelId");
		System.out.println("类型id"+modelId);
		
		JSONObject type= (JSONObject) parseObject.get("type");
		int typeId =(Integer)type.get("typeId");
		System.out.println("类别id"+typeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_shop_uuid", chukuShangpuuuid);
		map.put("goods_count", good);
		
		Goods goods=new Goods();
		Model mo=new Model();
		mo.setModelId(modelId);
		goods.setModel(mo);
		goods.setGoodsWSUuid(chukuCangkuuuid);//仓库uuid
		
		map.put("goods",goods);
		
		map.put("goods_w_s_uuid", chukuCangkuuuid);
		
		
		Chuku chuku =new Chuku();
		chuku.setChukuChukujiluuuid(chukuChukujiluuuid);//出库记录
		map.put("chuku", chuku);
		map.put("empid", 1);
		map.put("gogoodsrecord_w_s_uuid",chukuCangkuuuid);
		map.put("zhuangtai", Integer.parseInt(zt));
		Map<String, Object> xiuGaiChuKuZhuangTai = guanLiYuanRuKuAndChuKu.XiuGaiChuKuZhuangTai(map);
		printJson(response, xiuGaiChuKuZhuangTai);
	}

	public void ChaXunJinHuoZheXianTu(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> chaXunJinHuoZheXianTu = guanLiYuanRuKuAndChuKu.ChaXunJinHuoZheXianTu();
		List<RecordMonthAndSum>list=(List<RecordMonthAndSum>) chaXunJinHuoZheXianTu.get("data");
		Map<String, Object> map = new HashMap<String, Object>();
		int aa[]=new int[list.size()];
		int i=0;
		for (RecordMonthAndSum a : list) {
			aa[i]=a.getSum();
			System.out.println(aa[i]);
			i++;
		}
		int []bb=new int[list.size()];
		int i2=0;
		for (RecordMonthAndSum s : list) {
			bb[i2]=Integer.parseInt(s.getMonth());
			System.out.println( bb[i2]);
			i2++;
		}
		map.put("yue", bb);
		map.put("count",aa);
		printJson(response, map);
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
