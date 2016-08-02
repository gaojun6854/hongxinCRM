<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<script type="text/javascript" src="../js/jquery-date.js"></script>
<link rel="stylesheet" type="text/css" href="../css/dateTime/jquery.datetimepicker.css"/>
<script src="../js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
$(function(){
	
	 var now = new Date();
     var year = now.getFullYear();       //年
     var month = now.getMonth() + 1;     //月
     if (month<10) {
		month="0"+month;
	}
     var day = now.getDate();            //日
     if (day<10) {
 		day="0"+day;
 	}
	$("#date").val(year+month+day);
	$("#date_span").html(year+"年"+month+"月"+day+"日");
	
})
</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基金录入界面</title>
</head>
<body>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br/></span>
<form action="fundInfo!fundInsert.action" method="post">
系统当前日期:<span id="date_span"></span><br/>
选择日期<input type="text" readonly="readonly" name="fundInfo.workDate" id="datetimepicker9"><br/>
基  金 值  <input type="text" name="fundInfo.funds"><br/>
<input type="submit" value="录入">
</form>
<script>

$('#datetimepicker9').datetimepicker({
	
	format:'Ymd',
	formatDate:'Y/m/d',
	timepicker:false,
	lang:'ch',
	
});
</script>
</body>
</html>