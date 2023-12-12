<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<jsp:include page="../comm/jquery_ui1.0.jsp"></jsp:include>
<!-- Magnific Popup core CSS file -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/magnific/magnific-popup.css"> 
<!-- Magnific Popup core JS file -->
<script src="${pageContext.request.contextPath}/js/magnific/jquery.magnific-popup.js"></script>
<title>content</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
	$('.popup-gallery').magnificPopup({
		delegate: 'a',
		type: 'image',
		tLoading: 'Loading image #%curr%...',
		mainClass: 'mfp-img-mobile',
		gallery: {
			enabled: true,
			navigateByImgClick: true,
			preload: [0,1] // Will preload 0 - before current, and 1 after the current image
		},
		image: {
			tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
			titleSrc: function(item) {
				return item.el.attr('title') + '<small>by Marsel Van Oosten</small>';
			}
		}
	});
	
 $( "#tabs" ).tabs();
});

</script>
<body>
<div id="tabs">
<img src="${pageContext.request.contextPath}/img/lic.jpg" />
</div>
</body>
</html>
<!-- 
   朱家角2
<div class="popup-gallery">
<a href="img/100_0256.jpg" title="The Cleaner"><img src="img/100_0256.jpg" width="75" height="75"></a>
	<a href="img/100_0257.jpg"  title="Winter Dance"><img src="img/100_0257.jpg" width="75" height="75"></a>
	<a href="img/100_0258.jpg"  title="The Uninvited Guest"><img src="img/100_0258.jpg" width="75" height="75"></a>
	<a href="img/100_0259.jpg" title="Oh no, not again!"><img src="img/100_0259.jpg" width="75" height="75"></a>
	<a href="img/100_0260.jpg" title="Swan Lake"><img src="img/100_0260.jpg" width="75" height="75"></a>
	<a href="img/100_0261.jpg"  title="The Shake"><img src="img/100_0261.jpg" width="75" height="75"></a>
	<a href="img/100_0262.jpg" title="Who's that, mommy?"><img src="img/100_0262.jpg" width="75" height="75"></a>
</div>
 -->
