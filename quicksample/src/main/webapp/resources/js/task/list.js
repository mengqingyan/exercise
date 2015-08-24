$(document).ready(function() {
	initTable();
	$("#search_btn").click(function() {
		initTable();
	});
	
	$("#weather_btn").click(function() {
		getWeather();
	});
});

var ctx = $("#ctx").val();

var initTable = function() {
	
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
//                	console.log(data)
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

var showTaskContent = function() {
	$('#weather').hide();
	$('#taskcontent').show();
}

var getWeather = function() {
	
	
	var url = ctx + "/weather.json?cityCode=101280101";
	$('#weather').show();
	$('#taskcontent').hide();
	$.get(url,function(data,status){
//	    alert("Data.high_temps: " + data.high_temps + "\nData.low_temps: " + data.low_temps);
		drawWeatherInfo(data);
	  });
}

var drawWeatherInfo = function(data) {
	 $('#container').highcharts({
	        title: {
	            text: 'Future Temperature',
	            x: -20 //center
	        },
	        subtitle: {
	            text: 'Source: api.k780.com',
	            x: -20
	        },
	        xAxis: {
	            categories: data.dates
	        },
	        yAxis: {
	            title: {
	                text: 'Temperature (°C)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
//	        tooltip: {
//	            valueSuffix: '°C'
//	        },
	        tooltip: {
	            enabled: true,
//	            formatter: function() {
//	                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
//	            },
	            useHTML: true,
	            headerFormat: '<small>{point.key}</small><br/>',
	            valueSuffix: ' ℃',
	            shared: true
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        plotOptions: {
	            line: {
	                dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: true
	            }
	        },
	        series: [{
	            name: 'Daily high temp',
	            data: data.high_temps
	        }, {
	            name: 'Daily low temp',
	            data: data.low_temps
	        }]
	    });
}

