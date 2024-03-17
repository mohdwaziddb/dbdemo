<%--
  Created by IntelliJ IDEA.
  User: sakshi-pc
  Date: 26-Jul-23
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Designation</title>
    <link rel="stylesheet" href="/Common/css/jquery.toast.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript" src="/Common/js/common.js"></script>
    <script type="text/javascript" src="/Common/js/jquery.toast.js"></script>
</head>

Create Designation:-<br><br>

<input type="text" id="inp"></input>
<button class="btn btn-lg btn-primary" id="but">Create</button>
<br><br>

<button class="btn btn-lg btn-primary" onclick="createfromDao">Create from Dao</button>
<br><br>

<table id="tableid" border="1" width="100%" cellspacing="0" cellpadding="4" style="border-collapse: collapse;" bordercolor="#000000">
    <thead>
    <tr>
        <td>ID</td>
        <td>Designation</td>
    </tr>
    </thead>
    <tbody id="dataofdesignation">

    </tbody>
</table>

<script>

    $(document).ready(function () {
        $("#but").click(function () {
            if($("#inp").val() == ''){
                showValidationMessage("ERROR", "error", "Field is blanked");
            } else {
                createDesignation();
            }
        });

        getDesignationList();
    });

    function createDesignation() {
        var inpValue = $("#inp").val();
        var domain = window.location.origin;
        var dataurl = domain + "/api/testing/CreateDesignation";
        var datapass = {
            "designation":inpValue,
        };
        $.ajax({
            type: "POST",
            data: JSON.stringify(datapass),
            url: dataurl,
            //headers: getHeaders("GET"),
            contentType: "application/json",
            success: function (response) {
                if(response.message == "success"){
                    showValidationMessage("Success", "success", "Designation Created");
                    setTimeout(function () {
                        window.location.reload();
                    }, 1500);
                }
            },
            complete: function () {
                //hideloader();
                //showValidationMessage("Success", "success", "Designation Created");
            },
            error: function (error) {
                showValidationMessage("ERROR", "error", error.responseJSON.message);
            },
        });
    }

    function getDesignationList(){
        var domain = window.location.origin;
        //var dataurl = domain + "/api/testing/getEmpDesignationList";
        var dataurl = domain + "/api/testing/empDesignationlistSQL";
        $.ajax({
            type:"GET",
            data:{},
            url:dataurl,
            //headers: getHeaders("GET"),
            contentType: "application/json",
            beforeSend : function(){
                //showloader();
            },
            success:function(response){
                console.log(response);


                var designation_array = response.designation_array;
                /*if(designation_array.length > 0) {
                    $("#tableid").attr("style","display:block");
                }*/
                var tabletrdate = '';

                for(var i=0; i < designation_array.length; i++){
                    let em = designation_array[i];
                    let id = em.id;
                    let designation = em.designation;

                    tabletrdate += '<tr>' +
                        '<td>'+id+'</td>'+
                        '<td>'+designation+'</td>'+
                        '</tr>'

                }
                $("#dataofdesignation").html(tabletrdate);
                //code goes here
            },
            complete: function () {
                //hideloader();
            },
            error: function (error) {
                showValidationMessage("ERROR", "error", error.responseJSON.message);
            },
        })
    }
    
    function createfromDao() {
        console.log("check")
    }


</script>




</body>
</html>
