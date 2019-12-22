
package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.UserAndAddressDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsAddress;


public class UserAndAddressDaoImpl implements UserAndAddressDao {

	@Override
	public int addAddressidAndUserid(Map<String, Object> map){
		StringBuffer sql =new StringBuffer();
		sql.append(" INSERT into logistics_user_address (user_uuid,logistics_address_uuid) VALUES (?,?)");
		Object[] params = {map.get("user_uuid"),map.get("logistics_address_uuid")};
		System.out.println(new Date()+sql.toString());
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public List<LogisticsAddress> selectAddressByUserid(Map<String, Object> map){
		StringBuffer sql =new StringBuffer();
		sql.append(" SELECT * FROM logistics_address WHERE logistics_address_uuid IN  ");
		sql.append(" (SELECT logistics_address_uuid FROM logistics_user_address WHERE user_uuid = ? AND user_address_flag = 1 ) ");
		Object[] params = { map.get("user_uuid")};
		return MyDBUtil.QueryRunnerInQuery(LogisticsAddress.class, sql.toString(), params);
	}

	@Override
	public int deleteAddressByAddressid(Map<String, Object> map){
		StringBuffer sql =new StringBuffer(" UPDATE logistics_user_address SET user_address_flag=2 WHERE logistics_address_uuid=? AND user_uuid=? ");
		Object[] params = {map.get("logistics_address_uuid"),map.get("user_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

}
