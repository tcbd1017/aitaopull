package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.Laypage;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.EmpsWithDept;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月20日 下午5:05:03
* @edition 1.0
* @Description 类描述
*/
public class EmpsWithDeptImpl implements EmpsWithDept{

	@Override
	public List<Emp> selectEmpsWithDept(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" SELECT e.emp_id,e.emp_uuid,e.emp_name,e.emp_mobile,e.emp_mail,e.emp_creat_time,e.emp_update_time FROM 0301_emp as e ,0302_dept as d,030100_emp_and_dept as ed WHERE 1 = 1 ");
		sql.append(" and e.emp_status != 1 ");
		sql.append(" and d.dept_id = ? ");
		sql.append(" and ed.dept_id = d.dept_id ");
		sql.append(" and ed.emp_id =e.emp_id ");
		Object param[]= { map.get("dept_id")};
		Connection conn =null;
		PreparedStatement pst=null;
		 ResultSet rs=null;
		 List<Emp> list=null;
		try {
			 conn = BaseDBUtils.getConnection();
			 pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("pr"));
			 rs = BaseDBUtils.executeQuery(pst, param);
				Emp emp = null;
				if (rs.first()) {
					// 如果结果集中第一个位置数据则指针前移一位
					rs.previous();
					// 如果结果集中存在数据则实例化集合用于存储对象
					list = new ArrayList<Emp>();
					// 遍历结果集
					while (rs.next()) {
						// 实例化员工对象用于存储员工信息
						emp = new Emp();
						emp.setEmp_id(rs.getInt("emp_id"));
						emp.setEmp_uuid(rs.getString("emp_uuid"));
						emp.setEmp_name(rs.getString("emp_name"));
						emp.setEmp_mobile(rs.getString("emp_mobile"));
						emp.setEmp_mail(rs.getString("emp_mail"));
						emp.setEmp_creat_time(rs.getDate("emp_creat_time"));
						emp.setEmp_update_time(rs.getDate("emp_update_time"));
						// 将赋值后的对象存储到集合中
						list.add(emp);
					}
				}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int EmpsWithDeptcount(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(" SELECT count(*)as count FROM 0301_emp as e ,0302_dept as d,030100_emp_and_dept as ed WHERE 1 = 1 ");
		sql.append(" and e.emp_status != 1 ");
		sql.append(" and d.dept_id = ? ");
		sql.append(" and ed.dept_id = d.dept_id ");
		sql.append(" and ed.emp_id =e.emp_id ");
		Object param[]= { map.get("dept_id")};
		Connection conn =null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		int count=0;
		 try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		    rs = BaseDBUtils.executeQuery(pst, param);
		    while (rs.next()) {
				count=rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	

}
