<head>
<meta name="layout" content="learner/courseLearnerMain">
<style>
    h5{
     color: white;
     background-color: #424242;
     border:1px solid;
     padding: 5px;
    }
   </style>
</head>
<script>

  </script>

  <body>
  <div class="row">
    <g:if test="${!(regCrs.isEmpty())}">

                 <g:each var="crs" in="${regCrs}">

                     <div class="col-sm-3">
                             <div class="card">
                                    <div class="card-header bg-default"> <g:link action="leranerCourse" params="[crsLearner:crs.id]">${crs.courseoffering.course.course_name}</g:link>

                                     </div>
                                      <div class="card-body">

                                    <g:if test="${crs.courseoffering.course.imgpath==null}">
                                                                                    <img src="${createLinkTo(dir:'assets/images/course', file: 'default.jpg' )}" alt="Card image" style="width:100%; max-height:100px" />
                                                                                </g:if>
                                                    <g:if test="${crs.courseoffering.course.imgpath!=null}">
                                                                                   <img src="${createLinkTo(dir:crs.courseoffering.course.imgpath, file: crs.courseoffering.course.imgname )}" alt="Card image" style="width:100%; max-height:100px"/>
                                                    </g:if>
                                      <hr>
                                       <p style="float:right"><i class="fa fa-bookmark fa-1x"></i>&nbsp;${crs.courseoffering.batch}</p>
                                      <p><i class="fa fa-star fa-1x"></i>&nbsp;${crs.courseoffering.course.rating}</p>
                                      <center><a href="#" >${crs.courseoffering.course.courseowner.person.firstName}&nbsp;${crs.courseoffering.course.courseowner.person.lastName}</span></a></center>
                                     <!-- <a href="" style="float: right"> <i class="fa fa-folder" aria-hidden="true"></i></a> -->
                                    </div>

                                   </div>

                     </div>
                     <br>
                 </g:each>
                     </g:if>
                     </div>
    </body>