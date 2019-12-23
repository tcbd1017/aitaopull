
package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.MoneyDao;
import cn.kgc.tangcco.tcbd1017.lo.MoneyService;
import cn.kgc.tangcco.tcbd1017.lo.UserAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.impl.MoneyDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.impl.UserAndLogisticsDaoImpl;

/**
 * 
 * @author CUI
 *
 */
public class MoneyServiceImpl implements MoneyService {

	UserAndLogisticsDao uald=new UserAndLogisticsDaoImpl();
	MoneyDao md=new MoneyDaoImpl();
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

	@Override
	public Map<String, Object> payByMoneyId(Map<String, Object> map) {
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		
		int payMoney = md.payMoney(map);
		if(payMoney>0) {
			info.put("status", "success");
		}
		return info;
		
	}

}
