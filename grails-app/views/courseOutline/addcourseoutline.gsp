<head>
<meta name="layout" content="blank">
</head>
<div>

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">

<g:form url="[action:'savecourseoutline']">


<label>Course</label>
<input class="form-control" name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly >
<br/>
<label>Course Unit/Outline Number</label>
<input class="form-control" name="outline_number" type="text"  >

<label>Course Unit/Outline Text</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input class="form-control" name="outline" type="text"  >
<!-- <td><input name="email" type="hidden" value="${email}" ></td>
<input class="form-control" name="coursecode" type="hidden" value="${coursecode}" >
<input class="form-control" name="coursename" type="hidden" value="${coursename}" >
<input class="form-control" name="coursecodecoursename" type="hidden" value="${coursecodecoursename}" > </td> -->

<center><g:submitButton class="btn btn-primary" name="Add" value="Add"></g:submitButton> </center>
</g:form>
</div>

<br/>

                <table class="table col-sm-12 table-striped  table-bordered"  id="data1" >
                    <thead>
                        <tr class="danger" >

                            <th>Outline No.</th>
                            <th>Outline</th>
                            <th>Edit</th>

                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${courseOutlinelist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                                <td>${d.outline_number}</td>
                                <td>${d.outline}</td>
                                <td>  <a href="#" onclick="callMe(${d.id});"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<!-- <g:link action="deletecourseoutline" id="${d.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></g:link> --><span class="glyphicon glyphicon-edit"/></td>
                            <!--    <td> <g:link action="updatecourseoutline" id="${d.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></g:link>&nbsp;&nbsp;&nbsp;<g:link action="deletecourseoutline" id="${d.id}"><i class="fa fa-trash-o" aria-hidden="true"></i></g:link><span class="glyphicon glyphicon-edit"/></td>  -->
                            </tr>
                        </g:each>
                    </tbody>
                </table>
</div>

</div>
<div id="edit"></div>
<script>
    function callMe(id){
       // alert("callme");
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("edit").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseOutline/updatecourseoutline?id=" + id, true);
                xmlhttp.send();
    }
</script>
</body>
</html>