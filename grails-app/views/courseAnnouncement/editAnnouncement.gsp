<div class="container">
<g:form>
<table class="table table-stripped table-bordered ">
    <tr>
    <th>Notice</th>
    <td><input class="form-control" type="text" name="notice" value="${ca.notice}" required></td>
    <tr>
    <tr>
    <th>Scheduled Date</th>
    <td>Prev:${ca.schedule_date}::<g:datePicker name="myDate" value="${new Date()}" /></td>
    <tr>
    <td colspan="2">
    <center>
    <g:hiddenField name="coffrid" value="${coffr.id}" />
     <g:hiddenField name="caid" value="${ca.id}" />
     <g:submitToRemote class="btn btn-primary" url="[action: 'saveEditAnnouncement']" update="material" value="Edit"/>
    </center>
    </td>
    </tr>
</table>

</g:form>
</div>
<br>
<br>