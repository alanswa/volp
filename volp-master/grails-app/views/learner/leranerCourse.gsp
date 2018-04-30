
<!doctype html>
<html lang="en" class="no-js">
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <title>
    VOLP || Menu
  </title>

  <asset:stylesheet src="bootstrap.min.css"/>
  <asset:stylesheet src="font-awesome.min.css"/>
  <asset:stylesheet src="sb-admin.css"/>
  <asset:stylesheet src="courseInstructor.css"/>


</head>
<body id="page-top">

 <!-- ---------------------------Start of Navigation------------------------------------------ -->
 <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="mainNav" >
  <a class="navbar-brand" href="${request.contextPath}/learner/learner"><i class="fa fa-fw fa-home"></i>&nbsp;VOLP :: Course Learner</a>
  <button type="button" onclick="GenreateQuestion(${coffr.id})" >Online Exam</button>
  <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarResponsive">
   <g:set var="x" value="${1}"/>
   <ul class="navbar-nav navbar-sidenav" id="exampleAccordion" style="background: linear-gradient(to right, rgba(0,128,128,0), rgba(0,128,128,1));">
    <g:each var="m" in= "${menuSubmenuweek}">

    <g:if test="${x%2==0}">

    <ul style="list-style:none">

     <g:each var="sm" in= "${m}">
     <li class="nav-item" data-toggle="tooltip" data-placement="left" title="${sm}">
      <a class="nav-link" onclick="callSession(${sm.id});">
        <i class="fa fa-fw fa-book"></i>
        <span class="nav-link-text">Session${sm.session_number}</span>
      </a>
    </li>
    <br>
  </g:each>
</ul>
</g:if>

<g:if test="${x%2==1}">
<li class="nav-item" data-toggle="tooltip" data-placement="right" title="${m}">
  <a class="nav-link"  onclick="callWeek(${m[0].id});">
    <i class="fa fa-fw fa-book"></i>
    <span class="nav-link-text" >Week${m[0].week_number}</span>
  </a>
</li>
</g:if>
<g:set var="x" value="${x+1}"/>
</g:each>

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
      <a class="dropdown-item small" data-toggle="modal" data-target="#changeUserPasswordModal"> <i class="fa fa-key"></i>&nbsp;Change Password</a> <div class="dropdown-divider"></div>
      <a class="dropdown-item small" href="${request.contextPath}/login/logout"><i class="fa fa-sign-out"></i>&nbsp;Sign Out</a>

    </div>

  </li>



</ul>

</div> &nbsp;&nbsp;&nbsp;&nbsp;
</nav>
<!-- End of Navigation --------- --------------------------------------------- -->
<br> <br>
<div class="content-wrapper" >
  <div class="container card card-body">
    <!-- container-fluid-->

    <div id="material">
        <div class="card card-body">
        <h6> <b> About This Course </b></h6>
        <table class=" col-sm-12 table table-striped table-bordered">
          <tr>
            <th><b>Rating</b></th>
            <th><b>Category:</b> </th>
            <g:if test="${coffr.course.program != null}"><th><b>Program:</b></th></g:if>
            <g:if test="${coffr.course.department != null}"><th><b>Department:</b></th></g:if>
            <g:if test="${coffr.course.courseowner.organization != null}"><th><b>Offered By:</b></th></g:if>
            <th><b>Faculty:</b></th>
          </tr>
          <tr>
            <td>${coffr.course.rating}</td>
            <td>${coffr.course.coursecategory.name}</td>
            <g:if test="${coffr.course.program != null}">
            <td>${coffr.course.program.name} </td>
          </g:if>
          <g:if test="${coffr.course.department != null}">
          <td>${coffr.course.department.name}</td>
        </g:if>
        <g:if test="${coffr.course.courseowner.organization != null}">
        <td>${coffr.course.courseowner.organization.display_name}</td>
      </g:if>
      <td>${coffr.course.courseowner.person.firstName}&nbsp;&nbsp;${coffr.course.courseowner.person.lastName}
      </td>
    </tr>
  </table>
</div>

<br>
<g:if test="${coursevideos.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_Course_Videos">Course Videos(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_Course_Videos" class="panel-collapse collapse">
				<table class=" col-sm-12 table table-striped table-bordered">
					<g:each var="cv" in="${coursevideos}">
						<tr>
							<td> <center> <iframe class="embed-responsive-item" width="${vp.width}" height="${vp.height}" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe> </center> </td>
						</tr>
                        <tr>
                            <td> <center> ${cv.video_name}&nbsp;${cv.video_link}&nbsp;${cv.description}  </center> </td>
                        </tr>
                        <!-- <iframe width="480" height="270" src="https://www.youtube.com/embed/${cv.video_link}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe> -->
					</g:each>
				</table>
			</div>
		</div>
	</div>
</g:if>

<g:if test="${coursematerial.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_Course_Material">Course Material(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
                <div id="collapse_Course_Material" class="panel-collapse collapse">
                    <g:each var="cm" in="${coursematerial}">
	                    <div class="panel-body">
	                        <table class=" col-sm-12 table table-striped table-bordered">
	                        <tr>
	                            <td>${cm.material_name}&nbsp;&nbsp;${cm.description}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                <td><a href="${cm.material_link}" target="_blank"><i class="fa fa-download fa-2x"></i></a></td>
                            </tr>
                            </table>
                      </div>
  				</g:each>
  			</div>
  		</div>
  	</div>
</g:if>


<g:set var="mcq" value="${0}"/>
<g:set var="asssub" value="${0}"/>
<g:if test="${assignmentlist.size()!=0}">
<div class="panel-group">
	<div class="panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="form-control" data-toggle="collapse" href="#collapse_Course_Assessment">Course Assessment(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
            </h4>
        </div>
        <div id="collapse_Course_Assessment" class="panel-collapse collapse">
            <div class="panel-body">
                <g:each var="list" in= "${assignmentlist}">
					<g:if test="${list.assignment.assignmenttype.type!='MCQ'}">
						<b>Assignment/Project Title :</b>${list.assignment.title}&nbsp;&nbsp;
						<b>Description :</b>${list.assignment.description}&nbsp;&nbsp;
						<b>Weightage :</b>${list.assignment.weightage}&nbsp;&nbsp;
						<g:if test="${list.assignment.assignment_link!=''}">
							<b>Assignment Link:</b>
							<a href="${list.assignment.assignment_link}" target="_blank">${list.assignment.assignment_link}</a>
						</g:if>
						<g:if test="${assignmentsubmissionlist[asssub]!=null}">
							<br> Your File :<a href="${assignmentsubmissionlist[asssub].student_answer_file_path}/${assignmentsubmissionlist[asssub].student_answer_file_name}" target="_blank">${assignmentsubmissionlist[asssub].student_answer_file_name}</a><br>
						</g:if>
						<g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
							<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
								<br>Upload Your Work:
								<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" >
								<br>
								<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
								<br>
								<input class="form-control" type="hidden" name="ao" value="${list.id}">
							</g:form>
						</g:if>
						<g:else>
							<b>Due Date :</b>
							<g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/>
							<g:if test="${date<=list.due_date}">
								<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
									<br>Upload Your Work:
									<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" >
									<br>
									<g:submitButton class="btn btn-primary" name="upload" value="upload"></g:submitButton>
									<br>
									<input class="form-control" type="hidden" name="ao" value="${list.id}">
								</g:form>
							</g:if>
							<g:else>
								<g:form url="[controller:'Learner',action:'submitcourseassignment']" enctype="multipart/form-data">
									<br>Upload Your Work:
									<input class="btn btn-info form-control" type="file" id ="assignment" name="assignment" disabled="true">
									<br>
									<g:submitButton class="btn btn-primary" name="upload" value="upload" disabled="true"></g:submitButton>
									<br>
									<input class="form-control" type="hidden" name="ao" value="${list.id}">
								</g:form>
							</g:else>
						</g:else>
						<g:set var="asssub" value="${asssub+1}"/>
					</g:if>
					<g:else>
						<div class="card card-body">
							<div class="row"><b>MCQ Title :</b>${list.assignment.title}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<b>Description :</b>${list.assignment.description}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

								<b>Weightage :</b>${list.assignment.weightage}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</div>
							<label><b>Problem Statement :</b>${list.assignment.assignment_text}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;></label>
							<g:if test="${session.courseofferingtypelearner=='Self-Pace'}">
								<g:form url="[controller:'Learner',action:'submitcoursemcq']">
                                    <g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
                                        <g:if test="${assignmentsubmissionlist[asssub]!=null}">
                                            <g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
                                                <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                            </g:if>
                                            <g:else>
                                                <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                            </g:else>
                                        </g:if>
                                        <g:else>
                                            <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                        </g:else>
                                    </g:each>
                                	<input type="hidden" name="ao" value="${list.id}">
                                	<g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer"></g:submitButton>
                                </g:form>
							</g:if>
							<g:else>
								<label class="btn-warning" style="background-color: #f18973" ><b>Due Date :</b><g:formatDate date="${list.due_date}" name="due_date" format="dd-MM-yyyy"/></label>
								<g:if test="${date<=list.due_date}">
					                    <g:form url="[controller:'Learner',action:'submitcoursemcq']">
                                            <g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
                                                 <g:if test="${assignmentsubmissionlist[asssub]!=null}">
                                                    <g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
                                                        <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                    </g:if>
                                                    <g:else>
                                                        <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                    </g:else>
                                                </g:if>
                                                <g:else>
                                                    <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" /> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                </g:else>
                                            </g:each>
					                        <input type="hidden" name="ao" value="${list.id}">
					                        <g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer"></g:submitButton>
					                    </g:form>
								</g:if>
								<g:else>
					                    <g:form url="[controller:'Learner',action:'submitcoursemcq']">
                                            <g:each var="options" in= "${MCQOptionslistoflist[mcq]}">
                                                 <g:if test="${assignmentsubmissionlist[asssub]!=null}">
                                                    <g:if test="${assignmentsubmissionlist[asssub].mcqoptions.option_name==options.option_name}">
                                                        <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" checked="true" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                    </g:if>
                                                    <g:else>
                                                        <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                    </g:else>
                                                </g:if>
                                                <g:else>
                                                    <div class="radio"> <label><g:radio name="selectedoption" value="${options.option_name}" disabled="true"/> ${options.option_name}.${options.option_value} &nbsp;&nbsp;</label></div>
                                                </g:else>
                                            </g:each>
					                        <input type="hidden" name="ao" value="${list.id}">
					                        <g:submitButton class="btn btn-primary" name="Save Answer" value="Save Answer" disabled="true" ></g:submitButton>
					                    </g:form>
								</g:else>
							</g:else>
							<g:set var="mcq" value="${mcq+1}"/>
                            <g:set var="asssub" value="${asssub+1}"/>
						</div>
					</g:else>
				</g:each>
			</div>
        </div>
	</div>
</div>
</g:if>

<g:if test="${coffr.course.features.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_feature">Feature(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_feature" class="panel-collapse collapse">
				<g:each var="feature" in="${coffr.course.features}">
					<div class="panel-body">
                        <a class="form-control"> <i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${feature.feature}</a>
                    </div>
				</g:each>
			</div>
		</div>
	</div>
</g:if>



<g:if test="${coffr.course.prerequisite.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_prequisite">Prerequisite(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_prequisite" class="panel-collapse collapse">
				<g:each var="prerequisite" in="${coffr.course.prerequisite}">
					<div class="panel-body">
                        <a class="form-control"> <i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${prerequisite.prerequisite}</a>
                    </div>
				</g:each>
			</div>
		</div>
	</div>
</g:if>




<g:if test="${coffr.course.description.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_description">Description(s):&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_description" class="panel-collapse collapse">
				<g:each var="description" in="${coffr.course.description}">
					<div class="panel-body">
                        <a class="form-control"> <i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${description.description}</a>
                    </div>
				</g:each>
            </div>
        </div>
</g:if>



<g:if test="${courseoutcomes.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_course_outcome">Outcome(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_course_outcome" class="panel-collapse collapse">
	            <g:each var="courseoutcome" in="${courseoutcomes}">
	                <div class="panel-body">
                        <a class="form-control"> <i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${courseoutcome.co_statement}</a>
                    </div>
				      </g:each>
            </div>
        </div>
    </div>
</g:if>

<g:if test="${coffr.course.softwareurl.size()!=0}">
	<div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">
                    <a class="form-control" data-toggle="collapse" href="#collapse_usefule_link">Useful Link(s)&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
                </h4>
            </div>
            <div id="collapse_usefule_link" class="panel-collapse collapse">
                <g:each var="softwareurl" in="${coffr.course.softwareurl}">
	                <div class="panel-body">
                        <a href="http://${softwareurl.url}" target="_blank"><i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${softwareurl.url}</a>
                    </div>
                </g:each>
            </div>
        </div>
    </div>
</g:if>

<!-- <g:if test="${coffr.course.softwareurl.size()!=0}">
<b>Useful Link(s):</b> <br>
<table class=" col-sm-12 table table-striped table-bordered">
    <g:each var="softwareurl" in="${coffr.course.softwareurl}">
		<a href="http://${softwareurl.url}" target="_blank">${softwareurl.url}</a></tr></td>
	</g:each>
</table>
</g:if> -->

<div class="panel-group">
	<div class="panel panel-primary">
        <div class="panel-heading">
            <h4 class="panel-title">
                <a class="form-control" data-toggle="collapse" href="#collapse_course_outline">Course Contents&nbsp&nbsp&nbsp <i class="fa fa-plus"></i></a>
            </h4>
        </div>
        <div id="collapse_course_outline" class="panel-collapse collapse">
	        <ul class="list-group">
	        <g:set var="z" value="${0}"/>
                <g:each var="m" in= "${menu}">
                    <g:if test="${m.size==0}">
                    </g:if>
                    <g:elseif test="${z%2==1}">
                        <ul class="list-group">
                            <g:each var="sm" in= "${m}">
                                <li class="list-group-item"><i class="fa fa-circle"></i>&nbsp&nbsp&nbsp${sm.topic} </li>
                            </g:each>
                        </ul>
                    </g:elseif>
                    <g:else>
                        <li class="list-group-item">  ${m[0].outline} </li>
                    </g:else>
                  <g:set var="z" value="${z+1}"/>
                </g:each>
            </ul>
        </div>
      </div>
	</div>
</div>


<div id="edit">
</div>
<!-- <div id="updateMe">
</div> -->
<div id="editGrade"></div>



</div><!-- End of container-fluid-->

</div> <!-- End of content-wrapper-->

<br><br>
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
  <i class="fa fa-angle-up" ></i>
</a>
<!-- End Of Footer --------------------------------------- -->
<script>
  <!-- Week -->
  function callWeek(weekid)
  {
   var xmlhttp = new XMLHttpRequest();
   xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/learner/showCourseData?weekid=" +weekid +"&ot=w", true);
  xmlhttp.send();
}
<!-- Session -->
function callSession(sessionid)
{
 var xmlhttp = new XMLHttpRequest();
 xmlhttp.onreadystatechange = function() {
  if (this.readyState == 4 && this.status == 200) {
    document.getElementById("material").innerHTML = this.responseText;
  }
};
xmlhttp.open("GET", "${request.contextPath}/learner/showCourseData?sessionid=" +sessionid +"&ot=s", true);
xmlhttp.send();
}
///////////////
var markreviewStatus = false
function markAsReview(val,id)
{
   // alert("val"+val)
    var property = document.getElementById(val);
    property.style.backgroundColor = "#f4a1c7";
    markreviewStatus = true;
    retStatus();
}
 function retStatus()
 {
     return markreviewStatus;
 }

function GenreateQuestion(coffr)
{
    var xmlhttp = new XMLHttpRequest();
     xmlhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        document.getElementById("material").innerHTML = this.responseText;
      }
    };
    xmlhttp.open("GET", "${request.contextPath}/learner/generateQuestionPattern?coffr=" + coffr+"&ot=c", true);
    xmlhttp.send();
}
function myFunction(size,val,cid,id,qid,ans) {
         var xmlhttp = new XMLHttpRequest();
         xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            document.getElementById("material").innerHTML = this.responseText;
          }
        };
        xmlhttp.open("GET", "${request.contextPath}/learner/generateQuestion?val=" + val +"&cid="+ cid + "&name=" +id, true);
        xmlhttp.send();
}
function getQ(val,cid,id)
 {
    //alert("hi alankar"+);
      var xmlhttp = new XMLHttpRequest();
             xmlhttp.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
                document.getElementById("material").innerHTML = this.responseText;
              }
            };
            xmlhttp.open("GET", "${request.contextPath}/learner/generateQues?val=" + val + "&cid="+ cid + "&sessionid=" +id, true);
            xmlhttp.send();
 }
 var globalVariable=[];
 function handleClick(cb) {
 alert(cb);

    retOption();
 }
 function retOption()
 {
     return globalVariable;
 }

function saveQuestionData(size,val,cid,id,qid,opsize)
{
    alert("call save data option size"+opsize );
     var opid = [];
     var cars = ["A", "B", "C", "D", "E"];
     if(opsize == 1)
     {
         for (var i = 0; i <cars.length; i++) {
                     var ele = document.getElementById(cars[i]);
                     if(ele.checked == true)
                        {
                             opid.push(cars[i]);
                             alert(cars[i])
                        }
                }
     }
     else
     {
        for (var i = 0; i <opsize; i++) {
             var ele = document.getElementById(cars[i]);
             if(ele.checked == true)
                {
                     opid.push(cars[i]);
                     alert(cars[i])
                }
        }
     }
    //var opid = retOption();
    alert("save question"+opid);
    var qstatus = retStatus();
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function() {
       if (this.readyState == 4 && this.status == 200) {
              document.getElementById("material").innerHTML = this.responseText;
          }
       };
       xmlhttp.open("GET", "${request.contextPath}/learner/saveQuestionData?qid=" + qid + "&opid=" + opid +"&qstatus="+qstatus+"&cid="+cid, true);
       xmlhttp.send();
       globalVariable=[];
       markreviewStatus = false


}

///////////////
function callTopic(topicid){


  var xmlhttp = new XMLHttpRequest();
  xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("material").innerHTML = this.responseText;
    }
  };
  xmlhttp.open("GET", "${request.contextPath}/learner/showCourseData?topicid=" +topicid +"&ot=t", true);
  xmlhttp.send();
}
function callOutline(outlineid){
 var x = document.getElementById("material");
 x.style.display = "block";
 var x = document.getElementById("edit");
 x.style.display = "none";
            //alert("O:"+outlineid);
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function() {
              if (this.readyState == 4 && this.status == 200) {
                document.getElementById("material").innerHTML = this.responseText;
              }
            };
            xmlhttp.open("GET", "${request.contextPath}/learner/showCourseData?olid=" +outlineid +"&ot=o", true);
            xmlhttp.send();
          }

        </script>
        <!---------------------- Online Exam Code by PHR AJD ------------------>
        <script>
        var pos=0,choices, question, choice,chA,chB,chC,chD,chE, totalque;
        var questions;
          function getId(x){
           return document.getElementById(x);
          }

          function getQuestion(data){
            getId('startbtn').style.display="none";
            questions=data;
            totalque=questions.length;
           /* for(var i in questions)
            {
               alert(questions[i][0]);
            }*/
            renderQuestion();
            console.log(data);

          }
          function renderQuestion(){

            questionlist=getId('questionList');
            options=getId('options');
             question=questions[pos][0];
             chA=questions[pos][1];
             chB=questions[pos][2];
             chC=questions[pos][3];
             chD=questions[pos][4];
             chE=questions[pos][5];
             questionlist.innerHTML="<h3>"+question+"</h3>";
             options.innerHTML='<input type="radio" name="choices" value="">'+'<img src="chA">';
          }

        </script>
       <!----------------------End Online Exam Code by PHR AJD ------------------>
        <asset:javascript src="jquery.min.js"/>
        <asset:javascript src="bootstrap.bundle.min.js"/>
        <asset:javascript src="jquery.easing.min.js"/>
        <asset:javascript src="sb-admin.min.js"/>
      </body>
      </html>