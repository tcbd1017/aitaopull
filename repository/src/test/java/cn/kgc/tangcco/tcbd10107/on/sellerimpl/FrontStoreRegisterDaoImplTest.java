package cn.kgc.tangcco.tcbd10107.on.sellerimpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreRegisterDaoIns;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月14日 下午8:27:41 类说明
 */
public class FrontStoreRegisterDaoImplTest {
	static FrontStoreRegisterDaoIns bean = null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"ApplicationContext_on.xml");
			bean = (FrontStoreRegisterDaoIns) classPathXmlApplicationContext.getBean("FrontStoreRegisterDaoIns");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户开店测试
	@Test
	public void insert_seller_Register() throws SQLException {
		//BaseDBUtils.startTransaction();
		Map<String, Object> map = new HashMap<>();
		map.put("buyer_id", 1);
		map.put("seller_uuid", "3f059cb88928a6c0d6144c9abd9c0f3d01cacab1f5fb8b");
		map.put("seller_idcard_token", "142226199608036019");
		map.put("seller_create_time", new Date());
		map.put("storage_id", BaseUUID.rendem());
		map.put("seller_icon_url", "aaaaa");
		try {
			if(bean.select__Store_Uuid(map)==false) {
				int insert_seller_Register = bean.insert_seller_Register(map);
				System.out.println(insert_seller_Register);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 店铺信息注册
	@Test
	public void insert_Store_Register() throws SQLException {
		Map<String, Object> map = new HashMap<>();
		BaseDBUtils.startTransaction();
		map.put("store_id", "5");
		map.put("store_create_time", new Date());
		map.put("store_about", "涛涛店铺");
		map.put("store_img", "店铺图片");
		map.put("store_name", "aitao");
		try {
			int insert_seller_Register = bean.insert_Store_Register(map);
			System.out.println(insert_seller_Register);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void select__Store_Uuid() {
		try {
			Map<String, Object> map = new HashMap<>();
			// map.put("seller_uuid","3f059cb88928a6c0d6144c9abd9c0f3d01cacab1f5fb8b");
			map.put("seller_uuid", "3f059cb88928a6c0d6144c9abd9c0f3d01cacab1f5fb8b");
			boolean select__Store_Uuid = bean.select__Store_Uuid(map);
			System.out.println(select__Store_Uuid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void select__Store_Id() {
		Map<String, Object> map = new HashMap<>();
		map.put("store_id", "67914");
		// map.put("store_id",69910);
		try {
			boolean select__Store_Id = bean.select__Store_Id(map);
			System.out.println(select__Store_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void seller_idcard_token() {
		Map<String, Object> map = new HashMap<>();
		map.put("seller_idcard_token", "142226199608036019");
		try {
			boolean select__Store_Id = bean.seller_idcard_token(map);
			System.out.println(select__Store_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
