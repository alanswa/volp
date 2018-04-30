<!--<style>
#
</style>
<button class="btn btn-sm btn-danger" onclick="getQuestion(${questionOprtion})" id="startbtn">
Start Exam
</button>
<div class="panel panel-default">
<div class="panel-heading" id="questionList">Questions

</div>
  <div class="panel-body" id="options">
   Options
  </div>
  <div class="panel-footer" id="buttons">
  Buttons
  </div>
</div>-->
<div id="Data" >
<h1>Instruction To the Candidate</h1>
<button type="button" class="btn btn-success" onclick="myFunction('${qsize}','${session.mcq}','${cid}')">Lets Start </button>
</div >
<!--${mcqData.id}
<div id="Data" >
<g:each in="${mcqData}" var ="mcq" status ="id">
    <p>Q ${id+1}: ${mcq.mcqexam.question_statement}</p>
        <g:if test ="${mcq.mcqexam.question_statement_image_path}">
           <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${mcq.mcqexam.id}")}" params:'"${mcq}"'></br>
        </g:if>
        <g:include action="showChoice" id="${mcq.mcqexam.id}" />
</g:each>
<button id = "next" value ="next" class="btn btn-sm btn-warning" onclick="getQuestion(${mcqData.id},${qsize})">Next</button>
</div>-->


