package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;

public class LogisticsTextDaoImpl implements LogisticsTextDao{

	@Override
	public int addText(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder("INSERT INTO logistics_text(logistics_text_uuid,logistics_text_txt)VALUES(?,?)");
		Object[] params= {map.get("logistics_text_uuid"),map.get("logistics_text_txt")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

}
