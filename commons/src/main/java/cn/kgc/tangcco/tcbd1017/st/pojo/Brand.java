package cn.kgc.tangcco.tcbd1017.st.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 货物品牌
 */
public class Brand {
	/**
	    * 品牌id
	    */
	    private Integer brandId;

	    /**
	    * 品牌名称
	    */
	    private String brandName;

//	    /**
//	    * 类型id
//	    */
//	    private List<Model> model;

}