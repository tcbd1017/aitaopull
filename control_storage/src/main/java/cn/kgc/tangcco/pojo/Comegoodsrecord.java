package cn.kgc.tangcco.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 进货记录      记录存货  每次进 货物都要修改这个表
 *
 */
public class Comegoodsrecord {

	/**
	    * 进货记录表id
	    */
	    private Integer comegoodsrecordId;

	    /**
	    * 进货时间
	    */
	    private Date comegoodsrecordTime;

	    /**
	    * 进货数量
	    */
	    private Integer comegoodsrecordCount;

	    /**
	    * 货物id
	    */
//	    private Integer comegoodsrecordGoodsId;
	    private Goods goods;

	    /**
	    * 店铺id
	    */
//	    private Integer comegoodsrecordShopId;
	    private Shop shop;

	    /**
	    * 员工id 外键
	    */
	    //private Integer comegoodsrecordEmpId;
	    private Emp emp;

	    /**
	    * 仓库的uuid
	    */
	    private String comegoodsrecordWSUuid;

	    /**
	    * 入库uuid  (入库单号)
	    */
	    private String comegoodsrecordUuid;

}
