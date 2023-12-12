<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.liyuan3210.web.comm.service.SessionListener"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>accordionLeft</title>
</head>
<script type="text/javascript">
$(function() {
    $( "#datepicker" ).datepicker();
  });

</script>
<body>
<div id="datepicker"></div>
<br>
<br>
<br>
<h1>微信公众平台</h1>
<img src="${pageContext.request.contextPath}/img/wx.jpg" />
<br>
<h1>当前在线人数:</h1><font color="#FF0000" size="150"><%=SessionListener.count %>人</font>
</body>
</html>
