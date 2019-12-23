
package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.Date;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.AddressDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;


public class AddressDaoImpl implements AddressDao{

	@Override
	public int addAddress(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" INSERT INTO logistics_address(logistics_address_nickname,logistics_address_mobile");
		sql.append(" ,logistics_province_id,logistics_city_id, logistics_district_id,logistics_address,logistics_address_uuid");
		sql.append(" ) VALUE(?,?,?,?,?,?,?)  ");
		Object[] params = {map.get("logistics_address_nickname"),map.get("logistics_address_mobile"),
				map.get("logistics_province_id"),map.get("logistics_city_id"),map.get("logistics_district_id"),map.get("logistics_address"),map.get("logistics_address_uuid")};
		System.out.println(new Date()+sql.toString());
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public int updateAddressByAddressId(Map<String, Object> map) {
		StringBuffer sql =new StringBuffer(" UPDATE logistics_address SET ");
		sql.append(" logistics_address_nickname=?, logistics_address_mobile=?, ");
		sql.append(" logistics_province_id=?,logistics_city_id=?,logistics_district_id=?,logistics_address=? ");
		sql.append(" WHERE  logistics_address_uuid=? ");
		Object[] params = {map.get("logistics_address_nickname"),map.get("logistics_address_mobile"),map.get("logistics_province_id"),
					map.get("logistics_city_id"),map.get("logistics_district_id"),map.get("logistics_address"),map.get("logistics_address_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

//	@Override
//	public int deleteAddressByAddress(Map<String, Object> map) {
//		
//		StringBuffer sql =new StringBuffer(" UPDATE logistics_address SET logistics_address_flag=2 ");
//		sql.append(" WHERE logistics_address_uuid=? ");
//		Object[] params = {map.get("logistics_address_uuid")};
//		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
//	}

}
