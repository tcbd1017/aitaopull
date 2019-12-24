package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

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
	    
	    private Function f;
	    private Shop s;
		public Integer getShopId() {
			return shopId;
		}
		public void setShopId(Integer shopId) {
			this.shopId = shopId;
		}
		public String getShopName() {
			return shopName;
		}
		public void setShopName(String shopName) {
			this.shopName = shopName;
		}
		public String getShopUuid() {
			return shopUuid;
		}
		public void setShopUuid(String shopUuid) {
			this.shopUuid = shopUuid;
		}
		public String getShopAddress() {
			return shopAddress;
		}
		public void setShopAddress(String shopAddress) {
			this.shopAddress = shopAddress;
		}
		public String getShopAccount() {
			return shopAccount;
		}
		public void setShopAccount(String shopAccount) {
			this.shopAccount = shopAccount;
		}
		public String getShopPassword() {
			return shopPassword;
		}
		public void setShopPassword(String shopPassword) {
			this.shopPassword = shopPassword;
		}
		public String getShopPhone() {
			return shopPhone;
		}
		public void setShopPhone(String shopPhone) {
			this.shopPhone = shopPhone;
		}
		public Function getF() {
			return f;
		}
		public void setF(Function f) {
			this.f = f;
		}
		public Shop getS() {
			return s;
		}
		public void setS(Shop s) {
			this.s = s;
		}
	    
}
