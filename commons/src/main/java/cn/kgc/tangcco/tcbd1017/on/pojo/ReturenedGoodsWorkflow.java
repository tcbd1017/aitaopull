package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 上午11:35:46 
*    类说明 : 退货流程表
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturenedGoodsWorkflow {
	/**
	 * 退货流程表id
	 */
	private int returned_goods_workflow_id;
	/**
	 * 退货表id
	 */
	private int returned_goods_id;
	/**
	 * 退货申请状态 1未开始 2已完成
	 */
	private int returned_goods_workflow_return_request_status;
	/**
	 * 卖家审核退货请求  1未同意  2同意
	 */
	private int returned_goods_workflow_seller_return_audit;
	/**
	 * 物流单号是否产生   1未生成  2已生成
	 */
	private int returned_goods_workflow_logistics_uuid;
	/**
	 * 仓储是否确认收货  1未收到  2收到
	 */
	private int returned_goods_workflow_confirm_goods;
	/**
	 * 是否确认退款  1未退款  2退款
	 */
	private int returned_goods_workflow_refund;
	/**
	 *退货工作流的创建时间
	 */
	private Date returned_goods_workflow_create_time;
	/**
	 *退货工作流的更新时间
	 */
	private Date returned_goods_workflow_update_time;
	/**
	 *退货工作流的状态 1未完成 2完成
	 */
	private int returned_goods_workflow_status;
	
}
