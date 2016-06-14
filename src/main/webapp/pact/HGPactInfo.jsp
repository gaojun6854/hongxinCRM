<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/pact/jquery.fullPage.css">
<link rel="stylesheet" href="../css/pact/style.css">
<script src="../js/jquery-1.8.0.min.js"></script>
<script src="../js/pact/jquery-ui-1.10.3.min.js"></script>
<script src="../js/pact/jquery.fullPage.min.js"></script>
<!-- table -->
<meta http-equiv="content-script-type" content="text/javascript">
<meta http-equiv="content-style-type" content="text/css">
<meta http-equiv="content-language" content="en-gb">
<meta http-equiv="imagetoolbar" content="no" />
<link rel="stylesheet" href="../css/custom/dataTable.css" media="screen">
<style type="text/css">
body, th, td {
	font-family: Arial, Verdana, sans-serif;
	font-size: 0.9em;}
a:link, a:visited {
	color: #59B337;}
a:hover, a:active, a:focus {
	color: #000000;}
table.dataTable tr.marked {
	background-color: #FFD900;}
</style>
<link rel="stylesheet" type="text/css" href="../webUploader/css/webuploader.css">
<link rel="stylesheet" type="text/css" href="../webUploader/css/style.css">
<link rel="stylesheet" type="text/css" href="../css/bootstrap/bootstrap.css">
<c:if test="${empty sessionScope.login_user}">
	<script type="text/javascript">
	$(function () {
		window.top.location.href="/index.jsp";
	})
</script>
</c:if>
<script type="text/javascript">
$(document).ready(function() {
	$.fn.fullpage({
		slidesColor: ['#C2E5FF', '#C2E5FF', '#FDF6E1', '#E9E9E9', '#F3F3F3', '#F9F3DC'],
		anchors: ['page1', 'page2', 'page3', 'page4', 'page5', 'page6'],
		menu: '#menu',
		afterRender: function(){
			$('.screen-main span').each(function(){
				var $rel = $(this).attr('rel');
				var $arr = $rel.split(',');
				$(this).animate({
					left: $arr[2] + 'px',
					top: $arr[3] + 'px'
				}, 500);
			});
			$('.inner a').each(function(){
				var $rel = $(this).attr('rel');
				var $arr = $rel.split(',');
				$(this).animate({
					left: $arr[0] + 'px',
					top: $arr[1] + 'px'
				}, 500);
			});
		},
		afterLoad: function(anchorLink, index){
			if(index == 1){
				$('.screen-main span').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[2] + 'px',
						top: $arr[3] + 'px'
					}, 500);
				});
			}
			if(index == 2 || index == 3 || index == 4 || index == 5){
				$('.inner').eq(index - 2).find('a').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[2] + 'px',
						top: $arr[3] + 'px'
					}, 500);
				});
			}
			if(index == 6){
				$('.zanzhu-con a').fadeIn(1000);
			}
		},
		onLeave: function(index, direction){
			if(index == 1){
				$('.screen-main span').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[0] + 'px',
						top: $arr[1] + 'px'
					}, 500);
				});
			}
			if(index == 2 || index == 3 || index == 4 || index == 5){
				$('.inner').eq(index - 2).find('a').each(function(){
					var $rel = $(this).attr('rel');
					var $arr = $rel.split(',');
					$(this).animate({
						left: $arr[0] + 'px',
						top: $arr[1] + 'px'
					}, 500);
				});
			}
			if(index == 6){
				$('.zanzhu-con a').fadeOut(1000);
			}
		}
	});
});
</script>
</head>

<body>

<ul id="menu">
	<li data-menuanchor="page1" class="active"><a href="#page1">客户信息</a></li>
	<li data-menuanchor="page2"><a href="#page2">选择产品</a></li>
	<li data-menuanchor="page3"><a href="#page3">上传凭证信息</a></li>
	<!-- <li data-menuanchor="page4"><a href="#page4">购买合约</a></li>
	<li data-menuanchor="page5"><a href="#page5">TOP5</a></li>
	<li data-menuanchor="page6"><a href="#page6">TOP6</a></li> -->
</ul>

<div class="section section1">
	<div class="screen-main" style="width: 60%;height: 80%;">
		<table border="0" cellspacing="0" cellpadding="0" class="dataTable" style="color: black;">
		<tbody>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />姓名</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.custname}</td>
				</tr>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />联系方式</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.phonenum}</td>
				</tr>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />证件编号</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.papernum}</td>
				</tr>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />客户居住地址</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.curAddress}</td>
				</tr>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />客户Email</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.curAddress}</td>
				</tr><tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />性别</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.custgender}</td>
				</tr>
				<tr class="odd_row">
					<td><input type="hidden" readonly="readonly" id="checkme1" />邮寄地址</td>
					<td><input type="hidden" readonly="readonly" id="checkme1" />${cust.postAddr}</td>
				</tr>
		</tbody>
	</table>
	</div>
</div>

<div class="section">
	<div class="science-inner inner">
		<div class="screen-main" style="width: 60%;height: 80%;margin-top: 40px;">
		
		<div style="color: black;">
			<form>
				金额:<input type="text" id="amount"  /><br/>
				<input type="button" value="确定回购" onclick="javascript:pactReBuy()" />
			</form>
		</div>
	</div>
	</div>
	<script type="text/javascript">
		//reBuy 
		//合同回购
		//power by jungao@sinorfc.com
		function pactReBuy(){
			var amount=$("#amount").val();
			var total=${pactInfo.amount}+${pactInfo.backMoney};
			if(amount>total){
				alert("金额大于原合同金额+利息");
				return 0;
			}
			
			$.ajax({ 
		       type: "post", 
		       url: "pactInfo!addPactInfo.action?amount="+amount+"&custId=${cust.id}"+"&pactId=${pactInfo.pactId}"+"&isRebuy=yes", 
		       cache:false, 
		       async:false, 
		       dataType:"text",
		       success: function(data){
		    	   alert(data);
		    	   window.location="#page3";
		       }  
		});
		}
	</script>
</div>

<div class="section">
	<div class="famous-inner inner">
		<div id="wrapper">
		<div id="container">
			<!--头部，相册选择和格式选择-->
			<div id="uploader">
				<div class="queueList">
					<div id="dndArea" class="placeholder">
						<div id="filePicker"></div>
						<p>红歆财富图片上传,单次最多可选300张</p>
					</div>
				</div>
				<div class="statusBar" style="display: none;">
					<div class="progress">
						<span class="text">0%</span> <span class="percentage"></span>
					</div>
					<div class="info"></div>
					<div class="btns">
						<div id="filePicker2"></div>
						<div class="uploadBtn">开始上传</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="../webUploader/js/webuploader.js"></script>
<script type="text/javascript">
(function( $ ){
    // 当domReady的时候开始初始化
    $(function() {
        var $wrap = $('#uploader'),

            // 图片容器
            $queue = $( '<ul class="filelist"></ul>' )
                .appendTo( $wrap.find( '.queueList' ) ),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find( '.statusBar' ),

            // 文件总体选择信息。
            $info = $statusBar.find( '.info' ),

            // 上传按钮
            $upload = $wrap.find( '.uploadBtn' ),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find( '.placeholder' ),

            $progress = $statusBar.find( '.progress' ).hide(),

            // 添加的文件数量
            fileCount = 0,

            // 添加的文件总大小
            fileSize = 0,

            // 优化retina, 在retina下这个值是2
            ratio = window.devicePixelRatio || 1,

            // 缩略图大小
            thumbnailWidth = 110 * ratio,
            thumbnailHeight = 110 * ratio,

            // 可能有pedding, ready, uploading, confirm, done.
            state = 'pedding',

            // 所有文件的进度信息，key为file id
            percentages = {},
            // 判断浏览器是否支持图片的base64
            isSupportBase64 = ( function() {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function() {
                    if( this.width != 1 || this.height != 1 ) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            } )(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = ( function() {
                var version;

                try {
                    version = navigator.plugins[ 'Shockwave Flash' ];
                    version = version.description;
                } catch ( ex ) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                                .GetVariable('$version');
                    } catch ( ex2 ) {
                        version = '0.0';
                    }
                }
                version = version.match( /\d+/g );
                return parseFloat( version[ 0 ] + '.' + version[ 1 ], 10 );
            } )(),

            supportTransition = (function(){
                var s = document.createElement('p').style,
                    r = 'transition' in s ||
                            'WebkitTransition' in s ||
                            'MozTransition' in s ||
                            'msTransition' in s ||
                            'OTransition' in s;
                s = null;
                return r;
            })(),

            // WebUploader实例
            uploader;

        if ( !WebUploader.Uploader.support('flash') && WebUploader.browser.ie ) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function(container) {
                    window['expressinstallcallback'] = function( state ) {
                        switch(state) {
                            case 'Download.Cancelled':
                                alert('您取消了更新！')
                                break;

                            case 'Download.Failed':
                                alert('安装失败')
                                break;

                            default:
                                alert('安装已成功，请刷新！');
                                break;
                        }
                        delete window['expressinstallcallback'];
                    };

                    var swf = './expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/' +
                            'x-shockwave-flash" data="' +  swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">'  +
                        '<param name="movie" value="' + swf + '" />' +
                        '<param name="wmode" value="transparent" />' +
                        '<param name="allowscriptaccess" value="always" />' +
                    '</object>';

                    container.html(html);

                })($wrap);

            // 压根就没有安转。
            } else {
                $wrap.html('<a href="http://www.adobe.com/go/getflashplayer" target="_blank" border="0"><img alt="get flash player" src="http://www.adobe.com/macromedia/style_guide/images/160x41_Get_Flash_Player.jpg" /></a>');
            }

            return;
        } else if (!WebUploader.Uploader.support()) {
            alert( 'Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: '../webUploader/swf/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: '../webUploader/ajaxupload.action',
            // runtimeOrder: 'flash',

             accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024,    // 50 M
            
            formData:{ //附带值       
    			'url':'1111',
    			'custIDS':'${pactId}',
    			'picType':'3333',
    			},
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on( 'dndAccept', function( items ) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for ( ; i < len; i++ ) {
                // 如果在列表里面
                if ( ~unAllowed.indexOf( items[ i ].type ) ) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        uploader.on('dialogOpen', function() {
            console.log('here');
        });

        // uploader.on('filesQueued', function() {
        //     uploader.sort(function( a, b ) {
        //         if ( a.name < b.name )
        //           return -1;
        //         if ( a.name > b.name )
        //           return 1;
        //         return 0;
        //     });
        // });

        // 添加“添加文件”的按钮，
        uploader.addButton({
            id: '#filePicker2',
            label: '继续添加'
        });

        uploader.on('ready', function() {
            window.uploader = uploader;
        });

        // 当有文件添加进来时执行，负责view的创建
        function addFile( file ) {
            var $li = $( '<li id="' + file.id + '">' +
                    '<p class="title">' + file.name + '</p>' +
                    '<p class="imgWrap"></p>'+
                    '<p class="progress"><span></span></p>' +
                    '</li>' ),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo( $li ),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find( 'p.imgWrap' ),
                $info = $('<p class="error"></p>'),

                showError = function( code ) {
                    switch( code ) {
                        case 'exceed_size':
                            text = '文件大小超出';
                            break;

                        case 'interrupt':
                            text = '上传暂停';
                            break;

                        default:
                            text = '上传失败，请重试';
                            break;
                    }

                    $info.text( text ).appendTo( $li );
                };

            if ( file.getStatus() === 'invalid' ) {
                showError( file.statusText );
            } else {
                // @todo lazyload
                $wrap.text( '预览中' );
                uploader.makeThumb( file, function( error, src ) {
                    var img;

                    if ( error ) {
                        $wrap.text( '不能预览' );
                        return;
                    }

                    if( isSupportBase64 ) {
                        img = $('<img src="'+src+'">');
                        $wrap.empty().append( img );
                    } else {
                        $.ajax('../../server/preview.php', {
                            method: 'POST',
                            data: src,
                            dataType:'json'
                        }).done(function( response ) {
                            if (response.result) {
                                img = $('<img src="'+response.result+'">');
                                $wrap.empty().append( img );
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight );

                percentages[ file.id ] = [ file.size, 0 ];
                file.rotation = 0;
            }

            file.on('statuschange', function( cur, prev ) {
                if ( prev === 'progress' ) {
                    $prgress.hide().width(0);
                } else if ( prev === 'queued' ) {
                    $li.off( 'mouseenter mouseleave' );
                    $btns.remove();
                }

                // 成功
                if ( cur === 'error' || cur === 'invalid' ) {
                    console.log( file.statusText );
                    showError( file.statusText );
                    percentages[ file.id ][ 1 ] = 1;
                } else if ( cur === 'interrupt' ) {
                    showError( 'interrupt' );
                } else if ( cur === 'queued' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                    percentages[ file.id ][ 1 ] = 0;
                } else if ( cur === 'progress' ) {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if ( cur === 'complete' ) {
                    $prgress.hide().width(0);
                    $li.append( '<span class="success"></span>' );
                }

                $li.removeClass( 'state-' + prev ).addClass( 'state-' + cur );
            });

            $li.on( 'mouseenter', function() {
                $btns.stop().animate({height: 30});
            });

            $li.on( 'mouseleave', function() {
                $btns.stop().animate({height: 0});
            });

            $btns.on( 'click', 'span', function() {
                var index = $(this).index(),
                    deg;

                switch ( index ) {
                    case 0:
                        uploader.removeFile( file );
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if ( supportTransition ) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css( 'filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation='+ (~~((file.rotation/90)%4 + 4)%4) +')');
                    // use jquery animate to rotation
                    // $({
                    //     rotation: rotation
                    // }).animate({
                    //     rotation: file.rotation
                    // }, {
                    //     easing: 'linear',
                    //     step: function( now ) {
                    //         now = now * Math.PI / 180;

                    //         var cos = Math.cos( now ),
                    //             sin = Math.sin( now );

                    //         $wrap.css( 'filter', "progid:DXImageTransform.Microsoft.Matrix(M11=" + cos + ",M12=" + (-sin) + ",M21=" + sin + ",M22=" + cos + ",SizingMethod='auto expand')");
                    //     }
                    // });
                }


            });

            $li.appendTo( $queue );
        }

        // 负责view的销毁
        function removeFile( file ) {
            var $li = $('#'+file.id);

            delete percentages[ file.id ];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each( percentages, function( k, v ) {
                total += v[ 0 ];
                loaded += v[ 0 ] * v[ 1 ];
            } );

            percent = total ? loaded / total : 0;


            spans.eq( 0 ).text( Math.round( percent * 100 ) + '%' );
            spans.eq( 1 ).css( 'width', Math.round( percent * 100 ) + '%' );
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if ( state === 'ready' ) {
                text = '选中' + fileCount + '张图片，共' +
                        WebUploader.formatSize( fileSize ) + '。';
            } else if ( state === 'confirm' ) {
                stats = uploader.getStats();
                if ( stats.uploadFailNum ) {
                    text = '已成功上传' + stats.successNum+ '张照片至XX相册，'+
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                        WebUploader.formatSize( fileSize )  +
                        '），已上传' + stats.successNum + '张';

                if ( stats.uploadFailNum ) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html( text );
        }

        //更新不同状态
        function setState( val ) {
            var file, stats;

            if ( val === state ) {
                return;
            }

            $upload.removeClass( 'state-' + state );
            $upload.addClass( 'state-' + val );
            state = val;

            switch ( state ) {
                case 'pedding':
                    $placeHolder.removeClass( 'element-invisible' );
                    $queue.hide();
                    $statusBar.addClass( 'element-invisible' );
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass( 'element-invisible' );
                    $( '#filePicker2' ).removeClass( 'element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $( '#filePicker2' ).addClass( 'element-invisible' );
                    $progress.show();
                    $upload.text( '暂停上传' );
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text( '继续上传' );
                    break;

                case 'confirm':
                    $progress.hide();
                    $( '#filePicker2' ).removeClass( 'element-invisible' );
                    $upload.text( '开始上传' );

                    stats = uploader.getStats();
                    if ( stats.successNum && !stats.uploadFailNum ) {
                        setState( 'finish' );
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if ( stats.successNum ) {
                        alert( '上传成功,点击返回' );
                       var url1="../autoRepayment/findPaymentInfomation.action?no=2&id=${pactInfo.pactId}&redirect=updateAutoRepay";
                        window.location=url1;
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }
        //当文件上传成功时触发
        uploader.on('uploadSuccess', function(file, data) {
        	//alert(file.id+"-----"+data.code);
        	
        });
        //上传过程中触发，携带上传进度
        uploader.onUploadProgress = function( file, percentage ) {
            var $li = $('#'+file.id),
                $percent = $li.find('.progress span');

            $percent.css( 'width', percentage * 100 + '%' );
            percentages[ file.id ][ 1 ] = percentage;
            updateTotalProgress();
        };
        //当文件被加入队列以后触发
        uploader.onFileQueued = function( file ) {
            fileCount++;
            fileSize += file.size;

            if ( fileCount === 1 ) {
                $placeHolder.addClass( 'element-invisible' );
                $statusBar.show();
            }

            addFile( file );
            setState( 'ready' );
            updateTotalProgress();
        };

        //当文件被移除队列后触发
        uploader.onFileDequeued = function( file ) {
            fileCount--;
            fileSize -= file.size;

            if ( !fileCount ) {
                setState( 'pedding' );
            }

            removeFile( file );
            updateTotalProgress();

        };

        uploader.on( 'all', function( type ) {
            var stats;
            switch( type ) {
                case 'uploadFinished':
                    setState( 'confirm' );
                    break;

                case 'startUpload':
                    setState( 'uploading' );
                    break;

                case 'stopUpload':
                    setState( 'paused' );
                    break;

            }
        });

        uploader.onError = function( code ) {
            alert( 'Eroor: ' + code );
        };
		
        
        
        $upload.on('click', function() {
            if ( $(this).hasClass( 'disabled' ) ) {
                return false;
            }

            if ( state === 'ready' ) {
                uploader.upload();
            } else if ( state === 'paused' ) {
                uploader.upload();
            } else if ( state === 'uploading' ) {
                uploader.stop();
            }
        });

        $info.on( 'click', '.retry', function() {
            uploader.retry();
        } );

        $info.on( 'click', '.ignore', function() {
            alert( 'todo' );
        } );

        $upload.addClass( 'state-' + state );
        updateTotalProgress();
    });

})( jQuery );
</script>
	</div>
</div>

</body>
</html>