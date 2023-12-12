<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>www.liyuan3210.com</title>
</head>
<script type="text/javascript">

</script>
<frameset rows="6%,*,5%" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/login?method=forwardTopJsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame">
  <frameset cols="15%,*" frameborder="no" border="0" framespacing="0">
    <frame src="${pageContext.request.contextPath}/login?method=forwardLeftJsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame">
    <frame src="${pageContext.request.contextPath}/login?method=forwardLicJsp" name="contentFrame" id="contentFrame" title="contentFrame">
  </frameset>
  <frame src="${pageContext.request.contextPath}/login?method=forwardBottomJsp">
</frameset>
</html>
