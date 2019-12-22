package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.UserAndLogisticsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ResultSetUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;


public class UserAndLogisticsDaoImpl implements UserAndLogisticsDao {

	@Override
	public int addUseridAndLogisticsId(Map<String, Object> map){
		StringBuffer sql =new StringBuffer();
		sql.append(" insert into logistics_user_logistics(user_uuid,logistics_uuid) values(?,?)");
		Object[] params= {map.get("user_uuid"),map.get("logistics_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params) ;
	}

	@Override
	public List<Logistics> selectLogisticsIdByUserId(Map<String, Object> map){
		StringBuffer sql =new StringBuffer();
		sql.append("  SELECT logistics_logistics.* FROM logistics_logistics ");
		sql.append("  JOIN logistics_user_logistics ");
		sql.append("  WHERE logistics_user_logistics.`logistics_uuid`=logistics_logistics.`logistics_uuid` AND logistics_logistics.logistics_uuid IN ");
		sql.append("  ( ");
		sql.append("  SELECT logistics_uuid FROM logistics_logistics_status ");
		sql.append("  WHERE logistics_uuid NOT IN  ");
		sql.append("  (SELECT logistics_uuid FROM logistics_logistics_status ");
		sql.append("  GROUP BY logistics_uuid ");
		sql.append("  HAVING MAX(logistics_status)>? ");
		sql.append("  ORDER BY logistics_create DESC) ");
		sql.append("  AND logistics_status=? ");
		sql.append("  GROUP BY logistics_uuid ");
		sql.append(" )  ");
		sql.append("  AND user_uuid=?  ");
		Object[] params= {map.get("logistics_status"),map.get("logistics_status"),map.get("user_uuid")};
		return MyDBUtil.QueryRunnerInQuery(Logistics.class, sql.toString(), params);
	}

	@Override
	public List selectMoneyByUserId(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" SELECT l.logistics_uuid, l.logistics_commodity_name,l.logistics_sender_name,m.* ");
		sql.append(" FROM logistics_money AS m  JOIN logistics_money_logistics AS ml  ON m.money_uuid = ml.money_uuid  ");
		sql.append("  JOIN logistics_logistics AS l	ON ml.logistics_uuid=l.logistics_uuid ");
		sql.append(" JOIN logistics_user_logistics AS ul ON l.logistics_uuid=ul.logistics_uuid ");
		sql.append(" AND ul.user_uuid= ? ");
		Object[] params= {map.get("user_uuid")};
		PreparedStatement ps = MyDBUtil.getJDBCPreparedStatement(sql.toString());
		ResultSet rs = MyDBUtil.JDBCQuery(ps, params);
		List<Map<String, Object>> rsRetList = ResultSetUtil.rsRetList(rs);
		return rsRetList;
	}

	@Override
	public List<Logistics> selectLogisticsByStutas(Map<String, Object> map) {
		StringBuffer sql= new StringBuffer("SELECT logistics_logistics.* FROM logistics_logistics ");
		sql.append(" JOIN logistics_logistics_status");
		sql.append(" ON logistics_logistics.`logistics_uuid`=logistics_logistics_status.`logistics_uuid` ");
		sql.append(" JOIN logistics_user_logistics ");
		sql.append(" ON logistics_logistics_status.`logistics_uuid`=logistics_user_logistics.`logistics_uuid` ");
		sql.append(" AND user_uuid=? ");
		sql.append(" GROUP BY logistics_uuid ");
		Object[] params= {map.get("user_uuid")};
		return MyDBUtil.QueryRunnerInQuery(Logistics.class, sql.toString(), params);
	}

}
