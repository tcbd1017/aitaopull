package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.seller.GoodsDao;
import cn.kgc.tangcco.tcbd1017.on.seller.GoodsService;

/**
 * @author 谷亚坤
 * @version 创建时间：2019年12月14日 下午2:31:03
 * @ClassName 类名称 0203good逻辑层接口实现类
 * @Description 类描述 0203good上架下架逻辑层接口实现类
 **/
public class GoodsServiceImpl implements GoodsService {
	GoodsDao goodsDao = new GoodsDaoImpl();

	/**
	 * 查看所有未上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	@Override
	public Map<String, Object> selectOfflineGoods(int store_id) {
		/**
		 * 新建map用于返回
		 */
		Map<String, Object> info = new HashMap<String, Object>();
		/**
		 * 默认状态 失败
		 */
		info.put("status", "failed");
		/**
		 * 声明List集合 接收Dao层方法值
		 */
		List<Goods> list;
		try {
			/**
			 * 调用selectOfflineGoods方法
			 */
			list = goodsDao.selectOfflineGoods(store_id);
			/**
			 * 判断是否为空
			 */
			if (list != null) {
				/**
				 * 集合遍历
				 */
				ListIterator<Goods> it = list.listIterator();
				/**
				 * 集合遍历
				 */
				while (it.hasNext()) {
					Goods goods = (Goods) it.next();
					/**
					 * 更改状态 成功
					 */
					info.put("status", "success");
					/**
					 * 将值存储在Map中
					 */
					info.put("OfflineGoods", goods);
				}
			}
			/**
			 * 关闭数据源
			 */
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				/**
				 * 关闭数据源
				 */
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/**
		 * 返回Map对象
		 */
		return info;
	}

	/**
	 * 查看所有以上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	@Override
	public Map<String, Object> selectOnlineGoods(int store_id) {
		/**
		 * 新建map用于返回
		 */
		Map<String, Object> info = new HashMap<String, Object>();
		/**
		 * 默认状态 失败
		 */
		info.put("status", "failed");
		/**
		 * 声明List集合 接收Dao层方法值
		 */
		List<Goods> list;
		try {
			/**
			 * 调用selectOfflineGoods方法
			 */
			list = goodsDao.selectOnlineGoods(store_id);
			/**
			 * 判断是否为空
			 */
			if (list != null) {
				/**
				 * 集合遍历
				 */
				ListIterator<Goods> it = list.listIterator();
				/**
				 * 集合遍历
				 */
				while (it.hasNext()) {
					Goods goods = (Goods) it.next();
					/**
					 * 更改状态 成功
					 */
					info.put("status", "success");
					/**
					 * 将值存储在Map中
					 */
					info.put("OnlineGoods", goods);
				}
			}
			/**
			 * 关闭数据源
			 */
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				/**
				 * 关闭数据源
				 */
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/**
		 * 返回Map对象
		 */
		return info;
	}

	/**
	 * 根据商品id选择上架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	@Override
	public Map<String, Object> supdateGoods(int goods_id) {
		/**
		 * 新建map用于返回
		 */
		Map<String, Object> info = new HashMap<String, Object>();
		/**
		 * 默认状态 失败
		 */
		info.put("status", "failed");

		try {
			int supdateGoods = goodsDao.supdateGoods(goods_id);
			if (supdateGoods > 0) {
				/**
				 * 更改状态 成功
				 */
				info.put("status", "success");
				/**
				 * 初步设定修改成功 后期可更改msg
				 */
				System.out.println("成功上架");
			}
			/**
			 * 关闭数据源
			 */
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			try {
				/**
				 * 关闭数据源
				 */
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 根据商品id选择下架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	@Override
	public Map<String, Object> xupdateGoods(int goods_id) {
		/**
		 * 新建map用于返回
		 */
		Map<String, Object> info = new HashMap<String, Object>();
		/**
		 * 默认状态 失败
		 */
		info.put("status", "failed");

		try {
			int supdateGoods = goodsDao.xupdateGoods(goods_id);
			if (supdateGoods > 0) {
				/**
				 * 更改状态 成功
				 */
				info.put("status", "success");
				/**
				 * 初步设定修改成功 后期可更改msg
				 */
				System.out.println("成功下架");
			}
			/**
			 * 关闭数据源
			 */
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			try {
				/**
				 * 关闭数据源
				 */
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 根据商品id修改商品价钱
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	@Override
	public Map<String, Object> updateGoodsgoods_price(int goods_id, double goods_price) {
		/**
		 * 新建map用于返回
		 */
		Map<String, Object> info = new HashMap<String, Object>();
		/**
		 * 默认状态 失败
		 */
		info.put("status", "failed");

		try {
			int supdateGoods = goodsDao.updateGoodsgoods_price(goods_id, goods_price);
			if (supdateGoods > 0) {
				/**
				 * 更改状态 成功
				 */
				info.put("status", "success");
				/**
				 * 初步设定修改成功 后期可更改msg
				 */
				System.out.println("修改成功");
			}
			/**
			 * 关闭数据源
			 */
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			try {
				/**
				 * 关闭数据源
				 */
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return info;
	}
}
