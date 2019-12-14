package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Record;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午8:38:37
*/
public interface RecordDao {
	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息  (按照历史足迹时间降序排序)
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用
	 */
	public  List<Map> selectRecordAllBybuyer_id(String buyer_id) throws SQLException;
	
	/**
	 * 根据传过来的历史记录id删除相对应的商品足迹（单个删除）
	 * @param record_id 历史记录id
	 * @return返回值 受影响行数
	 * 
	 */
	public  int deleteRecordByRecord_id(String record_id) throws SQLException;
}
