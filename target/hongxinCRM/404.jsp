<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" href="css/main.css" type="text/css" media="screen, projection" /> <!-- main stylesheet -->
<link rel="stylesheet" type="text/css" media="all" href="css/tipsy.css" /> <!-- Tipsy implementation -->

<!--[if lt IE 9]>
	<link rel="stylesheet" type="text/css" href="css/ie8.css" />
<![endif]-->

<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script> <!-- uiToTop implementation -->
<script type="text/javascript" src="js/custom-scripts.js"></script>
<script type="text/javascript" src="js/jquery.tipsy.js"></script> <!-- Tipsy -->

<script type="text/javascript">

$(document).ready(function(){
			
	universalPreloader();
						   
});

$(window).load(function(){

	//remove Universal Preloader
	universalPreloaderRemove();
	
	rotate();
    dogRun();
	dogTalk();
	
	//Tipsy implementation
	$('.with-tooltip').tipsy({gravity: $.fn.tipsy.autoNS});
						   
});
</script>
<title>404 - Not found</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<div id="universal-preloader">
    <div class="preloader">
        <img src="images/universal-preloader.gif" alt="universal-preloader" class="universal-preloader-preloader" />
    </div>
</div>
<div id="wrapper">
	<div class="graphic"></div>
	<div class="not-found-text">
    	<h1 class="not-found-text">不见了,稍后再试试吧!</h1>
    </div>
<div class="dog-wrapper">
	<div class="dog"></div>
	<div class="dog-bubble">
    </div>
    <div class="bubble-options">
    	<p class="dog-bubble">
        	稍后再试试吧!
        </p>
    	<p class="dog-bubble">
	        <br />
        	稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	<br />
        	稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	稍后再试试吧!<br /><img style="margin-top:8px" src="images/cookie.png" alt="cookie" />
        </p>
        <p class="dog-bubble">
        	<br />
        	稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	<br />
        	稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	<br />
            稍后再试试吧!
        </p>
        <p class="dog-bubble">
        	稍后再试试吧! <br /><img style="margin-top:8px" src="images/cat.png" alt="cat" />
        </p>
        <p class="dog-bubble">
        	稍后再试试吧! @_@
        </p>
    </div>
</div>

	<div class="planet"></div>
</div>
</body>
</html>