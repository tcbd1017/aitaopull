package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.face.FaceAdd;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.TransactionManagementHandler;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerRegisterServiceIns;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.FrontBuyerRegisterServiceImpl;
/**
 * Servlet implementation class FrontBuyerRegister
 */
@WebServlet("/FrontBuyerRegister.action")
public class FrontBuyerRegister extends BaseServlet {
	private static final long serialVersionUID = -6493896960403114136L;
	static FrontBuyerRegisterServiceIns proxy=null;
    static {
    	 proxy= (FrontBuyerRegisterServiceIns)new TransactionManagementHandler(new FrontBuyerRegisterServiceImpl()).getProxy();
    }
    //用户注册
    public void Front_Buyer_Register(HttpServletRequest request,HttpServletResponse response,String json) {
    	Map<?, ?> maps= JSON.parseObject(json, Map.class);
    	Map<String,Object> map =new HashMap<>();
    	map.put("buyer_uuid", BaseUUID.generate());
    	map.put("buyer_mobile", maps.get("buyer_mobile"));
    	map.put("buyer_login_account", maps.get("buyer_login_account"));
    	map.put("buyer_login_password",maps.get("buyer_login_password"));
    	map.put("buyer_create_time", new Date());
    	map.put("buyer_login_create_time", new Date());
    	Map<String, Object> buyerRegister = proxy.BuyerRegister(map);
    	printJson(response, buyerRegister);
    }
    //人脸注册
    public void front_Buyer_FaceAdd(HttpServletRequest request, HttpServletResponse response) {
    	String uuid = BaseUUID.generate();
    	String img=request.getParameter("img");
    	System.out.println(img);
    	String result =FaceAdd.add(img,uuid);
    	printJson(response, result);
    }
}
