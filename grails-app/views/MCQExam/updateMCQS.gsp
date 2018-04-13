<input type="hidden" name="mcqDataId" value="${mcqData.id}">
Question statement : <g:textField name="question_statement" value="${mcqData.question_statement}" id="question_statement" size="70"/><div id="content" value='image'/></div><input type='checkbox' onclick='handleClick(this);' id="QuestionImage" value="QI">If you want image click checkbox</br></br>
    <div id="a"><img id="target"></div><div><label id="message"></div>
     <g:if test="${mcqData.question_statement_image_path}">
            <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${mcqData.id}")}" params:'"${mcqData}"'></br>
     </g:if>
    Question Weightage : <g:textField name="weightage" value="${mcqData.weightage}" /><br/><br/>
    <input type='checkbox' onclick='handleOptionClick(this);' id="Optionimage" >If you want images for Otions then click checkbox</br></br>
    <div id="OptionImages" style="display:none;" >  </div>
    Question Difficulty Level Data<g:select optionKey="id" name="difficultyLevelData" from="${difficultyLevelData}" value="${mcqData.difficultylevel?.id}" optionValue="${{it.level_value+" : "+it.name}}"  />
    <br/>Is Approved Question <g:checkBox name="isapproved" value="${mcqData.isapproved}"/>
    <div id="OptionContent" >
    <g:each var="options" in="${mcqDataOption}" status="i">
        <g:if test="${options.option_image_path}">
          <img src="${createLink(controller:'MCQExam',action:'renderOptionImage',id:"${options.id}")}" params:'"${options}"'></br>
             <g:if test="${options.is_correct == true}">
                 <font color="red"><li>${options.option_name}</li>
             </g:if>
        </g:if>
        <g:else>
            <g:if test="${mcqDataOption[i].is_correct}">
                <br><br>${mcqDataOption[i].option_name} <input type="radio" name="ans" value="${i}" id="${mcqDataOption[i].option_name}" checked="checked" /> <g:textField name="option_name"" value="${mcqDataOption[i].option_value}" /></p>
            </g:if>
            <g:else>
                <br><br>${mcqDataOption[i].option_name} <input type="radio" name="ans" value="${i}" id="${mcqDataOption[i].option_name}"  /> <g:textField name="option_name" value="${mcqDataOption[i].option_value}" /></p>
            </g:else>
        </g:else>
    </g:each>

    </div>
