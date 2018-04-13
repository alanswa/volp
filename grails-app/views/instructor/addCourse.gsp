

<head>
    <meta charset="UTF-8">
    <title>Add Course</title>

    <style>
</style>

</head>

<body >
 <div class="row card ">
  <g:if test="${flash.message}">
  <div class="alert alert-error" style="display: block;color:red">${flash.message}</div>
</g:if>
<div class="card-body">
    <g:form name="myForm" url="[action:'processCourse',controller:'instructor']" enctype="multipart/form-data">
    <h3>Add Course </h3>


       <div class="row col-sm-8">
       <label class="custom-file">
       <span class="btn btn-default btn-file">
        Select Picture: <input type="file" id="file" class="custom-file-input" name="coursePic"/>
        </span>
        <span class="custom-file-control"></span>
        </label>
        </div>
        <br>
        <div class="row col-sm-12">
        <div class="form-control col-sm-6" > Course Code:
            <input class="form-control" type="text" name="crs_code" placeholder="Eg. CS303" size="50">
        </div>
        <div class="card col-sm-6">*Course Name:
            <input  class="form-control" type="text" name="crs_name" placeholder="Eg. Computer Networks" required size="50">
        </div>
        </div>


    <div class="row col-sm-12">
        <div class="form-control col-sm-6" > Credits:
            <input class="form-control" type="number" name="credit" placeholder="Eg. 4" min="0" size="50"  />
        </div>
        <div class="card col-sm-6">*Course Category:
            <g:select class="form-control" optionKey="id" from="${cc}" optionValue="${{it.name}}" name="cat" noSelection="${['null':'Please Select One...']}" onchange="addCat(this.value)"  required="true" /></>
            <div id="scat"></div>
        </div>
    </div>

    <div class="row col-sm-12">
        <div class="form-control col-sm-6" >Dept:
            <g:select class="form-control" optionKey="id" from="${dept}" optionValue="${{it.name}}" name="dept" noSelection="${['null':'Please Select One...']}" />
        </div>
        <div class="card col-sm-6">Prog:
            <g:select class="form-control" optionKey="id" from="${prog}" optionValue="${{it.name}}" name="prog" noSelection="${['null':'Please Select One...']}" />
        </div>
    </div>
     *Visibility :
<div class="col-sm-12">

    <g:select class="form-control " optionKey="id" from="${cv}" optionValue="${{it.name}}" name="cv"  required="true"    />
</div>
    Instructor(s):
    <div class="col-sm-12">
    <g:select class="form-control col-sm-12" optionKey="id" from="${ints}" optionValue="${{it.uid}}" name="ins" multiple="true"/>
      </div>
    *Feature(s)
    <div id="newlink">
        <div class=" row col-sm-12">
            <div class="feed col-sm-3">
                <input class="form-control" type="number" name="featureno" placeholder="Enter No." size="50" required>
            </div>
            <div class="col-sm-6">
                <input class="form-control" type="text" name="feature" placeholder="Enter feature" size="50" required>
            </div>
            <div class="col-sm-3" id="addnew">
                <a class="btn btn-primary circlebtn" href="javascript:add_feed()">+</a>
            </div>
        </div>
    </div>


*Description(s)
<div id="newlinkdes">
    <div class=" row feeddes col-sm-12">
        <div class="col-sm-3">
            <input class="form-control" type="number" name="descno" placeholder="Enter No." size="50" required>
        </div>
        <div class="col-sm-6">
            <input class="form-control" type="text" name="description" placeholder="Enter description" size="50" required>
        </div>
        <div class="col-sm-3" id="addnewdes">
            <a class="btn btn-warning circlebtn" href="javascript:add_feeddes()">+</a>
        </div>
    </div>
</div>



Prerequisite (s)
<div id="newlinkpre">
    <div class=" row feedpre col-sm-12">
    <div class="col-sm-3" id="addnewpre">
                <a class="btn btn-primary circlebtn" href="javascript:add_feedpre()">+</a>
            </div>
    </div>
</div>

URL(s)
<div id="newlinkurl">
    <div class="row feedurl col-sm-12">
<div class="col-sm-3" id="addnewurl">
            <a class="btn btn-warning circlebtn" href="javascript:add_feedurl()">+</a>
        </div>
 </div>
</div>
<p>
    <br>
    <input class="btn btn-warning" type="reset" name="reset1">&nbsp&nbsp&nbsp&nbsp&nbsp
    <input class="btn btn-primary" type="submit" value="Add" name="submit" id="submitCrs" title="Please select all mandotory fields!!!" disabled="true">
</p>

</g:form>
<!-- Template. This whole data will be added directly to working form above -->

<div class="card-body"  id="newlinktpl" style="display:none">
    <div class="feed row">
       <input class="form-control col-sm-3" type="number" name="featureno" placeholder="Enter feature" size="50">
       <input class="form-control col-sm-9" type="text" name="feature" placeholder="Enter feature" size="50">
   </div>
</div>
<div class="card-body" id="newlinktpldes" style="display:none">
    <div class="feeddes row">
       <input class="form-control col-sm-3" type="number" name="descno" placeholder="Enter No." size="50">
       <input class="form-control col-sm-9" type="text" name="description" placeholder="Enter description" size="50">
   </div>
</div>
<div class="card-body" id="newlinktplpre" style="display:none">
    <div class="feedpre row">
       <input class="form-control col-sm-3" type="number" name="preno" placeholder="Enter No." size="50" required>
       <input class="form-control col-sm-9" type="text" name="prerequisite" placeholder="Enter Prerequisite" size="50" required>
   </div>
</div>
<div class="card-body" id="newlinktplurl" style="display:none">
    <div class="feedurl row">

       <input class="form-control col-sm-9" type="text" name="url" placeholder="Enter url" size="50" required>
   </div>
</div>
</div>
<br>
</div>
<script>
$(document).on('change', ':file', functigraon() {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect', [numFiles, label]);
});
</script>
</body>
</html>