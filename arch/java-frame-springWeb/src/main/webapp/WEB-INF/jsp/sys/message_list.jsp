<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>message_list</title>

	<script>

	$(function(){

		// Initialize tablesorter
		// ***********************
		$("#tbMessage")
			.tablesorter({
				theme: 'blue',
				widthFixed: true,
				sortLocaleCompare: true, // needed for accented characters in the data
				sortList: [ [0,1] ],
				//widgets: ['zebra', 'filter']
				widgets: ['zebra']
			}).tablesorterPager({
				container: $(".pager"),
				ajaxUrl : 'message?method=queryList&page={page}&size={size}&{filterList:filter}&{sortList:column}',
				customAjaxUrl: function(table, url) {
						//alert(param);
						url=url+"&queryPara="+JSON.stringify($("#tbMessageQueryPara").serializeToJson());
						$(table).trigger('changingUrl', url);
						return url;
				},
				
				ajaxObject: {
					dataType: 'json'
				},
				ajaxProcessing: function(data, table, xhr){
					if (data && data.hasOwnProperty('rows')) {
						var r, row, c, d = data.rows,
						total = data.total_rows,
						headers = data.headers,
						rows = [],
						len = d.length;
						for (r=0; r<len; r++ ) {
							row = ['<input type="radio" name="r" value="'+d[r]['id']+'"/>']; // new row array
							$('#tbMessage').find('thead').find('tr').find('th').each(function () {
								var hd=$(this).text();
							    var key=$(this).attr("id");
								for (c in d[r] ) {
									if (key==c&&typeof(c) === "string") {
										row.push(d[r][c]);
									}
								}
								if(d[r][key]==undefined&&d[r][key]!=null&&key!='c'){
									row.push('');
								}
							});
							rows.push(row); // add new row array to rows array
						}
						return [ total, rows,headers];
					}
				},
				output: '{startRow} to {endRow} ({totalRows})',
				updateArrows: true,
				page: 0,
				size: 25,
				fixedHeight: false,
				removeRows: false,
				cssNext        : '.next',  // next page arrow
				cssPrev        : '.prev',  // previous page arrow
				cssFirst       : '.first', // go to first page arrow
				cssLast        : '.last',  // go to last page arrow
				cssPageDisplay : '.pagedisplay', // location of where the "output" is displayed
				cssPageSize    : '.pagesize', // page size selector - select dropdown that sets the "size" option
				cssErrorRow    : 'tablesorter-errorRow', // error information row
				cssDisabled    : 'disabled' // Note there is no period "." in front of this class name

			});
		
		//验证规则
		$("#messageAddForm").validate({
			rules: {
				l_desc: "required"
			},
			messages: {
				l_desc: "请输入您的留言信息。"
			},submitHandler: function(form) {
				var data = $("#messageAddForm").serializeToJson();
				$.ajax({
					   type: "POST",
					   url: "message?method=insert",
					   dataType:"json",
					   data: data,
					   success: function(data){
					     if (data&&data.ret) {
					    	 alert(data.msg);
					    	 messageAddDialog.dialog( "close" );
					    	 location.reload();
						 } else {
							 alert(data.msg);
						 }
					   }
					});
			}
		});
		 function addmessage() {
			 $("#messageAddForm").validate();

			 $('#messageAddForm').submit();
		 }
		
		var messageAddDialog = $( "#messageAddDialog" ).dialog({
		    autoOpen: false,
		    height: 420,
		    width: 350,
		    modal: true,
		    buttons: {
		      "save": addmessage,
		      Cancel: function() {
		    	  messageAddDialog.dialog( "close" );
		      }
		    },
		    close: function() {
		    	messageAddDialog.dialog( "close" );
		    }
		  });
		
		 $( "#sreach" ).button().click(function( event ) {
		   		$("#tbMessage").find('th:eq(0)').trigger('sort');
	   	});

	   $( "#add" ).button().click(function( event ) {
	    	 //$("#dialog-form").load("${pageContext.request.contextPath}/login?method=forwardAddJsp",null,function(){});
	    	 messageAddDialog.dialog( "open" );
	  		
	      });
	});
	
	</script>
</head>
<body>
<!-- table -->
	<div>
	<!-- 
	<button type="button">Add Rows</button> <button type="button" class="toggle">Disable Pager</button> <button type="button">Destroy Pager</button>
	 -->
	<div id="toolbar" class="ui-widget-header ui-corner-all">
	
	
  	 <table>
  	 <form id="tbMessageQueryPara">
  	 <tr>
  	 <td>查询信箱:</td>
  	 <td> <input type="text" name="l_desc" id="l_desc"></td>
  	 </tr>
  	  </form>
  	 <tr>
  	 <td><button id="sreach">Sreach</button></td>
  	 </tr>
  	 </table>
  	
  	 
	</div>
	<br>
	<div>
	
  	<button id="add">留言</button>
  	
	</div>
<table class="tablesorter" id="tbMessage">
	<thead>
		<tr>
			<th class="filter-false" id="c"></th>
			<th id="l_desc">留言信箱</th>
			<th id="input_dt">留言时间</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
</table>

<div class="pager">
	Page: <select class="gotoPage"></select>	
	<img src="${pageContext.request.contextPath}/js/tablesorter/addons/pager/icons/first.png" class="first" alt="First" title="First page" />
	<img src="${pageContext.request.contextPath}/js/tablesorter/addons/pager/icons/prev.png" class="prev" alt="Prev" title="Previous page" />
	<span class="pagedisplay"></span> <!-- this can be any element, including an input -->
	<img src="${pageContext.request.contextPath}/js/tablesorter/addons/pager/icons/next.png" class="next" alt="Next" title="Next page" />
	<img src="${pageContext.request.contextPath}/js/tablesorter/addons/pager/icons/last.png" class="last" alt="Last" title= "Last page" />
	<select class="pagesize">
		<option value="10">10</option>
		<option value="20">20</option>
		<option value="30">30</option>
		<option value="40">40</option>
	</select>
</div>

</div>
<div id="messageAddDialog" title="Add">
  <form id="messageAddForm">
    <fieldset>
		<label for="text">留言</label>
		<textarea id="l_desc" name="l_desc" rows="12" cols="42"  class="text ui-widget-content ui-corner-all"></textarea>
    </fieldset>
  </form>
</div>
</body>
</html>