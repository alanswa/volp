    <!doctype html>
    <html lang="en" class="no-js">
    <head>

      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
      <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
      <title>
        <g:layoutTitle default="VOLP || Dashboard"/>
      </title>
      <meta name="viewport" content="width=device-width, initial-scale=1"/>


      <asset:stylesheet src="bootstrap.min.css"/>
      <asset:stylesheet src="font-awesome.min.css"/>
      <asset:stylesheet src="sb-admin.css"/>

      <asset:stylesheet src="courseInstructor.css"/>

      <g:layoutHead/>

    </head>
    <body>
     <g:render template="/layouts/learner/courseLearnerNav" />
     <br> <br>
     <div class="content-wrapper">
      <div class="container-fluid" id="mainContentDiv">
        <!-- container-fluid-->

        <g:render template="/layouts/learner/courseLearnerTimeline" />

        <g:layoutBody/>

      </div><!-- End of container-fluid-->

    </div> <!-- End of content-wrapper-->


    <g:render template="/layouts/learner/courseLearnerFooter" />
<script>
    function givenPlatformFeedback(type)
    {
    //alert(type)
     var xmlhttp = new XMLHttpRequest();
              xmlhttp.onreadystatechange = function() {
                  if (this.readyState == 4 && this.status == 200) {
                     document.getElementById("mainContentDiv").innerHTML = this.responseText;
                     //alert("done");
                  }
              };
              xmlhttp.open("GET", "${request.contextPath}/platformFeedback/givenPlatformFeedback?type="+type, true);
              xmlhttp.send();
    }
    function generateQuestionPattern(){

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
                    //alert("1");
                    document.getElementById("mainContentDiv").innerHTML = this.responseText;
        //alert("1"+this.responseText);
        }
        };
        xmlhttp.open("GET", "${request.contextPath}/Learner/generateQuestionPattern", true);
        xmlhttp.send();
    }
    </script>

    <asset:javascript src="jquery.min.js"/>
    <asset:javascript src="bootstrap.bundle.min.js"/>
    <asset:javascript src="jquery.easing.min.js"/>
    <asset:javascript src="sb-admin.min.js"/>

  </body>
  </html>
