package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.TypeDao;
import cn.kgc.tangcco.tcbd1017.lo.TypeService;
import cn.kgc.tangcco.tcbd1017.lo.impl.TypeDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsType;


public class TypeServiceImpl implements TypeService{

	TypeDao td=new TypeDaoImpl();
	@Override
	public Map<String, Object> selectType(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		List<LogisticsType> selectType = td.selectType(map);
		if(selectType!=null&&selectType.size()>0) {
			info.put("data", selectType);
			info.put("status", "success");
		}
		
		return info;
	}

	
}
