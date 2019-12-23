package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerCommentManagementService;
/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  下午1:41:24<br>
*类说明:  		评论管理（回复，查看，删除） action层
*/
/**
 * Servlet implementation class SellerCommentManagementAction
 */
@WebServlet(urlPatterns = "/SellerCommentManagement.action")
public class SellerCommentManagementAction extends BaseServlet {
	private static final long serialVersionUID = 5852430322885421817L;
	private static SellerCommentManagementService sellerCommentManagementService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sellerCommentManagementService = (SellerCommentManagementService) context.getBean(SellerCommentManagementService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SellerCommentManagementAction() {
        super();
    }

	/**
	 * 查看评论       "seller_id", 869910        动态sql "goods_id" ; order_uuid  ;   order_create_time;
	 *  (map )
	 * 主要有：
	 * key:comment_id             			value:评论id
	 * key:buyer_id               			value: 买家id
	 * key:order_id               			value: 订单id
	 * 
	 * key:comment_content        			value:评论内容 
	 * key:comment_create_time    			value:评论创建时间 
	 * 
	 * key:comment_status         			value:评论状态 1失效 2正常 
	 * key:comment_grade          			value:评分 商品评分  1极差  2 差 3 中 4 好   5 极好
	 * 
	 * key:order_uuid             			value:订单编号 
	 * key:order_create_time      			value:订单创建时间
	 * 
	 * key:goods_id               			value:商品id 
	 * key:goods_name             			value:商品名称 
	 * key:goods_price            			value:商品价格 
	 * key:    value: 
	 * key:reply_comment_content  value:回复评论内容 
	 * key:reply_comment_create_time    value:回复评论创建时间 

	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void queryComments(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		Map map = JSON.parseObject(json, Map.class);
		//System.out.println("action");
		Map<String , Object> queryComments = sellerCommentManagementService.queryComments(map);
		//System.out.println(" 正在输出  "+queryComments);
		printJson(response, queryComments);
	}
	/**
	 * 删除评论（更改买家评论状态）  前台传入  comment_id
	 * 
	 * 返回   status：  success
	 *  
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void removeComments(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		Map map = JSON.parseObject(json, Map.class);
		Map removeComments = sellerCommentManagementService.removeComments(map);
		printJson(response, removeComments);
	}
	
	/**
	 * 商家回复评论    
	 * 前台传入：  "seller_id": "1" , "order_id":"6" , "reply_comment_content":"感谢好评"
	 * 	                   卖家id     ，  订单id    , 回复评论内容，                                                  
	 * 返回： status: success 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void addSellerComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seller_id = request.getParameter("seller_id");
		String order_id = request.getParameter("order_id");
		String reply_comment_content = request.getParameter("reply_comment_content");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("seller_id", seller_id);
		map.put("order_id",order_id );
		map.put("reply_comment_content", reply_comment_content);
		Map addSellerComments = sellerCommentManagementService.addSellerComments(map);
		System.out.println(addSellerComments.get("status"));
		
	   printJson(response, addSellerComments);
//		Map map = JSON.parseObject(json, Map.class);
//		Map addSellerComments = sellerCommentManagementService.addSellerComments(map);
//		printJson(response, addSellerComments);
	}
	
	
	
	

}
