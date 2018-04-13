<div class="container">
<table class="table table-striped">
<th> Name of the Student</th>
<th> Registration Number</th>
<th> Organization </th>
<th> Year</th>
<th> Program</th>
<g:each var="learner" in="${coffrleraner.learner}">
<tr>
<td>
<g:if test="${learner.person!=null}">
 ${learner.person.firstName} ${learner.person.lastName}
</g:if>
</td>
<td>
 ${learner.registration_number}
 </td>
 <td>
 <g:if test="${learner.organization!=null}">
 ${learner.organization.organization_name}
 </g:if>
 </td>
 <td>
 <g:if test="${learner.current_year!=null}">
   Year:   ${learner.current_year.year}
 </g:if>
 </td>
 <td>
 <g:if test="${learner.program!=null}">
 ${learner.program.name}
  </g:if>
  </td>

</tr>
</g:each>
</table>
</div>

