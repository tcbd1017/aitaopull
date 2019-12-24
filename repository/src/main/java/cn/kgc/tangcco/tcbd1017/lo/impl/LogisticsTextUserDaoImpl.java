package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextUserDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;

public class LogisticsTextUserDaoImpl implements LogisticsTextUserDao{

	@Override
	public int addTextAndUser(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder("INSERT INTO logistics_text_user(logistics_text_uuid,logistics_user_id)VALUES(?,?)");
		Object[] params= {map.get("logistics_text_uuid"),map.get("logistics_user_id")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}
}
