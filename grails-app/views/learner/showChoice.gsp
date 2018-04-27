<g:each in="${mcqOption}">
   <g:if test="${mcqOption.option_image_path[0]}">
         <img src="${createLink(controller:'Learner',action:'renderImage',id:"${mcqOption[0].id}")}" params:'"${mcq}"'></br>
         A <input type="checkbox" name="A" id="A" onclick="handleClick(this.name,this.id)">
         B <input type="checkbox" name="B" id="B" onclick="handleClick(this.name,this.id)">
         C <input type="checkbox" name="C" id="C" onclick="handleClick(this.name,this.id)">
         D <input type="checkbox" name="D" id="D" onclick="handleClick(this.name,this.id)">
         E <input type="checkbox" name="E" id="E" onclick="handleClick(this.name,this.id)">
   </g:if>
   <g:else>
        <g:each var="sub" in="${mcqsubdata.mcqexamoption}">
          <g:if test="${sub.option_name == it.option_name}" >
          <g:set var="counter" value="${it.option_name}" />
               <g:checkBox name="${it.option_name}" id="${it.option_name}" value="true" onclick="handleClick(this.name)" />${it.option_name} ${it.option_value}</br>
          </g:if>
        </g:each>
        <g:if test="${counter != it.option_name}" >
           <g:checkBox name="${it.option_name}" id="${it.option_name}" value="" onclick="handleClick(this.name,${id})" />${it.option_name} ${it.option_value}</br>
        </g:if>
   </g:else>
</g:each>
