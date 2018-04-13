<html>
<head>
<meta name="layout" content="blank">

<script>
function fetchaddoptions(myval)
{
  var x=document.getElementById("bt");
  x.style.display='none';
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                                document.getElementById("test"+myval).innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/assignment/furtherprocessaddassignment", true);
                   xmlhttp.send();
         //  return(false)
}
function fetchcourseoutline()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/assignment/addcourseoutlines?courseoutline=" + courseoutline, true);
                xmlhttp.send();
       // return(false)
  }

</script>
</head>
<body>
 <form action="saveassignment">
<table>
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="coursecodecoursename" type="text" value="${coff.course.course_code}&nbsp;${coff.course.course_name}" readonly></td></tr>
<tr><td><label>Description</label></td><td><input class="form-control" name="batch" type="text" value="${coff.batch}" readonly></td></tr>
<tr><td><label>Start Date</label></td> <td> <g:formatDate date="${coff.start_date}" name="start_date" format="dd-MM-yyyy"/></td></tr>
<tr><td><label>End Date</label></td> <td> <g:formatDate date="${coff.end_date}" name="end_date" format="dd-MM-yyyy"/></td></tr>
<tr><td><label>Assignment Type</label></td><td><input class="form-control" name="assignmenttype" type="text" value="${assignmenttype}" readonly></td></tr>
</tbody>
</table>
<br><h5>Add/Edit Assignment</h5><br>
<table>
<tbody>
<g:if test="${session.uploadcategory=='Unit' || session.uploadcategory=='Topic'}">
<tr><td>Course Outline/Unit</td><td><g:select class="form-control" name="courseoutline" id="courseoutline" from="${courseoutlinelist.outline}" onChange="fetchcourseoutline();"/></td></tr>
</g:if>
<g:else>
<tr><td><lable>Assignment No.</lable></td><td><input class="form-control" name="assignment_no" id="assignment_no" type="text" ></td></tr>
<tr><td><lable>Assignment Title</lable></td><td><input class="form-control" name="assignment_title" id="assignment_title" type="text" size="60" ></td></tr>
<tr><td><lable>Assignment Description</lable></td><td><input class="form-control" name="assignment_description" id="assignment_description" type="text" size="60"></td></tr>
<tr><td><lable>Assignment Weightage</lable></td><td><input class="form-control" name="assignment_weightage" id="assignment_weightage" type="text" ></td></tr>
<tr><td><lable>Problem Statement</lable>t</td><td><input class="form-control" name="assignment_text" id="assignment_text" type="text" size="60" ></td></tr>
<tr><td><lable>Difficulty Level</lable></td><td><g:select class="form-control" name="difficultylevel" id="difficultylevel" from="${difficultylevellist}"/></td></tr>
<tr><td><lable>Scheduled Date</lable></td><td><g:datePicker class="form-control" name="schedule_date"   precision="day" value="${new Date()}"/></td></tr>
<tr><td><lable>Due Date</lable></td><td><g:datePicker name="due_date"   precision="day" value="${new Date()}"/></td></tr>
<tr><td><lable>Assignment Link</lable></td><td><input class="form-control" name="assignment_link" id="assignment_link" type="text" size="60" ></td></tr>
<tr><td><lable>Assignment File Name</lable></td><td><input class="form-control" name="assignment_name" id="assignment_name" type="text" size="60" ></td></tr>
    <g:if test="${session.assignmenttype=='MCQ'}">
        <tr><td><lable>Option Name</lable></td><td><g:select class="form-control" name="option_name" id="option_name" from="${mcqoptionlist}"/></td></tr>
        <tr><td><lable>Option Text</lable></td><td><input class="form-control" name="option_value" id="option_value" type="text" size="60" ></td></tr>
        <tr><td>Is Correct Option?</td><td><g:select class="form-control" name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
        <tr><td><lable>Option File Link</lable></td><td><input class="form-control" name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Option File Name</lable></td><td><input class="form-control" name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr>
        <tr><td></td><td><input class="btn btn-primary" type="button" name="Add Another Option" id="bt" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/></td></tr>
    </g:if>
    <g:else>
        <tr><td><lable>Model Answer Text</lable></td><td><input class="form-control" name="model_answer_text" id="model_answer_text" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Link</lable></td><td><input class="form-control" name="model_answer_link" id="model_answer_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Name</lable></td><td><input class="form-control" name="model_answer_name" id="model_answer_name" type="text" size="60" ></td></tr>
    </g:else>
</g:else>
 <td><div id="material2"></div></td>
 <td><div id="test1"></div></td>
 <td><div id="test2"></div></td>
 <td><div id="test3"></div></td>
 <td><div id="test4"></div></td>
 <td><div id="test5"></div></td>
 <td><div id="test6"></div></td>
 <td><div id="test7"></div></td>
 <td><div id="test8"></div></td>
 <tr><td></td><td><input class="btn btn-primary" type="submit" value="Save Assignment" /></td></tr>
</tbody>
</table>
</form>
</body>
</html>