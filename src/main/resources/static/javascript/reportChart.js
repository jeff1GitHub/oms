function drawChart(data){
	var seriess = eval('(' + '[]' + ')');
	var yTitle = new Array();
	var dataTitle = new Array();
	var j = 0;
	for(var i = 0; i< data.length; i++){
		var map = data[i];
		j = 0;
		for(var key in map){
			if(key != 'platform'){
				if(i == 0){
					var tmp = eval('(' + "{'name':'"+key+"'," +
							  "'type':'bar'," +
							  "'stack':'总量'," +
							  "'itemStyle' : { 'normal': {'label' : {'show': 'true', 'position': 'insideRight'}}}," +
							  "'data' : ["+ map[key] +"]}" + ')');
					seriess.push(tmp);
				} else {
					seriess[j].data.push(map[key]);
				}
				dataTitle[j] = key;
				j++;
			} else {
				yTitle[i] = map[key];
			}
		}
	}
	
	//路径配置
	require.config({
	    paths: {
	        echarts: 'http://echarts.baidu.com/build/dist/'
	    }
	});
	
	require(
        [
            'echarts',
            'echarts/chart/bar'   // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
        ],
        function (ec) {
            var option = {
            	    tooltip : {
            	        trigger: 'axis',
            	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            	        }
            	    },
            	    legend: {
            	        data: dataTitle
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            dataView : {show: true, readOnly: false},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    calculable : true,
            	    xAxis : [
            	        {
            	            type : 'value'
            	        }
            	    ],
            	    
            	    yAxis : [
            	        {
            	            type : 'category',
            	            data : yTitle
            	        }
            	    ],
            	    series : seriess
            	}
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption(option);
        }
    );
}