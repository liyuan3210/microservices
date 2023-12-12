<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>accordionLeft</title>
</head>
<script type="text/javascript">
$(function() {
    $( "#accordion" ).accordion();
  
  });
function onClickButton(url){
	  //alert(url);
	 	window.parent.contentFrame.location.href=url; 
}
</script>
<body>
<div id="accordion">

  <h3>测试/学习</h3>
  <div>
    <ul>
    
      <li>
      <a href="javascript:onClickButton('${pageContext.request.contextPath}/sys/user');">user(base tmp)</a>
      </li>
        
    </ul>
  </div>
  
  
    <h3>系统管理</h3>
  <div>

    <ul>
      <li>
      <a href="#">笔记管理</a>
      </li>
      
      <li>
      <a href="#">菜单管理</a>
      </li>
    </ul>
  </div>
  <!-- 
  <h3>Section 2</h3>
  <div>
    <p>
    Sed non urna. 
    Donec et ante.
    </p>
  </div>
  
  <h3>Section 3</h3>
  <div>
    <p>
    Nam enim risus,
     molestie et, 
     porta ac,
    </p>
    <ul>
      <li>List item one</li>
      <li>List item two</li>
      <li>List item three</li>
    </ul>
  </div>
  
  <h3>Section 4</h3>
  <div>
    <p>
    Cras dictum. 
    </p>
    <p>
    Suspendisse eu nisl. 
    </p>
  </div>
   -->

</div>
</body>
</html>
