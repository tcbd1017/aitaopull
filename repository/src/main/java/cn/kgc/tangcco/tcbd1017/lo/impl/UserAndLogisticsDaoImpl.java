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
		sql.append(" insert into logistics_user_logistics(uuid,logistics_uuid) values(?,?)");
		Object[] params= {map.get("uuid"),map.get("logistics_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params) ;
	}

	@Override
	public List<Logistics> selectLogisticsIdByUserId(Map<String, Object> map){
		StringBuffer sql =new StringBuffer();
		sql.append("  SELECT * FROM logistics_logistics WHERE logistics_uuid IN ");
		sql.append(" (SELECT logistics_uuid FROM logistics_user_logistics WHERE user_uuid =?)  ");
		Object[] params= {map.get("user_uuid")};
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

}
