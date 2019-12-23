package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;


/**
 * @author XUE TONG
 * @version 1.0 2019年12月18日上午11:54:41 </br>
 **/

public class OperationDaoTest {
	private static OperationDao operationDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			operationDao = (OperationDao) path.getBean(OperationDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectoperation() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Emp> emp = operationDao.selectoperation(map);
			Iterator<Emp> it = emp.listIterator();
			while (it.hasNext()) {
				Emp emp1 = it.next();
				System.out.println(emp1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectSellers() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Seller> seller = operationDao.selectSellers(map);
			Iterator<Seller> it = seller.listIterator();
			while (it.hasNext()) {
				Seller Sel = it.next();
				System.out.println(Sel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectCountSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
			int count;
			try {
				count = operationDao.selectCountSeller(map);
				System.out.println(count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void allocationSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seller_id", 15);
		map.put("principal_id", 15);
		try {
			int count = operationDao.allocationSeller(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void auditSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seller_id", 1);
		map.put("seller_status", 3);
		try {
			int count = operationDao.auditSeller(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectEmpSellers() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("020102_seller_principal.principal_id", 1);
			List<Seller> seller = operationDao.selectEmpSellers(map);
			Iterator<Seller> it = seller.listIterator();
			while (it.hasNext()) {
				Seller Sel = it.next();
				System.out.println(Sel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectEmpCountSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("020102_seller_principal.principal_id", 1);
		try {
			int count = operationDao.selectEmpCountSeller(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void selectGoods() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Goods> goods = operationDao.selectGoods(map);
			Iterator<Goods> it = goods.listIterator();
			while (it.hasNext()) {
				Goods good = it.next();
				System.out.println(good);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectCountGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
			int count;
			try {
				count = operationDao.selectCountGoods(map);
				System.out.println(count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void allocationGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", 15);
		map.put("principal_id", 15);
		try {
			int count = operationDao.allocationGoods(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void auditGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", 1);
		map.put("goods_status", 3);
		try {
			int count = operationDao.auditGoods(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("goods_id", 1);
//		map.put("goods_status", 3);
		map.put("020102_seller_principal.principal_id", 1);
//		map.put("020302_goods_principal.principal_id", 1);
		System.out.println(JSON.toJSONString(map));
	}
	
	@Test
	public void selectEmpGoods() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("020302_goods_principal.principal_id", 1);
			List<Goods> good = operationDao.selectEmpGoods(map);
			Iterator<Goods> it = good.listIterator();
			while (it.hasNext()) {
				Goods goods = it.next();
				System.out.println(goods);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectEmpCountGoods() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("020302_goods_principal.principal_id", 1);
		try {
			int count = operationDao.selectEmpCountGoods(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
