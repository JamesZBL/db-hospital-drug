var prefix = "/sale";
// 几月几日
var days = [];
// 每日销量
var vSaleCount = [];

$(function () {
    getData();
});

function getData() {
    $.ajax({
        type: "GET",
        url: "/sale/sta_sale_day",
        dataType: 'json',
        async: true,
        error: function (request) {
            layer.alert("获取数据失败");
        },
        success: function (data) {
            $.each(data, function (i, v) {
                days.push(v.dateUnit);
                vSaleCount.push(v.saleCount);
            });
            load();
        }
    });
}

function load() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('id_day'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '近30天日销量统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}: {c0} 件'
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: days
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            name: 'count',
            type: 'line',
            // smooth: true,
            areaStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: 'rgb(255, 158, 68)'
                    }, {
                        offset: 1,
                        color: 'rgb(255, 70, 131)'
                    }])
                }
            },
            data: vSaleCount
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}