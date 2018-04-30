package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseMaterialController {

    CourseMaterialService courseMaterialService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseMaterialService.list(params), model:[courseMaterialCount: courseMaterialService.count()]
    }

    def show(Long id) {
        respond courseMaterialService.get(id)
    }

    def create() {
        respond new CourseMaterial(params)
    }

    def save(CourseMaterial courseMaterial) {
        if (courseMaterial == null) {
            notFound()
            return
        }

        try {
            courseMaterialService.save(courseMaterial)
        } catch (ValidationException e) {
            respond courseMaterial.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseMaterial.label', default: 'CourseMaterial'), courseMaterial.id])
                redirect courseMaterial
            }
            '*' { respond courseMaterial, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseMaterialService.get(id)
    }

    def update(CourseMaterial courseMaterial) {
        if (courseMaterial == null) {
            notFound()
            return
        }

        try {
            courseMaterialService.save(courseMaterial)
        } catch (ValidationException e) {
            respond courseMaterial.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseMaterial.label', default: 'CourseMaterial'), courseMaterial.id])
                redirect courseMaterial
            }
            '*'{ respond courseMaterial, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseMaterialService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseMaterial.label', default: 'CourseMaterial'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseMaterial.label', default: 'CourseMaterial'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def selectcourseanduploadcategory()
    {
        println("I am in selectcourseanduploadcategory...."+session)
        //from addedit
        int row=0
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List templist= new ArrayList<String>()
        templist.add("Course Name")
        templist.add("Unit")
        templist.add("Topic")
        templist.add("File Name")
        templist.add("File Extension")
        templist.add("File Link")
        listOfLists.add(templist)
        row++
        //println("session.cid.course_name:"+session.course.course_name)
        Course course = Course.findById(session.cid)
        //Course course=session.course//Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        def courseoutlinelist=CourseOutline.findAllByCourse(course)
        courseoutlinelist.sort{it.outline_number}
        session.course=course
        session.courseoutlinelist=courseoutlinelist
        println("Course:"+course)
        println("courseoutlinelist:"+courseoutlinelist)
        def cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,null,null)
        for(CourseMaterial c:cm)
        {
            templist= new ArrayList<String>()
            templist.add(c.course.course_name)
            templist.add("")
            templist.add("")
            templist.add(c.material_name)
            templist.add(c.fileformat.extension)
            templist.add(c.material_link)
            listOfLists.add(templist)
            row++
        }
        for(CourseOutline courseoutline:courseoutlinelist)
        {
            cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,null)
            for(CourseMaterial c:cm)
            {
                templist= new ArrayList<String>()
                templist.add(c.course.course_name)
                templist.add(c.courseoutline.outline)
                templist.add("")
                templist.add(c.material_name)
                templist.add(c.fileformat.extension)
                templist.add(c.material_link)
                listOfLists.add(templist)
                row++
            }
            def coursetopiclist=CourseTopic.findAllByCourseoutline(courseoutline)
            for(CourseTopic coursetopic:coursetopiclist)
            {
                // println(course.course_name+"::"+courseoutline.outline+"::"+coursetopic.topic)
                cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,coursetopic)
                for(CourseMaterial c:cm)
                {
                    templist= new ArrayList<String>()
                    templist.add(c.course.course_name)
                    templist.add(c.courseoutline.outline)
                    templist.add(c.coursetopic.topic)
                    templist.add(c.material_name)
                    templist.add(c.fileformat.extension)
                    templist.add(c.material_link)
                    listOfLists.add(templist)
                    row++
                }
            }
        }
        for(int i=0;i<row;i++)
            println(listOfLists[i])
        session.row=row
        session.listOfLists=listOfLists

        //end addedit
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        println("Instructor:"+inst)
        def courses=Course.findAllByCourseowner(inst)
        ArrayList<String> courselist=new ArrayList<String>();
        courselist.add("Select Course")

       /* for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)*/
        ArrayList<String> uploadcategorylist=new ArrayList<String>();
        uploadcategorylist.add("Select Upload Category")
        uploadcategorylist.add("Course")
        uploadcategorylist.add("Unit")
        uploadcategorylist.add("Topic")
        println(uploadcategorylist)

        [uploadcategorylist:uploadcategorylist,row:row,listOfLists:listOfLists]
    }
    def processcoursematerial()
    {
        println("I am in processcoursematerial..."+params)
        String coursecodecoursename=params.coursecodecoursename
        String uploadcategory=params.uploadcategory
        session.uploadcategory=uploadcategory
        /*def crs=null
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
        session.email=email
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)*/
        Course course = session.course
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
        FileType ft=FileType.findByType("Text")
        println("file type::"+ft)
        def materialextensionlist=FileFormat.findAllByFiletype(ft)
        println("materialextensionlist::"+materialextensionlist)
        [courseoutlinelist:courseoutlinelist,materialextensionlist:materialextensionlist]
    }
    def furtherprocesscoursematerial()
    {
        println("I am in furtherprocesscoursematerial..."+params)
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
        println("topic list::"+coursetopiclist)
        FileType ft=FileType.findByType("Text")
        println("file type::"+ft)
        def materialextensionlist=FileFormat.findAllByFiletype(ft)
        println("materialextensionlist::"+materialextensionlist)
        [coursetopiclist:coursetopiclist,materialextensionlist:materialextensionlist]
    }
    def savecoursematerial()
    {
        println("I am in savecoursematerial.."+params)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String courseoutline="",coursetopic=""
        String course_material_link=params.course_material_link
        String material_description=params.material_description
        String course_material_name=params.course_material_name
        String material_extension=params.material_extension
        String material_keywords=params.material_keywords
        FileType ft=FileType.findByType("Text")
        FileFormat ff=FileFormat.findByExtensionAndFiletype(material_extension,ft)

        Course course=session.course
        CourseOutline courseoutlineobj=null
        CourseTopic coursetopicobj=null
        println("course:"+course)
        println("course_material_link:"+course_material_link)
        println("material_description:"+material_description)

        CourseMaterial cm=new CourseMaterial()
        cm.material_name=course_material_name
        cm.material_path=null
        cm.material_link=course_material_link
        cm.description=material_description
        cm.fileformat=ff
        cm.meta_data=material_keywords

        cm.username=session.uid
        cm.creation_date=new java.util.Date()
        cm.updation_date=new java.util.Date()
        cm.creation_ip_address=request.getRemoteAddr()
        cm.updation_ip_address=request.getRemoteAddr()
        cm.course=course

        if(session.uploadcategory.toString().equals("Course"))
        {
            println("I am in Course...")
            cm.courseoutline=null
            cm.coursetopic=null
        }
        if(session.uploadcategory.toString().equals("Unit"))
        {
            println("I am in Unit...")
            courseoutline=params.courseoutline
            println("Course Outline::"+courseoutline)
            courseoutlineobj=CourseOutline.findByOutlineAndCourse(courseoutline,course)
            cm.courseoutline=courseoutlineobj
            cm.coursetopic=null
        }
        if(session.uploadcategory.toString().equals("Topic"))
        {
            println("I am in Topic...")
            courseoutline=params.courseoutline
            courseoutlineobj=CourseOutline.findByOutlineAndCourse(courseoutline,course)
            cm.courseoutline=courseoutlineobj
            println("Course Topic:"+coursetopic)
            coursetopic=params.coursetopic
            coursetopicobj=CourseTopic.findByTopicAndCourseoutline(coursetopic,courseoutlineobj)
            cm.coursetopic=coursetopicobj
        }
        cm.save(failOnError:true,flush:true)
        // String coursecodecoursename=coursecode+":"+coursename
        // redirect(action: "addeditcoursematerial",params: [coursecodecoursename:coursecodecoursename])
        redirect(action: "selectcourseanduploadcategory")
    }
    def addeditcoursematerial()
    {
        println("I am in addeditcoursematerial...")
        int row=0
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List templist= new ArrayList<String>()
        templist.add("Course Name")
        templist.add("Unit")
        templist.add("Topic")
        templist.add("File Name")
        templist.add("File Extension")
        templist.add("File Link")
        listOfLists.add(templist)
        row++
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
        session.email=email
        Instructor inst=Instructor.findByUid(email)
        Course course=Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        def courseoutlinelist=CourseOutline.findAllByCourse(course)
        courseoutlinelist.sort{it.outline_number}
        session.course=course
        session.courseoutlinelist=courseoutlinelist
        println("Course:"+course)
        println("courseoutlinelist:"+courseoutlinelist)
        def cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,null,null)
        for(CourseMaterial c:cm)
        {
            templist= new ArrayList<String>()
            templist.add(c.course.course_name)
            templist.add("")
            templist.add("")
            templist.add(c.material_name)
            templist.add(c.fileformat.extension)
            templist.add(c.material_link)
            listOfLists.add(templist)
            row++
        }
        for(CourseOutline courseoutline:courseoutlinelist)
        {
            cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,null)
            for(CourseMaterial c:cm)
            {
                templist= new ArrayList<String>()
                templist.add(c.course.course_name)
                templist.add(c.courseoutline.outline)
                templist.add("")
                templist.add(c.material_name)
                templist.add(c.fileformat.extension)
                templist.add(c.material_link)
                listOfLists.add(templist)
                row++
            }
            def coursetopiclist=CourseTopic.findAllByCourseoutline(courseoutline)
            for(CourseTopic coursetopic:coursetopiclist)
            {
                // println(course.course_name+"::"+courseoutline.outline+"::"+coursetopic.topic)
                cm=CourseMaterial.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,coursetopic)
                for(CourseMaterial c:cm)
                {
                    templist= new ArrayList<String>()
                    templist.add(c.course.course_name)
                    templist.add(c.courseoutline.outline)
                    templist.add(c.coursetopic.topic)
                    templist.add(c.material_name)
                    templist.add(c.fileformat.extension)
                    templist.add(c.material_link)
                    listOfLists.add(templist)
                    row++
                }
            }
        }
        for(int i=0;i<row;i++)
            println(listOfLists[i])
        session.row=row
        session.listOfLists=listOfLists
        [row:row,listOfLists:listOfLists]
    }
    def deletecoursematerial()
    {
        println("I am in deletecoursematerial:"+params)
        int selectedrow=Integer.parseInt(params.id)
        println("selectedrow::"+selectedrow)
        def listOfLists=session.listOfLists
        int row=session.row
        // println("Number of rows::"+row)
        // println("list::"+listOfLists)
        String coursename=listOfLists[selectedrow][0]
        String outline=listOfLists[selectedrow][1]
        String topic=listOfLists[selectedrow][2]
        String material_name=listOfLists[selectedrow][3]
        String material_extension=listOfLists[selectedrow][4]
        String material_link=listOfLists[selectedrow][5]

        Course course=session.course
        CourseOutline courseoutlineobj=CourseOutline.findByCourseAndOutline(course,outline)
        CourseTopic coursetopicobj=CourseTopic.findByCourseoutlineAndTopic(courseoutlineobj,topic)
        FileType ft=FileType.findByType("Text")
        println("file type::"+ft)
        FileFormat ff=FileFormat.findByExtensionAndFiletype(material_extension,ft)
        println("file format:"+ff)
        CourseMaterial cm=CourseMaterial.findByCourseAndCourseoutlineAndCoursetopicAndMaterial_nameAndFileformatAndMaterial_link(course,courseoutlineobj,coursetopicobj,material_name,ff,material_link)
        cm.delete(failOnError:true,flush:true)
        // String coursecodecoursename=course.course_code+":"+coursename
        // redirect(action: "addeditcoursematerial",params: [coursecodecoursename:coursecodecoursename])
        redirect(action: "selectcourseanduploadcategory")
    }
}
