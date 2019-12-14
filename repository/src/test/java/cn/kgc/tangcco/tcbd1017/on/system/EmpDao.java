package cn.kgc.tangcco.tcbd1017.on.system;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月13日 下午3:15:04
* @edition 1.0
* @Description 类描述
*/
public interface EmpDao {
	
	/**
	 * 查询某部门下的员工简略信息列表
	 * @param map			查询条件
	 * @return				该部门下员工的简略信息列表
	 * @throws SQLException	SQLException
	 */
	public abstract List<Emp> selectEmpsWithDept(Map<String,Object> map);
	
	/**
	 * 修改员工信息
	 * @param map
	 * @return
	 */
	int updateEmp(Map<String, Object> map);
	/**
	 *  单个员工的完整信息
	 */
	List<Emp>selectEmp(Map<String, Object> map);
	/**
	 * 修改员工部门
	 */
	int updateEmpDate(Map<String, Object>map);
}
