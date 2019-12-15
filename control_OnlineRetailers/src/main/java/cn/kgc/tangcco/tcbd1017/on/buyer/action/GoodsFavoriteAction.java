package cn.kgc.tangcco.tcbd1017.on.buyer.action;

/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsFavoriteService;

/**
 * Servlet implementation class GoodsFavoriteAction
 */
@WebServlet("/GoodsFavoriteAction.action")
public class GoodsFavoriteAction extends BaseServlet {
	private static GoodsFavoriteService goodsFavoriteService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			goodsFavoriteService = (GoodsFavoriteService) context.getBean(GoodsFavoriteService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static final long serialVersionUID = 2530407763140359853L;

	
    public GoodsFavoriteAction() {
        super();
        
    }
    /**
     * 添加收藏宝贝 传入用户的id buyer_id , 店铺的id goods_id , 添加时间 goods_favorite_create_time , 更新时间 goods_favorite_update_time
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addGoodsFavorite(HttpServletRequest request, HttpServletResponse response,String Json)throws ServletException, IOException {
    	Map map=JSON.parseObject(Json,Map.class);
    	System.out.println("买家的Id"+map.get("buyer_id"));
    	System.out.println("商品的id"+map.get("goods_id"));
    	System.out.println("创建时间"+map.get("goods_favorite_create_time"));
    	System.out.println("更新时间"+map.get("goods_favorite_update_time"));
    	 map=goodsFavoriteService.addGoodsFavorite(map);
    	if(map.get("status").toString().equals("success")) {
    		System.out.println("成功");
    	}else {
    		System.out.println("失败");
    	}
    	printJson(response,map);

    	 
    }
    /** 
         * 删除收藏宝贝
         * 批量删除（）
         * 通过收藏商品id
     */
    public void removeGoodsFavorite(HttpServletRequest request, HttpServletResponse response,String Json)throws ServletException, IOException {
    	Map map=JSON.parseObject(Json,Map.class);
    
    	if(map.size()>1) {
    		List list=new ArrayList();
			list.add(map.get("goods_favorite_id"));
    		for (int i = 0; i<= list.size(); i++) {
    			System.out.println("收藏商品的id"+list.get(i));
    			map=goodsFavoriteService.removeGoodsFavorite((Map<String, Object>) list.get(i));	
			}
    	}else {
    		map=goodsFavoriteService.removeGoodsFavorite(map);	
    	}
    	
    	if(map.get("status").toString().equals("success")) {
    		System.out.println("成功");
    	}else {
    		System.out.println("失败");
    	}
    	
    	printJson(response,map);

   	 
   }
    /**  
     * 传入用户的id buyer_id 查询所有收藏宝贝     商品的名称模糊查询收藏宝贝
     * @param request
     * @param response
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public void queryGoodsFavorite(HttpServletRequest request, HttpServletResponse response,String Json) throws ServletException, IOException {
    	Map map=JSON.parseObject(Json,Map.class);
    	System.out.println("买家的Id"+map.get("buyer_id"));
    	System.out.println("商品的名称"+map.get("goods_name"));

    	map=goodsFavoriteService.queryGoodsFavorite(map);
    	printJson(response,map);

    }
    
	 

}
