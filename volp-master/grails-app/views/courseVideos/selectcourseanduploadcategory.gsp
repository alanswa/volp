<html>
<body>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

        <style>
        .table-borderless td,
        .table-borderless th {
            border: 0;
        }

        </style>
</body>
<script>

</script>
<body>
<div class="container">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class=" card col-sm-12  ">
 <g:form action="savecoursevideo">
<b>Select Course and Upload Category</b>
<br><br>
<table class="table table-responsive table-borderless" style="width:auto">
<tbody>
<!-- <tr >


<td><label>Select Course</label><g:select class="form-control input-small" name="coursecodecoursename" id="coursecodecoursename" from="${courselist}" onChange="return fetchvideo();"/></td>
<td></td>
</tr>
-->
<tr>

<td><label>Select Upload Category</label> <g:select class="form-control input-small" name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" onChange="return callme();"/></td>
</tr>
<!--<tr>
 <td><input class="btn btn-primary btn-block col-sm-2" style="align:center" type="submit" value="save" id="sbtbtn"/></td>
  </tr>-->
 <!-- <tr>
   <td><g:submitToRemote class="btn btn-primary " url="[action: 'savecoursevideo']" update="ajaxcall" value="Update"/></td>
    </tr>-->

<tr>
 <td><div id="material1"></div></td>
</tr>
 <tr>
 <td><div id="material2"></div>
 </td>
 </tr>

<tr>
 <td><div id="material3"></div></td>
 </tr>

 <tr>

 <td><div id="material4"></div></td>
 </tr>


</tbody>
</table>

</g:form>
</div>
</div>
<div id="addeditvdo">
<g:set var="x" value="${row-1}"/>
<table class="table table-striped table-responsive table-bordered" >
<tbody>
<g:each in="${(0..x).toList()}" var="i" status="d">
    <tr>
    <g:each in="${listOfLists[i]}" var="e" status="j">
         <g:if test="${i==0}">
            <td><b>${listOfLists[i][j]}</b></td>
        </g:if>
        <g:else>
            <td>${listOfLists[i][j]}</td>
        </g:else>
    </g:each>
    <g:if test="${i!=0}">
        <td> <g:link action="deletecoursevideo" id="${i}"><i class="fa fa-trash-o" aria-hidden="true" data-toggle="tooltip" title="delete"></i></g:link></td>
    </g:if>
    </tr>
</g:each>
</tbody>
</table>
</div>
</body>
</html>