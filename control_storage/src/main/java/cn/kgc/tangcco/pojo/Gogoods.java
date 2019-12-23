package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 *（这个表可能没用） 出货记录        记录存货  每次出 货物都要修改这个表
 * 
 *
 */
public class Gogoods {

		/**
	    * 出货表id
	    */
	    private Integer goId;

	    /**
	    * 货物id
	    */
//	    private Integer goGoodsId;
	    private Goods goods;

	    /**
	    * 店铺id
	    */
//	    private Integer goShopId;
	    private Shop shop;

	    /**
	    * 仓库uuid
	    */
	    private Integer goCangkuuuid;

	    /**
	    * 员工id
	    */
//	    private Integer goEmpId;
	    private Emp emp;

}