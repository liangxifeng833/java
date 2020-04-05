<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript"  src="http://admin.ljlj.cc/static/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
    var  ws;
    var url="ws://localhost:8081/webSocket1/echo";
    var url2="ws://localhost:8081/webSocket1/chatSocket";
    function connect(){        
         if ('WebSocket' in window) {
             ws = new WebSocket(url2);
         } else if ('MozWebSocket' in window) {
             ws = new MozWebSocket(url2);
         } else {
             alert('WebSocket is not supported by this browser.');
             return;
         }
         
         ws.onmessage=function(event){
            console.debug(event);   
            $("#content").append(event.data+"<br/>");
         };
        
    }
    
    function  send(){
        var value= $("#msg").val();
        ws.send(value);
    }


</script>
</head>
<body>
<button onclick="connect();">开启websocket</button>
 <hr/>
 <input id="msg"  /><button  onclick="send();"  >发送消息</button>
<div  id="content"  style="border: 1px solid black; width: 200px; height: 300px;" ></div>
</body>
</html>