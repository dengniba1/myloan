
String.prototype.Trim = function()
{
	//?????????
	return this.replace(/(^\s*)|(\s*$)/g , "");
}
String.prototype.TrimAllSpace = function()
{
	//?????????
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
        $('#btn_get_mobile_captcha').html('???????');
        clearInterval(intervalid);
        i=90;
        clickok=true;
    }
    else
    {
        $('#btn_get_mobile_captcha')[0].disabled=true;
        $('#btn_get_mobile_captcha').html(i+'???????');
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
                if(result=='??????'){
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

/*??*/


function checkMobile()
{
	var mobile=$('[name="zcmoblie"]').val();
	mobile=mobile.Trim();
	$('#mobile').val(mobile);
	if(mobile==''){
		$('.error_msg').html("????????");
        //showErr('mobile_error');
        return false;
	}
    else if(! /^1[3|4|5|7|8|9]\d{9}$/.test(mobile) )
	{
    	$('.error_msg').html("????????");
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
            $('.error_msg').html("?????????");
        }else if(!zcphoneKey){
            $('.error_msg').html("???????????");
        }else if(zcpswd == ""){
            $('.error_msg').html("???????");
        }else if(zcpswd.length<6 || zcpswd.length>20){
        	$('.error_msg').html("??????6~20???");
        }else if(isNaN(mobile_captcha)){
            $('.error_msg').html("???????????????");
        }
        else{
        	track_check_success("???????");
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
                		track_check_success("???????????");
                    	window.location.href = "/bd/collaborate/guide.html";
                    }else{
                        $('.error_msg').html(data.msg);
                    }
                }
            });
        }
    });

    //????????
	$(document).delegate(".call-vcode", "click", function(){
		var mobile=$('[name="zcmoblie"]').val();
        $('.error_msg').html('????????...');
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
                $('.error_msg').html('?????????????????????????');
            }
        });
	});
});


jQuery(function(){
    $(".two-h2").addClass("high");
    $(".one").hide();

    $(".one-h2").on("click",function(e){
        var timestamp = new Date().getTime();
        $('[name="verifyPic1"]').attr('src','/xun/validcode/genVerifyCode?t='+timestamp);
        $(".one-h2").addClass("high");
        $(".two-h2").removeClass("high");
        $(".one").show();
        $(".two").hide();
    });
    $(".two-h2").on("click",function(e){
        var timestamp = new Date().getTime();
        $('[name="verifyPic2"]').attr('src','/xun/validcode/genVerifyCode?t='+timestamp);
        $(".one-h2").removeClass("high");
        $(".two-h2").addClass("high");
        $(".one").hide();
        $(".two").show();
    });
    $(".two-h2").click();
    function login(){
        var phoneNumber=$("#login_phone").value;
        var password=$("#login_password").value;
        var validCode = $("#login_validateCode").val();
//        var phoneCode = $('#checkCode').val();

        if(phoneNumber.length==0)
        {
            $("#errmsg").val("??????");
            return;
        }
        if(password.length==0)
        {
            $("#errmsg").val("?????");
            return;
        }
        if(validCode.length==0)
        {
            $("#errmsg").val("??????");
            return;
        }
        var rawpwd = doRSAEncrypt(password);
        $('#login').attr('disabled',"true");

        $.ajax({
            url : "index",
            type:"post",
            data : "username="+encodeURI(encodeURI(username))+"&password="+rawpwd+code,
            success : function(data) {
                if(data!="success"){
                    alert(data);
                }else{
                    window.location.href="../../../index.html"
                }
                $('#login').removeAttr("disabled");
            }
        });
    }

});
