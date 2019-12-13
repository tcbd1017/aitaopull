package cn.kgc.tangcco.tcbd1017.on.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.OrderDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;

/**
 * 
 * @author 廖斌
 *
 */
public class OrderDaoImpl8888888 implements OrderDao{
	QueryRunner qrds = new QueryRunner(BaseDBUtils.getDataSource());
	QueryRunner qr = new QueryRunner();
	/**
	 * @return List<Order>
	 * 	查询订单  格式
	 *  map.put("object","查询角色")；
	 *  map.put("data","查询数据");
	 *	map.put("status", "failed");
	 *	map.put("msg", "");
	 *	map.put("code", 0);
	 * @throws SQLException 
	 * @return list
	 */
	@Override
	public List<Order> selectByOrder(Map<String,Object> map) throws SQLException {
		//如果map不为空，且长度大于0
		if (!map.isEmpty()&&map.size()>0) {
			//如果data中的对象是买家
			if (map.get("object") instanceof Buyer) {
				//获得买家信息
				Buyer buyer =(Buyer)map.get("object");
				int buyer_id = buyer.getBuyer_id();
				//如果map中包括了查询数据，并且查询数据类型是order
				if (map.containsKey("data")&&map.get("data") instanceof Order) {
					//获得订单查询信息
					Order order = (Order)map.get("data");
					//基础sql
					StringBuilder sql = new StringBuilder("Select * from 0109_order where 1=1  ");
					//基于买家角色进行查询
					sql.append(" and 0109_order.buyer_id = (select "+buyer_id+" FROM 0101_buyer WHERE 1=1) ");
					//sql语句占位符查询数据
					List list = new ArrayList();
					//处理动态sql
					Map addSql = addSql(order,sql,list);
					List list1 = (List)addSql.get("list");
					//执行sql，并返回集合
					return qrds.query(addSql.get("sql").toString(),list1.toArray(),  new BeanListHandler<Order>(Order.class));
					}
				}else if(map.get("object") instanceof Seller) {
					//获得卖家信息
					Seller seller =(Seller)map.get("object");
					int seller_id = seller.getSeller_id();
					//如果map中包括了查询数据，并且查询数据类型是order
					if (map.containsKey("data")&&map.get("data") instanceof Order) {
						//获得订单查询信息
						Order order = (Order)map.get("data");
						//基础sql
						StringBuilder sql = new StringBuilder("Select * from 0109_order where 1=1  ");
						//基于卖家角色进行查询
						sql.append(" and 0109_order.order_id = (select o.order_id from 0201_seller as s INNER JOIN	0109_order_goods as o WHERE "+seller_id+" =o.seller_id) ");
						//sql语句占位符查询数据
						List list = new ArrayList();
						//处理动态sql
						Map addSql = addSql(order,sql,list);
						List list1 = (List)addSql.get("list");
						//执行sql，并返回集合
						return qrds.query(addSql.get("sql").toString(),list1.toArray(),  new BeanListHandler<Order>(Order.class));
						}
				}//卖家查询
				
			//如果data中的对象是是卖家	
			}
		return null;
	}
	
	/**
	 * @return int
	 * 	修改订单，删除订单
	 * 	查询订单  格式 	
	 *  map.put("object","查询角色")；
	 *  map.put("date","修改数据");
	 *	map.put("status", "failed");
	 *	map.put("msg", "");
	 *	map.put("code", 0);
	 */
	@Override
	public int updateByOrder(Map<String,Object> map) throws SQLException{
		//如果map不为空，且长度大于0
		if (!map.isEmpty()&&map.size()>0) {
			//买家修改
			if (map.get("object") instanceof Buyer) {
			//获得买家id	
				Buyer buyer =(Buyer)map.get("object");
				int buyer_id = buyer.getBuyer_id();
				//如果map中包含data数据，并数据类型是Order对象
				if (map.containsKey("data")&&map.get("data") instanceof Order) {
					//获得订单修改信息
					Order order = (Order)map.get("data");
					//基础sql
					StringBuilder sql = new StringBuilder( " update 0109_order set " );	
					//sql语句占位符储存数组
					List list = new ArrayList();
					//动态生成sql
					Map addsql1 = addsql1(order,sql,list);
					sql = (StringBuilder)addsql1.get("sql");
					 list = (List)addsql1.get("list");
					//查询条件
					sql.append(" where buyer_id = (select "+buyer_id+" FROM 0101_buyer WHERE 1=1) ");
					//去除逗号
					String finnalSql = sql.toString().replace(",  where", "where");
					System.out.println(finnalSql);
					return qr.update(BaseDBUtils.getConnection(), finnalSql, list.toArray());
					
				}
			//卖家修改	
			}else if (map.get("object") instanceof Seller) {
					//获得卖家信息
					Seller seller =(Seller)map.get("object");
					int seller_id = seller.getSeller_id();
					//如果map中包含data数据，并数据类型是Order对象
					if (map.containsKey("data")&&map.get("data") instanceof Order) {
						//获得订单修改信息
						Order order = (Order)map.get("data");
						//基础sql
						StringBuilder sql = new StringBuilder( " update 0109_order set " );	
						//sql语句占位符储存数组
						List list = new ArrayList();
						//动态生成sql
						Map addsql1 = addsql1(order,sql,list);
						sql = (StringBuilder)addsql1.get("sql");
						 list = (List)addsql1.get("list");
						//查询条件
						sql.append(" where buyer_id = (select "+seller_id+" FROM 0101_buyer WHERE 1=1) ");
						//去除逗号
						String finnalSql = sql.toString().replace(",  where", "where");
						return qr.update(BaseDBUtils.getConnection(), finnalSql, list.toArray());
					}
			}
		}
		return 0;
	}
	
	/**
	 * @return int
	 * 	新增订单
	 */
	@Override
	public int insertByOrder(Map<String,Object> map) throws SQLException{
		//如果map不为空，且长度大于0
				if (!map.isEmpty()&&map.size()>0) {
						if (map.containsKey("data")&&map.get("data") instanceof Order) {
							//获得订单新增信息
							Order order = (Order)map.get("data");
							//基础sql
							StringBuilder sql = new StringBuilder(  " insert into 0109_order (order_uuid,logistics_id,order_create_time,order_update_time,order_status,buyer_id,order_payment,buyer_cash_voucher_id,order_payment_type,order_payment_time,order_consign_time,order_end_time,order_close_time,order_buyer_message) " );	
							sql.append("select ?,?,?,?,?,?,?,?,?,?,?,?,?,?");
							sql.append("from dual    where not exists");
							sql.append("(select order_uuid from 0109_order where order_uuid ="+order.getOrder_uuid()+" )");
							//sql语句占位符储存数组
							Object [] params = {order.getOrder_uuid(),order.getLogistics_id(),order.getOrder_create_time(),order.getOrder_update_time(),order.getOrder_status(),order.getBuyer_id(),order.getOrder_payment(),order.getBuyer_cash_voucher_id(),order.getOrder_payment_type(),order.getOrder_payment_time(),order.getOrder_consign_time(),order.getOrder_end_time(),order.getOrder_close_time(),order.getOrder_buyer_message()};
							//执行语句；
							System.out.println(sql.toString());
							return qr.update(BaseDBUtils.getConnection(), sql.toString(), params);
						}
				}
		return 0;
	}
	
	
	
	
	/**
	 * 	封装重复代码
	 * 
	 * 
	 */
	public Map addSql(Order order,StringBuilder sql,List list ) {
		if (order.getOrder_id()>0) {
			sql.append( " and order_id like ? " );
			list.add("%"+order.getOrder_id()+"%");
		}
		if (!StringUtils.isEmpty(order.getOrder_uuid())) {
			sql.append( " and order_uuid like ? " );
			list.add("%"+order.getOrder_uuid()+"%");
		}
		if (order.getLogistics_id()>0) {
			sql.append( " and logistics_id like ? " );
			list.add("%"+order.getLogistics_id()+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_create_time())) {
			sql.append( " and order_create_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_create_time())+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_update_time())) {
			sql.append( " and order_update_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_update_time())+"%");
		}
		if (order.getOrder_status()>0) {
			sql.append( " and order_status like ? " );
			list.add("%"+order.getOrder_status()+"%");
		}
		if (order.getBuyer_id()>0) {
			sql.append( " and buyer_id like ? " );
			list.add("%"+order.getBuyer_id()+"%");
		}
		if (order.getOrder_payment()>0) {
			sql.append( " and order_payment like ? " );
			list.add("%"+order.getOrder_payment()+"%");
		}
		if (order.getBuyer_cash_voucher_id()>0) {
			sql.append( " and buyer_cash_voucher_id like ? " );
			list.add("%"+order.getBuyer_cash_voucher_id()+"%");
		}
		if (order.getOrder_payment_type()>0) {
			sql.append( " and order_payment_type like ? " );
			list.add("%"+order.getOrder_payment_type()+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_payment_time())) {
			sql.append( " and order_payment_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_payment_time())+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_consign_time())) {
			sql.append( " and order_consign_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_consign_time())+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_end_time())) {
			sql.append( " and order_end_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_end_time())+"%");
		}
		if (!ObjectUtils.isEmpty(order.getOrder_close_time())) {
			sql.append( " and order_close_time like ? " );
			list.add("%"+ BaseDateUitls.getDateString(order.getOrder_close_time())+"%");
		}
		if (!StringUtils.isEmpty(order.getOrder_buyer_message())) {
			sql.append( " and order_buyer_message like ? " );
			list.add("%"+order.getOrder_buyer_message()+"%");
		}
		Map map = new HashMap();
		map.put("sql",sql);
		map.put("list",list);
		return map;
	}
	public Map addsql1(Order order,StringBuilder sql,List list ) {
		
		if (order.getOrder_id()>0) {
			sql.append( " order_id = ? , " );
			list.add(order.getOrder_id());
		}
		if (!StringUtils.isEmpty(order.getOrder_uuid())) {
			sql.append( "  order_uuid = ? , " );
			list.add(order.getOrder_uuid());
		}
		if (order.getLogistics_id()>0) {
			sql.append( "  logistics_id = ? , " );
			list.add(order.getLogistics_id());
		}
		if (!ObjectUtils.isEmpty(order.getOrder_create_time())) {
			sql.append( " order_create_time = ? , " );
			list.add(BaseDateUitls.getDateString(order.getOrder_create_time()));
		}
		if (!ObjectUtils.isEmpty(order.getOrder_update_time())) {
			sql.append( " order_update_time = ? , " );
			list.add(BaseDateUitls.getDateString(order.getOrder_update_time()));
		}
		if (order.getOrder_status()>0) {
			sql.append( "  order_status = ? , " );
			list.add(order.getOrder_status());
		}
		if (order.getBuyer_id()>0) {
			sql.append( "  buyer_id like ? , " );
			list.add(order.getBuyer_id());
		}
		if (order.getOrder_payment()>0) {
			sql.append( " order_payment = ? , " );
			list.add(order.getOrder_payment());
		}
		if (order.getBuyer_cash_voucher_id()>0) {
			sql.append( "  buyer_cash_voucher_id = ? , " );
			list.add(order.getBuyer_cash_voucher_id());
		}
		if (order.getOrder_payment_type()>0) {
			sql.append( " order_payment_type = ? , " );
			list.add(order.getOrder_payment_type());
		}
		if (!ObjectUtils.isEmpty(order.getOrder_payment_time())) {
			sql.append( " order_payment_time = ? , " );
			list.add(BaseDateUitls.getDateString(order.getOrder_payment_time()));
		}
		if (!ObjectUtils.isEmpty(order.getOrder_consign_time())) {
			sql.append( "  order_consign_time like ? , " );
			list.add(BaseDateUitls.getDateString(order.getOrder_consign_time()));
		}
		if (!ObjectUtils.isEmpty(order.getOrder_end_time())) {
			sql.append( "  order_end_time = ? , ");
			list.add(BaseDateUitls.getDateString(order.getOrder_end_time()));
		}
		if (!ObjectUtils.isEmpty(order.getOrder_close_time())) {
			sql.append( " order_close_time = ? , " );
			list.add(BaseDateUitls.getDateString(order.getOrder_close_time()));
		}
		if (!StringUtils.isEmpty(order.getOrder_buyer_message())) {
			sql.append( " order_buyer_message = ? , " );
			list.add(order.getOrder_buyer_message());
		}
		Map map = new HashMap();
		map.put("sql",sql);
		map.put("list",list);
		return map;
		
	}
	
	
	
}
