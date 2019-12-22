package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.PersonnelMattersDao;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月14日 下午7:36:38
* @edition 1.0
* @Description 类描述
*/
public class PersonnelMattersDaoImpl implements PersonnelMattersDao {
	/**
	 * 更改员工状态(员工离职)
	 */
	@Override
	public int delecteandupdateEmp(Map<String, Object> map) {
		
		/**
		 *  操作员的信息在传过来的map里边
		 */
		StringBuilder sql=new StringBuilder(" UPDATE 0301_emp set emp_update_time=now()");
		List<Object>list =new ArrayList<Object>();
		/**
		 * 被操作人的emp.id
		 */
		Emp emp=new Emp();
		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
		emp.setEmp_id((int)map.get("emp_id"));
		/**
		 * 被修改员工的权限
		 */
		Map<String, Object>map1=null;
		try {
			map1=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (map != null&&map.size()>0) {
			if (!ObjectUtils.isEmpty(map.get("emp_name"))) {
				sql.append(" ,emp_name = ?");
				list.add(map.get("emp_name"));
			}
			if (!ObjectUtils.isEmpty(map.get("emp_mobile"))) {
				sql.append(" ,emp_mobile =?");
				list.add(map.get("emp_mobile"));
			}
			if (!ObjectUtils.isEmpty(map.get("emp_mail"))) {
				sql.append(" ,emp_mail =?");
				list.add(map.get("emp_mail"));
			}
			/**
			 * 员工
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_status"))&&(int)map.get("empPower")==1&&(int)map.get("deptPower")==3) {
				
				if (!ObjectUtils.isEmpty(map1.get("empPower"))&&(int)map1.get("empPower")==1&&(int)map1.get("deptPower")<3) {
					System.out.println("1111111111");
					sql.append(" ,0301_emp.emp_status = ? ");
					list.add(map.get("emp_status"));
				}
			}
			/**
			 * 经理
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_status"))&&(int)map.get("empPower")==2&&(int)map.get("deptPower")==3) {
				System.out.println("2222222");
				if (!ObjectUtils.isNotEmpty(map1.get("empPower"))&&(int)map1.get("empPower")<=2&&(int)map1.get("deptPower")<4) {
					sql.append(" ,0301_emp.emp_status = ? ");
					list.add(map.get("emp_status"));
				}
			}
			
			/**
			 * 总经理
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_status"))&&(int)map.get("empPower")==3) {
			   System.out.println("3333333");
				sql.append(" ,0301_emp.emp_status = ? ");
				list.add(map.get("emp_status"));
			}
			
		}
		sql.append(" where 1 = 1 ");
		sql.append(" and 0301_emp.emp_id = ? ");
		sql.append(" and 0301_emp.emp_status != 1 ");
		list.add(map.get("emp_id"));
		Object param[]=list.toArray();
		Connection conn =null;
		PreparedStatement pst=null;
		int rs=0;
		try {
			conn=BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			 rs = BaseDBUtils.executeUpdate(pst,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	
	/**
	 * 修改员工的部门
	 */
	@Override
	public int updateEmpdept(Map<String, Object> map) {
		/**
		 * 操作员的信息在传过来的map里边
		 */
		StringBuilder sql= new StringBuilder();
		/**
		 * 被操作人的emp.id
		 */
		Emp emp=new Emp();
		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
		emp.setEmp_id((int)map.get("emp_id"));
		/**
		 * 被修改员工的权限
		 */
		Map<String, Object>map1=null;
		try {
			map1=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (map!=null&&map.size()>0) {
			/**
			 * 普通员工
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_id"))&&!ObjectUtils.isEmpty(map.get("dept_id"))&&(int)map.get("empPower")==1&&(int)map.get("deptPower")==3) {
				if ((int)map.get("dept_id")<6&&(int)map.get("dept_id")!=2 &&(int)map1.get("empPower")==1&&(int)map1.get("deptPower")<3) {
					sql.append(" UPDATE 030100_emp_and_dept set 030100_emp_and_dept.dept_id = ? WHERE 1 =1 ");
					sql.append(" and 030100_emp_and_dept.emp_id = ? ");
				}
			}
			/**
			 * 经理
			 * 
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_id"))&&!ObjectUtils.isEmpty(map.get("dept_id"))&&(int)map.get("empPower")==2&&(int)map.get("deptPower")==3) {
				if ((int)map.get("dept_id")<6 &&(int)map1.get("empPower")<=2&&(int)map1.get("deptPower")<4) {
					
					sql.append(" UPDATE 030100_emp_and_dept set 030100_emp_and_dept.dept_id = ? WHERE 1 =1 ");
					sql.append(" and 030100_emp_and_dept.emp_id =? ");
				}
			}
			/**
			 * 总经理
			 */
			if (!ObjectUtils.isEmpty(map.get("emp_id"))&&!ObjectUtils.isEmpty(map.get("dept_id"))&&(int)map.get("empPower")==3) {
				
					sql.append(" UPDATE 030100_emp_and_dept set 030100_emp_and_dept.dept_id = ? WHERE 1 =1 ");
					sql.append(" and 030100_emp_and_dept.emp_id = ? ");
			}
		}
		Object param[]= {map.get("emp_id"),map.get("dept_id")};
		System.out.println(param);
		Connection conn =null;
		PreparedStatement pst=null;
		int rs=0;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeUpdate(pst,param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}

//	@Override
//	public int updateEmpJurisdiction(Map<String, Object> map) throws SQLException {
//		StringBuilder sql = new StringBuilder();
//		Emp emp=new Emp();
//		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
//		emp.setEmp_id((int)map.get("emp_id"));
//		/**
//		 * 被修改员工的权限
//		 */
//		Map<String, Object>map1=null;
//		try {
//			map1=selectEmpPower.selectEmpPower(emp);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (map1!=null&&map1.size()>0) {
//			if ((int)map.get("empPower")==3&&(int)map.get("deptPower")==4) {
//			sql.append("UPDATE 030101_emp_and_emprole set 030101_emp_and_emprole.emprole_id= ? WHERE 1 = 1 ");
//			sql.append(" and 030101_emp_and_emprole.emp_id = ? ");
//			}
//		}
//		Object param[]= {map.get("dept_id"),map.get("emp_id")};
//		Connection conn =null;
//		PreparedStatement pst=null;
//		int rs=0;
//		try {
//			conn = BaseDBUtils.getConnection();
//			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
//			rs = BaseDBUtils.executeUpdate(pst,param);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return rs;
//	}

}
