package cn.kgc.tangcco.tcbd1017.st.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.st.impl.ShangJiChuKuServiceImpl;
import cn.kgc.tangcco.tcbd1017.st.pojo.Brand;
import cn.kgc.tangcco.tcbd1017.st.pojo.Chuku;
import cn.kgc.tangcco.tcbd1017.st.pojo.Model;
import cn.kgc.tangcco.tcbd1017.st.pojo.Type;


@WebServlet(urlPatterns = "/ShangJiChuKuAction.action")
public class ShangJiChuKuAction extends BaseServlet {

	ShangJiChuKuServiceImpl impl = new ShangJiChuKuServiceImpl();

	private static final long serialVersionUID = -2116972243232647335L;

	public void ChaXunPinPai(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		String brand_type_id = request.getParameter("brand_type_id");
		map.put("type", brand_type_id);
		// map.put("brand_type_id", request.getParameter("brand_type_id"));
		Map<String, Object> chaXunPinPai = impl.ChaXunPinPai(map);
		printJson(response, chaXunPinPai);
	}

	public void ChaXunXingHao(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("model_brand_id", request.getParameter("model_brand_id"));
		Map<String, Object> chaXunXingHao = impl.ChaXunXingHao(map);
		printJson(response, chaXunXingHao);

	}

	public void ShopGaouMaiDeCangKu(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		// 声明一个分页对象 获取当前页 获取每页的记录数
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));
		map.put("warehouse_shop_warehouseuuid", request.getParameter("warehouse_shop_warehouseuuid"));
		Map<String, Object> shopGaouMaiDeCangKu = impl.ShopGaouMaiDeCangKu(map);
		printJson(response, shopGaouMaiDeCangKu);
	}

	public void KaiShiChuKu(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();

		String chuku_gogoodsrecored_counts = request.getParameter("chuku_gogoodsrecored_count");
		int chuku_gogoodsrecored_count = Integer.parseInt(chuku_gogoodsrecored_counts);

		String chuku_type_ids = request.getParameter("chuku_type_id");
		int chuku_type_id = Integer.parseInt(chuku_type_ids);

		String chuku_brand_ids = request.getParameter("chuku_brand_id");
		int chuku_brand_id = Integer.parseInt(chuku_brand_ids);

		String chuku_model_ids = request.getParameter("chuku_model_id");
		int chuku_model_id = Integer.parseInt(chuku_model_ids);

		String chuku_shangpuuuid = request.getParameter("chuku_shangpuuuid");

		String chuku_cangkuuuid = request.getParameter("chuku_cangkuuuid");

		// 创建chuku对象
		Chuku chuku = new Chuku();

		// 将属性值封装到对象中
		Type y = new Type();
		y.setTypeId(chuku_type_id);
		chuku.setType(y);

		Brand b = new Brand();
		b.setBrandId(chuku_brand_id);
		chuku.setBrand(b);

		Model m = new Model();
		m.setModelId(chuku_model_id);
		chuku.setModel(m);

		chuku.setChukuGogoodsrecoredCount(chuku_gogoodsrecored_count);

		chuku.setChukuShangpuuuid(chuku_shangpuuuid);

		chuku.setChukuCangkuuuid(chuku_cangkuuuid);

		map.put("Chuku", chuku);

		Map<String, Object> map1 = impl.KaiShiChuKu(map);
		printJson(response, map1);
	}

	public void ChaXunLeiXing(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("brand_type_id", request.getParameter("brand_type_id"));
		Map<String, Object> ChaXunLeiXing = impl.ChaXunLeiXing();
		printJson(response, ChaXunLeiXing);
	}
	public void selectAllChuku(HttpServletRequest request, HttpServletResponse response) {    

		  Map<String,Object> map = new HashMap<String, Object>();
		  map.put("page", new PageRang(Integer.parseInt(request.getParameter("page")),
		    Integer.parseInt(request.getParameter("limit"))));
		  map.put("account", request.getParameter("account"));
		  map.put("Shop", request.getParameter("Shop"));
		  map.put("flag", request.getParameter("flag"));
		  map.put("uuid", request.getParameter("uuid"));
		  map.put("chuku_shangpuuuid", request.getParameter("chuku_shangpuuuid"));
		  map.put("cangku", request.getParameter("cangku"));
		   
		  Map<String, Object> selectAllChuku = impl.selectAllChuku(map);
		  Map<String, Object> responseMap = new HashMap<String, Object>();
		  List<Chuku> list = (List<Chuku>) selectAllChuku.get("data");
		  responseMap.put("code", 0);
		  responseMap.put("msg", "");
		  responseMap.put("count", list.size());
		  responseMap.put("data", list);

		  // 响应请求
		  printJson(response, responseMap);
		 }
}
