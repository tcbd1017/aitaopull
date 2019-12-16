package cn.kgc.tangcco.tcbd1017.on.buyer;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 下午10:07:53 <br>
 *          类描述:
 */

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.PostageInfoDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Address;
import cn.kgc.tangcco.tcbd1017.on.pojo.PostageInfo;

public class PostageInfoDaoTest {
	PostageInfoDao postageInfoDao = new PostageInfoDaoImpl();

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 1);
		try {
			List<PostageInfo> postageInfos = postageInfoDao.selectPostageInfosByBuyerId(map);
			if (postageInfos != null) {
				ListIterator<PostageInfo> it = postageInfos.listIterator();
				while (it.hasNext()) {
					PostageInfo postageInfo = it.next();
					System.out.println(postageInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test02() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address_id", 13);
		try {
			Address address = postageInfoDao.selectAddress(map);
			if (address != null) {
				System.out.println(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test03() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("address_parent_id", 0);
		try {
			List<Address> addresslList = postageInfoDao.selectAllAddress(map);
			if (addresslList != null) {
				System.out.println(addresslList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
