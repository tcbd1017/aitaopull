package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 店铺详细信息表
 *
 */
public class Shop {

	/**
	    * 店铺id
	    */
	    private Integer shopId;

	    /**
	    * 店铺名称
	    */
	    private String shopName;

	    /**
	    * 店铺唯一标识符
	    */
	    private String shopUuid;

	    /**
	    * 店铺地点
	    */
	    private String shopAddress;

	    /**
	    * 店铺账号
	    */
	    private String shopAccount;

	    /**
	    * 店铺密码
	    */
	    private String shopPassword;

	    /**
	    * 店铺联系方式
	    */
	    private String shopPhone;

}
