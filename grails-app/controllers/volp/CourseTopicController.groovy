package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseTopicController {

    CourseTopicService courseTopicService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseTopicService.list(params), model:[courseTopicCount: courseTopicService.count()]
    }

    def show(Long id) {
        respond courseTopicService.get(id)
    }

    def create() {
        respond new CourseTopic(params)
    }

    def save(CourseTopic courseTopic) {
        if (courseTopic == null) {
            notFound()
            return
        }

        try {
            courseTopicService.save(courseTopic)
        } catch (ValidationException e) {
            respond courseTopic.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseTopic.label', default: 'CourseTopic'), courseTopic.id])
                redirect courseTopic
            }
            '*' { respond courseTopic, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseTopicService.get(id)
    }

    def update(CourseTopic courseTopic) {
        if (courseTopic == null) {
            notFound()
            return
        }

        try {
            courseTopicService.save(courseTopic)
        } catch (ValidationException e) {
            respond courseTopic.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseTopic.label', default: 'CourseTopic'), courseTopic.id])
                redirect courseTopic
            }
            '*'{ respond courseTopic, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseTopicService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseTopic.label', default: 'CourseTopic'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseTopic.label', default: 'CourseTopic'), params.id])
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
        for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)
        [courselist:courselist]
    }
    def listcourseoutlines()
    {
        println("I am in listcourseoutlines:"+params)
        println("I am in listcourseoutlines:"+session)
        String coursecodecoursename=params.coursecodecoursename
        def crs=null
        if(coursecodecoursename.equals(""))
        {
            coursecodecoursename = session.coursecodecoursename
            crs=coursecodecoursename.split(":")
        }
        else
            crs=coursecodecoursename.split(":")
        String coursecode=crs[0]
        String coursename=crs[1]
        String email=session.uid
        session.coursecode=coursecode
        session.coursename=coursename
        session.emai=email
        Instructor inst=Instructor.findByUid(email)
        println("ok..."+email+" "+coursecode+" "+coursename+" "+inst)
        //Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        Course course=Course.findById(session.cid)
        println("course::"+course)
        def courseoutlinelist=CourseOutline.findAllByCourse(course)
        courseoutlinelist.sort{it.outline_number}
        println(courseoutlinelist)
        [courseoutlinelist:courseoutlinelist,course:course]
    }
    def addeditdeletecoursetopics(){
        println("in addeditdeletecoursetopics"+params.id)
        CourseOutline courseoutline=CourseOutline.findById(params.id)
        session.courseoutline=courseoutline
        ArrayList<String> coursetopiclist=new ArrayList<String>();
        def coursetopic=CourseTopic.findAllByCourseoutline(courseoutline)
        for(CourseTopic ct:coursetopic)
        {
            coursetopiclist.add(ct)
        }
        coursetopiclist.sort{it.topic_number}
        [courseoutline:courseoutline,coursetopiclist:coursetopiclist]
    }
    def savecoursetopic()
    {
        println("I am in savecoursetopic..."+params)
        String email=session.uid
        CourseOutline courseoutline=session.courseoutline
        int outline_number=courseoutline.outline_number
        String topic_number=params.tno
        topic_number = topic_number.replaceAll("\\s+","")   //remove all whitespaces
        String topic=params.tp
        println(courseoutline)
        println(outline_number+"  "+topic_number+"  "+topic+" "+courseoutline.id)
        CourseTopic ct=new CourseTopic()
        ct.topic_number=Integer.parseInt(topic_number)
        ct.topic=topic
        ct.courseoutline=courseoutline
        ct.username=email
        ct.creation_date=new java.util.Date()
        ct.updation_date=new java.util.Date()
        ct.creation_ip_address=request.getRemoteAddr()
        ct.updation_ip_address=request.getRemoteAddr()
        ct.save(failOnError:true,flush:true)
        redirect(action:"addeditdeletecoursetopics",params:[id:courseoutline.id])
    }
    def updatecoursetopic()
    {
        println("I am in updatecoursetopic.."+params)
        CourseTopic coursetopic=CourseTopic.findById(params.id)
        println("updatecoursetopic:"+coursetopic)
        session.coursetopic=coursetopic
        session.coursetopicid=params.id
        [coursetopic:coursetopic]
    }
    def saveupdatecoursetopic()
    {
        println("I am in saveupdatecoursetopic..")
        CourseTopic coursetopic=CourseTopic.findById(session.coursetopicid)
        println("saveupdatecoursetopic:"+coursetopic)
        String topic_number=params.tno
        topic_number = topic_number.replaceAll("\\s+","")
        String topic=params.tp
        coursetopic.topic_number=Integer.parseInt(topic_number)
        coursetopic.topic=topic
        coursetopic.updation_date=new java.util.Date()
        coursetopic.updation_ip_address=request.getRemoteAddr()
        coursetopic.username=session.uid
        coursetopic.save(failOnError:true,flush:true)
        redirect(action:"addeditdeletecoursetopics",params:[id:coursetopic.courseoutline.id])
    }
    def deletecoursetopic()
    {
        println("I am in deletecoursetopic.."+params)
        CourseTopic coursetopic=CourseTopic.findById(params.id)
        coursetopic.delete(failOnError:true,flush:true)
        redirect(action:"addeditdeletecoursetopics",params:[id:coursetopic.courseoutline.id])
    }
}
