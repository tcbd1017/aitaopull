package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.ShoppingCartDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShoppingCart;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月13日	上午8:33:47
	 */

public class ShoppingCartDaoTest {
    private static ShoppingCartDao spd = null;
    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "ApplicationContext_on.xml");
        try {
            spd = (ShoppingCartDao) classPathXmlApplicationContext.getBean(ShoppingCartDao.class.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
//	 ShoppingCartDao spd = new ShoppingCartDaoImpl();
    @Test
    public void selectShoppingCartInfoByBuyerIdTest() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("buyer_id", 4);
    	map.put("enableFuzzySelect", 0);
    	map.put("goods_name", "米");
    	
    	List<Map<String, Object>> list = spd.selectShoppingCartInfoByBuyerId(map);
    	int count = spd.getShoppingCartCount();
    	Iterator<Map<String, Object>> it = list.listIterator();
    	while (it.hasNext()) {
			Map<String, Object> result = (Map<String, Object>) it.next();
			Set<String> keySet = result.keySet();
			Iterator<String> itSet = keySet.iterator();
			while (itSet.hasNext()) {
				String string = (String) itSet.next();
				System.out.print(string + "\t");
				System.out.print(result.get(string) + "\t");
			}
			System.out.println();
		}
    	System.out.println(count);
    }
    
    @Test
    public void insertShoppingCartTest() {
    	ShoppingCart sc = new ShoppingCart();
    	sc.setShopping_cart_id(0);
    	sc.setBuyer_id(1);
    	sc.setGoods_id(1);
    	sc.setAmount_of_goods(9999);
    	sc.setShopping_cart_create_time(new Date());
    	sc.setShopping_cart_update_time(new Date());
    	sc.setShopping_cart_status(2);

    	
    	int result = spd.insertShoppingCart(sc);
    
    	if (result > 0) {
			System.out.println("插入成功");
		} else {
			System.out.println("插入失败");
		}
    	
    	
    }
    
    @Test
    public void updateShoppingCartInfoBuyerIdTest() {
    	ShoppingCart sc = new ShoppingCart();
    	sc.setBuyer_id(1);
    	sc.setGoods_id(1);
    	sc.setAmount_of_goods(5);
    	sc.setShopping_cart_update_time(new Date());
    	sc.setShopping_cart_status(1);
    	
    	int result = spd.updateShoppingCartInfo(sc);
    	
    	if (result == 0) {
			System.out.println("更新失败");
		} else {
			System.out.println("更新成功");
		}
    }
    
    
}


