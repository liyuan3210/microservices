<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<!-- jquery-ztree -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/js/jquery.ztree.core.min.js"></script>
<title>ztreeLeft</title>
</head>
<script type="text/javascript">
//节点透明样式
function showIconForTree(treeId, treeNode) {
	return !treeNode.isParent;
};
//参数配置
var setting = {
			view: {
				showIcon: showIconForTree
			},
			async: {
				enable: true,
				contentType: "application/json",
				url:"${pageContext.request.contextPath}/sys/menu?method=queryList"
			},
			data:{
				simpleData:{
					enable:true,
					idKey:"id",
					pIdKey:"p_id",
					rootPId: 0
				}
			},
			callback: {
				beforeClick:zTreeBeforeClick
			}
			
};
//单击事件
function zTreeBeforeClick(treeId, treeNode) {
		if (treeNode.isParent) {
			alert("请选择子节点!");
			return false;
		} else {
			window.parent.contentFrame.location.href=treeNode.addr;
			return true;
		}

}

//初始化
$(document).ready(function(){
	$.fn.zTree.init($("#ztreeLeft"), setting);
});
</script>
<body>
<div class="content_wrap">
	 <ul id="ztreeLeft" class="ztree"></ul>
</div>
</body>
</html>
