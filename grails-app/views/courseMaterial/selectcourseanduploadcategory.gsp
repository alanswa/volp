<html>
<head>
<meta name="layout" content="blank">
</head>
<body>
<div class="container">
    <form action="savecoursematerial">
        <b>Select Course and Upload Category</b>
        <br><br>
        <table class="table  table-bordered col-sm-12 table-strip">

            <!-- <tr>
            <td><label>Select Course</label></td>
            <td><g:select class="form-control"  name="coursecodecoursename" id="coursecodecoursename" from="${courselist}" onChange="return fetchmaterial();"/></td>
            </tr> -->
            <tr>
                <td colspan="2">Select Upload Category <g:select class="form-control"  name="uploadcategory" id="uploadcategory" from="${uploadcategorylist}" onChange="return callme();"/></td>
            </tr>

            </table>
    </form>
        <table class="table  table-bordered col-sm-12 table-strip">
            <tr><div id="material1"></div></tr>
            <tr><div id="material2"></div></tr>
            <tr><div id="material3"></div></tr>
            <tr><div id="material4"></div></tr>
            <tr><td><center><input class="btn btn-primary"  type="submit" value="save" /></center></td></tr>
        </table>
    <div id="showprev">
        <g:set var="x" value="${row-1}"/>
        <table class="table  table-bordered col-sm-12 table-strip">
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
                            <!-- <td><g:link action="deletecoursematerial" id="${i}">delete</g:link></td> -->
                        </g:if>
                </tr>
            </g:each>
        </table>
    </div>
<script>
function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("showprev").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function callme()
{
        //var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material1").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/processcoursematerial?uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        return(false)
}
function callmetoo()
{
        var courseoutline = document.getElementById("courseoutline").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material2").innerHTML = this.responseText;
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/furtherprocesscoursematerial?courseoutline=" + courseoutline, true);
                xmlhttp.send();
        return(false)
  }
  function callmetooagain()
  {
        var coursetopic = document.getElementById("coursetopic").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.getElementById("material13").innerHTML = this.responseText;
                    }
                };
             //   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/savecoursematerial?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
        return(false)
  }
</script>
</div>
</body>
</html>