var TableInit = function () {
    var oTableInit = new Object();

    //function (tableId, tableHeight, url, columnsData, PF, uniqueId)
    //tableId : 表格的ID,
    //tableHeight : 表格的高度,
    //url : 获取数据的地址,
    //columnsData : 自定义的字段匹配,
    //PF : 获取数据时需要传递的参数
    //RH : 处理返回值数据的方法
    //uniqueId : 标识列，一般是你数据表里的主键，便于后期的其他操作，如：删除、更新等

    oTableInit.Init = function (tableId, tableHeight, url, columnsData, PF,RH, uniqueId) {
        $('#' + tableId).bootstrapTable({
            url: url,
            method: 'get',
            striped: true,
            //cache: false,
            pagination: true,
            //sortable: true,
            //sortOrder: "asc",
            queryParams: PF,
            sidePagination: "server",
            pageNumber: 1,
            pageSize: 10,
            pageList: [10, 25, 50, 100],
            //search: false,
            //strictSearch: true,
            //showColumns: true,
            showRefresh: false,
           // minimumCountColumns: 2,
            //clickToSelect: false,
            //height: tableHeight,
            uniqueId: uniqueId,
            //showToggle: false,
            //cardView: false,
            //detailView: false,
            columns: columnsData,
            responseHandler:RH
        });
    };
    return oTableInit;
};
