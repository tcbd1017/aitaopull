package cn.kgc.tangcco.tcbd1017.on.system;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月20日 下午5:03:03
* @edition 1.0
* @Description 类描述
*/
public interface EmpsWithDept {
	
	/**
	 * 查询某部门下的员工简略信息列表
	 * @param map			查询条件
	 * @return				该部门下员工的简略信息列表
	 * @throws SQLException	SQLException
	 */
	public abstract List<Emp> selectEmpsWithDept(Map<String,Object> map);
	
	/**
	 * 部们人总数
	 * @param map
	 * @return
	 */
	int EmpsWithDeptcount(Map<String, Object>map);
}
