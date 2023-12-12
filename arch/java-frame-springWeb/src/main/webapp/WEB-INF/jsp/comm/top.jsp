<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>top</title>
<script>
  $(function() {
	    $( "#user,a" )
	      .button()
	      .click(function( event ) {
	    	  //alert('ok');
	    	  //event.preventDefault(); 
	      });
	    
	    $( "#accordion" )
	      .button()
	      .click(function( event ) {
	    	  window.parent.leftFrame.location.href='${pageContext.request.contextPath}/login?method=forwardAccordionLeftJsp';
	      });
	    $( "#index" )
	      .button()
	      .click(function( event ) {
	    	  window.parent.location.href='http://www.liyuan3210.com';
	      });
  });
  function onClickToolbar(url){
	  //alert(url);
	 	window.parent.leftFrame.location.href=url; 
  }
  
  function onClickButton(url){
	  //alert(url);
	 	window.parent.contentFrame.location.href=url; 
  }
</script>

<body>
<div align="center">

<div id="toolbar" class="ui-widget-header ui-corner-all">
<!-- 
	<button id="accordion">系统管理</button>
	 -->
	 <button id="index">首页</button>
  	<a href="javascript:onClickToolbar('${pageContext.request.contextPath}/login?method=forwardZtreeLeftJsp');">学习笔记</a>
	<a href="javascript:onClickButton('${pageContext.request.contextPath}/sys/message');">留言/建议</a>
	<a href="javascript:onClickButton('${pageContext.request.contextPath}/sys/servicePublic');">服务开放</a>
	<a href="javascript:onClickButton('${pageContext.request.contextPath}/login?method=forwardImgListJsp');">生活相册</a>
  	<a href="javascript:alert('暂不提供！');">下载</a>
</div>
</div>
</body>
</html>
