<table>
<tbody>
 <g:if test="${session.uploadcategory=='Topic'}">
<tr><td>Course Topic</td><td><g:select name="coursetopic" id="coursetopic" from="${coursetopiclist.topic}"/></td></tr>
</g:if>
<tr><td><lable>Assignment No.</lable></td><td><input name="assignment_no" id="assignment_no" type="text" ></td></tr>
<tr><td><lable>Assignment Title</lable></td><td><input name="assignment_title" id="assignment_title" type="text" size="60" ></td></tr>
<tr><td><lable>Assignment Description</lable></td><td><input name="assignment_description" id="assignment_description" type="text" size="60"></td></tr>
<tr><td><lable>Assignment Weightage</lable></td><td><input name="assignment_weightage" id="assignment_weightage" type="text" ></td></tr>
<tr><td><lable>Problem Statemen</lable>t</td><td><input name="assignment_text" id="assignment_text" type="text" size="60" ></td></tr>
<tr><td><lable>Difficulty Level</lable></td><td><g:select name="difficultylevel" id="difficultylevel" from="${difficultylevellist}"/></td></tr>
<tr><td><lable>Scheduled Date</lable></td><td><g:datePicker name="schedule_date"   precision="day" value="${new Date()}"/></td></tr>
<tr><td><lable>Due Date</lable></td><td><g:datePicker name="due_date"   precision="day" value="${new Date()}"/></td></tr>
<tr><td><lable>Assignment Link</lable></td><td><input name="assignment_link" id="assignment_link" type="text" size="60" ></td></tr>
<tr><td><lable>Assignment File Name</lable></td><td><input name="assignment_name" id="assignment_name" type="text" size="60" ></td></tr>
    <g:if test="${session.assignmenttype=='MCQ'}">
        <tr><td><lable>Option Name</lable></td><td><g:select name="option_name" id="option_name" from="${mcqoptionlist}"/></td></tr>
        <tr><td><lable>Option Text</lable></td><td><input name="option_value" id="option_value" type="text" size="60" ></td></tr>
        <tr><td>Is Correct Option?</td><td><g:select name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
        <tr><td><lable>Option File Link</lable></td><td><input name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Option File Name</lable></td><td><input name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr>
        <tr><td></td><td><input type="Button" name="Add Another Option" id="bt" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/></td></tr>
    </g:if>
    <g:else>
        <tr><td><lable>Model Answer Text</lable></td><td><input name="model_answer_text" id="model_answer_text" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Link</lable></td><td><input name="model_answer_link" id="model_answer_link" type="text" size="60" ></td></tr>
        <tr><td><lable>Model Answer File Name</lable></td><td><input name="model_answer_name" id="model_answer_name" type="text" size="60" ></td></tr>
    </g:else>
</tbody>
</table>