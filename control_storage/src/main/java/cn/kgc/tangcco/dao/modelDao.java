package cn.kgc.tangcco.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.Model;

public interface modelDao {
	/**
	 * 查看型号
	 * 若是三级联动   需要三表查     条件 外键  上一级所选  
	 * @param map
	 * @return
	 */
	public List<Model> selectXingHao( Map<String,Object>map);
	
	
	/**
	 * 查看型号	
	 * @param map  id
	 * @return
	 */
	public Model selectXingHaoById( int id);
	
	/**
	 * 查询价格
	 * @param id
	 * @return
	 */
	public Double selectPrice( int id ,int a,int b);
	
}
