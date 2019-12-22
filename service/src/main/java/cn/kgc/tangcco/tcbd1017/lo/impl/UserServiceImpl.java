package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.UserDao;
import cn.kgc.tangcco.tcbd1017.lo.UserService;
import cn.kgc.tangcco.tcbd1017.lo.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.lo.impl.UserDaoImpl;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsUser;


/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日下午11:21:02
 * 类描述：
 */
public class UserServiceImpl implements UserService{
	private static UserDao userDaoImpl=new UserDaoImpl();

	@Override
	public Map<String, Object> addUser(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		int addUser = userDaoImpl.addUser(map);
		System.out.println(addUser);
		if (addUser>0) {
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> updateUser(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		int updateUser = userDaoImpl.updateUser(map);
		if (updateUser>0) {
			info.put("status", "success");
		}
		return info;
	}

	@Override
	public Map<String, Object> selectUser(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", "0");
		info.put("msg","");
		info.put("status", "failed");
		 List<LogisticsUser> selectUser = userDaoImpl.selectUser(map);
		 if(selectUser!=null&&1==selectUser.size()) {
			 info.put("data", selectUser.get(0));
			 info.put("status", "success");
		 }
		return info;
	}

}
