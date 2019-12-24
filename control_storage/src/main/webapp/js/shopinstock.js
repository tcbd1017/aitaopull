
let $url = "";
	$.getJSON("res/serverconfig.json", (responseText) => {
	    $url = responseText.protocol + responseText.domain + responseText.port + responseText.context;
	    window.console.log($url)
	});
	
   $(document).ready(function() {
       layui.use(['form'], function(){
           var form = layui.form;
           //监听 仓库类型下拉框
           $.ajax({
                   url: $url + "/CangKuAction.action?methodName=AllCangKuFenLei",
                   type: 'post', //HTTP请求类型
                   dataType: 'json', //服务器返回json格式数据
                   data: {
                   },
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
			        url: $url + "/ShangJiChuKuAction.action?methodName=ChaXunLeiXing",
			        type: 'post', //HTTP请求类型
			        dataType: 'json', //服务器返回json格式数据
			        data: {
			        },
			        // async: true,
			        success: function(data) {
						   // window.console.log(data.data)
						    let a = data.data
						   window.console.log(a)
			            $.each(a, function(index, item) {
							   // window.console.log(item.name)
			                $('#typename').append(new Option(item.name,item.typeId)); // 下拉菜单里添加元素
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
			         url: $url + "/ShangJiChuKuAction.action?methodName=ChaXunPinPai",
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
			                 $('#brandname').append(new Option(item.brandName,item.brandId)); // 下拉菜单里添加元素
			             });
			             layui.form.render("select");
			         }
			  });
			  $.ajax({
			          url: $url + "/ShangJiChuKuAction.action?methodName=ChaXunXingHao",
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
			                  $('#modelname').append(new Option(item.modelName,item.modelId)); // 下拉菜单里添加元素
			              });
			              layui.form.render("select");
			          }
			   });
			   //仓库uuid
			   $.ajax({
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
			    });
       })
   })

layui.use('form', function() {
            var form = layui.form;

            //监听提交
            form.on('submit(formDemo)', function(data) {
                // layer.msg(JSON.stringify(data.field));
				// window.console.log(JSON.stringify(data.field));
				// window.console.log(data.field.typename);
				// window.console.log(data.field.brandname);
				
				
						
						$.ajax({
										url:  $url + "/ShangJiRuKuAction.action?methodName=KaiShiRuKu",
										type: 'post',
										data:{
											"jiance_comegoodsrecored_count":data.field.count,
											"jiance_type_id":data.field.typename,
											"jiance_brand_id":data.field.brandname,
											"jiance_model_id": data.field.modelname,
											"jiance_shangpuuuid": "店铺uuid001",
											"jiance_cangkuuuid":data.field.wuuid
										},
										dataType: "text",
										success: function(data){
											alert("aaaa")
											window.console.log(data);
											var ac = JSON.parse(data)
											window.console.log(ac);
											// window.console.log(ac.data);
											if(ac.data != 0 && ac.status == "success"){
												// alert("保存成功!");
												layer.msg("入库成功!");
											form.render()
											
											// window.location.href="back_index1.html"
											// console.log(sessionStorage.getItem('user').email)
											}else if(ac.data == 0 || ac.status == "failed"){
												layer.msg("入库失败!");
											}
										},
										error:function(){ 
											alert("系统异常！");
										}
									});	
                return false;
            });
        });
