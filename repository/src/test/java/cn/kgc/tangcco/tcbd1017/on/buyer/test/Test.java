package cn.kgc.tangcco.tcbd1017.on.buyer.test;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import cn.kgc.tangcco.lihaozhe.commons.date.BaseDate;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.Laypage;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.GoodsFavoriteDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.GoodsFavorite;

/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
public class Test {
 
 /**
   * 添加
   */
		
	public void add() {
		GoodsFavoriteDaoImpl dao=new GoodsFavoriteDaoImpl();
		Date date = BaseDate.getDate("2019-11-10 22:10:1");
		Date date1 = BaseDate.getDate("2019-12-12 22:10:1");
		GoodsFavorite good=new GoodsFavorite();
		good.setBuyer_id(5);
		good.setGoods_id(6);
		good.setGoods_favorite_create_time(date);
		good.setGoods_favorite_update_time(date1);
		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("buyer_id", good.getBuyer_id());
		map.put("goods_id", good.getGoods_id());
		map.put("goods_favorite_create_time", good.getGoods_favorite_create_time());
		map.put("goods_favorite_update_time", good.getGoods_favorite_update_time());
		try {
			int addGoodsFavorite = dao.addGoodsFavorite(map);
			System.out.println(addGoodsFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 删除
	 */
	@org.junit.Test
	public void dele() {
		GoodsFavoriteDaoImpl dao=new GoodsFavoriteDaoImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_favorite_id",2);
	
		try {
			int addGoodsFavorite = dao.deleteGoodsFavorite(map);
			System.out.println(addGoodsFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*
	 * 判断是否存在
	 * 
	 */
	public void is() {
		GoodsFavoriteDaoImpl dao=new GoodsFavoriteDaoImpl();
		GoodsFavorite good=new GoodsFavorite();
		good.setGoods_id(25);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_id", good.getGoods_id());
		try {
			int judgeGoodsFavorite = dao.judgeGoodsFavorite(map);
			System.out.println(judgeGoodsFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 查询
	 */
	@org.junit.Test
	public void selece() {
		GoodsFavoriteDaoImpl dao=new GoodsFavoriteDaoImpl();
		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_name","苹");
	//	map.put("laypage", new Laypage(1, 1));
		List<Map<String, Object>> se;
		
		try {
			se = dao.selectGoodsFavorite(map);
			if(se!=null) {
			Iterator<Map<String, Object>> iterator = se.iterator();
			while(iterator.hasNext()) {
				Map<String, Object> next = iterator.next();
				System.out.println(next.toString());
			}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@org.junit.Test
	public void count() {
		GoodsFavoriteDaoImpl dao=new GoodsFavoriteDaoImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("buyer_id",1);
		try {
			int countGoodsFavorite = dao.countGoodsFavorite(map);
			System.out.println(countGoodsFavorite);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date.toString());
	}
}
