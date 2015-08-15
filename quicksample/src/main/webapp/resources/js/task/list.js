$(document).ready(function() {
	var ctx = $("#ctx").val();
	
	var paras = $("#form-search").serialize();
	
	$("#taskList").dataTable({
        "language": {
            "url": ctx + "/resources/language/task_list_zh_CN.txt"
        },
        "bFilter": false,//去掉搜索框
        //"bAutoWidth": true, //自适应宽度
        "sPaginationType" : "full_numbers",
        //"sAjaxDataProp" : "taskList",
        "bDestroy" : true,
        "bProcessing" : true,
        "bServerSide" : true,
        "sAjaxSource" : ctx + "/task/list?now=" + new Date().getTime() + "&" + paras,
        
        "aoColumns" : [ 
            {"mDataProp" : "title"},
            {"mDataProp" : "description"}
        ],
        "bAutoWidth": true, //自适应宽度
        "sPaginationType" : "full_numbers",
    });
});