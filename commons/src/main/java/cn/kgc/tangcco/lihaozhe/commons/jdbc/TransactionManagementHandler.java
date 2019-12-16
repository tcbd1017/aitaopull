package cn.kgc.tangcco.lihaozhe.commons.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年11月23日 下午5:06:44 
*    类说明 
*/
public class TransactionManagementHandler implements InvocationHandler {
	private Object target;
	public TransactionManagementHandler(Object target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) {
		Object obj=null;
		try {
			//开启事务
			BaseDBUtils.startTransaction();
			//业务逻辑
			obj=method.invoke(target, args);
			//提交事务
			BaseDBUtils.commitAndClose();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			BaseDBUtils.rollbackAndClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	public Object getProxy() {
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
	}
}	
