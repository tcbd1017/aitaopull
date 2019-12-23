package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 货物信息表  根据这张表 可以查询全部的  在库数量   货物详情      每次出入库都对此操作都对库存操作
 *
 */
public class Goods {

		/**
	    * 货物id
	    */
	    private Integer goodsId;

	    /**
	    * 货物类型id
	    */
//	    private Integer goodsTypeId;
	    private Type type;

	    /**
	    * 货物型号id
	    */
//	    private Integer goodsModelId;
	    private Model model;

	    /**
	    * 货物品牌id
	    */
//	    private Integer goodsBrandId;
	    private Brand brand;

	    /**
	    * 店铺跟仓库中的货物关联
	    */
//	    private Integer goodsShopId;
	    private Shop shop;

	    /**
	    * 仓库uuid
	    */
	    private String goodsWSUuid;
	    
	    /**
	     * 货物库存 （没次出入库都对此操作）
	     */ 
	   private Integer  count; 
	   
	   
	/**
	 * 型号状态 1 存在 2 不存在
	 */
	private Integer modelFlag;
}
