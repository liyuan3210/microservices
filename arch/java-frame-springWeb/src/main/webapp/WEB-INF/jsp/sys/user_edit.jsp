<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>Insert title here</title>
</head>
<script type="text/javascript">
var tips = $( ".validateTips" );

$(function() {
	initUserEdit(getUrlParam("id"));
	
	//验证规则
	$("#userEditForm").validate({
		rules: {
			u_name: {
				required: true,
				minlength: 6
			},
			u_weight: {
				required: true,
				number: true
			},
			u_sex: "required",
			birth_dt: {
				required: true,
				date: true
			}
		},
		messages: {
			u_name:{
				required: "请输入您的名字。",
				minlength: "您的名字长度必须大于6位。"
			},
			u_weight: {
				required: "请输入您的体重.",
				number: "您的体重必须是数字类型。"
			},
			u_sex: "请选择您的性别。",
			birth_dt: {
				required: "请您输入您的出生年月。",
				number: "出生年月必须是日期类型."
			}
		},submitHandler: function(form) {
			
			 var data = $("#userEditForm").serializeToJson();
			 	$.ajax({
			    type: "POST",
			    url: "user?method=update",
			    dataType:"json",
			    data: data,
			    success: function(data){
				     if (data&&data.ret) {
				    	 alert(data.msg);
				    	window.close();
					 } else {
						 alert(data.msg);
					 }
			     }
				});
		}
	});


	function editUser() {
		
		$("#userEditForm").validate();

	    $('#userEditForm').submit();
	    
	}

	var userEditDialog=$( "#userEditDialog" ).dialog({
	  autoOpen: true,
	  height: 450,
	  width: 350,
	  modal: true,
	  buttons: {
	    "save": editUser,
	    Cancel: function() {
	  	  userEditDialog.dialog( "close" );
	    }
	  },
	  close: function() {
	  	window.close();
	  }
	});
	
	$( "#birth_dt" ).datepicker({dateFormat: "yy-mm-dd"});
});
function initUserEdit(e){
	//ajax提交数据**/
	$.ajax({
	   type: "POST",
	   url: "user?method=get",
	   dataType:"json",
	   data: {"id":e},
	   success: function(data){
		   
	    	 serializeToJson("#userEditForm", data, null, 'name');
	    	 
	   }
	});
}
</script>
<body>
<div id="userEditDialog" title="Edit">
  <form id="userEditForm">
    <fieldset>
    	<input type="hidden" name="id" field="id"/>
     <label for="name">Name</label>
      <input type="text" name="u_name" id="u_name" class="text ui-widget-content ui-corner-all">
      <label for="u_weight">Weight</label>
      <input type="text" name="u_weight" id="u_weight" class="text ui-widget-content ui-corner-all">
      <label for="text">Sex</label>
      	<select name="u_sex" id="u_sex" class="text ui-widget-content ui-corner-all"> 
		<option value="0" selected="selected">男</option> 
		<option value="1">女</option>
	  </select>
	  <label for="text">Birth</label>
      <input type="text" name="birth_dt" id="birth_dt" class="text ui-widget-content ui-corner-all">
    </fieldset>
  </form>
</div>
</body>
</html>