package cn.kgc.tangcco.tcbd1017.st;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.tcbd1017.st.pojo.SelectCountByType;



/**
 * 入库记录
 * @author 孙伊泽
 *
 */
public interface comeGoodsRecordDao	 {
	
	/**
	 * 查询入货记录 
	 * 
	 * 
	 * 
	 * 管理员  入库员       comegoodsrecord  与    emp   与    shop   goods  warehouse_shop
	 * 
	 * 1  查询 所有货物的入货记录   
	 *    
	 * 	
	 * 
	 * @param map   按月份    按仓库   按商家  按入货员   动态SQL
	 * @return 
	 * @throws SQLException 
	 */
	public Map<String,Object>selectRuKuJiLu(Map<String,Object>map) throws SQLException;
	
	/**
	 * 查询每月总的进货量         Sql语句  百度
	 * @param map  
	 * @return
	 * @throws SQLException 
	 */
	public List<RecordMonthAndSum> selectRuKuJiLu2() throws SQLException;
	
	
	/**
	 * 饼状图   按分类查询
	*  
	*sql条件   类别   然后计算个数   
	* 三表联查  查询某个分类下的入库数量 
	 * @throws SQLException 
	 */
	public  List<SelectCountByType> selectRuKuJiLuBingZhuangTu() throws SQLException;
	
	
	/**
	 * 
	 * 入库员    检查可以入货  在入货记录添加  记录 
	 * 并且操作者的id存进去
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public int insertJiLu(Map<String,Object>map) throws SQLException;

}
