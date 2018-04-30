<g:each in="${mcqOption}">
   <g:if test="${mcqOption.option_image_path[0]}">
         <img src="${createLink(controller:'Learner',action:'renderImage',id:"${mcqOption[0].id}")}" params:'"${mcq}"'></br>
         A <input type="checkbox" name="A" id="A" >
         B <input type="checkbox" name="B" id="B" >
         C <input type="checkbox" name="C" id="C" >
         D <input type="checkbox" name="D" id="D" >
         E <input type="checkbox" name="E" id="E" >
   </g:if>
   <g:else>
         <g:if test="${mcqsubdata}">
            <g:each var="sub" in="${mcqsubdata.mcqexamoption}">
              <g:if test="${sub.option_name == it.option_name}" >
              <g:set var="counter" value="${it.option_name}" />
                   <g:checkBox name="${it.option_name}" id="${it.option_name}" value="true"  />${it.option_name} ${it.option_value}</br>
              </g:if>
            </g:each>
            <g:if test="${counter != it.option_name}" >
               <g:checkBox name="${it.option_name}" id="${it.option_name}" value=""  />${it.option_name} ${it.option_value}</br>
            </g:if>
          </g:if>
          <g:else>
            <g:checkBox name="${it.option_name}" id="${it.option_name}" value=""  />${it.option_name} ${it.option_value}</br>
          </g:else>
   </g:else>
</g:each>
