
package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsLogisticsStatus;


public class LogisticsDaoImpl implements LogisticsDao{

	@Override
	public List selectLogisticsStatusByLogisticsId(Map<String, Object> map) {
		
		StringBuffer sql =new StringBuffer(" SELECT * FROM logistics_logistics_status  ");
			sql.append("WHERE logistics_uuid= ? ");
			sql.append("ORDER BY logistics_create DESC ");
			Object[] params = {map.get("logistics_uuid")};
			return MyDBUtil.QueryRunnerInQuery(LogisticsLogisticsStatus.class, sql.toString(), params);
	}

	@Override
	public int addLogistics(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_logistics( ");
		sql.append(" logistic_uuid,logistics_create_time,logistics_commodity_name,logistics_sender_name,logistics_sender_mobile ");
		sql.append(" logistics_sender_address,logistics_receiver_name,logistics_receiver_mobile,logistics_receiver_address ");
		sql.append(" ) VALUE(?,?,?,?,?,?,?,?,?)  ");
		Object[] params = {map.get("logistic_uuid"),map.get("logistics_create_time"),map.get("logistics_commodity_name"),map.get("logistics_sender_name"),
				map.get("logistics_sender_name"),map.get("logistics_sender_mobile"),map.get("logistics_sender_address"),map.get("logistics_receiver_name"),
				map.get("logistics_receiver_mobile"),map.get("logistics_receiver_address")
		};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public int addLogisticsStatus(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_logistics_status(logistics_status, logistics_uuid, logistics_company) VALUE(?,?,?)  ");
		Object[] params = {map.get("logistics_status"),map.get("logistics_uuid"),map.get("logistics_company")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public List selectLogisticsIdByLogistics(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" SELECT * FROM logistics_logistics WHERE logistic_uuid=?  ");
		Object[] params = {map.get("logistic_uuid")};
		return MyDBUtil.QueryRunnerInQuery(Logistics.class, sql.toString(), params);
		
	}

}
