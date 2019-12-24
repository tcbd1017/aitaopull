$(function() {
	// allCangKu();
	// sessionStorage.setItem("shop_name", "shouji");
	// sessionStorage.setItem("shop_id", "1");
	// let account = sessionStorage.getItem("account");
	// let shop_id = sessionStorage.getItem("shop_id");
	
	// if ("" != account && null != account) {
	// 	queryAllGoods(account, shop_id);
	// }

	queryAllGoods();
})



function queryAllGoods() {
	layui.use(['table', 'form', 'laydate','laypage'], function() {
		let table = layui.table;
		let form = layui.form;
		let laydate = layui.laydate;
		var laypage = layui.laypage;


		// let dataa = {
		// 	account: account,
		// 	shop_id: shop_id,
		// 	type_name:'',
		// 	shop_name:'',
		// 	goods_w_s_uuid:''
		// }


		table.render({
			where: {
				shop_uuid:'店铺uuid001'
			},
			elem: '#test',
			url: 'http://localhost:8080/warehouse/CangKuAction.action?methodName=ShopChaXunZiJiaCangKu',
			limit:20,
			cols: [
				[{
					type: 'checkbox'
				}, {
					field: 'warehouse_shop_warehouseuuid',
					width: '10%',
					title: '仓库名称',
					align: 'center',
					
				}, 
				{
					field: 'warehouse_name',
					width: '10%',
					title: '仓库类型名称',
					align: 'center',
					
				}, 
				{
					field: 'warehouse_typesize',
					width: '10%',
					title: '仓库类型大小',
					align: 'center',
					// templet:function(data){
					// 	return data.brand.brandName
					// }
				}, {
					field: 'warehouse_address',
					width: '10%',
					title: '仓库地址',
					align: 'center',
					// templet:function(data){
					// 	return data.model.modelName
					// }
				}, {
					field: 'warehouse_shop_buytime',
					title: '购买日期',
					
					align: 'center',
					// templet:function(data){
					// 	return data.model.modelPrice
					// }
				}, {
					field: 'warehouse_shop_daoqitime',
					width: '10%',
					title: '到期日期',
					
			
					// templet:function(data){
					// 	return data.count
					// }

				},  {
					field: 'warehouse_shop_warehousesize',
					width: '10%',
					title: '仓库所剩容量',
					align: 'center'
				}, {
					field: 'warehouse_price',
					width: '10%',
					title: '仓库价格',
					align: 'center',
					// templet:function(data){
					// 	return data.shop.shopName
					// }
				},{
					field: 'warehouse_shop_buyyear',
					width: '10%',
					title: '购买时长',
					align: 'center',
					templet:function(data){
						return data.warehouse_shop_buyyear+'年'
					}
				}]
			],
			page: true,
		});
		
		
	});
}	


// function allCangKu() {
// 	$.ajax({
// 		type: "get",
// 		url: 'https://localhost:8080/warehouse/GuanLiYuanRuKuAndChuKuAction.action?methodName=ChaKanAllCangKuUUid',
// 		data: {
// 			page:1,
// 			limit:20
// 		},
// 		async: false,
// 		dataType: "json",
// 		success: function(responseText) {
// 			// window.console.log("pid>>>"+pid)
// 			// switch (responseText.status) {
// 			// 	case "success":
// 			// 		$.each(responseText => {
					
					
// 			// 			$("#goods_w_s_uuid").append('<option selected  value=' + responseText.warehouseShopWarehouseuuid + '</option>')
// 			// 				// $("#province").append('<option selected  value=' + element.id + '>' + element.name + '</option>')
						
	
// 			// 		})
// 			// 		break;
			
// 			// 	default:
// 			// 		break;
// 			// }
			
// 		}

// 	})
// }
