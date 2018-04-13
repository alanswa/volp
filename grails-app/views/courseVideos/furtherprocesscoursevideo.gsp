<div class="container col-sm-6">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div>

<table class="table table-responsive" style="width:auto">
<tbody>
<g:if test="${session.uploadcategory=='Topic'}">
<tr><td>Course Topic</td><td><g:select name="coursetopic" id="coursetopic" from="${coursetopiclist.topic}"/></td></tr>
</g:if>
<tr><td>Video Link</td><td><input class="form-control" name="course_video_link" id="course_video_link" type="text" size="60" ></td></tr>
<tr><td>Video Name</td><td><input class="form-control" name="course_video_name" id="course_video_name" type="text" size="60" ></td></tr>
<tr><td>Video Duration in Minutes</td><td><input class="form-control" name="course_video_duration" type="text" id="course_video_duration" size="60"></td></tr>
<tr><td>Video Extension</td><td><g:select class="form-control" name="course_video_extension" id="course_video_extension" from="${videoextensionlist.extension}" /></td></tr>
<tr><td>Video Key words</td><td><input class="form-control" name="course_video_keywords" id="course_video_keywords" type="text" size="60"></td></tr>
<tr><td>Video Description</td><td><input class="form-control" name="course_video_description" id="course_video_description" type="text" size="60"></td></tr>
<tr>
   <td colspan="2"><center><g:submitToRemote class="btn btn-primary " url="[action: 'savecoursevideo']" update="ajaxcall" value="Update"/></center></td>
    </tr>
</tbody>
</table>
</div>
</div>