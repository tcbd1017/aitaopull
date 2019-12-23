package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 货物型号
 *
 */
public class Model {

	/**
	    * 型号id
	    */
	    private Integer modelId;

	    /**
	    * 型号名称
	    */
	    private String modelName;


	    /**
	    * 品牌id
	    */
//	    private Integer modelBrandId;

	    /**
		    * 货物价格
		*/
		private Double modelPrice;

		/**
		    * 货物型号大小
		    */
		private Integer modelSize;
	

}