$(function() {
    var state = 'pending',
    uploader = WebUploader.create({
    	pick:'#picker', 
    	resize:false, 
    	swf:'/allTest/pub/swf/Uploader.swf', 
    	server:'ajaxupload.action', 
    	disableGlobalDnd:true, 
    	auto:false, 
    	fileNumLimit:10, 
    	fileSizeLimit:20*1024*1024, 
    	fileSingleSizeLimit:200*1024*1024
    	});
    
    
    
    uploader.on('fileQueued', function(file) {
        $('#thelist').append('<div id="' + file.id + '" class="fl b14b"><div class="fn">' + file.name + '<span class="state" id="st' + file.id + '"></span></div><div class="fd"><input type="button" name="fdb" id="fdb'+ file.id +'" class="bs1" value="删 除" onclick="delf(this)" /></div><div class="clr"></div></div>');
    });
    uploader.on('uploadProgress', function(file, percentage) {
        $('#st' + file.id).text('上传中');
    });
    uploader.on('uploadSuccess', function(file, data) {
        $('#st' + file.id).text('已上传');
        $("#fdb"+ file.id).remove();
        if(data.code == 1001) {
            $("#ae").text("恭喜您，文件上传成功！")
        } else {
            $("#ae").text(data.msg);
        }
    });
    uploader.on('uploadError', function(file) {
        $('#st' + file.id).text('上传出错');
    });
    uploader.on('all', function(type) {
        if(type === 'startUpload') {
            state = 'uploading';
            $('#upbtn').text('暂停上传');
        } else if(type === 'stopUpload') {
            state = 'paused';
            $('#upbtn').text('开始上传');
        } else if(type === 'uploadFinished') {
            state = 'done';
            $('#upbtn').text('开始上传');
        }
    });
    $('#upbtn').on('click', function() {
        if(state === 'uploading') {
            uploader.stop();
        } else {
            uploader.upload();
        }
    });
});

//移除不需要上传的文件
function delf(node) {
    if(confirm("确定不需要上传该文件吗？")) {
        var item = node.parentNode.parentNode;
        uploader.removeFile(item.id, true);
        $(item).remove();
    }
}