// 是否循环播放
var loopSound = null;
// 播放状态(0:关闭  1:开启)
var playing = 1;

// 播放器
function playSound(){
	//获取消息数量
	var csCount = $("#csCount").text();
	var opCount = $("#opCount").text();
	
	//如果当前播放状态为开启
	if(playing === 1){
		// 如果有消息
		if(csCount>0 || opCount>0){
			//循环播放提示音 2000ms间隔
			loopSound = setInterval(function(){
				document.getElementById('tipMsg').play();
			}, 2000);
		} else {
			// 没有消息，取消循环播放
			clearInterval(loopSound);
		}
	} else {
		//关闭播放状态，停止播放
		clearInterval(loopSound);
	}
}

// 关闭或者打开声音
function stopOrPlaySound() {
	var audio = document.getElementById('tipMsg');

    var $hAudio = $("#Hui-audio");
	//如果为开启，进行关闭设置
	if(playing  === 1){
		playing = 0;
        $hAudio.find("i").remove();
        $hAudio.find("a").attr("title", "打开声音");
		$('<i class="Hui-iconfont" style="font-size:24px">&#xe6e6;</i>').appendTo($hAudio.find("a"));
		
	//如果为关闭，进行开启设置
	} else if(playing === 0){
		playing = 1;
        $hAudio.find("i").remove();
        $hAudio.find("a").attr("title", "关闭声音");
		$('<i class="Hui-iconfont" style="font-size:24px">&#xe6e5;</i>').appendTo($hAudio.find("a"));
	}
	//调用播放器
	playSound();
}


// 用户登出
function logout(){
	layer.confirm('确定要退出系统？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: "user-logout",
            success: function (result) {
            	layer.msg(result.message, {icon: 1, time: 2000});
            	window.location.href = "login";
            },
            error: function () {
            }
        });
    });
}

$(function(){
	var reConn = null;
	
	//接受服务端返回的消息，设置客服任务和运维任务的条数
	function setCountsForCsAndOp(response){
		var obj = eval( "(" + response.body + ")" );
    	$("#csCount").text(obj.csCount);
    	$("#opCount").text(obj.opCount);
    	// 播放提示音
		playSound();
	}
	
	// 调用后台任务数量推送
	function getWorkCount(){
		$.ajax({
		    url: "getWorkCount",
		    success: function (result) {
		    },
		    error: function () {
		    }
		});
	}

	var keepAliveInterval = null;
	// 保持Session不失效
	function keepAlive(){
		$.ajax({
		    url: "keepAlive"+"?time="+ new Date().valueOf(),
		    success: function (result) {
		    	if(result.code == 200){
		    		console.log("keepAlive: " + result.data);
		    	} else {
		    		//$(window).attr('location','/login');
		    		// 服务重启导致session失效后，停止轮询
		    		clearInterval(keepAliveInterval);
		    	}
		    },
		    error: function (result) {
		    	clearInterval(keepAliveInterval);
		    }
		});
	}
	
	//保持session连接不失效
	keepAliveInterval = setInterval(keepAlive, 60*10*1000);
	
	var reConTimes = 0;
	var reConMills = 5000;
	// webSocket链接或者断线重连
	function connectAndReconnect() {
		// 重连5次
		if(reConTimes>5){
			reConTimes = 0;
			reConMills = 5000;
			console.log("webSocket reconnetion canceled...");
			return;
		}
		//消息推送webSocket连接创建
		var stompClient = null;
		var socket = new SockJS('/workEndPoint');
		stompClient = Stomp.over(socket);
		
		//设置心跳毫秒
		stompClient.heartbeat.outgoing = 10000;
		stompClient.heartbeat.incoming = 10000;
		
		//发起连接
		stompClient.connect({},//header不设置
			//成功链接
			function (frame) {
				//消息订阅
				stompClient.subscribe('/topic/getResponse', function (response) {
					// 接受服务端返回的消息，设置客服任务和运维任务的条数
					setCountsForCsAndOp(response);
			    })
			    // 强制调用后台任务数量推送一次
			    getWorkCount();
				reConTimes = 0;
				reConMills = 5000;
			},
			//链接失败
			function(message) {
				// 链接失效进行重连
			    reConn = setTimeout(function(){
			    	reConTimes++;
					reConMills += 2000;
			    	connectAndReconnect();
			    }, reConMills);
			}
		);
	}
	
	// 页面加载后进行websocket连接
	connectAndReconnect();
});