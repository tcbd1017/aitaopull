package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.DeptDao;

/**
* @author 作者 : 廖斌
* @version 创建时间：Dec 14, 2019 9:33:24 AM
* 	
*/
public class DeptDaoImpl implements DeptDao{
	QueryRunner qrds = new QueryRunner(BaseDBUtils.getDataSource());
	QueryRunner qr = new QueryRunner();
	
	/**
	 *  	查找部门
	 * @author 廖斌
	 * @return map 
	 */
	@Override
	public List<EmpAllInfo> selectByDept(Map map) throws SQLException {
		StringBuilder sql =new StringBuilder(" SELECT e.emp_id,e.emp_uuid,e.emp_name,e.emp_status,e.emp_mobile,e.emp_mail,e.emp_creat_time,e.emp_update_time,  " );
				sql.append(" d.dept_id,d.dept_name,d.dept_creat_time,d.dept_update_time,d.dept_status," );
				sql.append(" dr.deptrole_id,dr.deptrole_name,dr.deptrole_creat_time,dr.deptrole_update_time,dr.deptrole_status, " );
				sql.append(" dp.deptpower_id,dp.deptpower_level,dp.deptpower_creat_time,dp.deptpower_update_time,dp.deptpower_status "); 
				sql.append(" FROM 0301_emp as e INNER JOIN 030100_emp_and_dept as ed INNER JOIN 0302_dept as d INNER JOIN 030201_dept_and_deptrole as ddr INNER JOIN 030202_deptrole as dr  " ); 
				sql.append(" INNER JOIN 030203_deptrole_and_deptpower as ddp INNER JOIN 030204_deptpower as dp " ); 
				sql.append(" WHERE 1=1 " ); 
				sql.append(" AND e.emp_id=ed.emp_id " ); 
				sql.append(" AND ed.dept_id=d.dept_id " ); 
				sql.append(" AND d.dept_id = ddr.dept_id " ); 
				sql.append(" AND ddr.deptrole_id=dr.deptrole_id" ); 
				sql.append(" AND dr.deptrole_id=ddp.deptrole_id " ); 
				sql.append(" AND ddp.deptpower_id = dp.deptpower_id "); 
		return qrds.query(sql.toString(), new BeanListHandler<EmpAllInfo>(EmpAllInfo.class));
	}
	
	
	/**
	 *  	修改部门
	 * @author 廖斌
	 * @return map 
	 */
	@Override
	public int updateByDept(Map map) throws SQLException {
		return 0;
	}
	
	
	/**
	 * 	增加部门
	 * 
	 */	
	@Override
	public int insertByDept(Map map) throws SQLException {
		return 0;
	}
	
}
