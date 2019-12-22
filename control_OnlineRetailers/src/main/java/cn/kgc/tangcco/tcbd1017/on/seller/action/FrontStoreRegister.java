package cn.kgc.tangcco.tcbd1017.on.seller.action;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.TransactionManagementHandler;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;

import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreRegisterServiceIns;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.FrontStoreRegisterServiceImpl;
/**
 * 刘煜
 * Servlet implementation class FrontStoreRegister
 */
@WebServlet("/FrontStoreRegister.action")
public class FrontStoreRegister extends BaseServlet {
	private static final long serialVersionUID = 7545024451817377360L;
	static FrontStoreRegisterServiceIns proxy=null;
    static {
    	 proxy= (FrontStoreRegisterServiceIns)new TransactionManagementHandler(new FrontStoreRegisterServiceImpl()).getProxy();
    }
    //用户开店
    public void add_Seller_Register( HttpServletRequest request , HttpServletResponse response,String json) {
    	Map<?, ?> maps= JSON.parseObject(json, Map.class);
    	Map<String,Object> map =new HashMap<>();
    	map.put("buyer_id", maps.get("buyer_id"));
		map.put("seller_uuid", BaseUUID.generate());
		map.put("seller_idcard_token", maps.get("seller_idcard_token"));
		map.put("seller_create_time", new Date());
		map.put("storage_id", BaseUUID.rendem());
		map.put("seller_icon_url",maps.get("seller_icon_url"));
		System.out.println(map.toString());
    	Map<String, Object> buyerRegister = proxy.add_Seller_Register(map);
    	printJson(response, buyerRegister);
    }
    //店铺信息注册()
    public void add_Store_Register(HttpServletRequest request , HttpServletResponse response, String json) {
    	Map<?, ?> maps= JSON.parseObject(json, Map.class);
    	Map<String, Object> map = new HashMap<>();
    	map.put("store_id",maps.get("store_id"));
		map.put("store_create_time", new Date());
		map.put("store_about", maps.get("store_about"));
		map.put("seller_create_time", new Date());
		map.put("store_img",maps.get("store_img"));
		map.put("store_name", maps.get("store_name"));
		Map<String, Object> add_Store_Register = proxy.add_Store_Register(map);
		printJson(response, add_Store_Register);
    }
}
