
<div id="setgradepage">
<div class="container">
<h5>Course:${coffr.course.course_name}</h5>

<g:form name="myForm" action="processGrade" id="gradeForm">
     <table class="table  table-bordered col-sm-12 table-strip" >
     <tr>
        <th>Grade</th><td ><input class="form-control" type="text" name="grade" placeholder="Eg. AA,BB,A+,A..." required /> </td>
     </tr>
     <tr>
             <th rowspan="2" style="vertical-align:middle">Range(MIN to MAX)</th><td><input  class="form-control" type="number" name="from" placeholder="MIN Eg:10" required /></td>
     </tr>
       <tr> <td><input class="form-control" type="number" name="to" placeholder="MAX Eg:20" required /></td>
     </tr>
     <tr>
             <th>Description</th><td><input class="form-control" type="text" name="desp" placeholder="Eg. First Class"/ required> </td>
     </tr>
      <tr > <td colspan="2"><g:hiddenField name="coffr" value="${coffr.id}" />

              <center> <g:submitToRemote class="btn btn-primary " url="[action: 'processGrade']" update="setgradepage" value="Add" before="if(!validateGradeForm()) return false;" /></center></td>
      </tr>
     </table>


</g:form>

<div id = "updateMe" >

<table class="table  table-bordered col-sm-12 table-strip ">
<tr>
<th>Grade</th>
<th colspan="2">Range</th>
<th>Description</th>
<th>Edit</th>
</tr>
<tr>
<th></th>
<th>From</th>
<th >To</th>

<th></th>
<th></th>
</tr>
<g:each in="${ag}">
        <tr>
        <td><input  class="form-control" type="text" name="grade" value="${it.grade_name}" disabled /></td>
 <td><input class="form-control" type="number" name="from" value="${it.grade_lower_value}" disabled /></td>
        <td><input class="form-control" type="number" name="to" value="${it.grade_higher_value}" disabled /></td>



        <td><input class="form-control" type="text" name="desp" value="${it.description}" disabled /></td>

        <td><a href="#" onclick=editGrade(${it.id},${coffr.id})><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></td>
        </tr>

        </g:each>
        </table>
 </div>


</div>