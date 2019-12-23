


layui.define(function (exports) {

  /*
    下面通过 layui.use 分段加载不同的模块，实现不同区域的同时渲染，从而保证视图的快速呈现
  */

 let $url = JSON.parse(sessionStorage.getItem("url"))
 let $user = JSON.parse(sessionStorage.getItem("user"))
  //区块轮播切换
  layui.use(['admin', 'carousel'], function () {
    var $ = layui.$
      , admin = layui.admin
      , carousel = layui.carousel
      , element = layui.element
      , device = layui.device();

    //轮播切换
    $('.layadmin-carousel').each(function () {
      var othis = $(this);
      carousel.render({
        elem: this
        , width: '100%'
        , arrow: 'none'
        , interval: othis.data('interval')
        , autoplay: othis.data('autoplay') === true
        , trigger: (device.ios || device.android) ? 'click' : 'hover'
        , anim: othis.data('anim')
      });
    });

    element.render('progress');

  });

  //数据概览
  layui.use(['admin', 'carousel', 'echarts'], function () {
    var $ = layui.$
      , admin = layui.admin
      , carousel = layui.carousel
      , echarts = layui.echarts;





    
      $.ajax({
        url: $url + "/back/statistics.action?methodName=conntToDayAVGgrade",
        type: 'post',
        dataType: "json",
        data:{'uuid':$user.uuid},
        success: function (re) {
          
          var echartsApp = [], options = [

            //新增的用户量
            {
              title: {
                text: '最近一周日测平均成绩',
                x: 'center',
                textStyle: {
                  fontSize: 14
                }
              },
              tooltip: { //提示框
                trigger: 'axis',
                formatter: "{b}<br>平均成绩：{c}分"
              },
              xAxis: [{ //X轴
                type: 'category',
                data: re.listDay
              }],
              yAxis: [{  //Y轴
                type: 'value'
              }],
              series: [{ //内容
                type: 'line',
                data: re.countList,
              }]
            }
          ]
            , elemDataView = $('#LAY-index-dataview').children('div')
            , renderDataView = function (index) {
              echartsApp[index] = echarts.init(elemDataView[index], layui.echartsTheme);
              echartsApp[index].setOption(options[index]);
              window.onresize = echartsApp[index].resize;
              admin.resize(function () {
                echartsApp[index].resize();
              });
            };
    
    
          //没找到DOM，终止执行
          if (!elemDataView[0]) return;
          renderDataView(0);
    


        }
      })













    //监听数据概览轮播
    var carouselIndex = 0;
    carousel.on('change(LAY-index-dataview)', function (obj) {
      renderDataView(carouselIndex = obj.index);
    });

    //监听侧边伸缩
    layui.admin.on('side', function () {
      setTimeout(function () {
        renderDataView(carouselIndex);
      }, 300);
    });

    //监听路由
    layui.admin.on('hash(tab)', function () {
      layui.router().path.join('') || renderDataView(carouselIndex);
    });
  });












  //最新订单
  layui.use('table', function () {
    var $ = layui.$
      , table = layui.table;




  });

  exports('console', {})
});