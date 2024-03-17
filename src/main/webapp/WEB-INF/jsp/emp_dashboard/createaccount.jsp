<%--
  Created by IntelliJ IDEA.
  User: sakshi-pc
  Date: 27-Jul-23
  Time: 5:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Acount</title>
    <link rel="stylesheet" href="/Common/css/jquery.toast.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript" src="/Common/js/common.js"></script>
    <script type="text/javascript" src="/Common/js/jquery.toast.js"></script>
</head>
<body>

<input type='text' id="username" name="username" class='form-control' placeholder='Enter Username'>
<label class='label_input' id='username_label_input'>Enter Username</label>
<br><br>

<input type='text' id="emailid" name="emailid" class='form-control' placeholder='Enter Email Id'>
<label class='label_input' id='emailid_label_input'>Enter Email Id</label>
<br><br>

<input type='text' id="phoneno" name="phoneno" class='form-control' placeholder='Enter Phone No'>
<label class='label_input' id='phoneno_label_input'>Enter Phone No</label>
<br><br>

<select id="designation_select" name="designation_select" onchange="showDesignation(this.value)">

</select>
<label class='label_input' id='designation_label_select'>Select Designation</label>
<input id="designation_input_hidden" type="hidden" value="">
<br><br>

<input type='password' class='form-control' id="password" name="password" placeholder='Enter Password'>
<img onclick="showPassword()" src="https://resources.edunexttechnologies.com/web-data/edunext-website/img/eye-off.svg" id="EYE" class="calendar_icon">
<br><br>

<button id="user_create_btn">Create</button>

<script>

    $(document).ready(function () {
        $("#user_create_btn").click(function () {
            createNewAccount();
        });
        getDesignationList();
    });

    function createNewAccount() {
        var username = $("#username").val();
        var emailid = $("#emailid").val();
        var phoneno = $("#phoneno").val();
        var password = $("#password").val();
        var designation_id = $("#designation_input_hidden").val();
        var domain = window.location.origin;
        var dataurl = domain + "/api/testing/CreateEmployee";

        var datapass = {
            "username":username,
            "emailid":emailid,
            "phoneno":phoneno,
            "password":password,
            "designation_id":designation_id,
        };

        $.ajax({
            type:"POST",
            data:JSON.stringify(datapass),
            contentType:"application/json",
            url:dataurl,
            success:function (response) {
                console.log(response)
                if(response.message == "Success"){
                    //showValidationMessage("Success", "success", "Login Success");
                    localStorage.setItem("username", username);
                    localStorage.setItem("password", Base64.encode(password));

                    window.location.href="/mvc/dashboard/employeedetail";

                } else {
                    showValidationMessage("ERROR", "error", "Enter valid information");
                }
            },
            complete: function () {
                //showValidationMessage("Success", "success", "Login Success");
            },
            error: function (error) {
                showValidationMessage("ERROR", "error", "Enter valid information");
            },

        })
    }

    function getDesignationList(){
        var domain = window.location.origin;
        var dataurl = domain + "/api/testing/getEmpDesignationList";
        $.ajax({
            type:"GET",
            data:{},
            contentType:"application/json",
            url:dataurl,
            success:function (response) {
                console.log(response)
                var designation_array = response.designation_array;
                var optiondata = '<option value="-1">-- Select --</option>';
                for(var i in designation_array){
                    let d = designation_array[i];
                    let designation = d.designation
                    let designation_id = d.id
                    optiondata+='<option value="'+designation_id+'">'+designation+'</option>'
                }
                $("#designation_select").html(optiondata);


            },
            complete: function () {
                //showValidationMessage("Success", "success", "Login Success");
            },
            error: function (error) {
                showValidationMessage("ERROR", "error", "No Designation Available.");
            },

        })
    }

    function showDesignation(value) {
        $("#designation_input_hidden").val(value)
    }
</script>
</body>
</html>
