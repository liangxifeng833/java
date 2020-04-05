<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
<a href="springmvc/testRedirect">测试请求重定向</a>
<hr>
<a href="springmvc/testView">测试自定义视图</a>
<hr>
<a href="springmvc/testViewAndViewResolver">测试视图解析</a>
<hr>
<!-- 
    模拟修改操作（@ModelAttrbute注解测试）
    １．原始数据为：id=1 username=Tom 
    ２．密码不能修改
    ３．表单回显，模拟操作直接在表单编写对应的属性值
 -->
<form action="springmvc/testModelAttribute" method="post">
    <input type="hidden" name="id" value="1">
    username：<input type="text" name="username"  value="Tom"/><br>
    <input type="submit" value="提交" />
 </form>
<hr>
<a href="springmvc/testSessionAttributes">将模型中数据保存在session中</a>
<hr>
<a href="springmvc/testMap">使用Map类型处理模型数据</a>
<hr>
<a href="springmvc/testModelAndView">使用ModelAndView处理模型数据</a>
<hr>
<a href="springmvc/testServletAPI">测试使用Servlet原生API作为目标方法的入参</a>
<hr>
<form action="springmvc/testPojo" method="post">
    用户名：<input name="username"  type="text"/><br>
    密码：<input name="password" type="password"> <br>
    城市：<input type="text" name="address.city" ><br>
    街道：<input type="text" name="address.street"><br>
    <input type="submit" name="dosubmit" value="提交"><br>
</form>
<hr>
<a href="springmvc/testRequestCookieValue">使用@CookieValue注解，映射cookie值到对应方法的入参</a>
<hr>
<a href="springmvc/testRequestHeader">测试请求头信息映射到对应方法的入参，使用@RequestHeader注解</a>
<hr>
<a href="springmvc/testRequestPram?username=lxf&age=21">测试请求参数映射为方法的入参，使用@RequestParam注解</a>
<hr>
<form action="springmvc/testRest/1" method="post" id="myForm1" name="myForm1">
    <input type="hidden" name="_method" value="DELETE">
    <input type="submit" value="Test REST 浏览器模拟DELETE请求"  name="1">
</form>
<br><br>
<form action="springmvc/testRest/1" method="post" id="myForm2" name="myForm2">
    <input type="hidden" name="_method" value="PUT">
    <input type="submit" value="Test REST 浏览器模拟PUT请求" >
</form>
<hr>
<form action="springmvc/testRest" method="post" id="myForm3" name="myForm3">
    <input type="submit" value="Test REST POST请求" >
</form>
<hr>
<a href="springmvc/testRest/1">Test REST GET请求</a>
<hr>
<a href="springmvc/testPathVariable/1">测试url地址占位符映射到对应方法的参数中</a>
<hr>
<a href="springmvc/testAntPath/suiyi/abc">测试url地址通配符</a>
<hr>
<a href="springmvc/testParamsAndHeaders?username=123&age=20">设置请求参数和请求头--测试</a>
<hr>
<form method="post" action="springmvc/testMethod">
    用户名：<input name="username"  type="text"/><br>
    密码：<input name="password" type="password"> <br>
    <input type="submit" name="dosubmit" value="提交"><br>
</form>
<hr>
<a href="helloClass/helloMethod">Hello World</a>
</body>
</html>