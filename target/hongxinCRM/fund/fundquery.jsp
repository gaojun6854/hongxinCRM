<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="content-script-type" content="text/javascript">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="content-language" content="en-gb">
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../css/custom/dataTable.css" media="screen">
<style type="text/css">
body, th, td { font-family: Arial, Verdana, sans-serif; font-size: 0.9em; }
a:link, a:visited { color: #59B337; }
a:hover, a:active, a:focus { color: #000000; }
table.dataTable tr.marked { background-color: #FFD900; }
</style>
<!-- jquery packed -->
<script type="text/javascript" src="../js/jquery.js"></script>
<!-- tableRowCheckboxToggle -->
<script type="text/javascript" src="../js/tableRowCheckboxToggle.js"></script>
<title>可以购买产品用户</title>
</head>
<body>

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
	$("#end").val(year+month+day);
})
</script>
</head>
<body>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br/></span>
<form action="fundInfo!fundquery.action" method="post">
	起始时间:<input type="text" name="start" />
	结束时间:<input type="text" name="end" id="end" />
	<input type="submit" value="查询" />
</form>
<h1 align="center" style="color: highlight;">指数信息</h1>
<span style="color: red; font-size: 15px;">${requestScope.flag}<br /></span>
<table border="0" cellspacing="0" cellpadding="0" class="dataTable">
  <thead>
    <tr>
	 <th class="dataTableHeader">日期</th>
	 <th class="dataTableHeader">指数</th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${fundInfos}" var="fundInfo">
    <tr class="odd_row">
	 	  <td><input type="hidden" readonly="readonly" id="checkme1" /><a href="fundInfo!getFundsInfo.action?WorkDate=${fundInfo.workDate}">${fundInfo.workDate}</a></td>
	  <td><input type="hidden" readonly="readonly" id="checkme1" />${fundInfo.funds}</td>
    </tr>
   </c:forEach>
  </tbody>
</table>
</body>
</html>