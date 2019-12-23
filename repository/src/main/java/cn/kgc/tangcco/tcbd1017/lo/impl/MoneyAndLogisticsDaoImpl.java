
package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.MoneyAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;


public class MoneyAndLogisticsDaoImpl implements MoneyAndLogisticsDao {

	@Override
	public int updateMoneyByLogisticsId(Map<String, Object> map){
		StringBuffer sql =new StringBuffer(" UPDATE logistics_money set money_status_id = 2 where money_uuid = ");
		sql.append(" (SELECT money_uuid from logistics_money_logistics as a INNER JOIN logistics_logistics as b on a.logistics_uuid = b.?)" );
		Object[] params = {map.get("logistics_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public int addMoneyAndLogistics(Map<String, Object> map) {
		
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_money_logistics(money_uuid,logistics_uuid)  ");
		 sql.append(" SELECT ?,? FROM DUAL ");
		 sql.append(" WHERE ?  NOT IN ( SELECT money_uuid FROM logistics_money_logistics) ");
		 Object[] params = {map.get("money_uuid"),map.get("logistics_uuid"),map.get("money_uuid")};
		 return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

}
