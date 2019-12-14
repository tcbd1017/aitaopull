package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.Laypage;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.EmpDao;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;


/**
* @author 许佳瑞
* @version 创建时间：2019年12月13日 下午3:46:21
* @edition 1.0
* @Description 类描述
*/
public class EmpDaoImp implements EmpDao {

	/**
	 * rbac权限下部门下员工的简略信息
	 */
	@Override
	public List<Emp> selectEmpsWithDept(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" SELECT e.emp_name,e.emp_mobile,e.emp_mail,e.emp_creat_time,e.emp_update_time FROM 0301_emp as e ,0302_dept as d,030100_emp_and_dept as ed,030101_emp_and_emprole as ee,030102_emprole as el,030103_emprole_and_emppower as pp , 030104_emppower as ep WHERE 1 = 1 ");
		sql.append(" and e.emp_status != 1 ");
		sql.append(" and d.dept_id = ? ");
		sql.append(" and ed.dept_id = d.dept_id ");
		sql.append(" and ed.emp_id =e.emp_id ");
		sql.append(" and ee.emp_id= e.emp_id ");
		sql.append(" and ee.emprole_id = el.emprole_id ");
		sql.append(" and pp.emprole_id=el.emprole_id ");
		sql.append(" and pp.emppower_id=ep.empower_id ");
		Map<String, Object>mapPower=new HashMap<String, Object>();
		SelectEmpPower selectEmpPower = new SelectEmpPowerImpl();
		Emp emp =(Emp) map.get("emp");
		try {
			mapPower=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (ObjectUtils.isEmpty(mapPower.get("empPower"))&&(int)mapPower.get("empPower")==1) {
			sql.append(" and ep.emppower_level <= 1 ");
		}
		if (ObjectUtils.isEmpty(mapPower.get("empPower"))&&(int)mapPower.get("empPower")==2) {
			sql.append(" and ep.emppower_level <= 2 ");
		}
		if (ObjectUtils.isEmpty(mapPower.get("empPower"))&&(int)mapPower.get("empPower")==3) {
			sql.append(" and ep.emppower_level <= 3 ");
		}
		Object param[]= { map.get("dept_id")};
		Connection conn =null;
		PreparedStatement pst=null;
		 ResultSet rs=null;
		 List<Emp> list=null;
		try {
			 conn = BaseDBUtils.getConnection();
			 pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (Laypage) map.get("laypage"));
			 rs = BaseDBUtils.executeQuery(pst, param);
				Emp emp2;
				if (rs.first()) {
					// 如果结果集中第一个位置数据则指针前移一位
					rs.previous();
					// 如果结果集中存在数据则实例化集合用于存储对象
					list = new ArrayList<Emp>();
					// 遍历结果集
					while (rs.next()) {
						// 实例化员工对象用于存储员工信息
						emp2 = new Emp();
						emp2.setEmp_name(rs.getString("emp_name"));
						emp2.setEmp_mobile(rs.getString("emp_mobile"));
						emp2.setEmp_mail(rs.getString("emp_mail"));
						emp2.setEmp_creat_time(rs.getDate("emp_creat_time"));
						emp2.setEmp_update_time(rs.getDate("emp_update_time"));
						// 将赋值后的对象存储到集合中
						list.add(emp2);
					}
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 修改员工信息
	 * 
	 */
	@Override
	public int updateEmp(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" UPDATE 0301_emp set emp_update_time=now(), ");
		List<Object>list =new ArrayList<Object>();
		if (map != null&&map.size()>0) {
			if (ObjectUtils.isEmpty(map.get("emp_name"))) {
				sql.append(" emp_name = ?,");
				list.add(map.get("emp_name"));
			}
			if (ObjectUtils.isEmpty(map.get("emp_mobile"))) {
				sql.append(" emp_mobile =?,");
				list.add(map.get("emp_mobile"));
			}
			if (ObjectUtils.isEmpty(map.get("emp_mail"))) {
				sql.append(" emp_mail =? ,");
				list.add(map.get("emp_mail"));
			}
			if (ObjectUtils.isEmpty(map.get("0301_emp.emp_status"))&&(int)map.get("empPower")>2||(int)map.get("deptPower")==3) {
				sql.append(" 0301_emp.emp_status = ? ");
			}
		}
		sql.append(" where 1 = 1 ");
		sql.append(" and 0301_emp.emp_status != 1 ");
		Connection conn =null;
		PreparedStatement pst=null;
		int rs=0;
		try {
			conn=BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			 rs = BaseDBUtils.executeUpdate(pst);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
		/**
		 * 
		 * 取出单个员工完整信息
		 */
	@Override
	public List<Emp> selectEmp(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" SELECT 0301_emp.emp_id,0301_emp.emp_uuid,0301_emp.emp_mobile ,0301_emp.emp_mail,0301_emp.emp_creat_time,0301_emp.emp_update_time,0301_emp.emp_status FROM 0301_emp  WHERE  1 = 1 ");
		sql.append(" and 0301_emp.emp_status != 1 ");
		sql.append(" and 0301_emp.emp_id= ? ");
		Object param[]= {map.get("emp_id")};
		Connection conn =null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<Emp> list=list=new ArrayList<Emp>();;
		try {
		conn = BaseDBUtils.getConnection();
	     pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	     rs = BaseDBUtils.executeQuery(pst, param);
	     while (rs.next()) {
			list.add( new Emp(rs.getInt("emp_id"), rs.getString("emp_uuid"),rs.getString("emp_name") ,rs.getString("emp_mobile") ,rs.getString("emp_mail") ,rs.getDate("emp_creat_time"),rs.getDate("emp_update_time"),rs.getInt("emp_status")));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
		/**
		 * 修改员工的部门
		 */
		@Override
		public int updateEmpDate(Map<String, Object> map) {
			StringBuilder sql=new StringBuilder(" UPDATE 030100_emp_and_dept set 030100_emp_and_dept.dept_id =? WHERE 1 =1 ");
			sql.append(" and 030100_emp_and_dept.emp_id =? ");
			Object param[]= {map.get("emp_id"),map.get("dept_id")};
			Connection conn =null;
			PreparedStatement pst=null;
			int rs=0;
			try {
				conn = BaseDBUtils.getConnection();
				pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				rs = BaseDBUtils.executeUpdate(pst, param);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return rs;
		}
	
}
