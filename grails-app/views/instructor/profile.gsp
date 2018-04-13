<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">
  <g:if test="${role == 'Instructor'}">
  <meta name="layout" content="instructorTemp">
</g:if>
<g:elseif test="${role == 'Learner'}">
<meta name="layout" content="blanklearner">
</g:elseif>
<g:else>
</g:else>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
body {font-family: Arial;}

/* Style the tab */
.tab {
  overflow: hidden;
  border: 1px solid #ccc;
  background-color: #f1f1f1;
}
.form-control-sm {
padding: 1px;
width: 100%;

}
}
/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 17px;
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #ddd;
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
  display: none;
  margin: auto auto;
  width: 70%;
  font-size: 15px;
  padding: 6px 12px;
  border: 0px solid #ccc;
  border-top: none;
}
img{

height: 120px;
width: 120px;

}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1">

<script>
  function openCity(evt, cityName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
      tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
      tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
  }
  function showImage(src, target) {
    var fr = new FileReader();
    fr.onload = function (e) { target.src = this.result; };
    src.addEventListener("change", function () {
      fr.readAsDataURL(src.files[0]);
    });

  }
  function FillBilling(f) {
    if(f.billingtoo.checked == true) {

      f.altcountry.value = f.country.value;
      f.altstate.value = f.state.value;
      f.altstreet.value = f.street.value;
      f.altdistrict.value = f.district.value;
      f.altcity.value = f.city.value;
      f.altpin.value = f.pin.value;
    }
  }
  function putImage() {
    var src = document.getElementById("profilepicture");
    var fsize = Math.round(src.size/3);
    if(fsize<50)
    {
      var target = document.getElementById("target");
      showImage(src, target);
    }
    else
    {
      var str = "**Your file size is larger than 50 KB please select another file.";
      var result = str.fontcolor("red");
      document.getElementById("message").innerHTML=result;
      document.getElementById("submitBtn").disabled = true;
    }
  }
</script>
</head>
<body>
<br>
  <div class="tab">
    <button class="tablinks" onclick="openCity(event, 'Personal Information')" >Personal Information</button>
    <button class="tablinks" onclick="openCity(event, 'Contact')">Contact</button>
    <button class="tablinks" onclick="openCity(event, 'Address')">Address</button>
    <button class="tablinks" onclick="openCity(event, 'Professional/Educational Information')">Professional/Educational Information</button>
    <button class="tablinks" onclick="openCity(event, 'Oraganization Information')">Oraganization Information</button>
  </div>
  <div id="Oraganization Information" class="tabcontent">
    <g:if test="${insData}">
    <g:if test="${insData.organization}">
    Oraganization name <g:textField class="form-control-sm " name="orgName" value="${insData.organization.organization_name}" /><br/>
  </g:if>
  <g:else>
  Oraganization Data is not found.
</g:else>
</g:if>
</div>
<div id="Professional/Educational Information" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorProfessionalData" >
  <g:if test="${insData}">
  <g:if test="${insData.person}">
  Hhighest Qualification <g:textField class="form-control-sm "  name="highest_qualification" value="${insData.person.highest_qualification}" /><br/>
  About You <g:textField class="form-control-sm "  name="short_description" value="${insData.person.short_description}" /><br/>
  User Skills <g:textField class="form-control-sm "  name="skills" value="${insData.person.skills}" /><br>
</g:if>
</g:if>
<g:else>
Hhighest Qualification <g:textField class="form-control-sm "  name="highest_qualification" value="${highest_qualification}" /><br/>
About You <g:textField class="form-control-sm "  name="short_description" value="${short_description}" /><br/>
User Skills <g:textField class="form-control-sm "  name="skills" value="${skills}" /><br>
</g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorProfessionalData"/></center>
</g:form>
</div>
<div id="Personal Information" class="tabcontent" style="display:block">
  <br/>

  <g:form controller="instructor" action="storeInstructorData" enctype="multipart/form-data">
  <g:if test="${role == 'Instructor'}">
  <g:if test="${insData}">
  <g:if test="{insData.person.logos.logo_path}">

</g:if>
FirstName <g:textField class="form-control-sm "  name="firstName" value="${insData.person.firstName}" /><br/>
MiddleName <g:textField class="form-control-sm "  name="middleName" value="${insData.person.middleName}" /><br/>
LastName <g:textField class="form-control-sm "  name="lastName" value="${insData.person.lastName}" /><br>
<g:if test="${insData.person.gender}">
Gender <g:select optionKey="id" name="gender" from="${genderInfo}" value="${insData?.person.gender.id}" optionValue="${{it.type}}" ></g:select>
</g:if>
<g:else>
Gender <g:select optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']"></g:select>
</g:else>
</br>Date_Of_Birth: <input class="form-control-sm " type="text"  id="datepicker"  name="date_of_birth" value="${insData.person.date_of_birth}"></p>
<g:if test="${insData.person.logos.logo_path}">
<img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.person.id}")}" params:'"${insData}"'></br>
</g:if>
<g:else>
</br><img id="target"/>
</br>Select Profile Picture: <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<g:else>
FirstName <g:textField class="form-control-sm "  name="firstName" value="${firstName}" /><br/>
MiddleName <g:textField class="form-control-sm "  name="middleName" value="${middleName}" /><br/>
LastName <g:textField class="form-control-sm "  name="lastName" value="${lastName}" /><br>
Gender <g:select optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']"></g:select>
</br>Date_Of_Birth: <input class="form-control-sm " type="text"  id="datepicker" name="date_of_birth"></p>
</br><img id="target"/>
</br>Select Profile Picture: <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<g:if test="${role == 'Learner'}">
<g:if test="${insData}">
<g:if test="{insData.person.logos.logo_path}">
<img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.person.id}")}" params:'"${insData}"'></br>
</g:if>
FirstName <g:textField class="form-control-sm "  name="firstName" value="${insData.person.firstName}" /><br/>
MiddleName <g:textField class="form-control-sm "  name="middleName" value="${insData.person.middleName}" /><br/>
LastName <g:textField class="form-control-sm "  name="lastName" value="${insData.person.lastName}" /><br>
Registration Number <g:textField class="form-control-sm "  name="registration_number" value="${insData.registration_number}" /><br>
Year <g:select optionKey="id" name="year" from="${yearInfo}" optionValue="${{insData.current_year?.year}}" ></g:select>
Gender <g:select optionKey="id" name="gender" from="${genderInfo}" value="${insData?.person.gender.id}" optionValue="${{it.type}}" ></g:select>
</br>Date_Of_Birth: <input class="form-control-sm " type="text"  id="datepicker" name="date_of_birth" value="${insData.person.date_of_birth}"></p>
<g:if test="${insData.person.logos.logo_path}">
<img src="${createLink(controller:'instructor',action:'renderImage',id:"${insData.person.id}")}" params:'"${insData}"'></br>
</g:if>
<g:else>
<br><img id="target"/>
<br>Select Profile Picture: <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<g:else>
FirstName <g:textField class="form-control-sm "  name="firstName" value="${firstName}" /><br/>
MiddleName <g:textField class="form-control-sm "  name="middleName" value="${middleName}" /><br/>
LastName <g:textField class="form-control-sm "  name="lastName" value="${lastName}" /><br>
Registration Number <g:textField class="form-control-sm "  name="registration_number" value="${registration_number}" /><br>
Year <g:select optionKey="id" name="year" from="${yearInfo}" optionValue="${{it.year}}" noSelection="['null':'select Year']"></g:select>
Gender <g:select optionKey="id" name="gender" from="${genderInfo}" optionValue="${{it.type}}" noSelection="['null':'select gender']"></g:select>
<br>Date_Of_Birth: <input class="form-control-sm " type="text"  id="datepicker" name="date_of_birth"></p>
<br><img id="target"/>
<br>Select Profile Picture: <input type="file" id="profilepicture" name="profilePic" onchange="putImage()"/>
<br><label id="message"></label>
</g:else>
</g:if>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorData"/></center>
</g:form>
</div>
<div id="Contact" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorContactData" enctype="multipart/form-data">
  <g:if test="${flash.message}">
  Please fill up personal information first..
</g:if>
</br>
<g:if test="${contactInfo}">
mobile_no <g:textField class="form-control-sm "  name="mobile_no" value="${contactInfo.mobile_no}" /><br/>
Alternate_mobile_no <g:textField class="form-control-sm "  name="ulternate_mobile_no" value="${contactInfo.ulternate_mobile_no}" /><br/>
alternate_email <g:textField class="form-control-sm "  name="alternate_email" value="${contactInfo.alternate_email}" /><br>
<br>telephone_no <input class="form-control-sm " type="text"  id="telephone_no" name="telephone_no" value="${contactInfo.telephone_no}">
<br>fax <input class="form-control-sm " type="text"  id="fax" name="fax" value="${contactInfo.fax}">
<br>website_url <input class="form-control-sm " type="text"  id="website_url"  name="website_url" value="${contactInfo.website_url}">
</g:if>
<g:else>
mobile_no <g:textField class="form-control-sm "  name="mobile_no" value="${mobile_no}" /><br/>
Alternate_mobile_no <g:textField class="form-control-sm "  name="ulternate_mobile_no" value="${ulternate_mobile_no}" /><br/>
alternate_email <g:textField class="form-control-sm "  name="alternate_email" value="${alternate_email}" /><br>
</br>telephone_no <input class="form-control-sm " type="text"  id="telephone_no" name="telephone_no">
<br>fax <input class="form-control-sm " type="text"  id="fax" name="fax">
<br>website_url <input class="form-control-sm " type="text"  id="website_url" name="website_url">
</g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorContactData"/></center>
</g:form>
</div>
<div id="Address" class="tabcontent">
  <g:form controller="instructor" action="storeInstructorAddressData" enctype="multipart/form-data">
  <g:if test="${flash.message}">
  Please fill up personal information first..
</g:if>
<br>
<g:if test="${addressInfo}">

<g:each var="address" in="${addressInfo}" status="counter">

<g:if test="${counter == 1}">
<br>***Permenent Addres is***</br>
</g:if>
<g:else>
<br>***Local Addres is***</br>
</g:else>
country <g:textField class="form-control-sm "  name="country" value="${address.country}" /><br/>
state <g:textField class="form-control-sm "  name="state" value="${address.state}" /><br/>
district <g:textField class="form-control-sm "  name="district" value="${address.district}" /><br>
<br>city <input class="form-control-sm " type="text"  id="city" name="city" value="${address.city}">
<br>street <input class="form-control-sm " type="text"  id="street" name="street" value="${address.street}">
</br>pin <input class="form-control-sm " type="text"  id="pin"  name="pin" value="${address.pin}">
<g:set var="x" value="${counter}"/>
</g:each>
<g:if test="${x == 0}">
</br><input type="checkbox" name="billingtoo" onclick="FillBilling(this.form)">
<em>Check this box if Permanent Address and Local Address are the same.</em>
</br>country <g:textField class="form-control-sm "  name="altcountry" value="${country}" /><br/>
state <g:textField class="form-control-sm "  name="altstate" value="${state}" /><br/>
district <g:textField class="form-control-sm "  name="altdistrict" value="${district}" /><br>
</br>city <input class="form-control-sm " type="text"   name="altcity" value="${city}">
</br>street <input class="form-control-sm " type="text"   name="altstreet" value="${street}">
</br>pin <input class="form-control-sm " type="text"  name="altpin" value="${pin}">
</g:if>

</g:if>

<g:else>
country <g:textField class="form-control-sm "  name="country" value="${country}" /><br/>
state <g:textField class="form-control-sm "  name="state" value="${state}" /><br/>
district <g:textField class="form-control-sm "  name="district" value="${district}" /><br>
</br>city <input class="form-control-sm " type="text"  id="city" name="city" value="${city}">
</br>street <input class="form-control-sm " type="text"  id="street" name="street" value="${street}">
</br>pin <input class="form-control-sm " type="text"  id="pin"  name="pin" value="${pin}">
</br><input type="checkbox" name="billingtoo" onclick="FillBilling(this.form)">
<em>Check this box if Permanent Address and Local Address are the same.</em>
</br>country <g:textField class="form-control-sm "  name="altcountry" value="${country}" /><br/>
state <g:textField class="form-control-sm "  name="altstate" value="${state}" /><br/>
district <g:textField class="form-control-sm "  name="altdistrict" value="${district}" /><br>
</br>city <input class="form-control-sm " type="text"   name="altcity" value="${city}">
</br>street <input class="form-control-sm " type="text"   name="altstreet" value="${street}">
</br>pin <input class="form-control-sm " type="text"  name="altpin" value="${pin}">
</g:else>
<br><br><center><g:actionSubmit class="btn btn-primary" value="Save" id="submitBtn" action="storeInstructorAddressData"/></center>
</g:form>
</div>
<script>

  $(document).ready(function(){
   $( "#datepicker" ).datepicker({
    changeMonth: true,
    changeYear: true,
    yearRange:'-100:+0'
  });


 });
</script>
</body>
</html>