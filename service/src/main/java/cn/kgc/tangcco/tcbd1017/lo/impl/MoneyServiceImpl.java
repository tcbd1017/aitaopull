package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.MoneyService;
import cn.kgc.tangcco.tcbd1017.lo.UserAndLogisticsDao;
 

public class MoneyServiceImpl implements MoneyService {

	UserAndLogisticsDao uald=new UserAndLogisticsDaoImpl();
	
	@Override
	public Map<String, Object> selectLogisticsByUserId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		List selectMoneyByUserId = uald.selectMoneyByUserId(map);
		if(selectMoneyByUserId!=null&&selectMoneyByUserId.size()>0) {
			info.put("data", selectMoneyByUserId);
			info.put("status", "success");
		}
		
		return info ;
		
	}

}
