
<div class="container" id="editgrade">
<g:form name="myForm" action="processEditGrade" >
<table class="table  table-striped table-bordered  col-sm-12 ">
<tr>
<th>Grade</th>
<th colspan="2">Range</th>
<th>Description</th>
<th> Edit</th>
</tr>
<tr>
<th></th>
<th >From</th>
<th>To</th>
<th></th>
<th></th>
</tr>

        <tr>
        <td><input class="form-control" type="text" name="grade" value="${ag.grade_name}" required /></td>
 <td><input class="form-control" type="number" name="from" value="${ag.grade_lower_value}" required min="0" /></td>
        <td><input class="form-control" type="number" name="to" value="${ag.grade_higher_value}" required min="0" /></td>



        <td><input class="form-control" type="text" name="desp" value="${ag.description}" required /></td>
        <td> <g:hiddenField name="ag" value="${ag.id}" />
             <g:hiddenField name="coffr" value="${coffr.id}" />
            <g:submitToRemote class="btn btn-primary " url="[action: 'processEditGrade']" update="editgrade" value="Update"/>
           </td>

        </tr>

</table>

</g:form>
</div>
<br>
<br>
<br>

