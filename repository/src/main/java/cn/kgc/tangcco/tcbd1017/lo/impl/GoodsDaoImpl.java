package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.GoodsDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;

public class GoodsDaoImpl implements GoodsDao{

	@Override
	public int addUseridAndLogisticsId(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer(" INSERT INTO logistics_goods_logistics(goods_uuid,logistics_uuid) VALUES(?,?) ");
		Object[] params= {map.get("goods_uuid"),map.get("logistics_uuid")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params) ;
	}

	@Override
	public List<Logistics> selectAllLogisticsByUserId(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer(" SELECT logistics_logistics.* ");
		sql.append(" FROM logistics_logistics ");
		sql.append(" JOIN logistics_goods_logistics ");
		sql.append(" ON logistics_logistics.`logistics_uuid`=logistics_goods_logistics.`logistics_uuid` ");
		sql.append(" AND logistics_goods_logistics.`goods_uuid`=? ");	
		Object[] params= {map.get("goods_uuid")};
		return MyDBUtil.QueryRunnerInQuery(Logistics.class, sql.toString(), params);
	}

}
