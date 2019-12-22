

package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ResultSetUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;

public class LogisticsDaoImpl implements LogisticsDao{

	@Override
	public List selectLogisticsStatusByLogisticsId(Map<String, Object> map) {
		
		StringBuffer sql =new StringBuffer(" SELECT lls.logistics_uuid,ls.`logistic_status_name`,lls.logistics_company,lls.logistics_create,ll.`logistics_receiver_address`,ll.`logistics_sender_address`   ");
			sql.append(" FROM logistics_logistics_status AS lls ");
			sql.append(" JOIN logistics_status AS ls ");
			sql.append(" ON lls.`logistics_status`=ls.`logistics_status_id` ");
			sql.append(" JOIN logistics_logistics AS ll  ");
			sql.append(" ON ll.`logistics_uuid`=lls.`logistics_uuid`  ");
			sql.append(" WHERE lls.logistics_uuid= ? ");
			sql.append(" ORDER BY logistics_create DESC ");
			Object[] params = {map.get("logistics_uuid")};
			PreparedStatement ps = MyDBUtil.getJDBCPreparedStatement(sql.toString());
			return ResultSetUtil.rsRetList(MyDBUtil.JDBCQuery(ps, params));
	}

	@Override
	public int addLogistics(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_logistics( ");
		sql.append(" logistics_uuid,logistics_create_time,logistics_commodity_name,logistics_sender_name,logistics_sender_mobile, ");
		sql.append(" logistics_sender_address,logistics_receiver_name,logistics_receiver_mobile,logistics_receiver_address ");
		sql.append(" ) VALUE(?,?,?,?,?,?,?,?,?)  ");
		Object[] params = {map.get("logistics_uuid"),map.get("logistics_create_time"),map.get("logistics_commodity_name"),map.get("logistics_sender_name"),
				map.get("logistics_sender_mobile"),map.get("logistics_sender_address"),map.get("logistics_receiver_name"),
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
		StringBuffer sql =new StringBuffer(" SELECT * FROM logistics_logistics WHERE logistics_uuid=?  ");
		Object[] params = {map.get("logistics_uuid")};
		return MyDBUtil.QueryRunnerInQuery(Logistics.class, sql.toString(), params);
		
	}

}
