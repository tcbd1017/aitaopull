package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.ShangJiaAndEMpmLogin;
import cn.kgc.tangcco.tcbd1017.st.pojo.Shop;

public class ShangJiaAndEMpmLoginImpl implements ShangJiaAndEMpmLogin{
	ShoploginDaoImpl in=new ShoploginDaoImpl();
	FunctionDaoImpl f=new FunctionDaoImpl();
	ShopDaoImpl ss=new ShopDaoImpl();
	
	@Override
	//商家登录 ShangJiChuKuService loginShop（）如果登录成功 把function表的信息读出来（上面那个方法中已包含）登录成功 
	public Map<String, Object> ShopLogin(String account,String password) {
		Map<String, Object>map2=new HashMap<String,Object>();
		map2.put("shop_account",account );
		map2.put("shop_password", password);
		try {
			map2.put("data",null);
			map2.put("status", "failed");
			List<Object> loginShop = in.loginShop(map2);
			if (loginShop.size()>0) {
				map2.put("data",loginShop);
				map2.put("status", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map2;
	}
	
	@Override
	public Map<String, Object> ShopZhuCe(Map<String, Object> map) {
		Map<String, Object>map2=new HashMap<String,Object>();
		try {
			map2.put("data",null);
			map2.put("status", "failed");
			int insertShop = in.insertShop(map);
			if (insertShop>0) {
				
				map2.put("status", "success");
				List<Shop> selectShopXiangQing = ss.selectShopXiangQing(map);
				map2.put("data",selectShopXiangQing);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public Map<String, Object> XiuGaiMiMa(Map<String, Object> map) {
		Map<String,Object>map1=new HashMap<>();
		map1.put("status","failed");
		map1.put("data",null );
		int updateMiMa = in.updateMiMa(map);
		if(updateMiMa>0) {
			map1.put("status", "success");
		}		
		return map1;
	}

	@Override
	public Map<String, Object> EmpLogin(String account,String password) {
		Map<String, Object>map2=new HashMap<String,Object>();
		map2.put("emp_account",account );
		map2.put("emp_password", password);
		try {
			map2.put("data",null);
			map2.put("status", "failed");
			List<Object> loginShop = in.empLogin(map2);
			if (loginShop.size()>0) {
				map2.put("data",loginShop);
				map2.put("status", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map2;
		
	}


	
}
