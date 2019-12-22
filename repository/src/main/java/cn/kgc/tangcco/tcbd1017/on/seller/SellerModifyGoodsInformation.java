package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.Map;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月21日     下午3:31:02
  类描述：
*
*/
public interface SellerModifyGoodsInformation {

	/**
	 * 根据商品id修改商品的详细信息
	 * @param map
	 * @return
	 */
	public abstract int modifyGoodsInformation(Map<String, Object> map);
	
}
