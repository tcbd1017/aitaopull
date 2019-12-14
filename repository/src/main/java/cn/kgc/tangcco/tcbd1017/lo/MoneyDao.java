package cn.kgc.tangcco.tcbd1017.lo;
 
import java.util.Map;

/**
 *  这是跟账单有关的表
 * @author CUI
 *
 */
public interface MoneyDao {


	/**
	 *  当创建运单时创建账单
	 * @param map 账单id,创建时间，更新时间 ,状态(默认为1.未付款)
	 * @return
	 */
	int addMoney(Map<String ,Object> map);
	
}
