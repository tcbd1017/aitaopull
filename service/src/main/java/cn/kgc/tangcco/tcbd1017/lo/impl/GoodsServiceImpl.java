package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.GoodsDao;
import cn.kgc.tangcco.tcbd1017.lo.GoodsService;
import cn.kgc.tangcco.tcbd1017.lo.LogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.MoneyAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.MoneyDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.date.BaseDateUitls;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SuiJi;
import cn.kgc.tangcco.tcbd1017.lo.impl.GoodsDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.impl.LogisticsDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.impl.MoneyAndLogisticsDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.impl.MoneyDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;

public class GoodsServiceImpl implements GoodsService {
	LogisticsDao ld= new LogisticsDaoImpl();
	private static GoodsDao gd=new GoodsDaoImpl();
	MoneyDao md= new MoneyDaoImpl();
	MoneyAndLogisticsDao mld=new MoneyAndLogisticsDaoImpl();
	
	@Override
	public Map<String, Object> addUseridAndLogisticsId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		//将订单信息添加到订单表
		String  logistics_uuid= SuiJi.getRandomNumber(15);
		map.put("logistics_uuid", logistics_uuid);
		String logistics_create_time = BaseDateUitls.getDateString(new Date());
		map.put("logistics_create_time", logistics_create_time);
		int addLogistics = ld.addLogistics(map);
		if(addLogistics>0) {
			//将信息添加到账单表中
			String money_uuid = SuiJi.getRandomNumber(16);
			map.put("money_uuid", money_uuid);
			//这里在后期应替换为通过重量实际计算出的价格
			map.put("money", "29.9");
			String money_time = BaseDateUitls.getDateString(new Date());
			map.put("money_time", money_time);
			int addMoney = md.addMoney(map);
			//将订单和账单添加到中间表
			int addMoneyAndLogistics = mld.addMoneyAndLogistics(map);
			//将订单和用户添加到中间表
			int addUseridAndLogisticsId = gd.addUseridAndLogisticsId(map);
			if(addMoney>0&&addMoneyAndLogistics>0&&addUseridAndLogisticsId>0) {
				info.put("logistics_uuid", logistics_uuid);
				info.put("status", "success");
			}
		}
		return info;
	}

	@Override
	public Map<String, Object> selectAllLogisticsByUserId(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		//根据传来的uuid查询全部的订单信息
		List<Logistics> selectAllLogisticsByUserId = gd.selectAllLogisticsByUserId(map);
		if(selectAllLogisticsByUserId.size()>0) {
			info.put("data", selectAllLogisticsByUserId);
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> selectLogisticsIdByLogistics(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		
		List selectLogisticsIdByLogistics = ld.selectLogisticsIdByLogistics(map);
		if(selectLogisticsIdByLogistics.size()>0) {
			info.put("data", selectLogisticsIdByLogistics);
			info.put("status", "success");
		}
		return info;
	}

	
}
