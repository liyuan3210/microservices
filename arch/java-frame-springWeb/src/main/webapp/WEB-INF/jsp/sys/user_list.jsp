<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<title>user_list</title>

	<script>

	$(function(){

		// Initialize tablesorter
		// ***********************
		$("#tbUser")
			.tablesorter({
				theme: 'blue',
				widthFixed: true,
				sortLocaleCompare: true, // needed for accented characters in the data
				sortList: [ [0,1] ],
				//widgets: ['zebra', 'filter']
				widgets: ['zebra']
			}).tablesorterPager({
				container: $(".pager"),
				ajaxUrl : 'user?method=queryList&page={page}&size={size}&{filterList:filter}&{sortList:column}',
				customAjaxUrl: function(table, url) {
						//alert(param);
						url=url+"&queryPara="+JSON.stringify($("#tbUserQueryPara").serializeToJson());
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
							$('#tbUser').find('thead').find('tr').find('th').each(function () {
								var hd=$(this).text();
							    var key=$(this).attr("id");
								for (c in d[r] ) {
									if (key==c&&typeof(c) === "string") {
										if(key=='u_sex'){
											if(d[r][c]==0){
												row.push('男'); 
											}else if(d[r][c]==1){
												row.push('女'); 
											}
										}else{
											row.push(d[r][c]); 
										}
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

	});
	
	  $(function() {
		  
		  
		   $( "#sreach" ).button().click(function( event ) {
		    		//需要变动的条件参数才调用查询后台
  					//$('table').trigger( 'search', [['', '', '', '', '', '', '', '', '', '', '', '',new Date()+'']] ); // find orange in any column
			   		$("#tbUser").find('th:eq(0)').trigger('sort');
			  	
		   });

		    $( "#add" ).button().click(function( event ) {
		    	//window.showModalDialog('user_add.jsp'); ', top=0,left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no'
		    	window.open('user?method=forwardAddJsp','add','width=360px,height=450px,top=200px,left=600px,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		      });
		    
		    $( "#edit" ).button().click(function( event ) {
		    	  var r=$('input:radio:checked').val();
		    	  if(r){
		    		  window.open('user?method=forwardEditJsp&id='+r,'edit','width=360px,height=450px,top=200px,left=600px,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no');
		    	  }else{
		    		  alert('请您选择一条数据!');
		    	  }
		    	 
		      });
		    
		    $( "#delete" )
		      .button()
		      .click(function( event ) {
		    	  var r=$('input:radio:checked').val();
		    	  if(r){
		    		  $.ajax({
		   			   type: "POST",
		   			   url: "user?method=delete",
		   			   dataType:"json",
		   			   data: {"id":r},
		   			   success: function(data){
		   			     if (data&&data.ret) {
		   			    	 alert(data.msg);
		   			    	 userAddDialog.dialog( "close" );
		   			    	location.reload();
		   				 } else {
		   					 alert(data.msg);
		   				 }
		   			   }
		   			});
		    	  }else{
		    		  alert('请您选择一条数据!');
		    	  }
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
  	 <form id="tbUserQueryPara">
  	 <tr>
  	 <td>姓名:</td>
  	 <td> <input type="text" name="u_name" id="u_name"></td>
  	 </tr>
  	  </form>
  	 <tr>
  	 <td><button id="sreach">Sreach</button></td>
  	 </tr>
  	 </table>
  	
  	 
	</div>
	<br>
	<div>
	
  	<button id="add">add</button>
  	<button id="edit">edit</button>
  	<button id="delete">delete</button>
  	
	</div>
<table class="tablesorter" id="tbUser">
	<thead>
		<tr>
			<th class="filter-false" id="c"></th>
			<th id="u_name">Name</th>
			<th id="u_weight">Weight</th>
			<th id="u_sex">Sex</th>
			<th id="birth_dt">Birth</th>
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

</body>
</html>