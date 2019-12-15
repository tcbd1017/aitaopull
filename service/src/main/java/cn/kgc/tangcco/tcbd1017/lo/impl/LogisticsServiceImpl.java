package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.LogisticsService;
import cn.kgc.tangcco.tcbd1017.lo.MoneyAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.MoneyDao;
import cn.kgc.tangcco.tcbd1017.lo.UserAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SuiJi;
 

public class LogisticsServiceImpl implements LogisticsService {

	
	LogisticsDao ld= new LogisticsDaoImpl();
	UserAndLogisticsDao uld =new UserAndLogisticsDaoImpl();
	MoneyDao md= new MoneyDaoImpl();
	MoneyAndLogisticsDao mld=new MoneyAndLogisticsDaoImpl();
	
	@Override
	public Map<String, Object> selectLogisticsByLogisticsId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		List selectLogisticsStatusByLogisticsId = ld.selectLogisticsStatusByLogisticsId(map);
		if(selectLogisticsStatusByLogisticsId!=null&&selectLogisticsStatusByLogisticsId.size()>0) {
			info.put("data", selectLogisticsStatusByLogisticsId);
			info.put("status", "success");
		}
		
		return info;
	}

	@Override
	public Map<String, Object> selectLogisticsByUserId(Map<String, Object> map) {
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		List selectLogisticsIdByUserId = uld.selectLogisticsIdByUserId(map);
		if(selectLogisticsIdByUserId!=null&&selectLogisticsIdByUserId.size()>0) {
			info.put("data", selectLogisticsIdByUserId);
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> addLogistics(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		String  logistic_uuid= SuiJi.getRandomNumber(15);
		map.put("logistic_uuid", logistic_uuid);
		int addLogistics = ld.addLogistics(map);
		if(addLogistics>0) {
			String money_uuid = SuiJi.getRandomNumber(16);
			map.put("money_uuid", money_uuid);
			int addMoney = md.addMoney(map);
			int addMoneyAndLogistics = mld.addMoneyAndLogistics(map);
			int addUseridAndLogisticsId = uld.addUseridAndLogisticsId(map);
			if(addMoney>0&&addMoneyAndLogistics>0&&addUseridAndLogisticsId>0) {
				info.put("logistic_uuid", logistic_uuid);
				info.put("status", "success");
			}
		}
		return info;
		
	}

}
