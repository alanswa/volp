<g:form>
    <table>
        <tr><th>Notice</th>
        <td><input type="text" name="notice" placeholder="Enter the Notice here..." required></td><tr>
        <tr><th>Scheduled Date</th>
                <td><g:datePicker name="myDate" value="${new Date()}" /><tr>

    </table>
    <g:hiddenField name="coffrid" value="${coffr.id}" />
    <g:hiddenField name="caid" value="${ca.id}" />
    <g:submitToRemote url="[action: 'processCourseAnnouncement']" update="material" value="Add"/>
</g:form>

<table>
<tr><th>Notice</th><th>Date</th></tr>
<g:each var="c" in="${ca}">
<tr><td>${c.notice}</td><td>${c.schedule_date}</td><td><a href="#" onclick="editAnnouncement(${c.id},${coffr.id})">Edit</a></td></tr>
</g:each>

