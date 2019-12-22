package cn.kgc.tangcco.dao;

import java.sql.SQLException;
import java.util.List;

public interface FunctionDao {
	
	/**
	 * 查看功能    根据 操作者的id定
	 * @param map
	 * @return  操作者id
	 * @throws SQLException 
	 */
	public List selectGongNeng( int emp_id) throws SQLException;

	
	
}
