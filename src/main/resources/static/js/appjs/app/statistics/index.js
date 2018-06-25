var prefix = "/sale";
// 几月几日
var days = [];
// 几月
var months = [];
// 哪年
var years = [];
// 每日销量
var vSaleCountDay = [];
// 每月销量
var vSaleCountMonth = [];
// 每年销量
var vSaleCountYear = [];


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
                vSaleCountDay.push(v.saleCount);
            });
            loadDay();
        }
    });
    $.ajax({
        type: "GET",
        url: "/sale/sta_sale_month",
        dataType: 'json',
        async: true,
        error: function (request) {
            layer.alert("获取数据失败");
        },
        success: function (data) {
            $.each(data, function (i, v) {
                months.push(v.dateUnit);
                vSaleCountMonth.push(v.saleCount);
            });
            loadMonth();
        }
    });
    $.ajax({
        type: "GET",
        url: "/sale/sta_sale_year",
        dataType: 'json',
        async: true,
        error: function (request) {
            layer.alert("获取数据失败");
        },
        success: function (data) {
            $.each(data, function (i, v) {
                years.push(v.dateUnit);
                vSaleCountYear.push(v.saleCount);
            });
            loadYear();
        }
    });
}

function loadDay() {
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
            data: vSaleCountDay
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function loadMonth() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('id_month'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '今年每月销量统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}: {c0} 件'
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: months
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
            data: vSaleCountMonth
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}

function loadYear() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('id_year'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '近5年销量统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            formatter: '{b0}: {c0} 件'
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: years
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
            data: vSaleCountYear
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}