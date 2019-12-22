package cn.kgc.tangcco.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.Brand;

public interface brandDao {
	/**
	 * 查看品牌
	 *  若是三级联动   需要两表查     条件 外键 
	 * @param map   上一级所选    
	 * @return
	 */
	public List<Brand> selectLeiXing( Map<String,Object>map);
	
	/**
	 * 查看品牌	
	 * @return   id
	 */
	public Brand selectLeiXingByid( int id);
	
}
