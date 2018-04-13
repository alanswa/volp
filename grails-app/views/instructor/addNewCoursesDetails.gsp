
<!doctype html>
<html lang="en" class="no-js">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <title>
    ${cid.course_name}
  </title>
  <asset:stylesheet src="bootstrap.min.css"/>
  <asset:stylesheet src="font-awesome.min.css"/>
  <asset:stylesheet src="sb-admin.css"/>

  <asset:stylesheet src="courseInstructor.css"/>

   <style>
    h5{
     color: white;
     background-color: #424242;
     border:1px solid;
     padding: 5px;
    }
   </style>

</head>
<body id="page-top">
  <!-- ---------------------------Start of Navigation------------------------------------------ -->
  <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav">
    <a class="navbar-brand" href="${request.contextPath}/instructor/instructor"><i class="fa fa-fw fa-home"></i>&nbsp;VOLP :: Course Instructor / ${cid.course_code}::${cid.course_name} </a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">

      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="background: linear-gradient(to right, rgba(0,128,128,0), rgba(0,128,128,1));">

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Edit Course">
          <a class="nav-link" href="#" onclick="editCourse()">
            <i class="fa fa-fw fa-pencil-square-o"></i>
            <span class="nav-link-text">Edit Course</span>
          </a>
        </li>

      <!--  <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Outcome">
          <a class="nav-link" href="#" onclick="callCourseOutcome('${cid.course_code}:${cid.course_name}')">
            <i class="fa fa-fw fa-pencil-square-o"></i>
            <span class="nav-link-text">Add Course Outcome</span>
          </a>
        </li> -->
 <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Outcome">
<g:set var="crscodename" value="${cid.course_code}:${cid.course_name}" />

          <g:link class="nav-link" action="addcourseoutcomes" controller="CourseOutcomes" params="[coursecodecoursename:crscodename]">
          <i class="fa fa-fw fa-plus"></i>
             <span class="nav-link-text">Add Course Outcome</span>
          </g:link>
        </li>
  <!--      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Outline">
          <a class="nav-link" href="#" onclick="callCourseOutline()">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Add Course Unit/Outline</span>
          </a>
        </li> -->
       <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Outline">
          <g:link class="nav-link" action="addcourseoutline" controller="CourseOutline" params="[coursecodecoursename:crscodename]">
           <i class="fa fa-fw fa-plus"></i>
                       <span class="nav-link-text">Add Course Unit/Outline</span>
          </g:link>
        </li>
    <!--    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Topic">
          <a class="nav-link" href="#" onclick="callCourseTopic()">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Add Course Topic</span>
          </a>
        </li>  -->
    <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Topic">
          <g:link class="nav-link" action="listcourseoutlines" controller="CourseTopic" params="[coursecodecoursename:crscodename]">
          <i class="fa fa-fw fa-plus"></i>
                      <span class="nav-link-text">Add Course Topic</span>

          </g:link>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Material">
        <g:link class="nav-link" action="selectcourseanduploadcategory" controller="CourseMaterial" >
        <i class="fa fa-fw fa-plus"></i>
             <span class="nav-link-text">Add Course Material</span>
        </g:link>
      <!--    <a class="nav-link" href="#" onclick="callCourseMaterial()">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Add Course Material</span>
          </a> -->
        </li>

        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add Course Video">
          <a class="nav-link" href="#" onclick="callCourseVideo()">
            <i class="fa fa-fw fa-plus"></i>
            <span class="nav-link-text">Add Course Video</span>
          </a>
        </li>
         <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Question Bank">
                  <a class="nav-link" href="#" onclick="callCreateQuestionBank()">
                    <i class="fa fa-fw fa-plus"></i>
                    <span class="nav-link-text">Create Question Bank</span>
                  </a>
                </li>
     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Setting Question Paper pattern">
                      <a class="nav-link" href="#" onclick="callSetQuestionPattern()">
                        <i class="fa fa-fw fa-plus"></i>
                        <span class="nav-link-text">Setting Question Paper pattern</span>
                      </a>
                    </li>
      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Setting of Exam Schedule">
                           <a class="nav-link" href="#" onclick="callSetQuestionSchedule()">
                             <i class="fa fa-fw fa-plus"></i>
                             <span class="nav-link-text">Setting of Exam Schedule</span>
                           </a>
                         </li>

      <!--  <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Assessment Grade">

         <a class="nav-link" href="#" onclick="getCourseOfferingForGrade()">
           <i class="fa fa-fw fa-book"></i>
           <span class="nav-link-text">Assessment Grade</span>
         </a>

       </li> -->
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Weeks">
   <!--      <g:link action="processweek" controller="Week" params="[coursecodecoursename:crscodename]">Add/Edit Weeks</g:link> -->
    <!--   <a class="nav-link" href="#" onclick="callWeek('${crscodename}');" >

           <i class="fa fa-fw fa-book"></i>
           <span class="nav-link-text">Add/Edit Weeks</span>
         </a>

       </li> -->
  <!--     <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Add/Edit Course Schedule">
 <g:link action="processession" controller="Session" params="[coursecodecoursename:crscodename]">Add/Edit Course Schedule</g:link>
         <a class="nav-link" href="#" onclick="callSession('${crscodename}')">
           <i class="fa fa-fw fa-clock-o"></i>
           <span class="nav-link-text">Add/Edit Course Schedule</span>
         </a>

       </li> -->
 <!--      <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Create Assignment">
 <g:link action="processassignment" controller="Assignment" params="[coursecodecoursename:crscodename]">Create Assignment</g:link>
         <a class="nav-link" href="#" onclick="callAssigment('${crscodename}')">
           <i class="fa fa-fw fa-plus"></i>
           <span class="nav-link-text">Create Assignment</span>
         </a>

       </li> -->
     </ul>

    <ul class="navbar-nav sidenav-toggler" style="background-color:white">
      <li class="nav-item">
        <a class="nav-link text-center blink" id="sidenavToggler" style="padding: 8px;">
          <i class="fa fa-wa fa-exchange" style="color:blue"></i>
        </a>
      </li>
    </ul>

    <ul class="navbar-nav ml-auto" >
      <li class="nav-item dropdown">

        <a class="nav-link dropdown-toggle mr-lg-2"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Hello, ${session.fname} <i class="fa fa-bars" aria-hidden="true""></i>
          <span class="d-lg-none">Dashboard</span>

        </a>

        <div class="dropdown-menu user-profile-cursor">
          <a class="dropdown-item small" data-toggle="modal" data-target="#userProfileModal"> <i class="fa fa-user"></i>&nbsp;User Profile</a><div class="dropdown-divider"></div>
          <a class="dropdown-item small" onclick="changepass()" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
          <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

        </div>

      </li>

    </ul>

  </div> &nbsp;&nbsp;&nbsp;&nbsp;
</nav>
<!-- End of Navigation --------- --------------------------------------------- -->
<br> <br>
<div class="content-wrapper" id="page">
  <div class="container-fluid">

    <!-- container-fluid-->
    <div id="ajaxcall">
<!--  This will disappear -->
    </div>
    <div id="editgrade">

    </div>
<!-- begins progress chart -->
<!-- ends progress chart -->
     <h5> List of current offered courses </h5>

                 <div class="row">

                 <g:if test="${!(coffr.isEmpty())}">

                      <g:each var="coffrobj" in="${coffr}">
                          <g:if test="${coffrobj.isActive}">
                          <div class="col-sm-3">
                                  <div class="card">
                                         <div class="card-header bg-default"> <g:link controller="CourseOffering" action="menuOfSelectedCourseOffered" params="[coffrid:coffrobj.id]">${coffrobj.course.course_name}</g:link>

                                          </div>
                                           <div class="card-body">
                                             <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />

                                           <hr>
                                           <a href="#" style="float: left" onclick="studentInfo(${coffrobj.id})" class="btn-sm btn-warning"><span>Student Info</span></a>
                                           <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${coffrobj.batch}</p>
                                         </div>

                                        </div>

                          </div>
                          </g:if>
                          <br>
                      </g:each>
                          </g:if>
                  </div> <!-- End of row-->

                  <h5> List of archived courses </h5>

                                   <div class="row">

                                   <g:if test="${!(coffr.isEmpty())}">

                                        <g:each var="coffrobj" in="${coffr}">
                                            <g:if test="${!coffrobj.isActive}">
                                            <div class="col-sm-3">
                                                    <div class="card">
                                                          <div class="card-header bg-default"> <g:link controller="CourseOffering" action="menuOfSelectedCourseOffered" params="[coffrid:coffrobj.id]">${coffrobj.course.course_name}</g:link>

                                                            </div>
                                                             <div class="card-body">
                                                               <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />

                                                             <hr>
                                                              <a href="#" style="float: left" onclick="studentInfo(${coffrobj.id})" class="btn-sm btn-warning"><span>Student Info</span></a>
                                                            <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${coffrobj.batch}</p>
                                                           </div>

                                                          </div>

                                            </div>
                                            </g:if>
                                            <br>
                                        </g:each>
                                            </g:if>
                                    </div> <!-- End of row-->
                    <br>
                     <br>


  </div><!-- End of container-fluid-->

</div> <!-- End of content-
wrapper-->


<!-- Start of Footer ------------------------------------ -->
<footer class="sticky-footer">
  <div class="container">
    <div class="text-center">
      <small style="font-size:14px">Copyright © VOLP</small>
    </div>
  </div>
</footer>

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top" >
  <i class="fa fa-angle-up"></i>
</a>
<!-- End Of Footer --------------------------------------- -->

<script>
function studentInfo(coffrid){
   // alert("coffrid:"+coffrid)
    var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
           // alert("1"+this.status)
                if (this.readyState == 4 && this.status == 200) {
               // alert("resp"+this.responseText)
                    document.getElementById("page").innerHTML = this.responseText;

                }

            };
            xmlhttp.open("GET", "${request.contextPath}/courseOfferingLearner/studentInfo?coffrid=" + coffrid, true);
            xmlhttp.send();
}
  function add_feed()
  {
    var div1 = document.createElement('div');
          // Get template data
          div1.innerHTML = document.getElementById('newlinktpl').innerHTML;
          // append to our form, so that template data
          //become part of form
          document.getElementById('newlink').appendChild(div1);
        }
        function add_feeddes()
        {
          var div1 = document.createElement('div');
                  // Get template data
                  div1.innerHTML = document.getElementById('newlinktpldes').innerHTML;
                  // append to our form, so that template data
                  //become part of form
                  document.getElementById('newlinkdes').appendChild(div1);
                }
                function add_feedurl()
                {
                  var div1 = document.createElement('div');
              // Get template data
              div1.innerHTML = document.getElementById('newlinktplurl').innerHTML;
              // append to our form, so that template data
              //become part of form
              document.getElementById('newlinkurl').appendChild(div1);
            }
            function add_feedpre()
            {
              var div1 = document.createElement('div');
              // Get template data
              div1.innerHTML = document.getElementById('newlinktplpre').innerHTML;
              // append to our form, so that template data
              //become part of form
              document.getElementById('newlinkpre').appendChild(div1);
            }



            function editCourse(){
              var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/instructor/getCourseDetails?cid="+${cid.id}, true);
xmlhttp.send();
}

function callCourseOutcome(crscodename){
alert("crscodename:"+crscodename)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/courseOutcomes/addcourseoutcomes?coursecodecoursename="+crscodename, true);
xmlhttp.send();
}
function callCourseOutline(){
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/courseOutline/selectcourse", true);
xmlhttp.send();
}
function callCourseTopic(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseTopic/selectcourse", true);
xmlhttp.send();
}

/*function callCourseMaterial(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseMaterial/selectcourseanduploadcategory", true);
xmlhttp.send();
}*/
function callCourseVideo(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/CourseVideos/selectcourseanduploadcategory", true);
xmlhttp.send();
}
function callSetQuestionPattern(){
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
                //alert("1");
                document.getElementById("ajaxcall").innerHTML = this.responseText;
    //alert("1"+this.responseText);
    }
    };
    xmlhttp.open("GET", "${request.contextPath}/MCQExam/createQuestionPattern", true);
    xmlhttp.send();
}
function callSetQuestionSchedule(){
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
                //alert("1");
                document.getElementById("ajaxcall").innerHTML = this.responseText;
    //alert("1"+this.responseText);
    }
    };
    xmlhttp.open("GET", "${request.contextPath}/MCQExam/createQuestionSchedule", true);
    xmlhttp.send();
}
function callCreateQuestionBank(){
//alert("11");
var xmlhttp = new XMLHttpRequest();
xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
            //alert("1");
            document.getElementById("ajaxcall").innerHTML = this.responseText;
//alert("1"+this.responseText);
}
};
xmlhttp.open("GET", "${request.contextPath}/MCQExam/createQuestionBank", true);
xmlhttp.send();
}
function callCourseOffering(){

  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {

      document.getElementById("ajaxcall").innerHTML = this.responseText;

    }
  };
  xmlhttp.open("GET", "${request.contextPath}/CourseOffering/selectcourse", true);
  xmlhttp.send();
}
// fetch Video script code -----------------------------------------
function fetchvideo()
{
 var coursecodecoursename = document.getElementById("coursecodecoursename").value;
 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
   if (this.readyState == 4 && this.status == 200) {
     document.getElementById("material4").innerHTML = this.responseText;
   }
 };
 xmlhttp.open("GET", "${request.contextPath}/courseVideos/addeditcoursevideo?coursecodecoursename=" + coursecodecoursename, true);
 xmlhttp.send();
 return(false)
}
function deletevdo(id)
{

   // alert("delete");
    var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
         document.getElementById("ajaxcall").innerHTML = this.responseText;
       }
     };
     xmlhttp.open("GET", "${request.contextPath}/courseVideos/deletecoursevideo?id=" + id, true);
     xmlhttp.send();
}
function callme()
{
 // var coursecodecoursename = document.getElementById("coursecodecoursename").value;
  var uploadcategory = document.getElementById("uploadcategory").value;
  //alert(uploadcategory)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material1").innerHTML = this.responseText;
    }
  };
  //xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
  xmlhttp.open("GET", "${request.contextPath}/courseVideos/processcoursevideo?uploadcategory=" + uploadcategory, true);
  xmlhttp.send();
  return(false)
}
function callmetoo()
{
  var courseoutline = document.getElementById("courseoutline").value;
  alert(courseoutline)
  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material2").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/courseVideos/furtherprocesscoursevideo?courseoutline=" + courseoutline, true);
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
             //   xmlhttp.open("GET", "${request.contextPath}/courseVideos/savecoursevideo?coursetopic=" + coursetopic, true);
             //   xmlhttp.send();
             return(false)
           }


// -------------End of Fetch Video Ajax Call-------------------------

 // -------------------------Assessment Grade Ajax Call---------------------------

 function getCourseOfferingForGrade(){
 ///alert("hi");

 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
                      //alert("1");
                      document.getElementById("ajaxcall").innerHTML = this.responseText;
                      var x = document.getElementById("editgrade");
                          // alert("x:"+x)
                          x.style.display = "none";
          //alert("1"+this.responseText);
        }
      };
      xmlhttp.open("GET", "${request.contextPath}/courseOffering/getCourseOfferingForGrade?cid="+${cid.id}, true);
      xmlhttp.send();
    }
  /*  function setGrade(coffr){
     var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/setGrade?coffr="+coffr, true);
       xmlhttp.send();
     } */
     /*function editGrade(ag,coffr){
      var x = document.getElementById("editgrade");
                         //  alert("x:"+x)
                         x.style.display = "block";
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("editgrade").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/assessmentGrade/editGrade?ag="+ag + "&coffr="+coffr, true);
       xmlhttp.send();
     }*/
// -------------------------End Assessment Grade Ajax Call---------------------------

// ------------------------- Week Ajax Call---------------------------
function callWeek(crscodename){
alert("crscodename:"+crscodename);
                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Week/processweek?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Week Ajax Call---------------------------
// ------------------------- Session Ajax Call---------------------------
function callSession(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Session/processession?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Session Ajax Call---------------------------
// ------------------------- Assignment Ajax Call---------------------------
function callAssigment(crscodename){

                         var xmlhttp = new XMLHttpRequest();
                         xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                       //alert("1");
                       document.getElementById("ajaxcall").innerHTML = this.responseText;
           //alert("1"+this.responseText);
         }
       };
       xmlhttp.open("GET", "${request.contextPath}/Assignment/processassignment?coursecodecoursename="+crscodename, true);
       xmlhttp.send();
     }
// -------------------------End Assignment Ajax Call---------------------------
function fetchcourseofferingweek()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Week/processweek?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingsession()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Session/processession?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function fetchcourseofferingassign()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material1").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/Assignment/processassignment?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}
function furtherprocessweek(id){
     var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/week/furtherprocessweek?id=" + id, true);
                       xmlhttp.send();
}
function updateweeks(id){
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/week/updateweeks?id=" + id, true);
                       xmlhttp.send();
}
function furtherprocesssession(id){
alert("Hi"+id);
var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
                           if (this.readyState == 4 && this.status == 200) {
                               document.getElementById("ajaxcall").innerHTML = this.responseText;
                           }
                       };
                       xmlhttp.open("GET", "${request.contextPath}/session/furtherprocesssession?id=" + id, true);
                       xmlhttp.send();
}
//-------------------Course Material Ajax Call ------------------------------
/* function fetchmaterial()
{
   var coursecodecoursename = document.getElementById("coursecodecoursename").value;
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
                       if (this.readyState == 4 && this.status == 200) {
                           document.getElementById("material4").innerHTML = this.responseText;
                       }
                   };
                   xmlhttp.open("GET", "${request.contextPath}/courseMaterial/addeditcoursematerial?coursecodecoursename=" + coursecodecoursename, true);
                   xmlhttp.send();
           return(false)
}

function callmeCourseMaterial()
{
document.getElementById("crs_material").innerHTML = "will change"
alert("callmeCourseMaterial")
//document.getElementById("material1").innerHTML = "will change"
        var coursecodecoursename = document.getElementById("coursecodecoursename").value;
        var uploadcategory = document.getElementById("uploadcategory").value;
        var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {

                        document.getElementById("crs_material").innerHTML = this.responseText;
                        alert("from proc cm"+this.responseText)
                    }
                };
                xmlhttp.open("GET", "${request.contextPath}/courseMaterial/processcoursematerial?coursecodecoursename=" + coursecodecoursename +"&uploadcategory="+uploadcategory, true);
                xmlhttp.send();
        //return(false)
  }

function callmetooCourseMaterial()
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
  */
//--------------------------------------------------------------------------


function checkpwd(){

var pwd1 = document.getElementById("newpassword").value;
var pwd2 = document.getElementById("confirmpassword").value;
//alert(pwd1+" "+pwd2)
if(pwd1==pwd2)
    return true;
else{
alert("Oops password not matched!!!")
     return false;

     }
return false;
}
function changepass()
{
    var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                  document.getElementById("page").innerHTML = this.responseText;
             }
             };
              xmlhttp.open("GET", "${request.contextPath}/Login/changepassword", true);
               xmlhttp.send();
}
//----------------------------------------------------------------------------------
<!--Alankar code for question paper-->

function handleClick(cb) {

  var chk = document.getElementById("QuestionImage").checked;
  if(chk){
           var d = document.getElementById("content");
           d.innerHTML += "<input type='file' id='myFile' value='image' name='myFile' onchange='img()'>";
           var parent1 = document.getElementById("a");
           parent1.innerHTML += "<img id='target'>"
           showImage(src, target);
   }
   else{
          var parent = document.getElementById("content");
          var child = document.getElementById("myFile");
          parent.removeChild(child);
          var parent1 = document.getElementById("a");
          var img = document.getElementById("target");
          parent1.removeChild(img);
          document.getElementById("message").innerHTML="";
   }
}

function img()
{
              var str="";
              var src = document.getElementById("myFile");
              var fsize  = parseFloat(src.files[0].size / 1024).toFixed(2);
              if(fsize<50)
              {
                var target = document.getElementById("target");
                showImage(src, target);
                document.getElementById("message").innerHTML=str;
                document.getElementById("update").disabled = false;
              }
              else
              {
                str = "**Your file size is larger than 50 KB please select another file.";
                var result = str.fontcolor("red");
                showImage(src, target);
                document.getElementById("message").innerHTML=result;
                document.getElementById("update").disabled = true;
                document.getElementById("target").value="";
              }
}

function putImage(ele,tar,msg) {
      var chk = document.getElementById("Optionimage").checked;
      var str;
      if(chk)
      {
                  var src = document.getElementById(ele.id);
                  var fsize  = parseFloat(src.files[0].size / 1024).toFixed(2);
                  if(fsize<50)
                  {
                    var target = document.getElementById(tar.id);
                    str="";
                    document.getElementById(msg.id).innerHTML=str;
                    showImage(src, target);
                    document.getElementById("update").disabled = false;
                  }
                  else
                  {
                    str = "**Your file size is larger than 50 KB please select another file.";
                    var result = str.fontcolor("red");
                    showImage(src, target);
                    document.getElementById(msg.id).innerHTML=result;
                    document.getElementById("update").disabled = true;
                  }
      }
  }

  function showImage(src, target) {
       var fr = new FileReader();
       fr.onload = function(){
           target.src = fr.result;
       }
       fr.readAsDataURL(src.files[0]);
   }
function updateMCQ(val)
{
  var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 document.getElementById("test").innerHTML = this.responseText;
             }
         };
         xmlhttp.open("GET", "${request.contextPath}/MCQExam/updateMCQS?mcqId="+val, true);
         xmlhttp.send();
}

function handleOptionClick (elements) {
      // This will disable just the div
     var d = document.getElementById("OptionImages");
     var chk = document.getElementById("Optionimage").checked;
     if(chk)
     {
        d.innerHTML += "<br><br>Select image <input type='file' id='myFileA' value='imageA' name='myFileA' onchange='putImage(this,targetA,messageA)'><div id='a'><img id='targetA'></div><div><label id='messageA'></div><br/>Please Enter your Answer <input type='text' name='answer' placeholder='Please enter your answer option' size='35'>";//<br><br>Please Enter your Answer<g:textField name='answer' value='${answer}' />";//<p>B: <input type='radio' name='myGroup' value='2'  id='2' /><input type='file' id='myFileB' value='imageB' name='myFileB' onchange='putImage(this,targetB,messageB)'><div id='a'><img id='targetB'></div><div><label id='messageB'></div></p><p>C: <input type='radio' name='myGroup' value='3'  id='3' /><input type='file' id='myFileC' value='imageC' name='myFileC' onchange='putImage(this,targetC,messageC)'><div id='a'><img id='targetC'></div><div><label id='messageC'></div></p><p>D: <input type='radio' name='myGroup' value='4'  id='4' /><input type='file' id='myFileD' value='imageD' name='myFileD' onchange='putImage(this,targetD,messageD)'><div id='a'><img id='targetD'></div><div><label id='messageD'></div></p><p>E: <input type='radio' name='myGroup' value='5'  id='5'/><input type='file' id='myFileE' value='imageE' name='myFileE' onchange='putImage(this,targetE,messageE)'><div id='a'><img id='targetE'></div><div><label id='messageE'></div></p>";
        document.getElementById('OptionImages').style.display = "block";
        document.getElementById('OptionContent').style.display = "none";
     }
     else
     {
        var parent = document.getElementById("OptionImages");
        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }
        document.getElementById('OptionImages').style.display = "none";
        document.getElementById('OptionContent').style.display = "block";
        document.getElementById("update").disabled = false;
     }
}

//---------------------------------------------------------------------------------------------------
</script>
<asset:javascript src="jquery.min.js"/>

<asset:javascript src="bootstrap.bundle.min.js"/>
<asset:javascript src="jquery.easing.min.js"/>
<asset:javascript src="sb-admin.min.js"/>
<body id="empty"></div>
</body>
</html>
