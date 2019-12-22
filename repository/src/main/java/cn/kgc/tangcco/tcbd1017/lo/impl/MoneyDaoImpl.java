package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.MoneyDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;

public class MoneyDaoImpl implements MoneyDao{
	@Override
	public int addMoney(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_money(money_uuid, money, money_time) ");
		sql.append("  VALUE( ? , ? , ? )  ");
		Object[] params = {map.get("money_uuid"),map.get("money"),map.get("money_time")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public int payMoney(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer(" UPDATE logistics_money SET money_status_id='2' ");
		sql.append(" WHERE money_uuid=? ");
		Object[] params = {map.get("money_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

}
