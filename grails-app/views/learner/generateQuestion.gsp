<!--<g:form action="generateQuestion">
<div id="Data" >
<g:each in="${mcqData}" var ="mcq" status ="id">
<g:hiddenField name="cid" value="${cid}" />
Weightage ${mcqpatern.weightageToEachQuestion}
    <p>Q ${session.counter}: ${mcq.mcqexam.question_statement}</p>
        <g:if test ="${mcq.mcqexam.question_statement_image_path}">
           <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${mcq.mcqexam.id}")}" params:'"${mcq}"'></br>
        </g:if>
        <g:include action="showChoice" id="${mcq.mcqexam.id}" />
</g:each>
<button id="next" value=""next class="btn btn-sm btn-warning" >Next</button>
</div>
<div>
    <g:each var ="i" in="${1..size}" status="ii">
          <button id="${i}" value="${i}" class="button" onclick="getQ('${session.mcq}','${cid}',id)">${i}</button>
    </g:each>
    Mark as Review:<g:checkBox name="review" value="${review}" id="review" onclick="markAsReview('${session.counter}',id)"/>
</div>
</g:form>-->

<div id="Data" >
<g:each in="${mcqData}" var ="mcq" status ="id">
Weightage ${mcqpatern.weightageToEachQuestion}
    <p>Q ${session.counter}: ${mcq.mcqexam.question_statement}</p>
        <g:if test ="${mcq.mcqexam.question_statement_image_path}">
           <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${mcq.mcqexam.id}")}" params:'"${mcq}"'></br>
        </g:if>
        <g:include action="showChoice" id="${mcq.mcqexam.id}" />

</g:each>
<button id="next" value=""next class="btn btn-sm btn-warning" onclick="myFunction('${qsize}','${session.mcq}','${cid}',id,'${mcqData.id}')">Next</button>
</div>
<div>
    <g:each var ="i" in="${1..size}" status="ii">
          <button id="${i}" value="${i}" class="button" onclick="getQ('${session.mcq}','${cid}',id)">${i}</button>
    </g:each>
    Mark as Review:<g:checkBox name="review" value="${review}" id="review" onclick="markAsReview('${session.counter}',id)"/>
</div>
