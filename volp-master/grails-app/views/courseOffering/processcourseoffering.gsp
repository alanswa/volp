<html>
<head>
<meta name="layout" content="blank">
</head>
<body>
<br>
<h5 class="well well-sm">Offer Course</h5>
<g:form url="[action:'savecourseoffering']">
<table>
<tbody>
<tr><td>Course:</td><td><input class="form-control" name="course" type="text" value="${course.course_name}" readonly></td></tr>
<tr><td><label>Start Date:</label></td><td><g:datePicker class="form-control" name="startdate"  precision="day" value="${new Date()}" /></td></tr>
<tr><td><label>End Date:</label></td><td><g:datePicker class="form-control" name="enddate"  precision="day" value="${new Date()}" /></td></tr>
<tr><td>Batch Description:</td><td><input class="form-control" name="batch" type="text"></td></tr>
 <tr><td>Course Pattern:</td><td><g:select class="form-control" name="courseofferingtype" from="${courseofferingtypelist.name}"/></td></tr>

<g:if test="${session.isuserbelongstoorganization==true}">
    <tr><td>Year:</td><td><g:select class="form-control" name="year" from="${yearlist.year}"/></td></tr>
    <tr><td>Academic Year:</td><td><g:select class="form-control" name="ay" from="${aylist.ay}"/></td></tr>
    <tr><td>Semester:</td><td><g:select class="form-control" name="sem" from="${semlist.sem}"/></td></tr>
    <tr><td>Course Type:</td><td><g:select class="form-control" name="coursetype" from="${coursetypelist.type}"/></td></tr>
</g:if>

<tr><td colspan="2" align="right"><g:submitButton class="btn btn-primary" name="offer course" value="Offer Course"></g:submitButton></td></tr>
</tbody>
</table>
</g:form>
<br>
                <table class="table table-striped table-responsive table-bordered"  id="data1" style="width:auto">
                    <thead>
                        <tr class="danger" >
                            <th>Course</th>
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Batch</th>
                            <th>isLive</th>
                            <th>Course Pattern</th>
                            <g:if test="${session.isuserbelongstoorganization==true}">
                                <th>Year</th>
                                <th>Academic Year</th>
                                <th>Semester</th>
                                <th>Course Type</th>
                            </g:if>
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${courseofferinglist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                                <td>${d.course.course_name}</td>
                                <td> <g:formatDate class="form-control" date="${d.start_date}" format="dd-MM-yyyy"/></td>
                                <td> <g:formatDate class="form-control" date="${d.end_date}" format="dd-MM-yyyy"/></td>
                                <td>${d.batch}</td>
                                <td>${d.isActive}</td>
                                <td>${d.courseofferingtype.name}</td>
                                 <g:if test="${session.isuserbelongstoorganization==true}">
                                    <g:if test="${d.year!=null}">
                                        <td>${d.year.year}</td>
                                    </g:if>
                                    <g:if test="${d.academicyear!=null}">
                                        <td>${d.academicyear}</td>
                                    </g:if>
                                    <g:if test="${d.semester!=null}">
                                        <td>${d.semester.sem}</td>
                                    </g:if>
                                    <g:if test="${d.coursetype!=null}">
                                        <td>${d.coursetype.type}</td>
                                    </g:if>
                                 </g:if>
                                 <g:else>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                 </g:else>
                                 <td> <g:link class="btn btn-primary" action="updatecourseoffering" id="${d.id}">edit</g:link></td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>


</body>
</html>