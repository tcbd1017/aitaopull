package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RecordDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.RecordService;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Record;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午9:00:38
*/
public class RecordServiceImpl implements RecordService {
	static RecordDao recordDao;
	static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			recordDao = (RecordDao) path.getBean(RecordDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息  (按照历史足迹时间降序排序)
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用
	 */
	@Override
	public Map<String, Object> queryAllRecord(String buyer_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectRecordAllBybuyer_id(buyer_id);
			if (m !=null) {
				map.put("status", "success");
				map.put("data",m);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}
	
	
	
	/**
	 * 根据传过来的历史记录id删除相对应的商品足迹（单个删除）
	 * @param record_id 历史记录id
	 */
	@Override
	public Map<String, Object> removeRecordByRecord_id(String record_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Record>());
		map.put("status", "failed");
		try {
			int count = recordDao.deleteRecordByRecord_id(record_id);
			if (count !=0) {
				map.put("status", "success");
				map.put("data",count);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}



	
	
	
	
	
	/**
	 * 根据传过来的买家id 和商品名称（支持模糊查询）
	 * 所对应的商品
	 */
	@Override
	public Map<String, Object> queryRecordAllByBuyerIdAndGoodsName(String buyer_Id, String goods_name) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectRecordAllByBuyerIdAndGoodsName(buyer_Id, goods_name);
			if (m !=null) {
				map.put("status", "success");
				map.put("data",m);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}




	
	
	/**
	 * 
	 * @param buyer_id 买家id
	 * @param goods_id 商品id
	 * 
	 * 
	 */
	@Override
	public Map<String, Object> addRecordByBuyer_idAndGoods_id(String buyer_id, String goods_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Record>());
		map.put("status", "failed");
		try {
			int count = recordDao.insertRecordByBuyer_idAndGoods_id(buyer_id, goods_id);
			if (count !=0) {
				map.put("status", "success");
				map.put("data",count);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}



	

	
	
	
	
	
}
