<html>
<head>
</head>
<body>
<div class="container ">

  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>

<g:form action="savecoursetopic" onsubmit="return callme();">
<Label>Add Course Topics<Label>
<table class="table table-striped table-bordered">
<tr>
<td>
<label>Unit Number....</label>
</td>
<td>
<input id = "outline_number" name="outline_number" type="text" value="${courseoutline.outline_number}" readonly>
</td>
</tr>
<tr>
<td>
<label>Topic Number</label>
</td>
<td>
<input name="topic_number" id="topic_number" type="text"  >
</td>
</tr>
<tr>
<td>
<label>Topic Statement</label>
</td>
<td>
<input name="topic1" id="topic1" type="text" size="50" ><br>
</td>
<tr>
<td></td>
<td>
<input class="btn btn-primary btn-block col-sm-4" type="submit" value="submit" />
</td>
</table>
</g:form>



<table class="table table-striped  table-bordered"  id="data1" >
                    <thead>
                        <tr class="danger" >
                            <th>Unit No.</th>
                            <th>Topic No.</th>
                            <th>Topic</th>
                            <th>Edit</th>
                            <!-- <th>Delete</th> -->
                        </tr>
                    </thead>
                    <tbody >
                        <g:each in="${coursetopiclist}" status="i" var="d">
                            <tr class="${(i % 2) == 0 ? 'success' : 'warning'}" >


                            <!--  <td><g:link action="deletecoursetopic" id="${d.id}">delete</g:link></td> -->
                                <td>${d.courseoutline.outline_number}</td>
                                <td>${d.topic_number}</td>
                                <td>${d.topic}</td>
                                <td><a href="#" onclick="updatecourse1(${d.id});"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
                                <!-- <td><a href="#" onclick="deletecourse1(${d.id});"><i class="fa fa-trash-o" aria-hidden="true"></i></a></td> -->
                            </tr>
                        </g:each>
                    </tbody>
                </table>

</div>
</body>
</html>


