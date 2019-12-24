package cn.kgc.tangcco.tcbd1017.st;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.Brand;

 

public interface brandDao {
	/**
	 * 查看品牌
	 *  若是三级联动   需要两表查     条件 外键 
	 * @param map   上一级所选    
	 * @return
	 */
	public List<Brand> selectPinPai( Map<String,Object>map);
	
	/**
	 * 查看品牌	
	 * @return   id
	 */
	public Brand selectPinPaiByid( int id);
	
}
