package cn.kgc.tangcco.tcbd1017.lo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.UserDao;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.MyDBUtil;
import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsUser;


/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午9:11:02
 * 类描述：
 */
public class UserDaoImpl implements UserDao{
	@Override
	public int addUser(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" INSERT INTO `logistics_user`(user_uuid,nickname,password,mobile) ");
		sql.append(" VALUES(?,?,?,?)");
		Object[] params= {map.get("user_uuid"),map.get("nickname"),map.get("password"),map.get("mobile")};
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), params);
	}

	@Override
	public int updateUser(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" UPDATE logistics_user SET ");
		List<Object> list=new ArrayList<Object>();
		if(map.containsKey("nickname")) {
			sql.append(" nickname=? ");
			list.add(map.get("nickname"));
		}
		if(map.containsKey("nickname")&&map.containsKey("password")) {
			sql.append(" ,password=? ");
			list.add(map.get("password"));
		}else if(map.containsKey("password")){
			sql.append(" password=? ");
			list.add(map.get("password"));
		}
		if((map.containsKey("nickname")||map.containsKey("password"))&&map.containsKey("mobile")) {
			sql.append(" ,mobile=? ");
			list.add(map.get("mobile"));
		}else if(map.containsKey("mobile")){
			sql.append(" mobile=? ");
			list.add(map.get("mobile"));
		}
		sql.append(" WHERE user_uuid=? ");
		list.add(map.get("user_uuid"));
		return MyDBUtil.QueryRunnerInUpdate(sql.toString(), list.toArray());
	}

	@Override
	public List<LogisticsUser> selectUser(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder(" select * from logistics_user where mobile=? and password=?");
		Object[] params= {map.get("account"),map.get("password")};
		return MyDBUtil.QueryRunnerInQuery(LogisticsUser.class, sql.toString(), params);
		
	}
	
}
