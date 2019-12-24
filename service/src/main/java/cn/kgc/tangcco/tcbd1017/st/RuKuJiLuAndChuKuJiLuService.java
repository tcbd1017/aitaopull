package cn.kgc.tangcco.tcbd1017.st;

import java.util.Map;

public interface RuKuJiLuAndChuKuJiLuService {
	
	/**
	 * 查询入库记录表
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaKanRuKuJiLuBiao(Map<String,Object>map);
	
	/**
	 * 查询出库记录表
	 * @param map
	 * @return
	 */
	public Map<String,Object> ChaKanChuKuJiLuBiao(Map<String,Object>map);
}
