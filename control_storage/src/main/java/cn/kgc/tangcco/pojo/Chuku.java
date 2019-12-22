package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 库存
 * 
 *
 */
public class Chuku {

	/**
	    * 出库表id
	    */
	    private Integer chukuId;

	    /**
	    * 出库货物品牌id
	    */
	    private Brand brand;

	    /**
	    * 出库货物类型id
	    */
	    private Type type;

	    /**
	    * 出库货物型号id 
	    */
	    private Model model;

	    /**
	    * 出库货物数量
	    */
	    private Integer chukuGogoodsrecoredCount;

	    /**
	    * 店铺uuid
	    */
	    private String chukuShangpuuuid;

	    /**
	    * 出库货物价格
	    */
	    private Double chukuPrice;

	    /**
	    * 仓库uuid
	    */
	    private String chukuCangkuuuid;

	    /**
	    * 出库记录表uuid  详单与订单编号
	    */
	    private String chukuChukujiluuuid;

	    /**
	    * 出库状态 1 出库成功 2库存不足出库失败 
	    */
	    private Integer chukuFlag;
	    
	    /**
	    * 货物信息   出库时直接读库存
	     */
	    private Goods goods;  
}