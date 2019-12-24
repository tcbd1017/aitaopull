package cn.kgc.tangcco.tcbd1017.st.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 出货记录表     每次出货都 对此表进行操作
 * 
 *
 */
public class Gogoodsrecord {

		/**
	    * 出库记录表id
	    */
	    private Integer gogoodsrecordId;

	    /**
	    * 出库时间
	    */
	    private Date gogoodsrecordTime;

	    /**
	    * 出库数量
	    */
	    private Integer gogoodsrecordCount;

	    /**
	    * 货物id
	    */
//	    private Integer gogoodsrecordGoodsId;
	    private Goods goods;

	    /**
	    * 店铺id
	    */
//	    private Integer gogoodsrecordShopId;
	    private Shop shop;

	    /**
	    * 员工id 外键
	    */
//	    private Integer gogoodsrecordEmpId;
	    private Emp emp;

	    /**
	    * 仓库的uuid
	    */
	    private String gogoodsrecordWSUuid;

	    /**
	    * 出库uuid (出库uuid) 相当于订单编号
	    */
	    private String gogoodsrecordUuid;

}
