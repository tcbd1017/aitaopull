package cn.kgc.tangcco.tcbd1017.lo.commons.jdbc;
 
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author Cui
 * @version 1.0 时间�?2019�?11�?14�?  下午5:34:44
 * 	类描述：这是动�?�代理类
 */
public class TransactionInvocationHandler implements InvocationHandler {
	
	/**
	 * �?要被代理的类
	 */
	private Object target;
	
	/**
	 * 用于传入�?要被代理的类
	 * @param target �?要被代理的类
	 */
	public TransactionInvocationHandler(Object target) {
		this.target =target;
	}
	
	/**
	 * 动�?�代理类的业务方�?
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object obj=null;
		try {
			//�?启事�?
			MyDBUtil.startTransaction();
			//通过反射调用方法，处理业务�?�辑
			method.setAccessible(true);
			//将方法返回的值接�?
			obj =method.invoke(target, args);
			//判断是否成功
			@SuppressWarnings("unchecked")
			Map<String , Object> map =((Map<String, Object>)obj);
			if(!map.get("status").equals("success")) {
				int i=1/0;
			}
			//关闭事务
			MyDBUtil.commit();
		} catch (Exception e) {
			MyDBUtil.rollback();
			System.out.println("===================================================================");
			System.out.println("status状态为failed，事务回滚,可能并无错误");
			System.out.println("查询可能未查询到信息");
			System.out.println("更新可能未成功");
			System.out.println("请检查sql语句或service层状态是否修改");
			System.out.println("===================================================================");
		}finally {
			MyDBUtil.Close();
			ResultSetUtil.close();
		}
		return obj;
	}

	/**
	 * @return 被创建出来的动�?�代理类
	 */
	public Object getProxy() {
		/**
		 * java在JDK1.5之后提供了一�?"java.lang.reflect.Proxy"�?
		 * 通过"Proxy"类提供的�?个newProxyInstance方法用来创建�?个对象的代理对象
		 * 
		 * newProxyInstance，方法有三个参数�?
		 * loader: 用哪个类加载器去加载代理对象
		 * interfaces:动�?�代理类�?要实现的接口
		 * h:动�?�代理方法在执行时，会调用h里面的invoke方法去执�?
		 * 
		 * getClass()：取得当前对象所属的Class对象   
		 * getClassLoader()：取得该Class对象的类装载�?
		 * 类装载器负责从Java字符文件将字符流读入内存，并构�?�Class类对�?
		 * getInterfaces()方法和Java的反射机制有关�?�它能够获得这个对象�?实现的所有接口�??
		 */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
}
