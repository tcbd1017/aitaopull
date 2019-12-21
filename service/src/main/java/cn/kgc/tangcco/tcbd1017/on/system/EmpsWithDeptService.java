package cn.kgc.tangcco.tcbd1017.on.system;
/**
* @author 许佳瑞
* @version 创建时间：2019年12月20日 下午6:39:09
* @edition 1.0
* @Description 类描述
*/


import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

public interface EmpsWithDeptService {
	
	Map<String, Object>queryEmpsWithDept(Map<String, Object>map);
}
