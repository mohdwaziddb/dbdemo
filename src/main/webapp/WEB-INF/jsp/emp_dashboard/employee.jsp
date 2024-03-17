<%--<%@ include file="/WEB-INF/jsp/student_dashboard_pwa/header.jsp"%>--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script type="text/javascript" src="/Common/js/common.js"></script>
<style>

</style>
</head>
<body>
Employees Details :- <br>
<div id="htmlprint">
<table border="1" width="100%" cellspacing="0" cellpadding="4" style="border-collapse: collapse" bordercolor="#000000">
    <thead>
    <tr>
        <td>Sr.No</td>
        <td>Name</td>
        <td>Email</td>
        <td>Phone</td>
    </tr>
    </thead>
    <tbody id="dataofemployee">

    </tbody>
</table>
</div>
<br>
<br>
<button class="btn btn-lg btn-primary" id="submit" onclick="location.href ='/mvc/dashboard/createdesignation'" >
    Create Designation
</button>

<br>
<br>
<button class="btn btn-lg btn-primary" id="submit" onclick="viewopen('/api/testing/downloadExcel')" >
    Download Excel
</button>

<br>
<br>
<button class="btn btn-lg btn-primary" id="submit" onclick="viewopen('/api/testing/downloadPDF')" >
    Download PDF
</button>

<br>
<br>
<button class="btn btn-lg btn-primary" id="submit" onclick="printpagediv('htmlprint')" >
    Download PDF with HTML
</button>


<%--<%@ include file="/WEB-INF/jsp/student_dashboard_pwa/footer.jsp"%>--%>

<script>
    $(document).ready(function(){
        getEmployeeList();
    });

    function getEmployeeList(){
        var domain = window.location.origin;
        var dataurl = domain + "/api/testing/EmployeeList";
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

                var employee_array = response.employee_array;
                var tabletrdate = "";
                var srno = 0;

                for(var i=0; i < employee_array.length; i++){
                    srno++
                    let em = employee_array[i];
                    let empid = em.empid;
                    let empname = em.empname;
                    let empemail = em.empemail;
                    let empphoneno = em.empphoneno;

                    tabletrdate += '<tr>' +
                        '<td>'+srno+'</td>'+
                        '<td>'+empname+'</td>'+
                        '<td>'+empemail+'</td>'+
                        '<td>'+empphoneno+'</td>'+
                        '</tr>'

                }
                $("#dataofemployee").html(tabletrdate);
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

</script>
</body>

</html>