package cn.kgc.tangcco.tcbd1017.on.system;

import java.util.List;

import cn.kgc.tangcco.tcbd1017.on.pojo.Dept;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月13日 下午1:50:28
* @edition 1.0
* @Description 类描述
*/
public interface SelectDeptDao {
	
	/**
	 * 查询所有部门简略信息
	 * @return				返回所有部门简略信息
	 * @throws SQLException	SQLException
	 */
	List<Dept> selectAllDepts(Emp emp);
	/**
	 * 员工所在部门显示
	 * @return				返回某员工所在部门简略信息 
	 * @param	emp			查询条件根据员工的所在部门只能查询本部门的信息
	 * @throws SQLException	SQLException
	 */
	List<Dept> selectDepts(Emp emp);
	
	
	
	
}
