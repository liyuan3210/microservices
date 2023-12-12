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
  <ul>
    <li><a href="#tabs-1">生活照</a></li>
    <!-- 
    <li><a href="#tabs-2">Proin dolor</a></li>
    <li><a href="#tabs-3">Aenean lacinia</a></li>
     -->
  </ul>
  <div id="tabs-1">
2010年宽文年会
<div class="popup-gallery">
	<a href="img/content/2011/1.jpg"><img src="img/content/2011/1.jpg" width="200" height="200"></a>
	<a href="img/content/2011/2.jpg"><img src="img/content/2011/2.jpg" width="200" height="200"></a>
	<a href="img/content/2011/3.jpg"><img src="img/content/2011/3.jpg" width="200" height="200"></a>
	<a href="img/content/2011/4.jpg"><img src="img/content/2011/4.jpg" width="200" height="200"></a>
</div>
2012年北京行
<div class="popup-gallery">
	<a href="img/content/2012/DSCN2897.jpg"><img src="img/content/2012/1.jpg" width="200" height="200"></a>
	<a href="img/content/2012/DSCN3003.jpg"><img src="img/content/2012/2.jpg" width="200" height="200"></a>
	<a href="img/content/2012/DSCN3079.jpg"><img src="img/content/2012/3.jpg" width="200" height="200"></a>
	<a href="img/content/2012/DSCN3403.jpg"><img src="img/content/2012/4.jpg" width="200" height="200"></a>
</div>
2015黄山
<div class="popup-gallery">
	<a href="img/content/2015/1.JPG"><img src="img/content/2015/1.JPG" width="200" height="200"></a>
	<a href="img/content/2015/2.JPG"><img src="img/content/2015/2.JPG" width="200" height="200"></a>
	<a href="img/content/2015/3.JPG"><img src="img/content/2015/3.JPG" width="200" height="200"></a>
	<a href="img/content/2015/4.JPG"><img src="img/content/2015/4.JPG" width="200" height="200"></a>
</div>
  </div>
</div>
</body>
</html>
