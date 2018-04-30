<table class="table  table-bordered col-sm-12 table-strip">
<tbody>
<g:if test="${session.uploadcategory=='Unit' || session.uploadcategory=='Topic'}">
<tr><td>Course Outline/Unit</td><td><g:select class="form-control"  name="courseoutline" id="courseoutline" from="${courseoutlinelist.outline}" onChange="return callmetoo();"/></td></tr>
</g:if>
<g:else>
<tr><td>Material Name</td><td><input class="form-control"  name="course_material_name" id="course_material_name" type="text" size="60" ></td></tr>
<tr><td>Material Link</td><td><input class="form-control"  name="course_material_link" id="course_material_link" type="text" size="60" ></td></tr>
<tr><td>Material Extension</td><td><g:select class="form-control"  name="material_extension" id="material_extension" from="${materialextensionlist.extension}" /></td></tr>
<tr><td>Material Key words</td><td><input class="form-control"  name="material_keywords" id="material_keywords" type="text" size="60"></td></tr>
<tr><td>Material Description</td><td><input class="form-control"  name="material_description" id="material_description" type="text" size="60"></td></tr>
</g:else>
</tbody>
</table>