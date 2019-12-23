package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月13日上午9:00:17
*/
public interface GoodsDao {

	public  List<Goods> selectAllGoods() throws SQLException;
	public   List<Goods> selectByGoodsType(String goodsType) throws SQLException;
	public   List<Map> selectVagueByGoods_nameOrGoods_brandOrGoods_presentation(String vague) throws SQLException;
	
}
