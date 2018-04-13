<html>
<head>
<meta name="layout" content="blank">


</head>
<body>
<br>

<g:form url="[action:'saveupdatecourseoffering']">
<table>
<tbody>
<tr><td><label>Course</label></td><td><input class="form-control" name="course" type="text" value="${session.course.course_name}" readonly> </td></tr>
<tr><td><label>Start Date:</label></td><td><g:datePicker name="startdate"  precision="day" value="${coff.start_date}" /></td></tr>
<tr><td><label>End Date:</label></td><td><g:datePicker name="enddate"  precision="day" value="${coff.end_date}" /></td></tr>
<tr><td>Batch Description:</td><td><input class="form-control" name="batch" type="text" value="${coff.batch}"></td></tr>
<g:if test="${session.isuserbelongstoorganization==true}">
    <tr><td>Year:</td><td><g:select class="form-control" name="year" from="${yearlist.year}"" value="${coff.year.year}"/></td></tr>
    <tr><td>Academic Year:</td><td><g:select class="form-control" name="ay" from="${aylist.ay}" value="${coff.academicyear.ay}"/></td></tr>
    <tr><td>Semester:</td><td><g:select class="form-control" name="sem" from="${semlist.sem}"" value="${coff.semester.sem}"/></td></tr>
    <tr><td>Course Type:</td><td><g:select class="form-control" name="coursetype" from="${coursetypelist.type}"" value="${coff.coursetype.type}"/></td></tr>
    <tr><td>Course Pattern:</td><td><g:select class="form-control" name="courseofferingtype" from="${courseofferingtypelist.name}" value="${coff.courseofferingtype.name}"/></td></tr>
    <tr><td>Want to Archive?:</td><td><g:select class="form-control" name="archive" from="${archivelist}" value="${archive}"/></td></tr>
</g:if>
<tr></tr>
<tr><td colspan="2" align="right"><g:submitButton class="btn btn-primary" name="update course offering" value="update"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
</body>
</html>