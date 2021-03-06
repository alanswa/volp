<head>
<meta name="layout" content="blank">
</head>
<div class="container">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">
<g:form url="[action:'savecourseoutcomes']">


<label>Course</label>
<input  class="form-control" name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly>
<br/>
<label>Course Outcome Number</label>
<input  class="form-control" name="outcome_number" type="number" min="0" >
<br/>
<label>Course Outcome Statement</label>
<input  class="form-control" name="outcome_statement" type="text"  >

<br/>
<label>Revision Year:</label>
<g:select class="form-control" name="ay" from="${aylist}" value=""/>
<br/>
<center><g:submitButton class="btn btn-primary" name="Add" value="Add"></g:submitButton></center>
</g:form>
<br/>
                <table class=" col-sm-12 table table-striped table-bordered">
                    <thead>
                        <tr class="danger" >

                            <th>Outcome Number.</th>
                            <th>Outcome Statement</th>
                            <th>Revision Year</th>
                            <th>Live</th>
                            <th>Update/Edit</th>

                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${courseoutcomelist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                                <td>${d.co_code}</td>
                                <td>${d.co_statement}</td>
                                <td>${d.revisionyear}</td>
                                <td>${d.isCurrent}</td>
                                <td> <a href="#editoutcome" onclick="callMeAddCourseOutcomes(${d.id});"><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"/></td>
                          <!--      <td> <g:link action="updatecourseoutcome" id="${d.id}"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></g:link>&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-edit"/></td>  -->
                            </tr>
                        </g:each>
                    </tbody>
                </table>
</div>
<div id="edit"></div>
<script>
    function callMeAddCourseOutcomes(id){
       // alert("callme");
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("edit").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseOutcomes/updatecourseoutcome?id=" + id, true);
                xmlhttp.send();
    }
    </script>