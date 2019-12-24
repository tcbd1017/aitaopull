package cn.kgc.tangcco.tcbd1017.st;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.st.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.tcbd1017.st.pojo.SelectCountByType;



/**
 * 对出库记录查询
 * @author 孙伊泽
 *
 */
public interface GogoodSrecordDao {
	
	/**
	 * 查询出库记录 
	* 
	 * 
	 * gogoodsrecord  shop   warehouse_shop  emp  goods
	 * 管理员 入货员
	 * 
	 * 
	 * @param map    查询 条件      按仓库   按商家  按入货员   动态SQL
	 * @return 
	 * @throws SQLException 
	 */
	public Map<String,Object>selectChuKuJiLu(Map<String,Object>map) throws SQLException;
	
	
	/**
	 * 饼状图   按分类查询
	*  查询 某个类别的出库数量     以类别分类  gogoodsrecord    goods   type
	*    sql条件   类别   然后计算个数   
	* 三表联查  查询  不同  分类下的  出库数量 
	 * @throws SQLException 
	*/
	public  List<SelectCountByType> selectRChuKuJiLuBingZhuangTu() throws SQLException;
	
	
	/**  
	 * 分类查询每月的出货记录数     gogoodsrecord    SQL语句  查询以月份分类   在把每个月的数据加一起  百度查以下  
	 * @param map 
	 * @return  
	 * @throws SQLException 
	 */
	public List<RecordMonthAndSum> FenLeiChaXun() throws SQLException;
	
	/**
	 * 出库员    检查可以出货  在出货记录表添加  记录    
	 * 并且操作者的id存进去
	 * @param map  
	 * @return
	 * @throws SQLException 
	 */
	public int insertJiLuChuKu(Map<String,Object>map) throws SQLException;
	


}
