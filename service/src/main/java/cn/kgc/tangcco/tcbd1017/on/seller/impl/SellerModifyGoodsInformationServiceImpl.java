package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerModifyGoodsInformation;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerModifyGoodsInformationService;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月21日     下午4:49:30
  类描述：
*
*/
public class SellerModifyGoodsInformationServiceImpl implements SellerModifyGoodsInformationService {

	SellerModifyGoodsInformation sellerModifyGoodsInformation = new SellerModifyGoodsInformationDaoImpl();
	
	@Override
	public Map<String, Object> modifyGoods(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int modifyGoodsInformation = sellerModifyGoodsInformation.modifyGoodsInformation(map);
			if (modifyGoodsInformation >0) {
				result.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
