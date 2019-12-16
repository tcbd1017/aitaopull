package cn.kgc.tangcco.tcbd1017.on.action.buyer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RewardPointsService;
import cn.kgc.tangcco.tcbd1017.on.pojo.RewardPoints;

/**
 * 
 * @author 胡文举
 * @version  1.1 
 *  2019年12月13日<dr>
 *	下午5:21:25<dr>
   * 类描述： 
 */
@WebServlet(urlPatterns = "/RewardPoints.action")
public class RewardPointsAction extends BaseServlet{
	private static RewardPointsService rewardPointsService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	 static {
	  try {
		  rewardPointsService = (RewardPointsService) context.getBean(RewardPointsService.class.getSimpleName());
	  } catch (Exception e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 }
	private static final long serialVersionUID = 1935701194266043076L;
	/**买家id
	 * 	data ： dao层返回的数据
	 * 	status : 查询是否成功信息
	 * 	msg : 后台给的其他信息
	 * 	传进来的json要求：包含角色对象，包含order订单查询信息对象；
	 * @param request
	 * @param response
	 * 	@return 返回一个map，key:data，格式:list，list中包含order对象；
	 */
	private void findRewardPoints(HttpServletRequest request,HttpServletResponse response,String json) {
		
		//接收值
		
		Map map =JSON.parseObject(json,Map.class);
		System.out.println(map);
		map=rewardPointsService.findRewardPoints(map);
		System.out.println(map);
		System.out.println("返回的参数：");
		List<RewardPoints> list = (List<RewardPoints>) map.get("data");
		Iterator<RewardPoints> iterator = list.iterator();
		if (list.size()>0) {
			while (iterator.hasNext()) {
				RewardPoints rewardPoints = (RewardPoints) iterator.next();
				System.out.println(rewardPoints);
			}
		}
		// 将返回数据向页面输出
		printJson(response, map);
	}
	/**
	 * 从页面获取积分店铺id，积分
	 * 将返回数据向页面输出
	 * @param request
	 * @param response
	 */
	private void addRewardPoints(HttpServletRequest request,HttpServletResponse response,String json) {
		//接收值
		Map map =JSON.parseObject(json,Map.class);
		//int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
		
		map=rewardPointsService.addRewardPoints(map);
		System.out.println(map.get("data"));
		printJson(response, map);
	}
}
