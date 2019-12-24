package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextDao;
import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextService;
import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextUserDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SuiJi;

public class LogisticsTextServiceImpl implements LogisticsTextService{
	LogisticsTextDao logisticstextDao=new LogisticsTextDaoImpl();
	LogisticsTextUserDao ltud=new LogisticsTextUserDaoImpl();
	@Override
	public Map<String, Object> insertText(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		String logistics_text_uuid = SuiJi.getRandomNumber(10);
		map.put("logistics_text_uuid", logistics_text_uuid);
		int addText = logisticstextDao.addText(map);
		if (addText>0) {
			int addTextAndUser = ltud.addTextAndUser(map);
			if(addTextAndUser>0) {
				info.put("status", "success");
			}
		}
		return info;
	}

}
