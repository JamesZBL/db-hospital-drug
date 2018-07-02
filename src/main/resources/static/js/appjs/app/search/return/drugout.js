var prefix = "/search";
$(function () {
    load();
});

function load() {
    $('#exampleTable')
        .bootstrapTable(
            {
                method: 'get', // 服务器数据的请求方式 get or post
                url: prefix + "/return", // 服务器数据的加载地址
                showRefresh: true,
                // showToggle : true,
                //	showColumns : true,
                iconSize: 'outline',
                toolbar: '#exampleToolbar',
                striped: true, // 设置为true会有隔行变色效果
                dataType: "json", // 服务器返回的数据类型
                pagination: true, // 设置为true会在底部显示分页条
                // queryParamsType : "limit",
                // //设置为limit则会发送符合RESTFull格式的参数
                singleSelect: false, // 设置为true将禁止多选
                // contentType : "application/x-www-form-urlencoded",
                // //发送到服务器的数据编码类型
                pageSize: 10, // 如果设置了分页，每页数据条数
                pageNumber: 1, // 如果设置了分布，首页页码
                //search : true, // 是否显示搜索框
                showColumns: false, // 是否显示内容下拉框（选择显示的列）
                sidePagination: "server", // 设置在哪里进行分页，可选值为"client" 或者 "server"
                queryParams: function (params) {
                    return {
                        //说明：传入后台的参数包括offset开始索引，limit步长，sort排序列，order：desc或者,以及所有列的键值对
                        limit: params.limit,
                        offset: params.offset
                        // name:$('#searchName').val(),
                        // username:$('#searchName').val()
                    };
                },
                // //请求服务器数据时，你可以通过重写参数的方式添加一些额外的参数，例如 toolbar 中的参数 如果
                // queryParamsType = 'limit' ,返回参数必须包含
                // limit, offset, search, sort, order 否则, 需要包含:
                // pageSize, pageNumber, searchText, sortName,
                // sortOrder.
                // 返回false将会终止请求
                columns: [
                    {
                        field: 'drugId',
                        title: '药品编号'
                    },

                    {
                        field: 'drugName',
                        title: '药品名称'
                    },
                    {
                        field: 'specification',
                        title: '规格'
                    },
                    {
                        field: 'quantity',
                        title: '出库数量'
                    },
                    {
                        field: 'quantityNow',
                        title: '实时库存'
                    },
                    {
                        field: 'price',
                        title: '单价',
                        formatter: function (v) {
                            return v.toFixed(2);
                        }
                    },
                    {
                        field: 'ammount',
                        title: '总金额',
                        formatter: function (v) {
                            return v.toFixed(2);
                        }
                    },
                    {
                        field: 'comment',
                        title: '备注'
                    },
                    {
                        field: 'supplierName',
                        title: '供应商'
                    }, {
                        field: 'manager',
                        title: '经办人'
                    },
                    {
                        field: 'gmtCreated',
                        title: '出库时间'
                    }
                ]
            });
}

function reLoad() {
    $('#exampleTable').bootstrapTable('refresh');
}