<div class="container">
<g:form>
    <table class="table table-stripped table-bordered ">
        <tr>
        <th>Notice</th>
        <td><input class="form-control" type="text" name="notice" placeholder="Enter the Notice here..." required></td>
        <tr>
        <tr>
        <th>Scheduled Date</th>
        <td><g:datePicker name="myDate" value="${new Date()}" /> </td>
        <tr>
     <g:hiddenField name="coffrid" value="${coffr.id}" />
    <g:hiddenField name="caid" value="${ca.id}" />
    <tr> <td colspan="2"><center><g:submitToRemote class="btn btn-primary" url="[action: 'processCourseAnnouncement']" update="material" value="Add"/></center></td></tr>
    </table>

</g:form>

<table class="table table-stripped table-bordered ">
<tr>
<th>Notice</th>
<th>Date</th>
<th> Edit</th>
</tr>
<g:each var="c" in="${ca}">
<tr>
<td>${c.notice}</td><td>${c.schedule_date}</td><td><a href="#" onclick="editAnnouncement(${c.id},${coffr.id})"><i class="fa fa-edit fa-2x"></i></a>
</td>
</tr>
</g:each>
</table>
</div>
<br>
<br>
