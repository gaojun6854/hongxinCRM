<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QQ空间图片浏览</title>
<script src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
<script src="../js/layer2/layer/layer.min.js"></script>
<style>
html{background-color:#E3E3E3; font-size:14px; color:#000; font-family:'微软雅黑'}
a,a:hover{ text-decoration:none;}
pre{font-family:'微软雅黑'}
.box{padding:20px; background-color:#fff; margin:50px 100px; border-radius:5px;}
.box a{padding-right:15px;}
#about_hide{display:none}
.layer_text{background-color:#fff; padding:20px;}
.layer_text p{margin-bottom: 10px; text-indent: 2em; line-height: 23px;}
.button{display:inline-block; *display:inline; *zoom:1; line-height:30px; padding:0 20px; background-color:#56B4DC; color:#fff; font-size:14px; border-radius:3px; cursor:pointer; font-weight:normal;}
.imgs img{width:300px;padding:0 30px 30px;display:block;}
ul,li{ list-style:none;}
.imgs{overflow:hidden;}
.imgs li{float:left;height:300px;}
</style>
<script type="text/javascript" language="javascript">
        $(function() {
            $('#fileUp').change(function() {
                $('#uploadLog').html('开始上传中....');
                $('#formFile').submit();
            });
        })
        function uploadSuccess(msg,rec) {
        	var imgId="#"+rec;
            $(imgId).attr('src', "../"+msg);
	
            $('#uploadLog').html('替换成功');
            refresh();
        }
    </script>
</head>
<body>
 <form id='formFile' name='formFile' method="post" action="../webUploader/ajaxuploadChange.action" target='frameFile'
    enctype="multipart/form-data">
    <input type='file' id='fileUp' name='file' style="display: none;" />
    <input type='hidden' id='custIDS' name='custIDS' />
    <input type='hidden' id='recNum' name='recNum' />
    <div id='uploadLog'>
    </div>
    <br />
    <!-- <img width='200' src='' height='200' id='imgShow' alt='缩略图' /> -->
    </form>
    <!--
        这个iframe拿到post过来的表单数据后会开始在自身内部访问post过来的页面地址,在内部中它会刷新页面，
        但是这已不重要了，因为当前的iframe已经被我display:none隐藏了！所以这样给用户看起来像是无刷新的
        页面文件上传，其实只是做一个一个小小的技巧！
    -->
    <iframe id='frameFile' name='frameFile' style='display: none;'></iframe>
<div class="box">
<input type="button" onclick="javascript:addImg('${pactId}');" value="增加图片">
    <div id="photosDemo" class="photos-demo">
         <ul id="imgs" class="imgs">
         <!-- layer-src表示大图  layer-pid表示图片id  src表示缩略图-->
			<c:forEach items="${receipts}" var="receipt">
	        	<li>
		        	<img src="../${receipt.recRealFile}" style="height:200px;cursor: move;" id="${receipt.recNum}"  />
		        	<input type="button" value="替换" onclick="javascript:changeImg('${receipt.recNum}','${receipt.recId}');" id="change${receipt.recNum}"/>
		        	<input type="button" value="删除" onclick="javascript:delImg('${receipt.recNum}');" id="del${receipt.recNum}" />
	        	</li>
		 	</c:forEach>
		 </ul>
	</div>
</div>
<script>
//调用示例
;!function(){
layer.use('extend/layer.ext.js', function(){
    //初始加载即调用，所以需放在ext回调里
    layer.ext = function(){
        layer.photosPage({
            title: '红歆财富合同及凭证信息',
            id: 100, //相册id，可选
            parent:'#imgs'
        });
    };
});
}();

</script>
<script type="text/javascript">
function changeImg(num,id){
	$('#fileUp').click();
	$("#custIDS").val(id);
	$("#recNum").val(num);
}
function delImg(recNum){
	$.ajax({ 
	       type: "post", 
	       url: "../webUploader/ajaxDaleteImg.action?recNum="+recNum, 
	       cache:false, 
	       dataType :'text',
	       success: function(data){
	    	   if(data==1){
	    		   alert("图片删除成功");
	    		   var num="#"+recNum;
	    		   var num1="#change"+recNum;
	    		   var num2="#del"+recNum;
	    		   $(num).remove();
	    		   $(num2).remove();
	    		   $(num1).remove();
	    		   refresh();
	    	   }else{
	    		   alert("图片删除失败");	    		   
	    	   }
	       } 
	});
}

function addImg(recId){
	window.location="../webUploader/up.html?custIDS="+recId;
}
</script>
</body>
</html>