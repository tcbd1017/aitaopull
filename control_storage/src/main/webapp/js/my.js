$(document).ready(function() {
	layui.use(['form'], function() {
		var form = layui.form;
		//监听 仓库类型下拉框
		$.ajax({
			url: "http://localhost:8080/control_storage/CangKuAction.action?methodName=AllCangKuFenLei",
			type: 'post', //HTTP请求类型
			dataType: 'json', //服务器返回json格式数据
			data: {},
			// async: true,
			success: function(data) {
				// window.console.log(data.data)
				let a = data.data.selectCangKuLeixing.selectCangKuLeixing
				window.console.log(a)
				$.each(a, function(index, item) {
					// window.console.log(item.warehouseShopWarehousesize) warehouse_name
					// $('#warehousesize').append(new Option(item.warehouse_typesize)); // 下拉菜单里添加元素
					$('#warehousesize').append(new Option(item.warehouse_name));
				});
				layui.form.render("select");
			}
		});
		//商品类型下拉框
		$.ajax({
			url: "http://localhost:8080/control_storage/ShangJiChuKuAction.action?methodName=ChaXunLeiXing",
			type: 'post', //HTTP请求类型
			dataType: 'json', //服务器返回json格式数据
			data: {},
			// async: true,
			success: function(data) {
				// window.console.log(data.data)
				let a = data.data
				window.console.log(a)
				$.each(a, function(index, item) {
					// window.console.log(item.name)
					$('#typename').append(new Option(item.name, item.typeId)); // 下拉菜单里添加元素
				});

				layui.form.render("select");
			},

		});
		//商品品牌下拉框
		// let typeid = $("#typename").val();
		//  function type(){
		// 	 var first = document.getElementById("typename")
		// 	 window.console.log(first)
		// 	 var b = first.selectedIndex
		// 	 window.console.log(b)
		// }
		// $("#typename").on("click", function(e) {

		//    var first = document.getElementById("typename")
		//    window.console.log(first)
		//    var b = first.selectedIndex
		//    window.console.log(b)
		// });
		// let b =$('#typename').value();
		// window.console.log(b)
		$.ajax({
			url: "http://localhost:8080/control_storage/ShangJiChuKuAction.action?methodName=ChaXunPinPai",
			type: 'post', //HTTP请求类型
			dataType: 'json', //服务器返回json格式数据
			data: {
				// brand_type_id:typeid
			},
			// async: true,
			success: function(data) {
				let a = data.data
				window.console.log(a)
				$.each(a, function(index, item) {
					// window.console.log(item.name)
					$('#brandname').append(new Option(item.brandName, item.brandId)); // 下拉菜单里添加元素
				});
				layui.form.render("select");
			}
		});
		$.ajax({
			url: "http://localhost:8080/control_storage/ShangJiChuKuAction.action?methodName=ChaXunXingHao",
			type: 'post', //HTTP请求类型
			dataType: 'json', //服务器返回json格式数据
			data: {
				// brand_type_id:typeid
			},
			async: true,
			success: function(data) {
				// window.console.log(data.data)
				let a = data.data
				window.console.log(a)
				$.each(a, function(index, item) {
					// window.console.log(item.name)
					let typename = $("#typename").val()
					window.console.log(typename)
					$('#modelname').append(new Option(item.modelName, item.modelId)); // 下拉菜单里添加元素
				});
				layui.form.render("select");
			}
		});
		//仓库uuid
		/* $.ajax({
		        url: $url + "/CangKuAction.action?methodName=AllGouMaiCangKuShop",
		        type: 'post', //HTTP请求类型
		        dataType: 'json', //服务器返回json格式数据
		        data: {
						
		        },
		        async: true,
		        success: function(data) {
					   // window.console.log(data.data)
					    let a = data.data.chakanxiangqingAllCangKu
					   window.console.log(a)
		            $.each(a, function(index, item) {
						   // window.console.log(item.name)
		                $('#wuuid').append(new Option(item.warehouseShopWarehouseuuid)); // 下拉菜单里添加元素
		            });
		            layui.form.render("select");
		        }
		 }); */
	})
})

layui.use('form', function() {
	var form = layui.form;

	//监听提交
	form.on('submit(formDemo)', function(data) {

		//      layer.msg(JSON.stringify(data.field));
		window.console.log(data.field.typename);
		window.console.log(data.field.brandname);
		window.console.log(data.field.modelname);
		/* $.ajax({
			url: "http://localhost:8080/warehouse/GoodsAction.action?methodName=SelectAllgoods",
			type: 'post',
			data: {

				"jiance_type_id": data.field.typename,
				"jiance_brand_id": data.field.brandname,
				"jiance_model_id": data.field.modelname,
			},
			dataType: "text",
			success: function(data) {
				alert("aaaa")
			},
			error: function() {
				alert("系统异常！");
			}
		});
		return false; */
		
		layui.use('table', function() {
			let table = layui.table;
			// 声明对象存储查询条件
			let param = {
				methodName: "SelectAllgoods",
				account: "shouji",
				goods_id: 1
			};
			table.render({
				// 异步请求时携带的参数
				where: param,
			//	checkbox: true,
				elem: '#test',
				//    url: "http://localhost:8080/warehouse/GoodsAction.action?methodName=SelectAllgoods&account=shouji&shop_id=1",
				url: "http://localhost:8080/control_storage/GoodsAction.action?methodName=SelectAllgoods",
			//	method: 'post',
				//	   layout:['prev', 'page', 'next],
				page: {
				    prev: '上一页',
				    next: '下一页',
				},
				limit: 5,
				limits: [5, 10, 15, 20, 25, 30, 35, 40, 45, 50],
				
				cols: [
					[{
						field: 'goodsId',
						width: '10%',
						title: '货物ID',
						sort: true,
						align: 'center'
					}, {
						field: 'model_name',
						width: '18%',
						title: '货物名称',
						align: 'center',
						templet: function(d) {
							return d.model.modelName
						}
					}, {
						field: 'count',
						width: '18%',
						title: '货物剩余数量',
						sort: true,
						align: 'center',
		
					}, {
						field: 'type_name',
						width: '18%',
						title: '货物类型',
						align: 'center',
						templet: function(d) {
							return d.type.name
						}
					}, {
						field: 'brand_name',
						title: '货物品牌',
						width: '18%',
						// minWidth: 150, //minWidth：局部定义当前单元格的最小宽度，layui 2.2.1 新增
						align: 'center',
						templet: function(d) {
							return d.brand.brandName
						}
					}, {
						field: 'model_price',
						// width: '18%',
						title: '货物价格',
						sort: true,
						align: 'center',
						templet: function(d) {
							return d.model.modelPrice
						}
					}, {
						field: 'model_size',
						// width: '18%',
						title: '货物大小',
						sort: true,
						align: 'center',
						templet: function(d) {
							return d.model.modelSize
						}
					}]
				],
			});
		});
	});
});
