package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.EmpDao;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月24日下午3:48:25 </br>
 **/

public class EmpDaoImpl implements EmpDao {

	@Override
	public EmpAllInfo selectEmp(Emp emp) throws SQLException {

		QueryRunner qr = new QueryRunner();
		StringBuffer sql = new StringBuffer(
				" SELECT e.emp_id,e.emp_uuid,e.emp_name,e.emp_mobile,e.emp_mail,e.emp_creat_time,e.emp_update_time,e.emp_status,d.dept_id,d.dept_name,d.dept_status,dt.deptrole_id,dt.deptrole_name,dt.deptrole_status,dp.deptpower_id,dp.deptpower_level,dp.deptpower_status,em.emprole_id,em.emprole_name,em.emprole_quantitative_limitation,em.emprole_status,epp.empower_id,epp.emppower_level,epp.emppower_status,e.emp_uuid,ei.emp_info_gender,ei.emp_info_idcard,ei.emp_info_idcard_name,ei.emp_info_birthday,ei.emp_info_icon_url,ei.emp_info_address ");
		sql.append(
				" FROM 0301_emp as e,0302_dept as d,030100_emp_and_dept as ed,030202_deptrole as dt, 030201_dept_and_deptrole as dd,030203_deptrole_and_deptpower as dede,030204_deptpower as dp,030101_emp_and_emprole as ee,030102_emprole as em,030103_emprole_and_emppower as emem,030104_emppower as epp,030301_emp_info as ei WHERE 1 = 1 ");
		sql.append(" and e.emp_id = ? ");
		sql.append(" and e.emp_status !=1 ");
		sql.append(" and d.dept_status =2 ");
		sql.append(" and  dp.deptpower_status = 2 ");
		sql.append(" and epp.emppower_status = 2 ");
		sql.append(" and ei.emp_info_status = 2 ");
		sql.append(" and dt.deptrole_status = 2 ");
		
		sql.append(" and e.emp_id= ed.emp_id ");
		sql.append(" and d.dept_id = ed.dept_id ");
		sql.append(" and dd.dept_id = d.dept_id ");
		sql.append(" and dd.deptrole_id =dt.deptrole_id ");
		sql.append(" and dt.deptrole_id =dede.deptrole_id ");
		sql.append(" and dede.deptpower_id = dp.deptpower_id ");
		sql.append(" and e.emp_id = ee.emp_id ");
		sql.append(" and ee.emprole_id = em.emprole_id ");
		sql.append(" and emem.emprole_id=em.emprole_id ");
		sql.append(" and emem.emprole_id = epp.empower_id ");
		sql.append(" and ei.emp_id = e.emp_id ");
		Object[] param = { emp.getEmp_id() };
		EmpAllInfo query = qr.query(BaseDBUtils.getConnection(), sql.toString(), param,new BeanHandler<EmpAllInfo>(EmpAllInfo.class));
		return query;
	}

}
