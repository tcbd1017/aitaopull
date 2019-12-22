package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.localdatetime.LocalDateTimeUtil;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerModifyGoodsInformationService;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.SellerModifyGoodsInformationServiceImpl;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月21日     下午4:58:20
  类描述：
*
*/
@WebServlet(urlPatterns = "/sellerGoodsModify.action")
public class SellerGoodsModifyAction extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4483095729748784758L;

	
	SellerModifyGoodsInformationService sellerModifyGoodsInformationService = new SellerModifyGoodsInformationServiceImpl();
	
	
	public void modifyGoods(HttpServletRequest request , HttpServletResponse response , String json) {
		Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
		map.put("goods_update_time",LocalDateTimeUtil.getStringByCurrentLocalDateTime() );
		Map modifyGoods = sellerModifyGoodsInformationService.modifyGoods(map);
		printJson(response, modifyGoods);
	}

}
