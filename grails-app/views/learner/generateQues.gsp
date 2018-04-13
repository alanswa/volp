<div id="Data" >
<g:each in="${mcqData}" var ="mcq" status ="id">
Weightage ${mcqpatern.weightageToEachQuestion}
    <p>Q ${qno}: ${mcq.mcqexam.question_statement}</p>
        <g:if test ="${mcq.mcqexam.question_statement_image_path}">
           <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${mcq.mcqexam.id}")}" params:'"${mcq}"'></br>
        </g:if>
        <g:include action="showChoice" id="${mcq.mcqexam.id}" />
</g:each>
<button id="next" value=""next class="btn btn-sm btn-warning" onclick="myFunction('${qsize}','${session.mcq}','${cid}',id)">Next</button>
</div>
 <div>
    <g:each var ="i" in="${1..size}" status="ii">
          <button id="${i}" value="${i}" class="button" onclick="getQ('${session.mcq}','${cid}',id)">${i}</button>
    </g:each>
 </div>
