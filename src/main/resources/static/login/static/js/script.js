
String.prototype.Trim = function()
{
	//去掉字符串首尾空格
	return this.replace(/(^\s*)|(\s*$)/g , "");
}
String.prototype.TrimAllSpace = function()
{
	//去掉字符串所有空格
	return this.replace(/\s+/g,"");
}
var i=90;
var intervalid;
var clickok=true;
if($('#pc_zhuCeJiShiTime').val()){
    i=$('#pc_zhuCeJiShiTime').val();
}
function timerRun()
{
    if(i<=0)
    {
        $('#btn_get_mobile_captcha')[0].disabled=false;
        $('#btn_get_mobile_captcha').html('点此获取验证码');
        clearInterval(intervalid);
        i=90;
        clickok=true;
    }
    else
    {
        $('#btn_get_mobile_captcha')[0].disabled=true;
        $('#btn_get_mobile_captcha').html(i+'秒后可重新获取');
        i--;
    }
}
$(document).ready(function(){
	$('#zcpswd').blur(function(){
	    checkPassword();
	});
    if($('#pc_zhuCeJiShiTime').val()){
        clickok=false;
        $('#btn_get_mobile_captcha')[0].disabled=true;
        i=$('#pc_zhuCeJiShiTime').val();
        intervalid=setInterval(timerRun,1000);
    }
    $('#btn_get_mobile_captcha').click(function(){
        if(clickok==false){
            return;
        }
        if(checkMobile()){
            //showSucc('mobile_error');
            clickok=false;
            $('#btn_get_mobile_captcha')[0].disabled=true;
            intervalid=setInterval(timerRun,1000);

            var mobile=$('[name="zcmoblie"]').val();
            var mobile_verifyPic=$('#validateCode').val();
            mobile=mobile.Trim();
            $.ajax({
                type: 'GET',
                url: "/bd/collaborate/setDaoJiShi",
                timeout: 10000,
                dataType: 'json',
                success: function(data){
                    //alert(data.msg);
                },
                error: function(xmlRequest,type,e){
                    alert('error');
                }
            });

            $.get("/bd/collaborate/getCaptcha?mobile="+mobile+"&verify="+mobile_verifyPic,function(result){
                $('.error_msg').html(result);
                if(result=='验证码不正确'){
                    i=0;
                    if('https:' == document.location.protocol){
                        $('#verifyPic').attr('src','/xun/validcode/genVerifyCode');
                    }
                    else{
                        $('#verifyPic').attr('src','/xun/validcode/genVerifyCode');
                    }
                }
            });
        }
    });
});
function track_check_success(text){
	var ext = encodeURIComponent('&success='+encodeURIComponent(text));
	var img = document.createElement('img');
    img.src = get_ra() + '&ext=' + ext;
}
function checkPassword()
{
    var pwd=$('#zcpswd').val();
    pwd=pwd.Trim();
    $('#zcpswd').val(pwd);
}

/*注册*/


function checkMobile()
{
	var mobile=$('[name="zcmoblie"]').val();
	mobile=mobile.Trim();
	$('#mobile').val(mobile);
	if(mobile==''){
		$('.error_msg').html("手机号码不能为空");
        //showErr('mobile_error');
        return false;
	}
    else if(! /^1[3|4|5|7|8|9]\d{9}$/.test(mobile) )
	{
    	$('.error_msg').html("手机号码格式错误");
        //showErr('mobile_error');
		return false;
	}
	else{
        //showSucc('mobile_error');
        $('.error_msg').html("");
	    return true;
	}
}



$(function(){
    $(document).delegate("#PostSubmit2", "click", function(){
        var zcmoblie = $('[name="zcmoblie"]').val(),
            zcpswd = $('[name="zcpswd"]').val(),
            mobile_captcha = $('[name="mobile_captcha"]').val();
        zcmobile =  zcmoblie.TrimAllSpace();
        var zcphoneKey = /^1[3|4|5|7|8|9]\d{9}$/.test(zcmobile);
        zcpswd = zcpswd.Trim();

        if(zcmoblie == ""){
            $('.error_msg').html("请填写你的手机号码");
        }else if(!zcphoneKey){
            $('.error_msg').html("请正确填写你的手机号码");
        }else if(zcpswd == ""){
            $('.error_msg').html("请填写你的密码");
        }else if(zcpswd.length<6 || zcpswd.length>20){
        	$('.error_msg').html("密码长度要求6~20位字符");
        }else if(isNaN(mobile_captcha)){
            $('.error_msg').html("输入验证码中包含特殊字符或字母");
        }
        else{
        	track_check_success("注册页认证成功");
        	$.ajax({
                type: 'POST',
                url: '/bd/collaborate/ajaxValidateVerifyCode.html',
                data: {
                    "mobile": zcmobile,
                    "password": zcpswd,
                    "mobile_captcha": mobile_captcha
                },
                dataType: 'json',
                timeout: 5000,
                success:function(data){
                	if(data.success == 1){
                		track_check_success("注册跳转到完善个人信息");
                    	window.location.href = "/bd/collaborate/guide.html";
                    }else{
                        $('.error_msg').html(data.msg);
                    }
                }
            });
        }
    });
    
    //电话告诉我验证码
	$(document).delegate(".call-vcode", "click", function(){
		var mobile=$('[name="zcmoblie"]').val();
        $('.error_msg').html('正在发送，请稍后...');
		$.ajax({
            type: 'POST',
            url: '/bd/collaborate/getVoiceCaptcha',
            data: {
					mobile: mobile
                },
            timeout: 10000,
            success: function(data){
                $('.error_msg').html(data);
            },
            error: function(xmlRequest,type,e){
                $('.error_msg').html('获取电话验证码出现错误，请尝试重新获取短信验证码。');
            }
        });	
	});
});
