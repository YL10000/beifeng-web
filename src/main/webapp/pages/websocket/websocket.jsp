<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../../common/js/jquery.min.js"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>  
<script type="text/javascript">
ws = new SockJS("http://localhost:8080/maven-web/webSocketServer/sockjs");
var tryTime=0;
initWs(ws);
function initWs(ws){
	ws.onopen = function () {  
	    //setConnected(true);  
	    console.log('Info: connection opened.');  
	    $("#messages").append("<li>连接成功</li>")
	    tryTime=0;
	}; 

	ws.onclose = function (){
		if (tryTime<5) {
			$("#messages").append("<li>断开连接，正在进行尝试重新连接</li>")
			ws = new SockJS("http://localhost:8080/maven-web/webSocketServer/sockjs");
			initWs(ws);
			tryTime++;
		}else{
			$("#messages").append("<li>服务器内部错误，断开连接</li>")
		}
	}

	ws.onmessage = function(evt){
		console.log(evt)
		$("#messages").append("<li>发送："+evt.data+"</li>")
		
	}
	ws.onerror = function(e) { /* 出现错误时 */
	    console.error(e);
	    $("#messages").append("<li>连接报错</li>")
	}
}


function sendMessage(){
	var message=$("input[name='message']").val();
	ws.send(message)
	$("#messages").append("<li>接受："+message+"</li>")
}


</script>
<title>Insert title here</title>
</head>
<body>
<div>
<input type="text" name="message"><button onclick="sendMessage()">发送</button>
<ol id="messages"></ol>
</div>

</body>
</html>