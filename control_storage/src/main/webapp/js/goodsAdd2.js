$(function() {
	// allCangKu();
	sessionStorage.setItem("account", "shouji");
	sessionStorage.setItem("shop_id", "1");
	let account = sessionStorage.getItem("account");
	let shop_id = sessionStorage.getItem("shop_id");
	
	if ("" != account && null != account) {
		queryAllGoods(account, shop_id);
	}
	

})




function queryAllGoods(account, shop_id) {
	layui.use(['table', 'form', 'laydate','laypage'], function() {
		let table = layui.table;
		let form = layui.form;
		let laydate = layui.laydate;
		var laypage = layui.laypage;


		let dataa = {
			account: account,
			shop_id: shop_id,
			type_name:'',
			shop_name:'',
			goods_w_s_uuid:''
		}


		table.render({
			where: dataa,
			elem: '#test',
			url: '/warehouse/GoodsAction.action?methodName=SelectAllgoods',
			limit:20,
			cols: [
				[{
					type: 'checkbox'
				}, {
					field: 'goodsId',
					width: '10%',
					title: 'ID',
					
					align: 'center',
					templet:function(data){
						return data.goodsId
					}
				}, 
				{
					field: 'name',
					width: '10%',
					title: '货物类型',
					align: 'center',
					templet:function(data){
						return data.type.name
					}
				}, 
				{
					field: 'brandName',
					width: '10%',
					title: '货物品牌',
					align: 'center',
					templet:function(data){
						return data.brand.brandName
					}
				}, {
					field: 'model_name',
					width: '10%',
					title: '货物型号',
					align: 'center',
					templet:function(data){
						return data.model.modelName
					}
				}, {
					field: 'model_price',
					title: '货物价格',
					
					align: 'center',
					templet:function(data){
						return data.model.modelPrice
					}
				}, {
					field: 'count',
					width: '10%',
					title: '货物数量',
					
			
					templet:function(data){
						return data.count
					}

				},  {
					field: 'goodsWSUuid',
					width: '10%',
					title: '仓库类型',
					align: 'center'
				}, {
					field: 'shop_name',
					width: '10%',
					title: '所属店铺',
					align: 'center',
					templet:function(data){
						return data.shop.shopName
					}
				}]
			],
			page: true,
		});
		
		
	});
}	


function queryAllGoodsBy(account, shop_id,type_name,cangku) {
	layui.use(['table', 'form', 'laydate','laypage'], function() {
		let table = layui.table;
		let form = layui.form;
		let laydate = layui.laydate;
		var laypage = layui.laypage;


		let dataa = {
			account: account,
			shop_id: shop_id,
			type_name:type_name,
			shop_name:cangku
		}


		table.render({
			where: dataa,
			elem: '#test',
			url: '/warehouse/GoodsAction.action?methodName=SelectAllgoods',
			limit:20,
			cols: [
				[{
					type: 'checkbox'
				}, {
					field: 'goodsId',
					width: '10%',
					title: 'ID',
					
					align: 'center',
					templet:function(data){
						return data.goodsId
					}
				}, 
				{
					field: 'name',
					width: '10%',
					title: '货物类型',
					align: 'center',
					templet:function(data){
						return data.type.name
					}
				}, 
				{
					field: 'brandName',
					width: '10%',
					title: '货物品牌',
					align: 'center',
					templet:function(data){
						return data.brand.brandName
					}
				}, {
					field: 'model_name',
					width: '10%',
					title: '货物型号',
					align: 'center',
					templet:function(data){
						return data.model.modelName
					}
				}, {
					field: 'model_price',
					title: '货物价格',
					
					align: 'center',
					templet:function(data){
						return data.model.modelPrice
					}
				}, {
					field: 'count',
					width: '10%',
					title: '货物数量',
					
			
					templet:function(data){
						return data.count
					}

				},  {
					field: 'goodsWSUuid',
					width: '10%',
					title: '仓库类型',
					align: 'center'
				}, {
					field: 'shop_name',
					width: '10%',
					title: '所属店铺',
					align: 'center',
					templet:function(data){
						return data.shop.shopName
					}
				}]
			],
			page: true,
		});
		
		
	});
}	



