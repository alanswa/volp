<html>
<head>
<meta name="layout" content="blank">
</head>
<script>
function callme()
{
        alert("Parth Cartoon");
        var uploadcategory1 = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
       //         xmlhttp.open("GET", "xyz", true);
                xmlhttp.open("GET", "${request.contextPath}/assignment/processaddassignment?uploadcategory1=" + uploadcategory1, true);
                xmlhttp.send();
        return(false)
  }
</script>
<body><br>
<table>
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><input class="form-control" name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<tr><td><label>Start Date</label></td> <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td></tr>
<tr><td><label>End Date</label></td> <td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td></tr>
</tbody>
</table>
<br><b>Add/Edit Assignment</b><br>
<g:form url="[action:'processaddassignment']">
<table>
<tbody>
<tr>
<td><label>Select Upload Category</label></td>
<td><g:select class="form-control" name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" /></td>
</tr>
<tr><td><label>Assignment Type</label></td><td><g:select class="form-control" name="assignmenttype" id="assignmenttype" from="${assignmenttypelist}"/></td></tr>
<tr><td><td><div id="material2"></div></td><td></tr>
<tr><td></td><td><g:submitButton name="Proceed" class="btn btn-primary" value="Proceed"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
</body>
</html>