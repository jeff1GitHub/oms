<!--请在下方写此页面业务相关的脚本-->


(function ($) {
    // 当domReady的时候开始初始化
    $(function () {
        var $wrap = $('.uploader-list-container'),

            // 图片容器
            $queue = $('<ul class="filelist"></ul>')
                .appendTo($wrap.find('.queueList')),

            // 状态栏，包括进度和控制按钮
            $statusBar = $wrap.find('.statusBar'),

            // 文件总体选择信息。
            $info = $statusBar.find('.info'),

            // 上传按钮
            $upload = $wrap.find('.uploadBtn'),

            // 没选择文件之前的内容。
            $placeHolder = $wrap.find('.placeholder'),

            $progress = $statusBar.find('.progress').hide(),

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
            isSupportBase64 = (function () {
                var data = new Image();
                var support = true;
                data.onload = data.onerror = function () {
                    if (this.width != 1 || this.height != 1) {
                        support = false;
                    }
                }
                data.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
                return support;
            })(),

            // 检测是否已经安装flash，检测flash的版本
            flashVersion = (function () {
                var version;

                try {
                    version = navigator.plugins['Shockwave Flash'];
                    version = version.description;
                } catch (ex) {
                    try {
                        version = new ActiveXObject('ShockwaveFlash.ShockwaveFlash')
                            .GetVariable('$version');
                    } catch (ex2) {
                        version = '0.0';
                    }
                }
                version = version.match(/\d+/g);
                return parseFloat(version[0] + '.' + version[1], 10);
            })(),

            supportTransition = (function () {
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

        if (!WebUploader.Uploader.support('flash') && WebUploader.browser.ie) {

            // flash 安装了但是版本过低。
            if (flashVersion) {
                (function (container) {
                    window['expressinstallcallback'] = function (state) {
                        switch (state) {
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

                    var swf = 'expressInstall.swf';
                    // insert flash object
                    var html = '<object type="application/x-shockwave-flash" data="' + swf + '" ';

                    if (WebUploader.browser.ie) {
                        html += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" ';
                    }

                    html += 'width="100%" height="100%" style="outline:0">' +
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
            alert('Web Uploader 不支持您的浏览器！');
            return;
        }

        // 实例化
        uploader = WebUploader.create({
            pick: {
                id: '#filePicker-2',
                label: '点击选择图片'
            },
            formData: {
                uid: 123
            },
            dnd: '#dndArea',
            paste: '#uploader',
            swf: 'lib/webuploader/0.1.5/Uploader.swf',
            chunked: false,
            chunkSize: 512 * 1024,
            server: 'work/uploadImage',
            // runtimeOrder: 'flash',

            // accept: {
            //     title: 'Images',
            //     extensions: 'gif,jpg,jpeg,bmp,png',
            //     mimeTypes: 'image/*'
            // },

            // 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
            disableGlobalDnd: true,
            fileNumLimit: 300,
            fileSizeLimit: 200 * 1024 * 1024,    // 200 M
            fileSingleSizeLimit: 50 * 1024 * 1024    // 50 M
        });

        // 拖拽时不接受 js, txt 文件。
        uploader.on('dndAccept', function (items) {
            var denied = false,
                len = items.length,
                i = 0,
                // 修改js类型
                unAllowed = 'text/plain;application/javascript ';

            for (; i < len; i++) {
                // 如果在列表里面
                if (unAllowed.indexOf(items[i].type)) {
                    denied = true;
                    break;
                }
            }

            return !denied;
        });

        uploader.on('dialogOpen', function () {
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

        uploader.on('ready', function () {
            window.uploader = uploader;
        });
        
        uploader.on('uploadSuccess', function (file, result){
//        	console.log(file);
//        	console.log(result);
        	$("#"+file.id+"a").attr("href", result.url);
        	//TODO 将上传的路径保存到隐藏变量中，提交的时候保存到数据库
        	$("#fileValues").append("<input name='filePath' value='"+ result.url +"'></input>");
        	$("#fileValues").append("<input name='fileName' value='"+ result.originFileName +"'></input>");
        });
        
        // 当有文件添加进来时执行，负责view的创建
        function addFile(file) {
            var $li = $('<li id="' + file.id + '">' +
            		'<a id="'+ file.id +'a" href="javascript:;" download="' + file.name + '">'+
                '<p class="title">' + file.name + '</p>' +
                '<p class="imgWrap"></p>' +
                '<p class="progress"><span></span></p>' +
                '</a>' +
                '</li>'),

                $btns = $('<div class="file-panel">' +
                    '<span class="cancel">删除</span>' +
                    '<span class="rotateRight">向右旋转</span>' +
                    '<span class="rotateLeft">向左旋转</span></div>').appendTo($li),
                $prgress = $li.find('p.progress span'),
                $wrap = $li.find('p.imgWrap'),
                $info = $('<p class="error"></p>'),

                showError = function (code) {
                    switch (code) {
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

                    $info.text(text).appendTo($li);
                };

            if (file.getStatus() === 'invalid') {
                showError(file.statusText);
            } else {
                // @todo lazyload
                $wrap.text('预览中');
                uploader.makeThumb(file, function (error, src) {
                    var img;

                    if (error) {
                        $wrap.text('不能预览');
                        return;
                    }

                    if (isSupportBase64) {
                        img = $('<img src="' + src + '">');
                        $wrap.empty().append(img);
                    } else {
                        $.ajax('lib/webuploader/0.1.5/server/preview.php', {
                            method: 'POST',
                            data: src,
                            dataType: 'json'
                        }).done(function (response) {
                            if (response.result) {
                                img = $('<img src="' + response.result + '">');
                                $wrap.empty().append(img);
                            } else {
                                $wrap.text("预览出错");
                            }
                        });
                    }
                }, thumbnailWidth, thumbnailHeight);

                percentages[file.id] = [file.size, 0];
                file.rotation = 0;
            }

            file.on('statuschange', function (cur, prev) {
                if (prev === 'progress') {
                    $prgress.hide().width(0);
                } else if (prev === 'queued') {
                    $li.off('mouseenter mouseleave');
                    $btns.remove();
                }

                // 成功
                if (cur === 'error' || cur === 'invalid') {
                    console.log(file.statusText);
                    showError(file.statusText);
                    percentages[file.id][1] = 1;
                } else if (cur === 'interrupt') {
                    showError('interrupt');
                } else if (cur === 'queued') {
                    percentages[file.id][1] = 0;
                } else if (cur === 'progress') {
                    $info.remove();
                    $prgress.css('display', 'block');
                } else if (cur === 'complete') {
                    $li.append('<span class="success"></span>');
                }

                $li.removeClass('state-' + prev).addClass('state-' + cur);
            });

            $li.on('mouseenter', function () {
                $btns.stop().animate({height: 30});
            });

            $li.on('mouseleave', function () {
                $btns.stop().animate({height: 0});
            });

            $btns.on('click', 'span', function () {
                var index = $(this).index(),
                    deg;

                switch (index) {
                    case 0:
                        uploader.removeFile(file);
                        return;

                    case 1:
                        file.rotation += 90;
                        break;

                    case 2:
                        file.rotation -= 90;
                        break;
                }

                if (supportTransition) {
                    deg = 'rotate(' + file.rotation + 'deg)';
                    $wrap.css({
                        '-webkit-transform': deg,
                        '-mos-transform': deg,
                        '-o-transform': deg,
                        'transform': deg
                    });
                } else {
                    $wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
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

            $li.appendTo($queue);
        }

        // 负责view的销毁
        function removeFile(file) {
            var $li = $('#' + file.id);

            delete percentages[file.id];
            updateTotalProgress();
            $li.off().find('.file-panel').off().end().remove();
        }

        function updateTotalProgress() {
            var loaded = 0,
                total = 0,
                spans = $progress.children(),
                percent;

            $.each(percentages, function (k, v) {
                total += v[0];
                loaded += v[0] * v[1];
            });

            percent = total ? loaded / total : 0;


            spans.eq(0).text(Math.round(percent * 100) + '%');
            spans.eq(1).css('width', Math.round(percent * 100) + '%');
            updateStatus();
        }

        function updateStatus() {
            var text = '', stats;

            if (state === 'ready') {
                text = '选中' + fileCount + '张图片，共' +
                    WebUploader.formatSize(fileSize) + '。';
            } else if (state === 'confirm') {
                stats = uploader.getStats();
                if (stats.uploadFailNum) {
                    text = '已成功上传' + stats.successNum + '张照片至XX相册，' +
                        stats.uploadFailNum + '张照片上传失败，<a class="retry" href="#">重新上传</a>失败图片或<a class="ignore" href="#">忽略</a>'
                }

            } else {
                stats = uploader.getStats();
                text = '共' + fileCount + '张（' +
                    WebUploader.formatSize(fileSize) +
                    '），已上传' + stats.successNum + '张';

                if (stats.uploadFailNum) {
                    text += '，失败' + stats.uploadFailNum + '张';
                }
            }

            $info.html(text);
        }

        function setState(val) {
            var file, stats;

            if (val === state) {
                return;
            }

            $upload.removeClass('state-' + state);
            $upload.addClass('state-' + val);
            state = val;

            switch (state) {
                case 'pedding':
                    $placeHolder.removeClass('element-invisible');
                    $queue.hide();
                    $statusBar.addClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'ready':
                    $placeHolder.addClass('element-invisible');
                    $('#filePicker2').removeClass('element-invisible');
                    $queue.show();
                    $statusBar.removeClass('element-invisible');
                    uploader.refresh();
                    break;

                case 'uploading':
                    $('#filePicker2').addClass('element-invisible');
                    $progress.show();
                    $upload.text('暂停上传');
                    break;

                case 'paused':
                    $progress.show();
                    $upload.text('继续上传');
                    break;

                case 'confirm':
                    $progress.hide();
                    $('#filePicker2').removeClass('element-invisible');
                    $upload.text('开始上传');

                    stats = uploader.getStats();
                    if (stats.successNum && !stats.uploadFailNum) {
                        setState('finish');
                        return;
                    }
                    break;
                case 'finish':
                    stats = uploader.getStats();
                    if (stats.successNum) {
                        alert('上传成功');
                    } else {
                        // 没有成功的图片，重设
                        state = 'done';
                        location.reload();
                    }
                    break;
            }

            updateStatus();
        }

        uploader.onUploadProgress = function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress span');

            $percent.css('width', percentage * 100 + '%');
            percentages[file.id][1] = percentage;
            updateTotalProgress();
        };

        uploader.onFileQueued = function (file) {
            fileCount++;
            fileSize += file.size;

            if (fileCount === 1) {
                $placeHolder.addClass('element-invisible');
                $statusBar.show();
            }

            addFile(file);
            setState('ready');
            updateTotalProgress();
        };

        uploader.onFileDequeued = function (file) {
            fileCount--;
            fileSize -= file.size;

            if (!fileCount) {
                setState('pedding');
            }

            removeFile(file);
            updateTotalProgress();

        };

        uploader.on('all', function (type) {
            var stats;
            switch (type) {
                case 'uploadFinished':
                    setState('confirm');
                    break;

                case 'startUpload':
                    setState('uploading');
                    break;

                case 'stopUpload':
                    setState('paused');
                    break;

            }
        });

        uploader.onError = function (code) {
            alert('Eroor: ' + code);
        };

        $upload.on('click', function () {
            if ($(this).hasClass('disabled')) {
                return false;
            }

            if (state === 'ready') {
                uploader.upload();
            } else if (state === 'paused') {
                uploader.upload();
            } else if (state === 'uploading') {
                uploader.stop();
            }
        });

        $info.on('click', '.retry', function () {
            uploader.retry();
        });

        $info.on('click', '.ignore', function () {
            alert('todo');
        });

        $upload.addClass('state-' + state);
        updateTotalProgress();
    });
})(jQuery);

$(function () {
    var ue = UE.getEditor('editor');
});

var id = getUrlParam("id");
var role = getUrlParam("role");
$("#role").val(getUrlParam("id"));

function addTaskRemark() {
	if (id == null) {
		layer.msg("新建任务不能增加备注！", {icon: 5, time: 2000});
	} else {
		var taskRemark = $("#taskRemark").val();
		if(taskRemark.trim().length > 0){
			$("#taskRemarks").append($("<div><span>"+ taskRemark +"</span></div>"));
		}
		$.ajax({
            url: "work/remark",
            type: "post",
            data: {
            	id:id,
                remark:taskRemark
            },
            success: function (result) {
                layer.msg(result.message, {icon: 1, time: 1000});
            },
            error: function (result) {
            	layer.msg(result.message, {icon: 1, time: 1000});
            }
        });
	}
}

function showTab(obj) {
	$(".cur").removeClass("cur");
    $(obj).parent("li").addClass('cur');
    var tabId = $(obj).attr("name");
    $(".tabCon").hide();
    $("#"+tabId).show();
}

$("#optionsRadios1").hide();
$("#optionsRadios2").hide();
$("#optionsRadios1Text").text();
$("#optionsRadios2Text").text();

$(document).ready(function () {
    if (id != null) {
        // 设置禁止修改
        $("#priority").attr("disabled", true);
        $("#aliasName").attr("disabled", true);
        $("#domain").attr("disabled", true);
        // 只允许运维修改故障标签
        if ($("#roleId").val() != 2) {
            $("#taskFaultType").attr("disabled", true);
        }

        $.get("work/" + id, 
        	{
            	id: id
        	}, 
        	function (result) {
            if (result.code == 200) {
                $("#id").val(result.data.id);
                $("#aliasName").val(result.data.aliasName);
                $("#faultType").val(result.data.faultType.code);
                $("#priority").val(result.data.priority.code);
                $("#domain").val(result.data.domain);
                $("#describe").val(result.data.describe);
                $("#aliasName").val(result.data.aliasName);

        	    var ue = UE.getEditor('editor');  
        	    var htmlStr = $("#describe").val();
        	    ue.ready(function() {  
        	        ue.setContent(htmlStr, false);  
        	    });
        	    
        	    //显示操作日志
        	    var taskLogs = result.data.taskLogs;
        	    if(taskLogs != null && taskLogs.length > 0){
        	    	for(var i = 0; i < taskLogs.length; i++){
        	    		$("<div>"+ taskLogs[i].message + "&nbsp;&nbsp;&nbsp;&nbsp;"+ taskLogs[i].createTime +"</div><br>").appendTo($("#tab2"));
        	    	}
        	    }
        	    
        	    //显示备注
        	    var taskRemarks = result.data.taskRemarks;
        	    if(taskRemarks != null && taskRemarks.length > 0){
        	    	for(var i = 0; i < taskRemarks.length; i++){
        	    		$("<div><span>" + taskRemarks[i].createUser + "&nbsp;&nbsp;&nbsp;&nbsp;"+ taskRemarks[i].createTime +"</span></div>").appendTo($("#taskRemarks"));
        	    		$("<div><span>" + taskRemarks[i].remark + "</span></div>").appendTo($("#taskRemarks"));
        	    	}
        	    }
        	    
        	    //TODO 显示上传的附件
        	    displayAttachment(result.data.taskAttachments);
        	    
                if (role == 1) {
                    if (result.data.status.code == 3) {
                        $("#submitBtn").attr("onclick", "confirmWork()");
                        $("#submitBtn").html("确认任务!");
                        $("#submitBtn").attr("disabled", false);
                        
                        $("#optionsRadios1").show();
                        $("#optionsRadios2").show();
                        $("#optionsRadios1Text").text("已解决");
                        $("#optionsRadios2Text").text("未解决");
                    }
                } else if (role == 2) {
                    if (result.data.status.code == 2 || result.data.status.code == 4) {
                        $("#submitBtn").attr("onclick", "finishWork()");
                        $("#submitBtn").html("完成任务!");
                        $("#submitBtn").attr("disabled", false);

                        $("#optionsRadios1").show();
                        $("#optionsRadios2").show();
                        $("#optionsRadios1Text").text("已解决");
                        $("#optionsRadios2Text").text("无效问题");
                        //$('input[type=radio]').each(function () {
                        //    $(this).removeAttr(hidden);
                        //});
                    } else if (result.data.status.code == 1) {
                    	$("#submitBtn").html("接受任务!");
                        $("#submitBtn").attr("onclick", "receiveWork()");
                        $("#submitBtn").attr("disabled", false);
                        
                        $("#optionsRadios1").hide();
                        $("#optionsRadios2").hide();
                    }
                }
                
              	//显示解决状态
                if(result.data.status.code == 5){
                	$("#optionsRadios1").prop("checked","checked");
                	$("#optionsRadios1").show();
                    $("#optionsRadios2").show();
                    $("#optionsRadios1Text").text("已解决");
                    $("#optionsRadios2Text").text("未解决");
                    $("#submitBtn").attr("disabled", false);
                    $("#submitBtn").hide();
                }
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    } else {
    	//如果如果是新增
    	$("#submitBtn").attr("disabled", false);
    }
});

/**
 * 提交任务(客服)
 */
function submit() {
	var workContent
	
	var fileName = "";
    $("input[name='fileName']").each(function(i){
    	fileName += $(this).val() + ";";
    });
    if(fileName.length > 0){
    	fileName = fileName.substring(0, fileName.length-1);
    }
    
    var filePath = "";
    $("input[name='filePath']").each(function(i){
    	filePath += $(this).val() + ";";
    });
    if(filePath.length > 0){
    	filePath = filePath.substring(0, filePath.length-1);
    }
    
    $.ajax({
        url: "work",
        type: "put",
        data: {
            faultType: $("#faultType").val(),
            priority: $("#priority").val(),
            describe: UE.getEditor('editor').getContent(),
            aliasName: $("#aliasName").val(),
            domain: $("#domain").val(),
            level: $("#level").val(),
            fileNames: fileName,
            filePaths: filePath
        },
        success: function (result) {
            if (result.code == 200) {
                layer.msg(result.message, {icon: 1, time: 1000});
                parent.$("#form-search").submit();

                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        },
        error: function (result) {

        }
    });
}

/**
 * 接受任务(运维)
 */
function receiveWork() {
    layer.confirm('确定接受任务!', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var url = "receiveWork/" + id;
        $.post(url, {}, function (result) {
            if (result.code == 200) {
                layer.msg(result.message, {icon: 1, time: 1000});
                parent.$("#form-search").submit();

                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    });
}

/**
 * 完成任务(运维)
 */
function finishWork() {
    layer.confirm('确定完成任务!', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var url = "finishWork/" + id;
        var status = $('input[name="optionsRadios"]').filter(':checked').val();
		var faultType = $("#faultType").val();
        $.post(url, {status: status, faultType: faultType}, function (result) {
            if (result.code == 200) {
                layer.msg(result.message, {icon: 1, time: 1000});
                parent.$("#form-search").submit();

                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    });
}

/**
 * 确认任务(客服)
 */
function confirmWork() {
    layer.confirm('确定修改任务状态!', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var url = "confirmWork/" + id;

        var status = $('input[name="optionsRadios"]').filter(':checked').val();
        
        $.post(url, {status: status}, function (result) {
            if (result.code == 200) {
                layer.msg(result.message, {icon: 1, time: 1000});
                parent.$("#form-search").submit();

                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            } else {
                layer.msg(result.message, {icon: 5, time: 1000});
            }
        }, "json");
    });
}

function displayAttachment(attachments){
	if(attachments != null && attachments.length > 0){
		for(var i = 0; i < attachments.length; i++) {
			var fileListUl = $(".filelist"),innerLi;
			var temp = attachments[i].url.toLowerCase()
			if(temp.lastIndexOf("jpeg")>0
					|| temp.lastIndexOf("jpg")>0
					|| temp.lastIndexOf("png")>0
					|| temp.lastIndexOf("gif")>0
					|| temp.lastIndexOf("bmp")>0){
				innerLi = $("<li id='WU_FILE_"+ i +"' class='state-complete'>"
	   				     + "<a href='" + attachments[i].url + "' download='" + attachments[i].fileName + "'>"
	   				     + "<img src='" + attachments[i].url + "' />"
	   				  	 + "<p> "+ attachments[i].fileName +"</p>"
	   				     + "</a></li>");
			} else {
				innerLi = $("<li id='WU_FILE_"+ i +"' class='state-complete'>"
   				     	+ "<a href='" + attachments[i].url + "' download='" + attachments[i].fileName + "'>"
   						+ "<link src='" + attachments[i].url + "' />"
   						+ "<p> "+ attachments[i].fileName +"</p>"
   						+ "<p>无法预览</p>"
   				     	+ "</a></li>");
			}
			fileListUl.append(innerLi);
		}
		$(".statusBar").attr("style", "display:none");
		$("#dndArea").addClass("element-invisible");
	}
}
