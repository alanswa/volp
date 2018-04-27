package volp

import grails.validation.ValidationException

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import static org.springframework.http.HttpStatus.*
import grails.converters.JSON
class LearnerController {

    LearnerService learnerService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond learnerService.list(params), model:[learnerCount: learnerService.count()]
    }

    def show(Long id) {
        respond learnerService.get(id)
    }

    def create() {
        respond new Learner(params)
    }

    def save(Learner learner) {
        if (learner == null) {
            notFound()
            return
        }

        try {
            learnerService.save(learner)
        } catch (ValidationException e) {
            respond learner.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'learner.label', default: 'Learner'), learner.id])
                redirect learner
            }
            '*' { respond learner, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond learnerService.get(id)
    }

    def update(Learner learner) {
        if (learner == null) {
            notFound()
            return
        }

        try {
            learnerService.save(learner)
        } catch (ValidationException e) {
            respond learner.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'learner.label', default: 'Learner'), learner.id])
                redirect learner
            }
            '*'{ respond learner, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        learnerService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'learner.label', default: 'Learner'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'learner.label', default: 'Learner'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def learner()
    {
        println("I am in learner session:"+session)
        Learner l = Learner.findByUid(session.uid)
        session.fname = l.person.firstName
        //  println("L:"+l.person.firstName)
        def regCrs = CourseOfferingLearner.findAllByLearner(l)
        println("Reg:"+regCrs)
        [l:l,regCrs:regCrs]
    }
    def leranerCourse(){
        println("I am in leranerCourse session:"+session)
        println("I am in leranerCourse params:"+params)
        Learner learner = Learner.findByUid(session.uid)
        ArrayList assignmentsubmissionlist=new ArrayList()
        CourseOfferingLearner regCrs = CourseOfferingLearner.findById(params.crsLearner)
        session.crsLearner=params.crsLearner
        CourseOffering coffr = regCrs.courseoffering
        Course crs = coffr.course
        def co = CourseOutline.findAllByCourse(crs)
        co.sort{it.outline_number}
        ArrayList menuSubmenu = new ArrayList()
        ArrayList ct = new ArrayList<CourseTopic>()
        for(CourseOutline c: co) {
            ArrayList submenu = new ArrayList()
            ArrayList mainmenu = new ArrayList()
            mainmenu.add(c)
            def crstop = CourseTopic.findAllByCourseoutline(c)
            crstop.sort{it.topic_number}
            submenu.addAll(crstop)
            menuSubmenu.add(mainmenu)
            menuSubmenu.add(submenu)
            println("C:"+c)
            println("crstop:"+crstop)
            ct.addAll(crstop)
        }
        println("Menu:"+menuSubmenu)
        println("CO:"+co)
        println("CT:"+ct)

        //dpp code
        ArrayList menuSubmenuweek = new ArrayList()
        ArrayList sess = new ArrayList<CourseTopic>()
        def weeklist=Week.findAllByCourseoffering(coffr)
        weeklist.sort{it.week_number}
        for(Week w:weeklist)
        {
            ArrayList mainmenu = new ArrayList()
            ArrayList submenu = new ArrayList()
            def s=Session.findAllByWeek(w)
            if(s.size()==0)
                continue
            mainmenu.add(w)
            s.sort{it.session_number}
            submenu.addAll(s)
            menuSubmenuweek.add(mainmenu)
            menuSubmenuweek.add(submenu)
        }
        println("my list::"+menuSubmenuweek)
        def coursematerial=CourseMaterial.findAllByCourseAndCoursetopicAndCourseoutline(coffr.course,null,null)
        def coursevideos=CourseVideos.findAllByCourseAndCoursetopicAndCourseoutline(coffr.course,null,null)
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        def courseoutcomes=CourseOutcomes.findAllByCourse(coffr.course)
        println("courseoutcomes:"+courseoutcomes)
        //let us find assignments linked to course
        ArrayList assignmentlist=new ArrayList()
        ArrayList MCQOptionslist=null
        ArrayList MCQOptionslistoflist=new ArrayList()
        def assignment=Assignment.findAllByCourseAndCourseoutlineAndCoursetopic(coffr.course,null,null)
        for(Assignment a:assignment)
        {
            def assignmentoffering=AssignmentOffering.findAllByAssignmentAndCourseoffering(a,coffr)
            for(AssignmentOffering ao:assignmentoffering)
            {
                assignmentlist.add(ao)
                if(ao.assignment.assignmenttype.type=="MCQ")
                {
                    MCQOptionslist = new ArrayList()
                    def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                    MCQOptionslist.addAll(mcqoptions)
                    MCQOptionslistoflist.add(MCQOptionslist)
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
                else
                {
                    AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                    if(assignmentsubmission==null)
                        assignmentsubmissionlist.add(null)
                    else
                        assignmentsubmissionlist.add(assignmentsubmission)
                }
            }
        }
        session.courseofferingtypelearner=coffr.courseofferingtype.name
        Date date=new java.util.Date()
        [assignmentsubmissionlist:assignmentsubmissionlist,date:date,assignmentlist:assignmentlist,MCQOptionslistoflist:MCQOptionslistoflist,menuSubmenuweek:menuSubmenuweek,menu:menuSubmenu,coffr:coffr,coursematerial:coursematerial,coursevideos:coursevideos,vp:vp,courseoutcomes:courseoutcomes]
    }
    def showCourseData(){

        println("in showCourseData params:"+params)
        ArrayList listOflistOfLists=null
        ArrayList MCQOptionslistoflist=null
        ArrayList assignmentsubmissionlist=new ArrayList()
        session.ot=params.ot
        session.sessionid=params.sessionid
        if(params.ot=="w")   //week
        {
            println("Clicked on week::"+params.weekid)
        }
        if(params.ot=="s")   //session
        {
            println("Clicked on session:"+params.sessionid)
            Learner learner = Learner.findByUid(session.uid)
            listOflistOfLists= new ArrayList()
            ArrayList listOfLists = new ArrayList()
            ArrayList list = new ArrayList()
            MCQOptionslistoflist=new ArrayList()
            ArrayList MCQOptionslist=new ArrayList()
            /* ArrayList lll = new ArrayList()
             ArrayList ll = new ArrayList()
             ArrayList l = new ArrayList()
             l.add(4)
             l.add(5)
             l.add(6)
             ll.add(l)
             l = new ArrayList()
             l.add(1)
             l.add(2)
             l.add(3)
             ll.add(l)
             lll.add(ll)
             ll = new ArrayList()
             l = new ArrayList()
             l.add(7)
             l.add(8)
             l.add(9)
             ll.add(l)
             l = new ArrayList()
             l.add(10)
             l.add(11)
             l.add(12)
             ll.add(l)
             lll.add(ll)
             println("lll:"+lll)*/
            Session sess=Session.findById(params.sessionid)
            println("session_number::"+sess.session_number)
            println("Course Offering::"+sess.courseoffering)
            list = new ArrayList()
            list.add(sess)
            listOfLists.add(list)
            //find course videos and material for course unit
            def courseoutlinem = CourseMaterial.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline,sess.courseoffering.course,null)
            def courseoutlinev = CourseVideos.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline,sess.courseoffering.course,null)
            println("courseoutlinem:"+courseoutlinem)
            println("courseoutlinev:"+courseoutlinev)
            list = new ArrayList()
            list.addAll(courseoutlinev)
            listOfLists.add(list)
            list = new ArrayList()
            list.addAll(courseoutlinem)
            listOfLists.add(list)

            //find assignments related to unit
            session.courseofferingtypelearner=sess.courseoffering.courseofferingtype.name
            println("Course Offering Type::"+session.courseofferingtypelearner)
            def assignment=Assignment.findAllByCourseoutlineAndCourse(sess.courseoutline,sess.courseoffering.course)
            println("Assignments Outline::"+assignment)
            //let us find wheteher these assignments are offered or not
            def assoffering=null
            list = new ArrayList()
            for(Assignment a:assignment)
            {
                assoffering=AssignmentOffering.findAllByAssignmentAndCourseoffering(a,sess.courseoffering)
                for(AssignmentOffering ao:assoffering)
                {
                    println("Offered Assignments:"+ao)
                    list.add(ao)
                    if(ao.assignment.assignmenttype.type=="MCQ")
                    {
                        MCQOptionslist = new ArrayList()
                        def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                        MCQOptionslist.addAll(mcqoptions)
                        MCQOptionslistoflist.add(MCQOptionslist)
                        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                        if(assignmentsubmission==null)
                            assignmentsubmissionlist.add(null)
                        else
                            assignmentsubmissionlist.add(assignmentsubmission)
                    }
                    else
                    {
                        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                        if(assignmentsubmission==null)
                            assignmentsubmissionlist.add(null)
                        else
                            assignmentsubmissionlist.add(assignmentsubmission)
                    }
                }
            }
            listOfLists.add(list)
            listOflistOfLists.add(listOfLists)


            def ctlist=sess.coursetopic
            for(CourseTopic ct:ctlist)
            {
                listOfLists = new ArrayList()
                println("Topic::"+ct.topic)
                list = new ArrayList()
                list.add(ct)
                listOfLists.add(list)
                def coursetopicm = CourseMaterial.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline,sess.courseoffering.course,ct)
                def coursetopicv = CourseVideos.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline,sess.courseoffering.course,ct)
                println("coursetopicm:"+coursetopicm)
                println("coursetopicv:"+coursetopicv)
                list = new ArrayList()
                list.addAll(coursetopicv)
                listOfLists.add(list)
                list = new ArrayList()
                list.addAll(coursetopicm)
                listOfLists.add(list)
                assignment=Assignment.findAllByCourseoutlineAndCourseAndCoursetopic(sess.courseoutline,sess.courseoffering.course,ct)
                println("Assignments Topic::"+assignment)
                assoffering=null
                list = new ArrayList()
                for(Assignment a:assignment)
                {
                    assoffering=AssignmentOffering.findAllByAssignmentAndCourseoffering(a,sess.courseoffering)
                    for(AssignmentOffering ao:assoffering)
                    {
                        println("Offered Assignments:"+ao)
                        list.add(ao)
                        if(ao.assignment.assignmenttype.type=="MCQ")
                        {
                            MCQOptionslist = new ArrayList()
                            def mcqoptions = MCQOptions.findAllByAssignment(ao.assignment)
                            MCQOptionslist.addAll(mcqoptions)
                            MCQOptionslistoflist.add(MCQOptionslist)
                            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                            if(assignmentsubmission==null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        }
                        else
                        {
                            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,ao)
                            if(assignmentsubmission==null)
                                assignmentsubmissionlist.add(null)
                            else
                                assignmentsubmissionlist.add(assignmentsubmission)
                        }
                    }
                }
                listOfLists.add(list)
                listOflistOfLists.add(listOfLists)
            }
            println("listOflistOfLists::"+listOflistOfLists)
            println("MCQOptionslistoflist::"+MCQOptionslistoflist)

        }
        if(params.ot=="o") { //if clicked on outline
            println("outline")
            CourseOutline co = CourseOutline.findById(params.olid)
            def cm = CourseMaterial.findAllByCourseoutline(co)
            def cv = CourseVideos.findAllByCourseoutline(co)

            session.co = co
            session.ot = "o"
            println("CM:" + cm)
            println("CV:" + cv)
            [co:co,cm:cm,cv:cv,ot:"o"]
        }
        if(params.ot=="t")
        {//clicked on topic
            println("topic")
            CourseTopic ct= CourseTopic.findById(params.topicid)
            println("CT:"+ct)
            def cm = CourseMaterial.findAllByCoursetopic(ct)
            def cv = CourseVideos.findAllByCoursetopic(ct)

            session.co = ct
            session.ot = "t"
            println("CM:" + cm)
            println("CV:" + cv)
            [co:ct,cm:cm,cv:cv,ot:"t"]
        }
        def vplist=VideoProperties.list()
        def vp=null
        for(VideoProperties v:vplist)
        {
            vp=v
        }
        Date date=new java.util.Date()
        [listOflistOfLists:listOflistOfLists,vp:vp,date:date,MCQOptionslistoflist:MCQOptionslistoflist,assignmentsubmissionlist:assignmentsubmissionlist]
    }
    def submitassignment()
    {
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering)
        def fp=FolderPath.list()
        CourseOffering coff=assignmentoffering.courseoffering
        //let us find courseoffering learner
        Learner learner=Learner.findByUid(session.uid)
        println("Learner::"+learner)
        CourseOfferingLearner cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(coff,learner)
        println("cofflearner::"+cofflearner)
        String student_answer_file_path=fp[0].path+"/courseoffering/"+coff.id+"/"+cofflearner.id+"/"
        println("student_answer_file_path::"+student_answer_file_path)

        String student_answer_file_name=""
        String fullPath="";
        println("I am in submitassignment....."+params)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        def file = request.getFile("assignment")
        if(file.empty)
        {
            flash.message = "File cannot be empty"
            println("File cannot be empty")
        }
        else
        {
            student_answer_file_name=file.originalFilename
          //  Learner learner=Learner.findByUid(session.uid)
            println("Lerner::"+learner)
            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
            if(assignmentsubmission==null)  //insert assignment information
            {
                assignmentsubmission=new AssignmentSubmission()
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.marks=-1
                assignmentsubmission.teacher_remark=""
                assignmentsubmission.student_answer_text=""
                assignmentsubmission.username=session.uid
                assignmentsubmission.creation_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.creation_ip_address=request.getRemoteAddr()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                assignmentsubmission.learner=learner
                assignmentsubmission.assignmentoffering=assignmentoffering
                assignmentsubmission.assessmentgrade=null
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            else    //update assignment information
            {
                println("assignmentsubmission::"+assignmentsubmission)
                File f=new File(student_answer_file_path+assignmentsubmission.student_answer_file_name)
                if(f.exists())
                {
                    f.delete()
                    println("File Deleted Successfully...")
                }
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            //File uploading code
            fullPath=student_answer_file_path+student_answer_file_name
            file.transferTo(new File(fullPath))
            println("File uploaded successfully..")
        }
        //redirect(controller:"learner",action: "showCourseData",params: [sessionid:session.sessionid,ot:session.ot])
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitmcq()
    {
        println("I am in submitmcq")
        String selectedoption=params.selectedoption
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering+" selectedoption::"+selectedoption)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        Learner learner=Learner.findByUid(session.uid)
        println("Lerner::"+learner)
        MCQOptions mcqoption=MCQOptions.findByAssignmentAndOption_name(assignmentoffering.assignment,selectedoption)
        println("mcqoption::"+mcqoption)
        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
        if(assignmentsubmission==null)  //insert assignment information
        {
            assignmentsubmission=new AssignmentSubmission()
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.marks=-1
            assignmentsubmission.teacher_remark=""
            assignmentsubmission.student_answer_text=""
            assignmentsubmission.username=session.uid
            assignmentsubmission.creation_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.creation_ip_address=request.getRemoteAddr()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            assignmentsubmission.learner=learner
            assignmentsubmission.assignmentoffering=assignmentoffering
            assignmentsubmission.assessmentgrade=null
            assignmentsubmission.mcqoptions=mcqoption
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        else    //update assignment information
        {
            println("assignmentsubmission::"+assignmentsubmission)
            assignmentsubmission.mcqoptions=mcqoption
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitcoursemcq()
    {
        println("I am in submitcoursemcq..."+params)
        String selectedoption=params.selectedoption
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering+" selectedoption::"+selectedoption)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        Learner learner=Learner.findByUid(session.uid)
        println("Lerner::"+learner)
        MCQOptions mcqoption=MCQOptions.findByAssignmentAndOption_name(assignmentoffering.assignment,selectedoption)
        println("mcqoption::"+mcqoption)
        AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
        if(assignmentsubmission==null)  //insert assignment information
        {
            assignmentsubmission=new AssignmentSubmission()
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.marks=-1
            assignmentsubmission.teacher_remark=""
            assignmentsubmission.student_answer_text=""
            assignmentsubmission.username=session.uid
            assignmentsubmission.creation_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.creation_ip_address=request.getRemoteAddr()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            assignmentsubmission.learner=learner
            assignmentsubmission.assignmentoffering=assignmentoffering
            assignmentsubmission.assessmentgrade=null
            assignmentsubmission.mcqoptions=mcqoption
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        else    //update assignment information
        {
            println("assignmentsubmission::"+assignmentsubmission)
            assignmentsubmission.mcqoptions=mcqoption
            assignmentsubmission.submission_date=new java.util.Date()
            assignmentsubmission.updation_date=new java.util.Date()
            assignmentsubmission.updation_ip_address=request.getRemoteAddr()
            SoftwareRemark sr=null
            if(current_date<=due_date)
            {
                sr=SoftwareRemark.findByRemark("On Time")
                assignmentsubmission.softwareremark=sr
            }
            else
            {
                sr=SoftwareRemark.findByRemark("Late")
                assignmentsubmission.softwareremark=sr
            }
            assignmentsubmission.save(failOnError:true,flush:true)
        }
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def submitcourseassignment()
    {
        println("I am in submitcourseassignment....")
        AssignmentOffering assignmentoffering=AssignmentOffering.findById(params.ao)
        println("AssignmentOffering::"+assignmentoffering)
        def fp=FolderPath.list()
        CourseOffering coff=assignmentoffering.courseoffering
        //let us find courseoffering learner
        Learner learner=Learner.findByUid(session.uid)
        println("Learner::"+learner)
        CourseOfferingLearner cofflearner=CourseOfferingLearner.findByCourseofferingAndLearner(coff,learner)
        println("cofflearner::"+cofflearner)
        String student_answer_file_path=fp[0].path+"/courseoffering/"+coff.id+"/"+cofflearner.id+"/"
        println("student_answer_file_path::"+student_answer_file_path)

        String student_answer_file_name=""
        String fullPath="";
        println("I am in submitassignment....."+params)
        Date due_date=assignmentoffering.due_date
        Date current_date=new java.util.Date()
        def file = request.getFile("assignment")
        if(file.empty)
        {
            flash.message = "File cannot be empty"
            println("File cannot be empty")
        }
        else
        {
            student_answer_file_name=file.originalFilename
          //  Learner learner=Learner.findByUid(session.uid)
            println("Lerner::"+learner)
            AssignmentSubmission assignmentsubmission=AssignmentSubmission.findByLearnerAndAssignmentoffering(learner,assignmentoffering)
            if(assignmentsubmission==null)  //insert assignment information
            {
                assignmentsubmission=new AssignmentSubmission()
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.marks=-1
                assignmentsubmission.teacher_remark=""
                assignmentsubmission.student_answer_text=""
                assignmentsubmission.username=session.uid
                assignmentsubmission.creation_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.creation_ip_address=request.getRemoteAddr()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                assignmentsubmission.learner=learner
                assignmentsubmission.assignmentoffering=assignmentoffering
                assignmentsubmission.assessmentgrade=null
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            else    //update assignment information
            {
                println("assignmentsubmission::"+assignmentsubmission)
                File f=new File(student_answer_file_path+assignmentsubmission.student_answer_file_name)
                if(f.exists())
                {
                    f.delete()
                    println("File Deleted Successfully...")
                }
                assignmentsubmission.student_answer_file_path=student_answer_file_path
                assignmentsubmission.student_answer_file_name=student_answer_file_name
                assignmentsubmission.submission_date=new java.util.Date()
                assignmentsubmission.updation_date=new java.util.Date()
                assignmentsubmission.updation_ip_address=request.getRemoteAddr()
                SoftwareRemark sr=null
                if(current_date<=due_date)
                {
                    sr=SoftwareRemark.findByRemark("On Time")
                    assignmentsubmission.softwareremark=sr
                }
                else
                {
                    sr=SoftwareRemark.findByRemark("Late")
                    assignmentsubmission.softwareremark=sr
                    assignmentsubmission.username=session.uid
                }
                assignmentsubmission.save(failOnError:true,flush:true)
            }
            //File uploading code
            fullPath=student_answer_file_path+student_answer_file_name
            file.transferTo(new File(fullPath))
            println("File uploaded successfully..")
        }
        //redirect(controller:"learner",action: "showCourseData",params: [sessionid:session.sessionid,ot:session.ot])
        flash.message="Assignment Submitted Successfully..."
        redirect(controller:"learner",action: "leranerCourse",params: [crsLearner:session.crsLearner])
    }
    def generateQuestionPattern()
    {
        def questionOprtion =[][]
        println("I am in learner question Genrate")
        println("session"+session)
        println("params"+params.coffr)
        def cid = params.coffr
        CourseOffering courseOffer = CourseOffering.findById(cid)
        String uid = session.uid
        Learner student = Learner.findByUid(uid)
        def learnerCourses = CourseOfferingLearner.findAllByLearner(student)
        def questionData = MCQExam.findAllByCourseoffering(courseOffer)
        def questionPattern = MCQBasedExamPattern.findAllByMcqexam(questionData)
        println("questionPattern course "+questionPattern.numberOfQuestion+"type"+questionPattern.difficultylevel.name)
        println("type"+questionPattern.size)
        CourseOfferingLearner studentLearner = CourseOfferingLearner.findByLearnerAndCourseoffering(student,courseOffer)
        println("CourseOfferingLearner"+studentLearner)
        def dfl = DifficultyLevel.list()
        int i = 0
        def mcqData1 = MCQExamSubmission.findByCourseofferinglearnerAndMcqexam(studentLearner,questionData)
        if(!mcqData1){
        for(DifficultyLevel d:dfl)
        {
            println("DifficultyLevel"+d.name)
            ArrayList<Integer> list = new ArrayList<Integer>()
            for (int j =0;j<MCQExam.findAllByDifficultylevelAndCourseofferingAndIsapproved(d, courseOffer,true).size;j++)
            {
                list.add(j)
            }
            Collections.shuffle(list)
            println("list"+list)
            for(int ii =0;ii<questionPattern[i].numberOfQuestion;ii++) {
                def randomQuestion = MCQExam.findAllByDifficultylevelAndCourseofferingAndIsapproved(d, courseOffer,true)
                def q =randomQuestion.get(list.get(ii))
                def quesOption = MCQExamOptions.findAllByMcqexam(q)
                MCQExamSubmission mcqsub = new MCQExamSubmission()
                def mcqpatern = MCQBasedExamPattern.findByCourseOfferingAndDifficultylevel(courseOffer,d)
                mcqsub.weightage = mcqpatern.weightageToEachQuestion
                mcqsub.remark="incomplete"
                mcqsub.updation_date = new java.util.Date()
                mcqsub.creation_date = new java.util.Date()
                mcqsub.username = session.uid
                mcqsub.updation_ip_address = request.getRemoteAddr()
                mcqsub.creation_ip_address = request.getRemoteAddr()
                mcqsub.courseofferinglearner = studentLearner
                mcqsub.mcqexam = q
                mcqsub.save(flush: true,failOnError: true)
            }
            i++
            }
        }
        else
        {
            println("Data not present")
        }
        def mcqData = MCQExamSubmission.findAllByCourseofferinglearner(studentLearner)
        println("test............................."+mcqData)
        for(MCQExamSubmission mcq :mcqData)
        {
            def qo =[]
            println("alankara test"+mcq.mcqexam.question_statement)
            qo.add(mcq.mcqexam.question_statement)
            if(mcq.mcqexam.question_statement_image_path)
            {
                println("path present")
                qo.add(mcq.mcqexam.question_statement_image_path)
            }
            else {
                println("path not present")
                qo.add("ImageNot")
            }
            def options = MCQExamOptions.findAllByMcqexam(mcq.mcqexam)
            println("options"+options.option_value.size)
            if(options.option_value.size==1){
                qo.add(options.option_image_path[0])
                println("image path"+options.option_image_path)
            }
            else {
                for(MCQExamOptions o:options)
                {
                    qo.add(o.option_value)
                }
                println("options" + options.option_value)
            }
            questionOprtion.add(qo)
        }
        println("questionOprtion"+questionOprtion)
        session.mcq = mcqData.id
        session.counter = 0
        println("my mcq"+session.mcq)
        [mcqData:mcqData,qsize:mcqData.size,questionOprtion:questionOprtion as JSON,cid:cid]
    }
    def showChoice()
    {
        println("show choice params "+params.id)
        def mcqData = MCQExam.findById(params.id)
        println("show chice mcqData"+mcqData)
        def mcqOption = MCQExamOptions.findAllByMcqexam(mcqData)
        println("show choice mcqoption data"+mcqOption)
        def mcqsubdata = MCQExamSubmission.findByMcqexam(mcqData)
        [mcqOption:mcqOption,mcqData:mcqData,mcqsubdata:mcqsubdata]
        //def option = MCQExamOptions.findBy
        //render (mcqOption.option_value)
    }
    def renderImage () {
        println("option render")
        File imageFile
        println("imageFile param"+params)
        def questionImage = MCQExamOptions.findById(params.id)
        println("questionImage"+questionImage)
       if(questionImage)
        {
            println("questionImage" + questionImage.option_image_path)
            imageFile = new File(questionImage.option_image_path)
            BufferedImage originalImage = ImageIO.read(imageFile)
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()
            ImageIO.write(originalImage, "jpg", outputStream)
            byte[] imageInByte = outputStream.toByteArray()
            response.setHeader("Content-Length", imageInByte.length.toString())
            response.contentType = "image/jpg"
            response.outputStream << imageInByte
            response.outputStream.flush()
            response.outputStream.close()
            return
        }
        else {
            flash.message="Image not found!!"
            println("image not found")
            render("Error")

        }
        render("Ok")
    }
    def generateQuestion()
    {
        println("i am in genrate question"+session.uid)
        println("final test params"+params)
        def cid = params.cid
        CourseOffering courseOffer = CourseOffering.findById(cid)
        println("courseOffer"+courseOffer)
        println("session counter"+session.counter)
        def list =session.mcq
        String uid = session.uid
        Learner student = Learner.findByUid(uid)
        println("student"+student)
        def studentLearner = CourseOfferingLearner.findByLearnerAndCourseoffering(student,courseOffer)
        println("CourseOfferingLearner alan"+studentLearner)
        def size = MCQExamSubmission.findAllByCourseofferinglearner(studentLearner)
        println("size ......................"+size)
        println("i am generateQuestion"+size.size)
        def mcqData = MCQExamSubmission.findByCourseofferinglearnerAndId(studentLearner,list[session.counter])
        println("testing......................"+mcqData)
        println("Course offering::"+mcqData.courseofferinglearner.courseoffering.id)
        println("Difficulty level::"+mcqData.mcqexam.difficultylevel.id)
        println("mcqexam::"+mcqData.mcqexam.id)
        def mcqpatern = MCQBasedExamPattern.findByCourseOfferingAndDifficultylevel(mcqData.courseofferinglearner.courseoffering,mcqData.mcqexam.difficultylevel)
        println(" weightage::"+mcqpatern.weightageToEachQuestion)
        session.counter +=1
        println(mcqData.mcqexam)
        println("params"+params)
        [mcqData:mcqData,size:size.size,cid:cid,mcqpatern:mcqpatern]
    }
    def generateQues()
    {
        println("params qqqqqqqqqqqqq"+params)
        def list =session.mcq
        String uid = session.uid
        def cid = params.cid
        CourseOffering courseOffer = CourseOffering.findById(cid)
        Learner student = Learner.findByUid(uid)
        session.counter = Integer.parseInt(params.sessionid)
        def studentLearner = CourseOfferingLearner.findByLearnerAndCourseoffering(student,courseOffer)
        def size = MCQExamSubmission.findAllByCourseofferinglearner(studentLearner)
        def mcqData = MCQExamSubmission.findByCourseofferinglearnerAndId(studentLearner,list[Integer.parseInt(params.sessionid)-1])
        def mcqpatern = MCQBasedExamPattern.findByCourseOfferingAndDifficultylevel(mcqData.courseofferinglearner.courseoffering,mcqData.mcqexam.difficultylevel)
        println(" weightage::"+mcqpatern.weightageToEachQuestion)
        int qno = Integer.parseInt(params.sessionid)
        println("testing...................qno"+mcqData.mcqexamoption)
        [mcqData:mcqData,size:size.size,qno:qno,cid:cid,mcqpatern:mcqpatern]
    }
    def saveQuestionData()
    {
        println("save question data params"+params)

        //MCQExam mcq = MCQExam.findById(params.qid)
        //println("mcq"+mcq)
        MCQExamSubmission mcqesub = MCQExamSubmission.findById(params.qid)
        println("Question statemwnr"+mcqesub)
        def examQeues = mcqesub.mcqexam
        String str = params.opid
        def opList = str.replaceAll(",","") as List
        def mcq = mcqesub.mcqexam
        println()
        println(opList.size())
        if(opList.size()>0) {
            for (int i = 0; i < opList.sort().size(); i++) {
                def op = MCQExamOptions.findByOption_nameAndMcqexam(opList[i], mcq)
                println("OP"+op)
                println("save question Data " + op.option_name + " value" + op.option_value)
                mcqesub.addToMcqexamoption(op)
            }
            if (!(params.qstatus).equals("false")) {
                mcqesub.remark = "markAsReview"
            } else {
                mcqesub.remark = "Completed";
            }
            mcqesub.username = session.uid
            mcqesub.updation_ip_address = request.getRemoteAddr()
            mcqesub.updation_date =  new java.util.Date()
            mcqesub.save(flush:true,failOnError: true)
        }

            println("i am in genrate question" + session)
            def crslearn = CourseOfferingLearner.findById(session.crsLearner)
            println("crslearn" + crslearn)
            def courseOffer = crslearn.courseoffering
            println("alankar................................courseOffer" + courseOffer)
            def list = session.mcq
            String uid = session.uid
            Learner student = Learner.findByUid(uid)
            println("student" + student)
            def studentLearner = CourseOfferingLearner.findByLearnerAndCourseoffering(student, courseOffer)
            println("CourseOfferingLearner alan" + studentLearner)
            def size = MCQExamSubmission.findAllByCourseofferinglearner(studentLearner)
            println("size ......................" + size)
            println("i am generateQuestion" + size.size)
            println("session.counter"+session.counter+"..............."+size.size)
        if(session.counter<size.size) {
            def mcqData = MCQExamSubmission.findByCourseofferinglearnerAndId(studentLearner, list[session.counter])
            println("testing......................" + mcqData)
            println("Course offering::" + mcqData.courseofferinglearner.courseoffering.id)
            println("Difficulty level::" + mcqData.mcqexam.difficultylevel.id)
            println("mcqexam::" + mcqData.mcqexam.id)
            def mcqpatern = MCQBasedExamPattern.findByCourseOfferingAndDifficultylevel(mcqData.courseofferinglearner.courseoffering, mcqData.mcqexam.difficultylevel)
            println(" weightage::" + mcqpatern.weightageToEachQuestion)
            session.counter += 1
            println(mcqData.mcqexam)
            println("params" + params)
            [mcqData: mcqData, size: size.size, cid: courseOffer.id, mcqpatern: mcqpatern]
        }
        else
        {
            render("ok")
        }
    }
}
