package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class CourseVideosController {

    CourseVideosService courseVideosService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond courseVideosService.list(params), model:[courseVideosCount: courseVideosService.count()]
    }

    def show(Long id) {
        respond courseVideosService.get(id)
    }

    def create() {
        respond new CourseVideos(params)
    }

    def save(CourseVideos courseVideos) {
        if (courseVideos == null) {
            notFound()
            return
        }

        try {
            courseVideosService.save(courseVideos)
        } catch (ValidationException e) {
            respond courseVideos.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'courseVideos.label', default: 'CourseVideos'), courseVideos.id])
                redirect courseVideos
            }
            '*' { respond courseVideos, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond courseVideosService.get(id)
    }

    def update(CourseVideos courseVideos) {
        if (courseVideos == null) {
            notFound()
            return
        }

        try {
            courseVideosService.save(courseVideos)
        } catch (ValidationException e) {
            respond courseVideos.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'courseVideos.label', default: 'CourseVideos'), courseVideos.id])
                redirect courseVideos
            }
            '*'{ respond courseVideos, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        courseVideosService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'courseVideos.label', default: 'CourseVideos'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'courseVideos.label', default: 'CourseVideos'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
def selectcourseanduploadcategory()
    {
        println("I am in selectcourseanduploadcategory....session"+session)
        /* commented by vikas
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        println("Instructor:"+inst)
        def courses=Course.findAllByCourseowner(inst)
        ArrayList<String> courselist=new ArrayList<String>();
        courselist.add("Select Course")
        for(Course c:courses)
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

        //copied form edit by vikas
        println("I am in addeditcoursevideo...")
        int row=0
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List templist= new ArrayList<String>()
        templist.add("Course Name")
        templist.add("Unit")
        templist.add("Topic")
        templist.add("Video Name")
        templist.add("Video Extension")
        templist.add("Video Duration in mins")
        templist.add("Video Link")
        listOfLists.add(templist)
        row++
       /* String coursecodecoursename=params.coursecodecoursename
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
        Instructor inst=Instructor.findByUid(email)*/

        Course course=Course.findById(session.cid)//Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
        def courseoutlinelist=CourseOutline.findAllByCourse(course)
        courseoutlinelist.sort{it.outline_number}
        session.course=course
        session.courseoutlinelist=courseoutlinelist
        println("Course:"+course)
        println("courseoutlinelist:"+courseoutlinelist)
        def cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,null,null)
        for(CourseVideos c:cm)
        {
            templist= new ArrayList<String>()
            templist.add(c.course.course_name)
            templist.add("")
            templist.add("")
            templist.add(c.video_name)
            templist.add(c.fileformat.extension)
            templist.add(c.duration_in_minutes)
            templist.add(c.video_link)
            listOfLists.add(templist)
            row++
        }
        for(CourseOutline courseoutline:courseoutlinelist)
        {
            cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,null)
            for(CourseVideos c:cm)
            {
                templist= new ArrayList<String>()
                templist.add(c.course.course_name)
                templist.add(c.courseoutline.outline)
                templist.add("")
                templist.add(c.video_name)
                templist.add(c.fileformat.extension)
                templist.add(c.duration_in_minutes)
                templist.add(c.video_link)
                listOfLists.add(templist)
                row++
            }
            def coursetopiclist=CourseTopic.findAllByCourseoutline(courseoutline)
            for(CourseTopic coursetopic:coursetopiclist)
            {
                // println(course.course_name+"::"+courseoutline.outline+"::"+coursetopic.topic)
                cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,coursetopic)
                for(CourseVideos c:cm)
                {
                    templist= new ArrayList<String>()
                    templist.add(c.course.course_name)
                    templist.add(c.courseoutline.outline)
                    templist.add(c.coursetopic.topic)
                    templist.add(c.video_name)
                    templist.add(c.fileformat.extension)
                    templist.add(c.duration_in_minutes)
                    templist.add(c.video_link)
                    listOfLists.add(templist)
                    row++
                }
            }
        }
        for(int i=0;i<row;i++)
            println(listOfLists[i])
        session.row=row
        session.listOfLists=listOfLists
        //[row:row,listOfLists:listOfLists]
        //[courselist:courselist,uploadcategorylist:uploadcategorylist]
        [uploadcategorylist:uploadcategorylist,row:row,listOfLists:listOfLists]
    }
    def processcoursevideo()
    {
        println("I am in processcoursevideo..."+params)
        String uploadcategory=params.uploadcategory
        session.uploadcategory=uploadcategory
        /* commented by Vikas
        String coursecodecoursename=params.coursecodecoursename
        String uploadcategory=params.uploadcategory
        session.uploadcategory=uploadcategory
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
        Instructor inst=Instructor.findByUid(email)*/
        Course course=Course.findById(session.cid)//Course.findByCourse_codeAndCourse_nameAndCourseowner(coursecode,coursename,inst)
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
        FileType ft=FileType.findByType("Video")
        println("file type::"+ft)
        def videoextensionlist=FileFormat.findAllByFiletype(ft)
        println("videoextensionlist::"+videoextensionlist)
        [courseoutlinelist:courseoutlinelist,videoextensionlist:videoextensionlist]
    }
    def furtherprocesscoursevideo()
    {
        println("I am in furtherprocesscoursevideo..."+params)
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
        FileType ft=FileType.findByType("Video")
        println("file type::"+ft)
        def videoextensionlist=FileFormat.findAllByFiletype(ft)
        println("videoextensionlist::"+videoextensionlist)
        [coursetopiclist:coursetopiclist,videoextensionlist:videoextensionlist]
    }
    def savecoursevideo()
    {
        println("I am in savecoursevideo.."+params)
        String coursecode=session.coursecode
        String coursename=session.coursename
        String courseoutline="",coursetopic=""
        String course_video_link=params.course_video_link
        String video_description=params.course_video_description
        String course_video_name=params.course_video_name
        String course_video_duration=params.course_video_duration
        String course_video_extension=params.course_video_extension
        String course_video_keywords=params.course_video_keywords

        Course course=session.course
        CourseOutline courseoutlineobj=null
        CourseTopic coursetopicobj=null
        println("course:"+course)
        println("course_video_link:"+course_video_link)
        println("video_description:"+video_description)
        println("course_video_name:"+course_video_name)
        println("course_video_duration:"+course_video_duration)
        println("course_video_extension:"+course_video_extension)
        println("course_video_keywords:"+course_video_keywords)
        FileType ft=FileType.findByType("Video")
        FileFormat ff=FileFormat.findByExtensionAndFiletype(course_video_extension,ft)

        CourseVideos cm=new CourseVideos()
        cm.video_name=course_video_name
        cm.video_path=null
        cm.video_link=course_video_link
        cm.description=video_description
        course_video_duration = course_video_duration.replaceAll("\\s+","")
        cm.duration_in_minutes=Double.parseDouble(course_video_duration)
        cm.fileformat=ff
        cm.meta_data=course_video_keywords

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
    def addeditcoursevideo()
    {
        println("I am in addeditcoursevideo...")
        int row=0
        List<List<String>> listOfLists = new ArrayList<List<String>>();
        List templist= new ArrayList<String>()
        templist.add("Course Name")
        templist.add("Unit")
        templist.add("Topic")
        templist.add("Video Name")
        templist.add("Video Extension")
        templist.add("Video Duration in mins")
        templist.add("Video Link")
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
        def cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,null,null)
        for(CourseVideos c:cm)
        {
            templist= new ArrayList<String>()
            templist.add(c.course.course_name)
            templist.add("")
            templist.add("")
            templist.add(c.video_name)
            templist.add(c.fileformat.extension)
            templist.add(c.duration_in_minutes)
            templist.add(c.video_link)
            listOfLists.add(templist)
            row++
        }
        for(CourseOutline courseoutline:courseoutlinelist)
        {
            cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,null)
            for(CourseVideos c:cm)
            {
                templist= new ArrayList<String>()
                templist.add(c.course.course_name)
                templist.add(c.courseoutline.outline)
                templist.add("")
                templist.add(c.video_name)
                templist.add(c.fileformat.extension)
                templist.add(c.duration_in_minutes)
                templist.add(c.video_link)
                listOfLists.add(templist)
                row++
            }
            def coursetopiclist=CourseTopic.findAllByCourseoutline(courseoutline)
            for(CourseTopic coursetopic:coursetopiclist)
            {
               // println(course.course_name+"::"+courseoutline.outline+"::"+coursetopic.topic)
                cm=CourseVideos.findAllByCourseAndCourseoutlineAndCoursetopic(course,courseoutline,coursetopic)
                for(CourseVideos c:cm)
                {
                    templist= new ArrayList<String>()
                    templist.add(c.course.course_name)
                    templist.add(c.courseoutline.outline)
                    templist.add(c.coursetopic.topic)
                    templist.add(c.video_name)
                    templist.add(c.fileformat.extension)
                    templist.add(c.duration_in_minutes)
                    templist.add(c.video_link)
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
    def deletecoursevideo()
    {
        println("I am in deletecoursevideo:"+params)
        int selectedrow=Integer.parseInt(params.id)
        println("selectedrow::"+selectedrow)
        def listOfLists=session.listOfLists
        int row=session.row
       // println("Number of rows::"+row)
       // println("list::"+listOfLists)
        String coursename=listOfLists[selectedrow][0]
        String outline=listOfLists[selectedrow][1]
        String topic=listOfLists[selectedrow][2]
        String video_name=listOfLists[selectedrow][3]
        String video_extension=listOfLists[selectedrow][4]
        String video_duration=listOfLists[selectedrow][5]
        String video_link=listOfLists[selectedrow][6]
        FileType ft=FileType.findByType("Video")
        FileFormat ff=FileFormat.findByExtensionAndFiletype(video_extension,ft)

        Course course=session.course
        CourseOutline courseoutlineobj=CourseOutline.findByCourseAndOutline(course,outline)
        CourseTopic coursetopicobj=CourseTopic.findByCourseoutlineAndTopic(courseoutlineobj,topic)
        CourseVideos cm=CourseVideos.findByCourseAndCourseoutlineAndCoursetopicAndVideo_nameAndVideo_linkAndDuration_in_minutesAndFileformat(course,courseoutlineobj,coursetopicobj,video_name,video_link,video_duration,ff)
        cm.delete(failOnError:true,flush:true)
       // String coursecodecoursename=course.course_code+":"+coursename
       // redirect(action: "addeditcoursematerial",params: [coursecodecoursename:coursecodecoursename])
       // redirect(action: "selectcourseanduploadcategory")

        //calling again
        println("I am in selectcourseanduploadcategory....")
        String email=session.uid
        Instructor inst=Instructor.findByUid(email)
        println("Instructor:"+inst)
        def courses=Course.findAllByCourseowner(inst)
        ArrayList<String> courselist=new ArrayList<String>();
        courselist.add("Select Course")
        for(Course c:courses)
        {
            courselist.add(c.course_code+":"+c.course_name)
        }
        println(courselist)
        ArrayList<String> uploadcategorylist=new ArrayList<String>();
        uploadcategorylist.add("Select Upload Category")
        uploadcategorylist.add("Course")
        uploadcategorylist.add("Unit")
        uploadcategorylist.add("Topic")
        println(uploadcategorylist)
        [courselist:courselist,uploadcategorylist:uploadcategorylist]
    }    
}
