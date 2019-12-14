package cn.kgc.tangcco.tcbd1017.lo.commons.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Cui
 * @version 1.0 时间�?2019�?11�?14�?  下午4:08:00
 */
public class MyDBUtil {
	
	/**
	 * 线程�?
	 */
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>(); 
	
	/**
	 * 获取数据�?
	 */
	private static DataSource ds =new ComboPooledDataSource();
	
	/**
	 * 连接�?
	 *
	 */
	private static QueryRunner qr=new QueryRunner();
	
	/**
	 * 私有构�?�方�?
	 */
	private MyDBUtil() {}
	
	/**
	 * 
	 * @return 数据�?
	 */
	public static DataSource getDataSource() {
		return ds;
	}
	
	/**
	 * 
	 * @return 数据库连�?
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		
		Connection conn = t.get();
		if(conn == null) {
			
			conn=ds.getConnection();
			t.set(conn);
		}
		
		return conn;
	}
	
	/**
	 * 	JDBC原生
	 * 	�?启事�?
	 * @param conn
	 * @throws SQLException
	 */
	public static void startTransaction() throws SQLException {
		
		if(getConnection()!=null)
		getConnection().setAutoCommit(false);
	}
	/**
	 * 	JDBC原生
	 * 	关闭事务
	 * @throws SQLException
	 */
	public static void commit() throws SQLException {
		if(getConnection()!=null)
		getConnection().commit();
		
	}
	/**
	 * JDBC原生
	 * 	回滚事务
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException {
		if(getConnection()!=null)
		getConnection().rollback();
		
	}
	/**
	 * JDBC原生
	 * 	关闭连接
	 * @throws SQLException
	 */
	public static void Close() throws SQLException {
		
		if(getConnection()!=null)
		getConnection().close();
		
		t.remove();
	}
	
	/**
	 * 	提交事务并关闭连�?
	 * 	使用了DBUtils 可以自己看情�?
	 * @throws SQLException
	 */
	public static void commitAndClose() throws SQLException {
		// 提交事务并关闭连�?
		DbUtils.commitAndClose(getConnection());
		// 从当前线程中移除Connection
		t.remove();
	}
	
	/**
	 * 	事务回滚并关闭连�?
	 * 	使用了DBUtils 可以自己看情�?
	 * @throws SQLException
	 */
	public static void RollbackAndclose() throws SQLException {
		// 事务回滚关闭连接
		DbUtils.rollbackAndClose(getConnection());
		// 从当前线程中移除Connection
		t.remove();
	}
	
	
	/**
	 * JDBC原生sql处理
	 * 	第一步，编译sql语句	
	 * 获取PreparedStatement
	 * 
	 * @param conn Connection
	 * @param sql  SQl语句
	 * @return PreparedStatement
	 */
	public static PreparedStatement getJDBCPreparedStatement(String sql){
		// 在该连接中预编译SQL
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = getConnection().prepareStatement(sql);
			return prepareStatement;
		} catch (SQLException e) {
			e.printStackTrace();
			return prepareStatement;
		}
	}
	/**
	 * JDBC原生sql处理
	 * 	第一步，编译sql语句(带分�?)	
	 * 	获取PreparedStatement
	 * 
	 * @param conn Connection
	 * @param sql  SQl语句
	 * @return PreparedStatement
	 */
	public static PreparedStatement getJDBCPreparedStatement(String sql, PageRang pr){
		// 在该连接中预编译SQL
		PreparedStatement prepareStatement=null;
		try {
			sql += " limit " + (pr.getPageNumber() - 1) * pr.getPageSize() + " , " + pr.getPageSize();
			prepareStatement = getConnection().prepareStatement(sql);
			return prepareStatement;
		} catch (SQLException e) {
			e.printStackTrace();
			return prepareStatement;
		}
	}
	
	/**
	 * JDBC原生处理查询结果�?
	 * 	用于查询
	 * @param ps 预编�?
	 * @param param 传�?�参�?
	 * @return ResultSet 返回查询结果�?
	 * @throws SQLException SQl异常
	 */
	public static ResultSet JDBCQuery(PreparedStatement ps, Object... params){
		ResultSet executeQuery=null;
		try {
			
			if (params != null && params.length > 0) {
				// 如果参数数组不是null并且长度大于�?
				for (int i = 0; i < params.length; i++) {
					// 遍历数组元素的�?�并将该值设置到PreparedStatement对象�?
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行查询返回结果�?
			executeQuery = ps.executeQuery();
			return executeQuery;
		} catch (Exception e) {
			e.printStackTrace();
			return executeQuery;
		}
	}
	/**
	 * 原生JDBC更新的结果集
	 * @param ps 预编�?
	 * @param param 传�?�参�?
	 * @return
	 */
	public static int JDBCUpdate(PreparedStatement ps, Object... params){
		 int executeUpdate=0;
		 
		 try {
		if (params != null && params.length > 0) {
			// 如果参数数组不是null并且长度大于�?
			for (int i = 0; i < params.length; i++) {
				// 遍历数组元素的�?�并将该值设置到PreparedStatement对象�?
					ps.setObject(i + 1, params[i]);
				}
			}
		executeUpdate = ps.executeUpdate();
		// 执行查询返回结果�?
		return executeUpdate;
		 } catch (SQLException e) {
				// 执行查询返回结果�?
			 e.printStackTrace();
			 return executeUpdate;
		}
	}

	
	/**
	 * 	更新
	 * @param sql sql语句
	 * @param params 传�?�的参数
	 * @return 影响的数据条�?
	 * @throws SQLException 
	 */
	public static int QueryRunnerInUpdate(String sql,Object... params){
		int update=0;
		/**
		 * 为了便捷�?以直接在工具类中处理了异常，可以删掉
		 */
		
			try {
				update = qr.update(getConnection(), sql, params);
				return update;
			} catch (SQLException e) {
				e.printStackTrace();
				return update;
			}
	}

	/**
	 *  数据查询
	 * @param <T> 查询的类
	 * @param type 查询的类class
	 * @param sql sql语句
	 * @param params 传�?�的参数
	 * @return 返回的数�?
	 */
	public static <T> List<T> QueryRunnerInQuery(Class<? extends T> type,String sql,Object... params){
		List<T> query=null;
		/**
		 * 为了便捷�?以直接在工具类中处理了异常，可以删掉
		 */
		try {
			query = qr.query(getConnection(), sql, new BeanListHandler<T>(type), params);
			return query;
		} catch (SQLException e) {
			e.printStackTrace();
			return query;
		}
	}
	/**
	 * 带分页的数据查询
	 * @param <T> 查询的类
	 * @param type 查询的类class
	 * @param sql sql语句
	 * @param pr 分页
	 * @param params 传�?�的参数
	 * @return
	 */
	public static <T> List<T> QueryRunnerInQuery(Class<? extends T> type,String sql,PageRang pr,Object... params){
		sql += " limit " + (pr.getPageNumber() - 1) * pr.getPageSize() + " , " + pr.getPageSize();
		return QueryRunnerInQuery(type, sql, params);
	}
	
	public static String likeSql(String s) {
		return "%"+s+"%";
	}
}
