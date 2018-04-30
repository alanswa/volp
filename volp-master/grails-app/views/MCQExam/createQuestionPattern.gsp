<g:form controller="MCQExam">
<g:each in="${diffcultyLevelData}" var="item">
   ${item.key}:::${item.value}<br>
</g:each>
<g:if test="${mcqBasedata}">
    <table class="table">
       <thead>
       <tbody>
         <tr class="success">
           <th>Difficulty Level</th>
           <th>Number of Question</th>
           <th>Weight of each question</th>
           <th>Total Marks</th>
         </tr>
         <tr>
                <g:each in="${mcqBasedata}" var="item">
                    <th>${item.difficultylevel.name}:</th>
                    <th><g:textField name="numberofquestion" id="numberofquestion" value="${item.numberOfQuestion}" /></th>
                    <th><g:textField name="weightage" id="weightage" value="${item.weightageToEachQuestion}" /></th><br>
                    <th><g:textField name="totalmark" id="$totalmark" /></th><br>
         </tr>
               </g:each>
               <tr>
               <th>Total:</th>
               <th><g:textField name="totalNumberOfQuestion" id="totalNumberOfQuestion" /></th>
               <th></th>
               <th><g:textField name="totalMarks" id="totalMarks" /></th>
               </tr>
               <tr><th></th><th></th><th><g:submitToRemote url="[action: 'saveMCQBasedPattern']" update="updateMe" value="SUBMIT" /><th></th></th></tr>
         </tbody>
       </table>
   <div id="updateMe"></div>
</g:if>
<g:else>
 <table class="table">
    <thead>
    <tbody>
      <tr class="success">
        <th>Difficulty Level</th>
        <th>Number of Question</th>
        <th>Weight</th>
        <th>Total Mark</th>
      </tr>
      <tr>
             <g:each in="${diffcultyLevelData}" var="item">
                 <th>${item.key}:</th>
                 <th><g:textField name="numberofquestion" id="numberofquestion" /></th>
                 <th><g:textField name="weightage" id="weightage" /></th><br>
                 <th><g:textField name="totalmark" id="$totalmark" /></th><br>
      </tr>
            </g:each>
            <tr>
            <th>Total:</th>
            <th><g:textField name="totalNumberOfQuestion" id="totalNumberOfQuestion" /></th>
            <th></th>
            <th><g:textField name="totalMarks" id="totalMarks" /></th>
            </tr>
            <tr><th></th><th></th><th><g:submitToRemote url="[action: 'saveMCQBasedPattern']" update="updateMe" value="SUBMIT" /><th></th></th></tr>
      </tbody>
    </table>
<div id="updateMe"></div>
</g:else>
</g:form>
