$(document).ready(function() {
	initTable();
	$("#search_btn").click(function() {
		initTable();
	});

});


var initTable = function() {
	
	var ctx = $("#ctx").val();
	
	var paras = getFormSearchParams("form-search");
	
	$("#taskList").dataTable({
        "language": {
            "url": ctx + "/resources/language/task_list_zh_CN.txt"
        },
        "aLengthMenu": [[3, 10, 30, 50], [3, 10, 30, 50]],
        "bFilter": false,//去掉搜索框
        //"bAutoWidth": true, //自适应宽度
        "sPaginationType" : "full_numbers",
        //"sAjaxDataProp" : "taskList",
        "bDestroy" : true,
        "bProcessing" : true,
        "bServerSide" : true,
        "sAjaxSource" : ctx + "/task/list?now=" + new Date().getTime() + "&" + paras,
        
        "aoColumns" : [ 
            
            {
                "mData":"title",
                "bSortable": true,
               
        	} ,
            {
        		"mData" : "description",
        		"bSortable": false,
        	},
            
        	 {
                "mData":"operation",
                "bSortable": false,
                "render": function ( data, type, row ) {
                	console.log(data)
                	return "<a href=" + ctx + "/task/delete/" + row.id + ">删除</a>";
                }
        	} 
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType" : "full_numbers",
    });
}


var getFormSearchParams = function(formId) {
	var searchInputs = $("#" + formId).find("input[name^='search_']");
	var param = "";
	var len = searchInputs.length;
	$.each(searchInputs, function(index, ele) {
		param += "searchParams['" + ele.name + "']=" + ele.value;
		if(index < len - 1) {
			param += "&";
		}
	});
//	console.log("param: " + param);
	return param;
}

