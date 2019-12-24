package cn.kgc.tangcco.tcbd1017.st.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.st.impl.ShangJiaRuKuServiceImpl;
import cn.kgc.tangcco.tcbd1017.st.pojo.Brand;
import cn.kgc.tangcco.tcbd1017.st.pojo.Jiance;
import cn.kgc.tangcco.tcbd1017.st.pojo.Model;
import cn.kgc.tangcco.tcbd1017.st.pojo.Type;


@WebServlet(urlPatterns =  "/ShangJiRuKuAction.action")
public class ShangJiRuKuAction extends BaseServlet{
	ShangJiaRuKuServiceImpl impl = new ShangJiaRuKuServiceImpl();
	
	private static final long serialVersionUID = -763180325875700140L;
	
	
	public void ChaKanGoods(HttpServletRequest request, HttpServletResponse response) {
		  Map<String, Object > map = new HashMap<String, Object>();
		  //分页
		  String page = request.getParameter("page");
		  String limit = request.getParameter("limit");
		  int page1 = Integer.parseInt(page);
		  int limit1 = Integer.parseInt(limit);
		  PageRang pageRang = new PageRang(page1, limit1);
		  map.put("PageRang", pageRang);
		  
		  //参数
		  map.put("account", request.getParameter("account"));
		  
		  map.put("shop_id", request.getParameter("shop_id"));
		  
		  map.put("goods_id", request.getParameter("goods_id"));
		  map.put("shop_name", request.getParameter("shop_name"));
		  map.put("goods_count", request.getParameter("goods_count"));
		  map.put("goods_w_s_uuid", request.getParameter("goods_w_s_uuid"));
		  map.put("type_name", request.getParameter("type_name"));
		  map.put("brand_name", request.getParameter("brand_name"));
		  map.put("model_name", request.getParameter("model_name"));
		  map.put("model_price", request.getParameter("model_price"));
		  map.put("model_size", request.getParameter("model_size"));
		  
		  Map<String, Object> chaKanGoods = impl.ChaKanGoods(map);
		  printJson(response, chaKanGoods);
		 
		
	}


	public void KaiShiRuKu(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		//获取jiance表数量
		String jiance_comegoodsrecored_counts = request.getParameter("jiance_comegoodsrecored_count");
		int jiance_comegoodsrecored_count = Integer.parseInt(jiance_comegoodsrecored_counts);
		
		//获取jiance表类型id
		String jiance_type_ids = request.getParameter("jiance_type_id");
		int jiance_type_id = Integer.parseInt(jiance_type_ids);
		
		//获取jiance表品牌id
		String jiance_brand_ids = request.getParameter("jiance_brand_id");
		int jiance_brand_id = Integer.parseInt(jiance_brand_ids);
		
		//获取jiance表型号id
		String jiance_model_ids = request.getParameter("jiance_model_id");
		int jiance_model_id = Integer.parseInt(jiance_model_ids);
		
		//获取jiance表商店uuid
		String jiance_shangpuuuid = request.getParameter("jiance_shangpuuuid");
		
		//获取jiance表仓库uuid
		String jiance_cangkuuuid = request.getParameter("jiance_cangkuuuid");
		
		//创建Jiance对象
		Jiance jiance = new Jiance();
		
		//将属性值封装到对象中
		jiance.setJianceComegoodsrecoredCount(jiance_comegoodsrecored_count);
		
		Type y=new Type();
		y.setTypeId(jiance_type_id);
		jiance.setType(y);
		
		Brand b=new Brand();
		b.setBrandId(jiance_brand_id);
		jiance.setBrand(b);
		
		Model m=new Model();
		m.setModelId(jiance_model_id);
		jiance.setModel(m);
		
		jiance.setJianceShangpuuuid(jiance_shangpuuuid);
		
		jiance.setJianceCangkuuuid(jiance_cangkuuuid);
		
		map.put("jiance", jiance);
		
		Map<String, Object> map1 = impl.KaiShiRuKu(map);
		System.out.println(map1.toString());
		printJson(response, map1);
	}
	
	public void ChaXunZhuCe(HttpServletRequest request, HttpServletResponse response) {
	    Map<String, Object > map = new HashMap<String, Object>();
	    //分页
	    String page = request.getParameter("page");
	    String limit = request.getParameter("limit");
	    int page1 = Integer.parseInt(page);
	    int limit1 = Integer.parseInt(limit);
	    PageRang pageRang = new PageRang(page1, limit1);
	    map.put("page", pageRang);
	    
	    //参数
	    map.put("account", request.getParameter("account"));
	    
	    map.put("Shop", request.getParameter("Shop"));
	    
	    map.put("flag", request.getParameter("flag"));
	    map.put("uuid", request.getParameter("uuid"));
	    map.put("chuku_shangpuuuid", request.getParameter("chuku_shangpuuuid"));
	    map.put("cangku", request.getParameter("cangku"));
	    map.put("flag", request.getParameter("flag"));
	    map.put("uuid", request.getParameter("uuid"));
	  
	    
	    Map<String, Object> ChaXunZhuCe = impl.ChaXunZhuCe(map);
	    Map<String, Object> responseMap=new HashMap<String, Object>();
	     responseMap.put("code", 0);
	   responseMap.put("msg", "");
	   List<Jiance> ssJiances=(List<Jiance>) ChaXunZhuCe.get("data");
	   responseMap.put("count",ssJiances.size() );
	   responseMap.put("data", ChaXunZhuCe);
	    printJson(response, responseMap);
	   
	  
	 }
}
