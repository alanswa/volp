package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseController {

    CourseService courseService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseService.list(params), model:[courseCount: courseService.count()]
    }

    def show(Long id) {
        respond courseService.get(id)
    }

    def create() {
        respond new Course(params)
    }

    def save(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*' { respond course, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseService.get(id)
    }

    def update(Course course) {
        if (course == null) {
            notFound()
            return
        }

        try {
            courseService.save(course)
        } catch (ValidationException e) {
            respond course.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'course.label', default: 'Course'), course.id])
                redirect course
            }
            '*'{ respond course, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'course.label', default: 'Course'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'course.label', default: 'Course'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    def showOutLineMaterial()
    {
        println("in showOutLineMaterial params:"+params)
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
        else{//clicked on topic
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
    }
    def editCourseMaterial(){
        println("in editCourseMaterial params:"+params)
        println("in editCourseMaterial session:"+session)
        CourseMaterial cm = CourseMaterial.findById(params.mid)
        [cm:cm]
    }
    def processEditMaterial(){
        println("in processEditMaterial params:"+params)
        println("in processEditMaterial session:"+session)
        CourseMaterial cm1 = CourseMaterial.findById(params.cmid)
        println("CM:"+cm1)
        cm1.material_link = params.ml
        cm1.meta_data = params.md
        cm1.description = params.desp
        cm1.save(flush: true,failOnError:true)
        def co =session.co


        if(session.ot=="o"){
            println("processEditMaterial OL")
            def cm = CourseMaterial.findAllByCourseoutline(co)
            def cv = CourseVideos.findAllByCourseoutline(co)
            [co:co,cm:cm,cv:cv,ot:"o"]
        }
        else if(session.ot=="t"){
            println("processEditMaterial T")
            def cm = CourseMaterial.findAllByCoursetopic(co)
            def cv = CourseVideos.findAllByCoursetopic(co)
            [co:co,cm:cm,cv:cv,ot:"t"]
        }
    }
    def editCourseVdo(){
        println("in editCourseVdo params:"+params)
        println("in editCourseVdo session:"+session)
        CourseVideos cv = CourseVideos.findById(params.mid)
        [cv:cv]
    }
    def processEditVdo(){
        println("in processEditVdo params:"+params)
        println("in processEditVdo session:"+session)
        CourseVideos cv1 = CourseVideos.findById(params.cvid)
        println("CV1:"+cv1)
        cv1.meta_data = params.md
        cv1.description = params.desp
        cv1.duration_in_minutes = Double.parseDouble(params.vd)
        cv1.video_link = params.vl
        cv1.save(flush: true,failOnError:true)
        def co =session.co
        if(session.ot=="o"){
            println("processEditMaterial OL")
            def cm = CourseMaterial.findAllByCourseoutline(co)
            def cv = CourseVideos.findAllByCourseoutline(co)
            [co:co,cm:cm,cv:cv,ot:"o"]
        }
        else if(session.ot=="t"){
            println("processEditMaterial T")
            def cm = CourseMaterial.findAllByCoursetopic(co)
            def cv = CourseVideos.findAllByCoursetopic(co)
            [co:co,cm:cm,cv:cv,ot:"t"]
        }
    }
    def getOffering(){ //call from instructor/instructor
        println("in getOffering params:"+params)
        Course crs = Course.findById(params.courseid)
        def coffrAct = CourseOffering.findAllByCourseAndIsActive(crs,true)
        def coffrArc = CourseOffering.findAllByCourseAndIsActive(crs,false)
        ArrayList col = new ArrayList()
        for(CourseOffering co:coffrAct) {
            def x = CourseOfferingLearner.findAllByCourseoffering(co)
            if(x.size()>0)
                col.addAll(x)
        }
        [coffrAct:coffrAct.size(),coffrArc:coffrArc.size(),col:col.size()]
    }
}
