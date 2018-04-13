<g:each in="${mcqOption}">
   <g:if test="${mcqOption.option_image_path[0]}">
         <img src="${createLink(controller:'Learner',action:'renderImage',id:"${mcqOption[0].id}")}" params:'"${mcq}"'></br>
         Enter your Choice :: <g:textField name="ans" value="${ans}" id="ans" />
   </g:if>
   <g:else>
        <g:checkBox name="ans" value="" />${it.option_name} ${it.option_value}</br>
   </g:else>
</g:each>
