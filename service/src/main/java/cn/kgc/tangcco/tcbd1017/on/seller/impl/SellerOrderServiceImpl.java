package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderService;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月13日     下午7:27:53
  类描述：
*
*/
public class SellerOrderServiceImpl implements SellerOrderService {

	SellerOrderDao sellerGoodsSellConditionDao = new SellerOrderDaoimpl();
	
	@Override
	public Map<String, Object> queryOrder(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "failed");
		List<Map<String, Object>> list = sellerGoodsSellConditionDao.selectOrderByCondition(map);
		if (list.size()>0 && list!= null) {
			result.put("status", "success");
			result.put("data", list);
		}
		return result;
	}

	

}
