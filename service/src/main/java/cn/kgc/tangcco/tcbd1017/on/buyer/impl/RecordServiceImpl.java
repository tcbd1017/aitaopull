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
	 * 返回买家历史记录里所对应的商品图片url等信息
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

	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 1》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id返回买家详细信息
	 * @param buyer_id 买家id
	 * @return 返回该买家详细信息
	 */
	@Override
	public Map<String, Object> queryBuyerInfoBybuyer_id(String buyer_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectBuyerInfoBybuyer_id(buyer_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 2》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码  返回买家历史记录里商品图片url等信息 （并进行了分页）      例：传过来的json字符串    {"pageNo":"1","buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return 返回该买家历史记录里商品图片url等信息
	 */
	@Override
	public Map<String, Object> queryGoodsPictureUrlByBuyerId(String buyer_id,String pageNo) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectGoodsPictureUrlBybuyer_id(buyer_id,pageNo);
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
	 * 《肖越根据前台页面需求额外新添加的方法 3》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家历史记录总条数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(buyerHistory)买家历史记录总条数    和 总页数(totalPages)
	 */
	@Override
	public Map<String, Object> queryCountBuyerHistory(String buyer_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectCountBuyerHistory(buyer_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 4》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车前两条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车前两条数据
	 */
	@Override
	public Map<String, Object> queryBuyerShoppingCart(String buyer_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectBuyerShoppingCart(buyer_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 5》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码获取买家购物车的所有数据 (购物车商品图片、重量、长宽高...等信息)
	 * 每页显示五条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车的所有数据
	 */
	@Override
	public Map<String, Object> queryAllBuyerShoppingCart(String buyer_id,String pages) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectAllBuyerShoppingCart(buyer_id,pages);
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
	 * 《肖越根据前台页面需求额外新添加的方法 6》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车总条数和总页数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(shoppingCart)买家购物车总条数 和 总页数(totalPages 按照一页显示五条数据来计算)
	 */
	@Override
	public Map<String, Object> queryCountShoppingCart(String buyer_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectCountShoppingCart(buyer_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 7》
	 * 大家如果有需要就调用就好了
	 * 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @param buyer_id 买家id
	 * @param name 商品名
	 * @return 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @throws SQLException
	 */
	@Override
	public Map<String, Object> queryDimBuyerShoppingCart(String buyer_id, String name) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectDimBuyerShoppingCart(buyer_id, name);
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
	 * 《肖越根据前台页面需求额外新添加的方法 8》
	 * 根据买家id和购物车id删除对应的购物车商品
	 * @param buyer_id 买家id
	 * @param shopping_scart_id  购物车id
	 * @return
	 */
	@Override
	public Map<String, Object> removeShoppingCartByBuyerIdAndShoppingCartId(String buyer_id, String shopping_scart_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Integer>());
		map.put("status", "failed");
		try {
			int count = recordDao.deleteShoppingCartByBuyerIdAndShoppingCartId(buyer_id, shopping_scart_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 9》
	 * @param goods_id 商品id
	 * @return 根据商品id返回该商品所对应的详细信息
	 */
	@Override
	public Map<String, Object> queryProductDetailsByGoodsId(String goods_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectProductDetailsByGoodsId(goods_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 10》
	 * @param goods_name 商品名称
	 * @return 根据商品名称（支持模糊查询），查询所对应的商品信息(只要第一条)
	 */
	@Override
	public Map<String, Object> queryDimDetailsByGoodsName(String goods_name) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectDimDetailsByGoodsName(goods_name);
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
	 * 《肖越根据前台页面需求额外新添加的方法 11》
	 * @param goods_id 商品id
	 * @return 根据商品id返回同一店铺相关商品的前两条数据
	 */
	@Override
	public Map<String, Object> queryRelatedProductsByGoodsId(String goods_id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			List<Map> m= recordDao.selectRelatedProductsByGoodsId(goods_id);
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
	 * 《肖越根据前台页面需求额外新添加的方法 12》
	 * @param buyer_id 买家id
	 * @param goods_id 商品id
	 * @param amount_of_goods 添加商品数量
	 * @param shopping_cart_create_time 购物车创建时间
	 * @return 根据买家id、商品id、商品数量、购物车创建时间增加购物车信息
	 */
	@Override
	public Map<String, Object> addAddShoppingCart(String buyer_id, String goods_id, String amount_of_goods,
			String shopping_cart_create_time) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Map>());
		map.put("status", "failed");
		try {
			int count = recordDao.insertAddShoppingCart(buyer_id, goods_id, amount_of_goods, shopping_cart_create_time);
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
