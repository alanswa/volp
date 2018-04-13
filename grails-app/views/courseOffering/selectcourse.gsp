<div class="container">
<h5>Offer Course</h5>
<g:form url="[action:'processcourseoffering']">
<table class="table table-bordered">
<tr>
<td><h5>Select Course</h5></td>
<td><g:select class="form-control" name="coursecodecoursename" from="${courselist}"/></td>
</tr>
<tr>
<td colspan="2"><center><g:submitButton class="btn btn-primary" name="Proceed" value="Proceed"></g:submitButton></center></td>

</tr>
</table>
</g:form>
</div>
<br>
