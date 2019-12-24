package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 检测货物 此表是            商家  和   入货员 共同检测的表    通过  判断  到期时间 和  仓库剩余容量    来决定是否入库      1  入库成功  把信息加到相应的位置
 * 																		2   入库失败  会把消息显示到页面上   
 *
 */
public class Jiance {

		/**
	    * 检测货物表id
	    */
	    private Integer jianceId;

	    /**
	    * 货物品牌id
	    */
	    private Brand brand;

	    /**
	    * 货物类型id
	    */
	    private Type type;

	    /**
	    * 货物型号id
	    */
	    private Model model;

	    /**
	    * 入库货物数量
	    */
	    private Integer jianceComegoodsrecoredCount;

	    /**
	    * 商铺uuid
	    */
	    private String jianceShangpuuuid;

	    /**
	    * 入库货物价格
	    */
	    private Double jiancePrice;

	    /**
	    * 仓库的uuid
	    */
	    private String jianceCangkuuuid;

	    /**
	    * 入库记录 uuid    相当于订单编号
	    */
	    private String jianceRukujiluuuid;

	    /**
	    * 货物状态（1 库满 2 正在等待入库 3 入库成功 4到期了）
	    */
	    private Integer jianceFlag;
	    
	    /**
	     * 存入一个仓库对象 在入库员入库的时候 显示剩余容量  和时间所用
	     */
	    private WarehouseShop ware;
	    
}
