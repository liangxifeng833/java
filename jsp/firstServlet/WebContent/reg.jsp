<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户注册</title>
</head>
<body>
<h1>用户注册</h1>
<form action="servlet/RegServlet" method="post">
		用户名：<input  type="text" name="uname"  value="" /><br/>
		密码：<input type="password" name="pass"  value="" /><br/>
		生日：<input type="text" name="birthday" value="" /><br/>
		爱好：<input type="checkbox" name="favorites"  value="nba"  checked="checked"/>NBA  <input type="checkbox" name="favorites"  value="music"/>音乐<br/>
		是否接受条款：<input  type="checkbox"  name="isAccept"  value="true" /><br/>	
		<input type="submit" name="sub" value="提交注册" />
</form>

</body>
</html>