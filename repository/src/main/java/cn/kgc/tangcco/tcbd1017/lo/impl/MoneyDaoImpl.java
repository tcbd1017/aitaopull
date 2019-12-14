package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.MoneyDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
 

public class MoneyDaoImpl implements MoneyDao{
	@Override
	public int addMoney(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_money(money_uuid, money, money_time) ");
		sql.append("  SELECT ? , ? , ? FROM DUAL WHERE money_number  ");
		Object[] params = {map.get("money_uuid"),map.get("money"),map.get("money_time")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

}
