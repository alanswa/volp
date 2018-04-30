<div class="container">
<g:form url="[action:'saveupdatecourseoutline']">
<table class="table col-sm-12 table-striped  table-bordered"  id="data1" >
<tbody>
<tr>
<td ><b>Course</b></td>
<td ><input class="flow-control" name="coursecodecoursename" type="text" value="${coursecodecoursename}" readonly> </td>
</tr>
<tr>
<td><b>Course Outline Number</b></td>
<td><input class="flow-control" name="outline_number" type="text"  value="${outline_number}"></td>
</tr>
<tr>
<td><b>Course Outline Text</b></td>
<td><input class="flow-control" name="outline" type="text" value="${outline}" ></td>
</tr>
<tr>

<!-- <td><input class="flow-control" name="email" type="hidden" value="${email}" ></td>
<td><input class="flow-control" name="coursecode" type="hidden" value="${coursecode}" ></td>
<td><input class="flow-control" name="coursename" type="hidden" value="${coursename}" ></td> -->

<td colspan="2"><g:submitButton class="btn btn-primary" name="Update" value="Update"></g:submitButton></td>
</tr>
</tbody>
</table>
</g:form>
</div>