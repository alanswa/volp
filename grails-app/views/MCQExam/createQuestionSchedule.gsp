<g:form controller="MCQExam">
<g:if test="${mcqSchedule1}">
    Select Exam Schedule Date <g:datePicker name="scheduleDate" value="${mcqSchedule1?.shedulDate}"  precision="day" noSelection="['':'-Choose-']" default="${new Date().plus(7)}" relativeYears="[0..7]" type="date"/>
    <br> Previous Start Time ${mcqSchedule1.startTime}
    <br>Start Time:<g:select name="startTimeH" from="${1..12}"value="${mcqSchedule1?.startTime}" noSelection="['':'-Choose your Hours-']" type="number"/> Minute :<g:select name="startTimeM" from="${0..60}" noSelection="['':'-Choose your Minute-']"/>
    <br> Previous end Time ${mcqSchedule1.endTime}
    <br>End Time:<g:select name="endTimeH" from="${1..12}" value="${mcqSchedule1?.endTime}" noSelection="['':'-Choose your Hours-']"/> Minute :<g:select name="endTimeM" from="${0..60}" noSelection="['':'-Choose your Minute-']"/>
    <br>Session Maximum SDuration<g:textField name="maxtime"  value="${mcqSchedule1.maxTime}"/>
</g:if>
<g:else>
    Select Exam Schedule Date <g:datePicker name="scheduleDate" value="${new Date()}" precision="day" noSelection="['':'-Choose-']" default="${new Date().plus(7)}" relativeYears="[0..7]" type="date"/>
    <br>Start Time:<g:select name="startTimeH" from="${1..12}" noSelection="['':'-Choose your Hours-']"/> Minute :<g:select name="startTimeM" from="${0..60}" noSelection="['':'-Choose your Minute-']"/>
    <br>End Time:<g:select name="endTimeH" from="${1..12}" noSelection="['':'-Choose your Hours-']"/> Minute :<g:select name="endTimeM" from="${0..60}" noSelection="['':'-Choose your Minute-']"/>
    <br>Session Maximum SDuration<g:textField name="maxtime" />
</g:else>
<br><g:actionSubmit value="Set Schedule Time" action="SetScheduleTime" />
</g:form>