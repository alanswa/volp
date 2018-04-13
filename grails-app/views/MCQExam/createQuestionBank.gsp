
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>
<g:if test="${flash}">
    ${flash.message}
</g:if>
<g:else>
<g:form controller="MCQExam" action="storeQuestionBank" enctype="multipart/form-data" >
<div id="test">
    Question statement : <g:textField name="question_statement" value="${question_statement}" id="question_statement" size="70"/><div id="content" value='image'/></div><input type='checkbox' onclick='handleClick(this);' id="QuestionImage" value="QI">If you want image click checkbox
    <div id="a"><img id="target" ></div><div><label id="message"></div>
    Question Weightage : <g:textField name="weightage" value="${weightage}" /><br/><br/>
    <input type='checkbox' onclick='handleOptionClick(this);' id="Optionimage" >If you want images for Otions then click checkbox</br></br>
    <div id="OptionImages" style="display:none;" >  </div>
    Question Difficulty Level Data<g:select name="difficultyLevelData" from="${difficultyLevelData}" optionValue="${{it.level_value+" : "+it.name}}" optionKey="id" />
    <br/>Is Approved Question <g:checkBox name="isapproved" value="${true}"/>
    <div id="OptionContent" >
        <br><br>A: <input type="radio" name="ans" value="1" id="A" checked="checked" /> <g:textField name="option_name" value="${option_name}" /></p>
        <p>B: <input type="radio" name="ans" value="2"  id="2" /><g:textField name="option_name" value="${option_name}" /></p>
        <p>C: <input type="radio" name="ans" value="3"  id="3" /><g:textField name="option_name" value="${option_name}" /></p>
        <p>D: <input type="radio" name="ans" value="4"  id="4" /><g:textField name="option_name" value="${option_name}" /></p>
        <p>E: <input type="radio" name="ans" value="5"  id="5"/><g:textField name="option_name" value="${option_name}" /></p>
    </div>
</div>
<input type="submit" name="update" id="update" value="Update" />
<table>
  <tr>
    <th>Edit</th>
    <th>Sr No</th>
    <th>Question statement</th>
    <th>Question Weightage</th>
    <th>Question Approved</th>
    <th>Question Difficulty Level</th>
  </tr>
   <g:each var="option" in="${mcqAllData}" status="counter" >
   <tr >
      <td><a href ="#" onclick="updateMCQ(${option.id})" id="${option.id}" ><i class="fa fa-pencil-square-o fa-2x" aria-hidden="true"></i></a></td>

      <td>${option.sno}</td>
      <td>${option.question_statement}
      </br>
      <g:if test="${option.question_statement_image_path}">
        <img src="${createLink(controller:'MCQExam',action:'renderImage',id:"${option.id}")}" params:'"${option}"'></br>
      </g:if>
      <g:each var="options" in="${mcqOption[counter]}">
      <ul>
          <g:if test="${options.option_image_path}">
                <img src="${createLink(controller:'MCQExam',action:'renderOptionImage',id:"${options.id}")}" params:'"${options}"'></br>
                <g:if test="${options.is_correct == true}">
                     <font color="red"><li>${options.option_name}</li>
                </g:if>
          </g:if>
          <g:else>
             <g:if test="${options.is_correct == true}">
                   <font color="red"><li>${options.option_value}</li>
             </g:if>
             <g:else>
                  <font color="black"><li>${options.option_value}</li>
             </g:else>
          </g:else>

      </ul>
      </g:each></td>
      <td>${option.weightage}</td>
      <td>${option.isapproved}</td>
      <td>${option.difficultylevel.name}</td>
    </tr>
   </g:each>
 </table>
</g:form>
</g:else>

