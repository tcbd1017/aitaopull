package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午9:27:21
*/
@WebServlet("/xy.action")
public class RecordAction extends BaseServlet{

	static RecordService recordService ;
	static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			recordService = (RecordService) path.getBean(RecordService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static final long serialVersionUID = -8246976140474130692L;
	
	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息  (按照历史足迹时间降序排序)
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用
	 */
	public void queryAllRecord(HttpServletRequest request,HttpServletResponse response,String buyer_id) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_id)) {
			
			 map = JSON.parseObject(buyer_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_id);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryAllRecord(map.get("buyer_id").toString());
		printJson(response, map);
	}
	/**
	 * 根据传过来的历史记录id删除相对应的商品足迹（单个删除）
	 * @param record_id 历史记录id
	 */
	public void removeRecordByRecord_id(HttpServletRequest request,HttpServletResponse response,String record_id) {
		Map map=null;
		if (!StringUtils.isEmpty(record_id)) {
			
			 map = JSON.parseObject(record_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(record_id);
		}
		
		System.out.println(map.containsKey("record_id"));
		 map = recordService.removeRecordByRecord_id(map.get("record_id").toString());
		printJson(response, map);
		
		
	}
	
	
	
	
}
