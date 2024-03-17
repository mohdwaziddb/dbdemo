<%--
  Created by IntelliJ IDEA.
  User: sakshi-pc
  Date: 27-Jul-23
  Time: 12:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Detail</title>
    <link rel="stylesheet" href="/Common/css/jquery.toast.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript" src="/Common/js/common.js"></script>
    <script type="text/javascript" src="/Common/js/jquery.toast.js"></script>
</head>
<body>
Employee Details :-
<br>
<br>
<div id="dataofemployee">

</div>
<br><br>
<button id="log_out">Log Out</button>


<script>
    $(document).ready(function () {
        getSingleEmpData()
        
        $("#log_out").click(function () {
            localStorage.removeItem("username");
            localStorage.removeItem("password");
            window.location.href="/";
        })
    });


    function getSingleEmpData() {
        var username = localStorage.getItem("username");
        var domain = window.location.origin;
        var dataurl = domain + "/api/testing/getEmployeeListbyname";
        var datapass = {
            "name":username,
        }

        //datalist['check_'+username] = username;

        $.ajax({
            type:"GET",
            contentType:"application/json",
            url:dataurl,
            data:datapass,
            success:function (response) {
                //console.log(response);
                var data = response.data[0];
                //console.log(data)
                var empdata = '';
                let empid = data.empid;
                let empname = data.empname;
                let empemail = data.empemail;
                let empphoneno = data.empphoneno;

                empdata += '<table id="tableid" border="1" width="100%" cellspacing="0" cellpadding="4" style="border-collapse: collapse;" bordercolor="#000000">' +
                    '<tr>' +
                    '<td>Id</td>' +
                    '<td>'+empid+'</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<td>Name</td>' +
                    '<td>'+empname+'</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<td>Email</td>' +
                    '<td>'+empemail+'</td>' +
                    '</tr>' +
                    '<tr>' +
                    '<td>Phone No.</td>' +
                    '<td>'+empphoneno+'</td>' +
                    '</tr>'


                $("#dataofemployee").html(empdata);


            },
            complete: function () {
                //showValidationMessage("Success", "success", "Designation Created");
            },
            error: function (error) {
                showValidationMessage("ERROR", "error", "No Information Available");
            },
        })

    }
</script>
</body>
</html>
