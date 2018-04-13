package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AssignmentController {

    AssignmentService assignmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond assignmentService.list(params), model:[assignmentCount: assignmentService.count()]
    }

    def show(Long id) {
        respond assignmentService.get(id)
    }

    def create() {
        respond new Assignment(params)
    }

    def save(Assignment assignment) {
        if (assignment == null) {
            notFound()
            return
        }

        try {
            assignmentService.save(assignment)
        } catch (ValidationException e) {
            respond assignment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignment.id])
                redirect assignment
            }
            '*' { respond assignment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond assignmentService.get(id)
    }

    def update(Assignment assignment) {
        if (assignment == null) {
            notFound()
            return
        }

        try {
            assignmentService.save(assignment)
        } catch (ValidationException e) {
            respond assignment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'assignment.label', default: 'Assignment'), assignment.id])
                redirect assignment
            }
            '*'{ respond assignment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        assignmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'assignment.label', default: 'Assignment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'assignment.label', default: 'Assignment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def selectcourse()
    {
        println("I am in selectcourse.."+session.uid)
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        println("Instructor:"+inst)
        def courses=Course.findAllByCourseowner(inst)
        ArrayList<String> courselist=new ArrayList<String>();
        courselist.add("Select Your Course")
        for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)
        [courselist:courselist]
    }
    def processassignment()
    {
        println("I am in processassignment...."+params)
        /*String coursecodecoursename=params.coursecodecoursename
        def crs=null
        if(coursecodecoursename.equals(""))
        {
            coursecodecoursename = session.coursecodecoursename
            crs=coursecodecoursename.split(":")
        }
        else
            crs=coursecodecoursename.split(":")
        session.coursecodecoursename=coursecodecoursename
        String coursecode=crs[0]
        String coursename=crs[1]*/
        String email=session.uid
       // session.coursecode=coursecode
       // session.coursename=coursename
        session.email=email
        Instructor inst=Instructor.findByUid(email)
       // println("ok..."+email+" "+coursecode+" "+coursename+" "+inst)
       // Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
       // println("Course::"+course)
      //  session.course=course
      //  def coff=CourseOffering.findAllByCourseAndIsActive(course,true)
        def coff = CourseOffering.findById(params.coffrid)
        println("coff::"+coff)
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        int row=0
        List templist=new ArrayList<String>()
        templist.add("Course Code")
        templist.add("Course Name")
        templist.add("Start Date")
        templist.add("End Date")
        templist.add("Batch")
        listOfLists.add(templist)
        row++
        for(CourseOffering cff:coff)
        {
            templist= new ArrayList<String>()
            templist.add(cff.course.course_code)
            templist.add(cff.course.course_name)
            templist.add(cff.start_date.format('d-M-yyyy'))
            templist.add(cff.end_date.format('d-M-yyyy'))
            templist.add(cff.batch)
            listOfLists.add(templist)
            row++
        }
        println("listOfLists:"+listOfLists)
        session.listOfListsAssignment=listOfLists
        session.assignmentcoff=coff
        [listOfLists:listOfLists,row:row]
    }
    def addassignment()
    {
        println("I am in addassignment.."+params)
        int selectedrow=Integer.parseInt(params.id)
        session.assignmentid = params.id
        println(session.listOfListsAssignment[selectedrow])
        Course course=session.course
        String startdate=session.listOfListsAssignment[selectedrow][2]
        String enddate=session.listOfListsAssignment[selectedrow][3]
        String batch=session.listOfListsAssignment[selectedrow][4]
        println(course)
        println(startdate)
        println(enddate)
        println(batch)
        def coff=session.assignmentcoff
        int row=1
        for(CourseOffering c:coff)
        {
            if(row==selectedrow)
            {
                println("cff.course.course_code::"+c.course.course_code)
                println("cff.course.course_name:"+c.course.course_name)
                println(c.start_date.format('d-M-yyyy'))
                println(c.end_date.format('d-M-yyyy'))
                println(c.batch)
                session.assignmentcoff=c
                break
            }
            row++
        }
        coff=session.assignmentcoff
        ArrayList<String> uploadcategorylist=new ArrayList<String>();
        uploadcategorylist.add("Select Upload Category")
        uploadcategorylist.add("Course")
        uploadcategorylist.add("Unit")
        uploadcategorylist.add("Topic")
        println(uploadcategorylist)
        ArrayList<String> assignmenttypelist=new ArrayList<String>();
        def at=AssignmentType.list()
        for(AssignmentType a:at)
        {
            assignmenttypelist.add(""+a.type)
        }
        session.trackoptions=1
        [coff:coff,uploadcategorylist:uploadcategorylist,assignmenttypelist:assignmenttypelist]
    }
    def processaddassignment()
    {
        println("I am in processaddassignment...."+params)
        String uploadcategory=params.uploadcategory
        String assignmenttype=params.assignmenttype
        session.uploadcategory=uploadcategory
        session.assignmenttype=assignmenttype
        String coursecode=session.assignmentcoff.course.course_code
        String coursename=session.assignmentcoff.course.course_name
        String email=session.uid
        session.coursecode=coursecode
        session.coursename=coursename
        session.email=email
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        def courseoutlinelist1=CourseOutline.findAllByCourse(course)
        courseoutlinelist1.sort{it.outline_number}

        def courseoutlinelist=new ArrayList();
        CourseOutline cobj=new CourseOutline()
        cobj.course=course
        cobj.outline="Select Outline/Unit for Course"
        courseoutlinelist.add(cobj)

        for(CourseOutline co:courseoutlinelist1)
        {
            courseoutlinelist.add(co)
        }
        session.course=course
        session.courseoutlinelist=courseoutlinelist
        ArrayList<String> difficultylevellist=new ArrayList<String>();
        def dl=DifficultyLevel.list()
        for(DifficultyLevel d:dl)
        {
            difficultylevellist.add(d.name)
        }
        InitialData idval=InitialData.findByName("mcqoptions")
        ArrayList<String> mcqoptionlist=new ArrayList<String>();
        int x=97
        int y=idval.number
        for(int i=0;i<y;i++)
        {
            char temp=(char)x
            mcqoptionlist.add(""+temp)
            x++
        }
        println("mcqoptionlist:"+mcqoptionlist)
        ArrayList<String> correctoptionlist=new ArrayList<String>();
        correctoptionlist.add("No")
        correctoptionlist.add("Yes")
        [correctoptionlist:correctoptionlist,mcqoptionlist:mcqoptionlist,assignmenttype:assignmenttype,coff:session.assignmentcoff,courseoutlinelist:courseoutlinelist,difficultylevellist:difficultylevellist]
    }
    def furtherprocessaddassignment()
    {
        println("I am in furtherprocessaddassignment...")
        int trackoptions=session.trackoptions
        trackoptions++
        session.trackoptions=trackoptions
        InitialData idval=InitialData.findByName("mcqoptions")
        ArrayList<String> mcqoptionlist=new ArrayList<String>();
        int x=97
        int y=idval.number
        for(int i=0;i<y;i++)
        {
            char temp=(char)x
            mcqoptionlist.add(""+temp)
            x++
        }
        println("mcqoptionlist:"+mcqoptionlist)
        ArrayList<String> correctoptionlist=new ArrayList<String>();
        correctoptionlist.add("No")
        correctoptionlist.add("Yes")
        [correctoptionlist:correctoptionlist,mcqoptionlist:mcqoptionlist]
    }
    def addcourseoutlines()
    {
        println("I am in addcourseoutlines...")
        String courseoutline=params.courseoutline
        CourseOutline co=CourseOutline.findByOutlineAndCourse(courseoutline,session.course)
        println("course outline::"+co)
        def coursetopiclist1=CourseTopic.findAllByCourseoutline(co)
        coursetopiclist1.sort{it.topic_number}
        def coursetopiclist=new ArrayList();
        CourseTopic cobj=new CourseTopic()
        cobj.courseoutline=co
        cobj.topic="Select Topic"
        coursetopiclist.add(cobj)

        for(CourseTopic ct:coursetopiclist1)
        {
            coursetopiclist.add(ct)
        }

        session.courseoutlineobj=co
        ArrayList<String> difficultylevellist=new ArrayList<String>();
        def dl=DifficultyLevel.list()
        for(DifficultyLevel d:dl)
        {
            difficultylevellist.add(d.name)
        }
        InitialData idval=InitialData.findByName("mcqoptions")
        ArrayList<String> mcqoptionlist=new ArrayList<String>();
        int x=97
        int y=idval.number
        for(int i=0;i<y;i++)
        {
            char temp=(char)x
            mcqoptionlist.add(""+temp)
            x++
        }
        ArrayList<String> correctoptionlist=new ArrayList<String>();
        correctoptionlist.add("No")
        correctoptionlist.add("Yes")
        [correctoptionlist:correctoptionlist,coursetopiclist:coursetopiclist,difficultylevellist:difficultylevellist,mcqoptionlist:mcqoptionlist]
    }
    def saveassignment()
    {
        println("I am in saveassignment.."+params)
        println("test::"+params.isCorrect)
        CourseOffering coff=session.assignmentcoff
        println("coff:"+coff)
        println("upload type::"+session.uploadcategory)
        AssignmentType at=AssignmentType.findByType(params.assignmenttype)
        println("Assignment type::"+at)
        DifficultyLevel dl=DifficultyLevel.findByName(params.difficultylevel)
        println("Diificulty Level::"+dl)
        Assignment assignment=null
        CourseOutline courseoutline=CourseOutline.findByOutline(params.courseoutline)
        CourseTopic ct=null
        if(courseoutline!=null)
        {
            ct=CourseTopic.findByTopicAndCourseoutline(params.coursetopic,courseoutline)
        }
        println("courseoutline::"+courseoutline)
        println("ct::"+ct)
        int size=0
        if(session.uploadcategory.equals("Course"))
        {
            assignment = Assignment.findByTitleAndAssignment_textAndCourseAndAssignmenttype(params.assignment_title, params.assignment_text, coff.course, at)
        }
        if(session.uploadcategory.equals("Unit"))
        {
            assignment = Assignment.findByTitleAndAssignment_textAndCourseAndAssignmenttypeAndCourseoutline(params.assignment_title, params.assignment_text, coff.course, at,courseoutline)
        }
        if(session.uploadcategory.equals("Topic"))
        {
            assignment = Assignment.findByTitleAndAssignment_textAndCourseAndAssignmenttypeAndCourseoutlineAndCoursetopic(params.assignment_title, params.assignment_text, coff.course, at,courseoutline,ct)
        }
            if(assignment==null)
            {
                //insert into assignment
                assignment=new Assignment()
                def assignmenttemp=Assignment.findAllByCourse(coff.course)
                size=assignmenttemp.size()+1
                assignment.assignment_number=size
                assignment.title=params.assignment_title
                assignment.description=params.assignment_description
                String weightage=params.assignment_weightage
                if(weightage!=null) {
                    weightage = weightage.replaceAll("\\s+", "")
                    if(weightage!="")
                        assignment.weightage= Double.parseDouble(weightage)
                    else
                        assignment.weightage=0
                }
                else
                    assignment.weightage=0
                assignment.assignment_path=""
                assignment.assignment_file_name=params.assignment_name
                assignment.assignment_link=params.assignment_link
                assignment.assignment_text=params.assignment_text
                assignment.username=session.uid
                assignment.creation_date=new java.util.Date()
                assignment.updation_date=new java.util.Date()
                assignment.creation_ip_address=request.getRemoteAddr()
                assignment.updation_ip_address=request.getRemoteAddr()
                assignment.coursetopic=ct
                assignment.courseoutline=courseoutline
                assignment.course=coff.course
                assignment.assignmenttype=at
                assignment.difficultylevel=dl
                if(!params.assignmenttype.equals("MCQ"))
                {
                    assignment.model_answer_path=null
                    assignment.model_answer_file_name=params.model_answer_name
                    assignment.model_answer_text=params.model_answer_text
                    assignment.model_answer_link=params.model_answer_link
                    assignment.save(failOnError:true,flush:true)
                }
                if(params.assignmenttype.equals("MCQ"))
                {
                    for(int i=0;i<params.option_name.length;i++)
                    {
                        MCQOptions mcqoptions=new MCQOptions()
                        mcqoptions.option_name=params.option_name[i]
                        mcqoptions.option_value=params.option_value[i]
                        if(params.isCorrect[i].toString().equals("Yes"))
                            mcqoptions.isCorrect=true
                        else
                            mcqoptions.isCorrect=false
                        mcqoptions.mcq_option_file_name=params.mcq_option_file_name[i]
                        mcqoptions.mcq_option_file_path=null
                        mcqoptions.mcq_option_file_link=params.mcq_option_file_link[i]
                        mcqoptions.assignment=assignment
                        mcqoptions.username=session.uid
                        mcqoptions.creation_date=new java.util.Date()
                        mcqoptions.updation_date=new java.util.Date()
                        mcqoptions.creation_ip_address=request.getRemoteAddr()
                        mcqoptions.updation_ip_address=request.getRemoteAddr()
                        mcqoptions.save(failOnError:true,flush:true)
                    }
                }
            }
            AssignmentOffering assignmentoffering=AssignmentOffering.findByAssignmentAndCourseoffering(assignment,coff)
            if(assignmentoffering==null)
            {
                assignmentoffering=new AssignmentOffering()
                String assignment_number=params.assignment_no
                assignment_number=assignment_number.replaceAll("\\s+","")
                println("hrishi::"+assignment_number)
                if(assignment_number!=null) {
                    assignmentoffering.assignment_offering_number=Integer.parseInt(assignment_number)
                }
                else
                    assignmentoffering.assignment_offering_number=0
                Date schedule_date=params.date('schedule_date','dd-MM-yyyy')
                Date due_date=params.date('due_date','dd-MM-yyyy')
                assignmentoffering.schedule_date=schedule_date
                assignmentoffering.due_date=due_date
                assignmentoffering.username=session.uid
                assignmentoffering.creation_date=new java.util.Date()
                assignmentoffering.updation_date=new java.util.Date()
                assignmentoffering.creation_ip_address=request.getRemoteAddr()
                assignmentoffering.updation_ip_address=request.getRemoteAddr()
                assignmentoffering.assignment=assignment
                assignmentoffering.courseoffering=coff
                assignmentoffering.courseassesmentscheme=null
                assignmentoffering.save(failOnError:true,flush:true)
            }

        println("Assignment::"+assignment)
        redirect(action: "addassignment", params: [id: session.assignmentid])
    }
}
