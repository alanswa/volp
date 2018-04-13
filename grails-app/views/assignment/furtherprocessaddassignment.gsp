<table>
<tbody>
 <tr><td><lable>Option Name</lable></td><td><g:select name="option_name" id="option_name" from="${mcqoptionlist}"/></td></tr>
 <tr><td><lable>Option Text</lable></td><td><input name="option_value" id="option_value" type="text" size="60" ></td></tr>
         <tr><td>Is Correct Option?</td><td><g:select name="isCorrect" id="isCorrect" from="${correctoptionlist}"/></td></tr>
 <tr><td><lable>Option File Link</lable></td><td><input name="mcq_option_file_link" id="mcq_option_file_link" type="text" size="60" ></td></tr>
 <tr><td><lable>Option File Name</lable></td><td><input name="mcq_option_file_name" id="mcq_option_file_name" type="text" size="60" ></td></tr>
 <tr><td></td><td><input type="Button" name="Add Another Option" id="bt" value="Add Another Option" onclick="return fetchaddoptions(${session.trackoptions});"/></td></tr>
 </tbody>
</table>