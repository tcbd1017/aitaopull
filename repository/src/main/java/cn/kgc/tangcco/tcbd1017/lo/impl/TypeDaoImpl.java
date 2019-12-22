package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.TypeDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsType;

public class TypeDaoImpl implements TypeDao{

	@Override
	public List<LogisticsType> selectType(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer(" SELECT * FROM logistics_type ");
		return MyDBUtil.QueryRunnerInQuery(LogisticsType.class, sql.toString());
	}
}
