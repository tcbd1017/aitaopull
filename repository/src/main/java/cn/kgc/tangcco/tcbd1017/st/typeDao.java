package cn.kgc.tangcco.tcbd1017.st;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.Type;

public interface typeDao {
	
	/**
	 * 查看类型
	 * @param map 
	 * @return 
	 */
	public List<Type> selectLeiXing( );
	
	/**
	 * 查看类型
	 * @param map  id
	 * @return 
	 */
	public Type selectLeiXingById( int id);
}
