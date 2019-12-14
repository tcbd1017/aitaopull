package cn.kgc.tangcco.tcbd1017.lo.commons.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtil {

	private ResultSetUtil() {
		
	}
	private static ThreadLocal<ResultSetUtil> t = new ThreadLocal<ResultSetUtil>(); 
	
	
	private static ResultSetUtil getResultSetUtil() {
		ResultSetUtil rsu=t.get();
		if(rsu == null) {
			
			t.set(new ResultSetUtil());
			rsu=t.get();
		}
		return rsu;
	}
	public static  List<Map<String ,Object>> rsRetList(ResultSet rs){
		return ResultSetUtil.getResultSetUtil().rsRetListx(rs);
		
	}
	
	public static void close() {
			t.remove();
	}
	private List<Map<String ,Object>> rsRetListx(ResultSet rs){
		List<Map<String ,Object>> list = null;
		if(rs==null) {
			return list;
		}
		try {
			list = new ArrayList<Map<String ,Object>>();
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					String columnLabel = rs.getMetaData().getColumnLabel(i);
					map.put(columnLabel,rs.getObject(i));
				}
			    list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
