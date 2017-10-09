/**
 * Created by liwenlong on 2017/6/1.
 */

(function(){

    var getPhoto = function () {
        var id = "15601840101";
        var url = "/account/getAccountPhoto/"+id;
        $.ajax({
            type: 'GET',
            url: url,
            contentType: 'application/json',
            success: function(data){
                $("#account_photo").attr('src', data);
            },
            error: function(xmlRequest,type,e){
                alert('error');
            }
        });
    }
    getPhoto();

    var inputProduct = function(){

        var loan = {};
        loan.name=$('#product_name').val();

        var requestInformation = {};
        requestInformation.minAge = $('#minAge').val();
        requestInformation.maxAge = $('#maxAge').val();
        $('input[name="base_data"]:checked').each(function(){
            requestInformation[$(this).val()] = true;
        });

        requestInformation.minLoanDay = $('#minLoanDay').val();
        requestInformation.maxLoanDay = $('#maxLoanDay').val();

        requestInformation.needSociaIlnsurance = $("input[name='needSociaIlnsurance']:checked").val();
        requestInformation.needFund = $("input[name='needFund']:checked").val();
        loan.requestInformation = requestInformation;
        var url = "/product/inputProduct";
        $.ajax({
            type: 'POST',
            url: url,
            data : JSON.stringify(loan),
            contentType: 'application/json',
            processData: false,
            success: function(data){

            },
            error: function(xmlRequest,type,e){
            }
        });
    }

    $('#product_input').on('click', inputProduct);

})();
//任何需要执行的js特效
