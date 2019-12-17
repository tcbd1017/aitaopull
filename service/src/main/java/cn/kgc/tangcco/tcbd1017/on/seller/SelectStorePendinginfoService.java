package cn.kgc.tangcco.tcbd1017.on.seller;


import java.util.Map;

/**
 * 
 * @ClassName: SelectStorePendinginfoService 
 * @Description: TODO(先将所有的已付款变成待发货，然后查询出有多少待发货)
 * @author A18ccms a18ccms_gmail_com 
 * @date 2019年12月14日 上午8:52:34 * *
 */
public interface SelectStorePendinginfoService {
	/**
	 * 
	 * @Title: findStorePending 
	 * @Description: TODO(首先调用方法 将数据库内所有的已付款变成待发货 然后调用另一个办法查询所有待发货信息) 
	 * @param @return    设定文件 
	 * @return Map<String,Object>    返回类型 
	 *  map.put("date","对象");返回具体数据
	 *  map.put("status", "failed");查询失败
	 *  maps.put("status","success");查询成功
	 *  map.put("msg", "");提示词
	 *   map.put("code", 0);固定返回值
	 * @throws
	 */
	Map<String, Object> findStorePending();
}
