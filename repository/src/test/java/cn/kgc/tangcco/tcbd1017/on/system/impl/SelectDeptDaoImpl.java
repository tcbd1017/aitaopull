package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.ObjectUtils;


import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Dept;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.DeptDao;
import cn.kgc.tangcco.tcbd1017.on.system.SelectDeptDao;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月13日 下午1:53:55
* @edition 1.0
* @Description 类描述
*/
public class SelectDeptDaoImpl implements SelectDeptDao {
	private static QueryRunner queryRunner = new QueryRunner();
	/**
	 * 根据权限显示员工部门
	 */
	@Override
	public List<Dept> selectAllDepts(Emp emp) {
		StringBuilder sql=new StringBuilder();
		Map<String, Object>map=new HashMap<String, Object>();
		SelectEmpPower selectEmpPower=new  SelectEmpPowerImpl();
		List<Dept> list=new ArrayList<Dept>();
		try {
		map=selectEmpPower.selectEmpPower(emp);
		if (ObjectUtils.isEmpty(map.get("deptPower"))) {
			sql.append(" SELECT 0302_dept.dept_id,0302_dept.dept_name FROM 0302_dept WHERE 1 = 1 ");
			sql.append(" and dept_status =2 ");
		}
		if (ObjectUtils.isEmpty(map.get("deptPower"))&&(int)map.get("deptPower")<=3) {
			sql.append(" and dept_id !=6 ");
		}
		if (ObjectUtils.isEmpty(map.get("deptPower"))&&(int)map.get("deptPower")<3&&ObjectUtils.isEmpty(map.get("empPower"))&&(int)map.get("empPower")==1) {
			sql.append("and dept_id !=2");
		}
			list= queryRunner.query(BaseDBUtils.getConnection(), sql.toString(), new BeanListHandler<Dept>(Dept.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
		/**
		 * 
		 * 员工所在部门显示
		 */
	@Override
	public List<Dept> selectDepts(Emp emp) {
		StringBuilder sql= new StringBuilder(" SELECT 0302_dept.dept_id,0302_dept.dept_name FROM 0302_dept inner JOIN 0301_emp inner JOIN 030100_emp_and_dept on 1 = 1 ");
		sql.append(" and dept_status = 2 ");
		sql.append(" and 0301_emp.emp_uuid = ? ");
		sql.append("and 030100_emp_and_dept.emp_id = 0301_emp.emp_id ");
		sql.append(" and 030100_emp_and_dept.dept_id =0302_dept.dept_id ");
		
		// 参数数组
				Object[] param = { emp.getEmp_uuid() };
				// 从数据源中获取连接
				Connection conn =null;
				// 预编译SQL语句
				PreparedStatement pst = null;
				// 执行查询获取结果集
				ResultSet rs=null;
				List<Dept> list= new ArrayList<Dept>();
				try {
					pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
					conn = BaseDBUtils.getConnection();
					rs = BaseDBUtils.executeQuery(pst, param);
					while (rs.next()) {
						list.add(new Dept(rs.getInt("dept_id"), rs.getString("dept_name")));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		return list;
	}
	

}
