package volp

import grails.validation.ValidationException

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.sql.Time

import static org.springframework.http.HttpStatus.*

class MCQExamController {

    MCQExamService MCQExamService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQExamService.list(params), model:[MCQExamCount: MCQExamService.count()]
    }

    def show(Long id) {
        respond MCQExamService.get(id)
    }

    def create() {
        respond new MCQExam(params)
    }

    def save(MCQExam MCQExam) {
        if (MCQExam == null) {
            notFound()
            return
        }

        try {
            MCQExamService.save(MCQExam)
        } catch (ValidationException e) {
            respond MCQExam.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQExam.label', default: 'MCQExam'), MCQExam.id])
                redirect MCQExam
            }
            '*' { respond MCQExam, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQExamService.get(id)
    }

    def update(MCQExam MCQExam) {
        if (MCQExam == null) {
            notFound()
            return
        }

        try {
            MCQExamService.save(MCQExam)
        } catch (ValidationException e) {
            respond MCQExam.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQExam.label', default: 'MCQExam'), MCQExam.id])
                redirect MCQExam
            }
            '*'{ respond MCQExam, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQExamService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQExam.label', default: 'MCQExam'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQExam.label', default: 'MCQExam'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
    def createQuestionBank()
    {
        println("i am in Session.id::::"+session.cid)
        println("params"+params)
        def options = InitialData.list()
        def difficultyLevelData = DifficultyLevel.list()
        String courseId = session.cid
        Course c = Course.findById(courseId)
        def mcqOption =[]
        CourseOffering courseOfferId = CourseOffering.findByCourse(c)
        if(!courseOfferId){
                    flash.message ="course is not offered.............."
        }
        def mcqAllData = MCQExam.findAllByCourseoffering(courseOfferId)
        for (MCQExam mcqo:mcqAllData)
        {
            println("mcqo"+mcqo)
            def option = MCQExamOptions.findAllByMcqexam(mcqo)
            println("option"+option)
            mcqOption.add(option)
        }
        println("mcqAllData"+mcqAllData)
        println("mcqOption"+mcqOption)
        [options:options,difficultyLevelData:difficultyLevelData,mcqAllData:mcqAllData,mcqOption:mcqOption]
    }
    def storeQuestionBank() {
        println("i am in store question bank"+params);
        String courseId = session.cid
        Course c = Course.findById(courseId)
        CourseOffering courseOfferId = CourseOffering.findByCourse(c)
        println("c  :: " + c)
        if(params.mcqDataId){
            println"mcqData is already present"
            String mcqId = params.mcqDataId
            def mcqQuesData = MCQExam.findById(mcqId)
            println("mcqData"+mcqQuesData)
            mcqQuesData.question_statement = params.question_statement
            if (params.myFile) {
                println("Testing :::::::::::::::"+mcqQuesData.question_statement_image_path)
                if (mcqQuesData.question_statement_image_path) {
                    println("Testing :::::::::::::::"+mcqQuesData.question_statement_image_path)
                    String filename = mcqQuesData.question_statement_image_path
                    boolean fileSuccessfullyDeleted = new File(filename).delete()
                    println("fileSuccessfullyDeleted" + fileSuccessfullyDeleted);
                }
                println("Image is available")
                def fp = FolderPath.list()
                def file = request.getFile("myFile")
                String image_path = fp[0].path + "/QuestionBank/"
                String path = c.course_code + c.course_name + session.uid
                def dirPath = new File(image_path + path)
                if (!dirPath.exists()) {
                    dirPath.mkdirs()
                }
                String image_file_name = ""
                String fullPath = "";
                if (file.empty) {
                    flash.message = "File cannot be empty"
                    println("File cannot be empty")
                } else {
                    image_file_name = c.course_code + "_" + c.course_name + "_" + mcqQuesData.sno + file.originalFilename
                    fullPath = image_path + path + "//" + image_file_name
                    file.transferTo(new File(fullPath))
                    println("File uploaded successfully..")
                }
                mcqQuesData.question_statement_image_path = fullPath
                mcqQuesData.question_statement_image_name = image_file_name
            }
            mcqQuesData.weightage = Double.parseDouble(params.weightage)
            if (params.isapproved) {
                mcqQuesData.isapproved = params.isapproved
            }
            else
            {
                mcqQuesData.isapproved=false
            }
            mcqQuesData.username = session.uid
            mcqQuesData.updation_date = new java.util.Date()
            mcqQuesData.updation_ip_address = request.getRemoteAddr()
            DifficultyLevel dfl = DifficultyLevel.findById(params.difficultyLevelData)
            mcqQuesData.courseoffering = courseOfferId
            mcqQuesData.difficultylevel = dfl
            if (params.answer) {
                println("params.answer")
                def mcqOptions1 = MCQExamOptions.findAllByMcqexam(mcqQuesData)
                if (mcqOptions1) {
                    if(mcqOptions1.option_image_path[0])
                    {
                        println("check image path"+ mcqOptions1.option_image_path[0])
                        if (params.myFileA) {
                            String filename = mcqOptions1.option_image_path[0]
                            boolean fileSuccessfullyDeleted = new File(filename).delete()
                            println("fileSuccessfullyDeleted" + fileSuccessfullyDeleted);
                        }
                    }
                    mcqOptions1.each { it.delete(flush: true, failOnError: true) }
                }
                MCQExamOptions mcqOptions = new MCQExamOptions();
                if (params.myFileA){
                    def fp = FolderPath.list()
                def file = request.getFile("myFileA")
                String image_path = fp[0].path + "/QuestionBank/"
                String path = c.course_code + c.course_name + session.uid
                def dirPath = new File(image_path + path)
                if (!dirPath.exists()) {
                    dirPath.mkdirs()
                }
                String image_file_name = ""
                String fullPath = "";
                if (file.empty) {
                    flash.message = "File cannot be empty"
                    println("File cannot be empty")
                } else {
                    image_file_name = c.course_code + "_" + c.course_name + "_" + mcqQuesData.sno + "_" + "Ansoptions" + file.originalFilename
                    fullPath = image_path + path + "//" + image_file_name
                    file.transferTo(new File(fullPath))
                    println("File uploaded successfully..")
                }
                mcqOptions.option_image_path = fullPath
                mcqOptions.option_image_name = image_file_name
            }
                if ((params.answer).length() == 1) {
                    mcqOptions.option_name = (params.answer).toUpperCase()
                    mcqOptions.is_correct = true
                } else {
                    flash.message = "Please select appropriate option"
                }
                mcqOptions.username = session.uid
                mcqOptions.option_value = "1"
                mcqOptions.updation_date = new java.util.Date()
                mcqOptions.creation_date = new java.util.Date()
                mcqOptions.creation_ip_address = request.getRemoteAddr()
                mcqOptions.updation_ip_address = request.getRemoteAddr()
                mcqOptions.mcqexam = mcqQuesData
                mcqOptions.save(flush: true, failOnError: true)
            } else {
                def mcqOptions =  MCQExamOptions.findAllByMcqexam(mcqQuesData)
                int numOfOption = mcqOptions.size()
                println("numOfOption"+numOfOption)
                if(numOfOption == 1)
                {
                    println("mcqOptions"+mcqOptions)
                }
                else{
                for (int i = 0; i < numOfOption; i++) {
                    if (params.option_name[i].toString().equals("")) {
                        println("alan")
                        mcqOptions[i].delete(flush: true, failOnError: true)
                    } else {
                        mcqOptions[i].username = session.uid
                        mcqOptions[i].option_value = params.option_name[i]
                        mcqOptions[i].updation_date = new java.util.Date()
                        mcqOptions[i].updation_ip_address = request.getRemoteAddr()
                        if (Integer.parseInt(params.ans) == i) {
                            println(params.ans + "::::" + (i + 1))
                            println("mcqOptions[i].option_name" + mcqOptions[i].option_name)
                            mcqOptions[i].is_correct = true
                        } else {
                            println("i else" + i)
                            mcqOptions[i].is_correct = false
                        }
                        mcqOptions[i].mcqexam = mcqQuesData
                        mcqOptions[i].save(flush: true, failOnError: true)
                    }
                }

                }
            }
            mcqQuesData.save(flush: true, failOnError: true)
        }
        else {
            if (c) {
                MCQExam mcqQuesData
                println("courseOfferId" + courseOfferId)
                if (!courseOfferId) {

                    flash.message = "Course is not offered.............."
                    render("ok")
                } else {
                    def mcqData = MCQExam.findAllByCourseoffering(courseOfferId)
                    println("course is offering in database :::" + mcqData.size)
                    mcqQuesData = new MCQExam()
                    if (mcqData == null) {
                        mcqQuesData.sno = 1
                    } else {
                        mcqQuesData.sno = mcqData.size + 1
                    }
                    mcqQuesData.question_statement = params.question_statement
                    if (params.myFile) {
                        println("Image is available")
                        def fp = FolderPath.list()
                        def file = request.getFile("myFile")
                        String image_path = fp[0].path + "/QuestionBank/"
                        String path = c.course_code + c.course_name + session.uid
                        def dirPath = new File(image_path + path)
                        if (!dirPath.exists()) {
                            dirPath.mkdirs()
                        }
                        String image_file_name = ""
                        String fullPath = "";
                        if (file.empty) {
                            flash.message = "File cannot be empty"
                            println("File cannot be empty")
                        } else {
                            image_file_name = c.course_code + "_" + c.course_name + "_" + mcqQuesData.sno + file.originalFilename
                            fullPath = image_path + path + "//" + image_file_name
                            file.transferTo(new File(fullPath))
                            println("File uploaded successfully..")
                        }
                        mcqQuesData.question_statement_image_path = fullPath
                        mcqQuesData.question_statement_image_name = image_file_name
                    }
                    mcqQuesData.weightage = Double.parseDouble(params.weightage)
                    if (params.isapproved) {
                        mcqQuesData.isapproved = params.isapproved
                    }
                    mcqQuesData.username = session.uid
                    mcqQuesData.updation_date = new java.util.Date()
                    mcqQuesData.creation_date = new java.util.Date()
                    mcqQuesData.updation_ip_address = request.getRemoteAddr()
                    mcqQuesData.creation_ip_address = request.getRemoteAddr()
                    DifficultyLevel dfl = DifficultyLevel.findById(params.difficultyLevelData)
                    mcqQuesData.courseoffering = courseOfferId
                    mcqQuesData.difficultylevel = dfl
                    println("mcqData is " + mcqData)
                    if (params.answer) {
                        println("params.answer")
                        MCQExamOptions mcqOptions = new MCQExamOptions()
                        def fp = FolderPath.list()
                        def file = request.getFile("myFileA")
                        String image_path = fp[0].path + "/QuestionBank/"
                        String path = c.course_code + c.course_name + session.uid
                        def dirPath = new File(image_path + path)
                        if (!dirPath.exists()) {
                            dirPath.mkdirs()
                        }
                        String image_file_name = ""
                        String fullPath = "";
                        if (file.empty) {
                            flash.message = "File cannot be empty"
                            println("File cannot be empty")
                        } else {
                            image_file_name = c.course_code + "_" + c.course_name + "_" + mcqQuesData.sno + "_" + "Ansoptions" + file.originalFilename
                            fullPath = image_path + path + "//" + image_file_name
                            file.transferTo(new File(fullPath))
                            println("File uploaded successfully..")
                        }
                        mcqOptions.option_image_path = fullPath
                        mcqOptions.option_image_name = image_file_name
                        if ((params.answer).length() == 1) {
                            mcqOptions.option_name = (params.answer).toUpperCase()
                            mcqOptions.is_correct = true
                        } else {
                            flash.message = "Please select appropriate option"
                        }
                        mcqOptions.username = session.uid
                        mcqOptions.option_value = "1"
                        mcqOptions.creation_date = new java.util.Date()
                        mcqOptions.updation_date = new java.util.Date()
                        mcqOptions.creation_ip_address = request.getRemoteAddr()
                        mcqOptions.updation_ip_address = request.getRemoteAddr()
                        mcqOptions.mcqexam = mcqQuesData
                        mcqOptions.save(flush: true, failOnError: true)
                    } else {
                        def MyOptions = ["A", "B", "C", "D", "E"]
                        int numOfOption = params.option_name.size() - params.option_name.count { it == '' }
                        for (int i = 0; i < numOfOption; i++) {
                            MCQExamOptions mcqOptions = new MCQExamOptions()
                            mcqOptions.option_name = MyOptions[i]
                            mcqOptions.username = session.uid
                            mcqOptions.option_value = params.option_name[i]
                            mcqOptions.creation_date = new java.util.Date()
                            mcqOptions.updation_date = new java.util.Date()
                            mcqOptions.creation_ip_address = request.getRemoteAddr()
                            mcqOptions.updation_ip_address = request.getRemoteAddr()
                            if (Integer.parseInt(params.ans) == (i + 1)) {
                                println(params.ans + "::::" + (i + 1))
                                mcqOptions.is_correct = true
                            } else {
                                println("i" + i)
                                mcqOptions.is_correct = false
                            }
                            mcqOptions.mcqexam = mcqQuesData
                            mcqOptions.save(flush: true, failOnError: true)
                        }
                    }
                    mcqQuesData.save(flush: true, failOnError: true)
                }
            } else {
                flash.message = "Course is not available"
            }
        }
        println("store question bank params"+params)

        render(params)
    }
    def MCQExam()
    {
        println("I am in MCQExam method")
        println("Session.id::::"+session.uid)
        def login = Login.findByUsername(session.uid)
        println("login details"+login.usertype.type)
        Instructor instructor = Instructor.findByUid(login.username)
        println("instructor Details"+instructor)
        def course = Course.findAllByCourseowner(instructor)
        println("course"+course.course_name)
        println("coursecategory"+course.coursecategory.name)
        [instructor:instructor,course:course]

    }
    def renderImage () {

        File imageFile
        def questionImage = MCQExam.findById(params.id)
        if(questionImage)
        {
            println("questionImage" + questionImage.question_statement_image_path)
            imageFile = new File(questionImage.question_statement_image_path)
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
    def renderOptionImage () {

        File imageFile
        def OptionImage = MCQExamOptions.findById(params.id)
        if(OptionImage)
        {
            println("questionImage" + OptionImage.option_image_path)
            imageFile = new File(OptionImage.option_image_path)
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
    def updateMCQS()
    {
        println("i am in update mcq"+params)
        String id = params.mcqId
        def mcqData = MCQExam.findById(id)
        def mcqDataOption = MCQExamOptions.findAllByMcqexam(mcqData)
        println("McqData"+mcqDataOption);
        def difficultyLevelData =DifficultyLevel.list()//.findByName(mcqData.difficultylevel.name)
        [mcqData:mcqData,mcqDataOption:mcqDataOption,difficultyLevelData:difficultyLevelData]
    }
    def createQuestionPattern()
    {
        println("i am in create question pattern")
        println("course id::::::::::::"+session.cid)
        String uid = session.uid
        String courseId = session.cid
        Course c = Course.findById(courseId)
        Instructor ins = Instructor.findByUid(uid)
        CourseOffering courseOfferId = CourseOffering.findByCourse(c)
        def mcqAllData = MCQExam.findAllByCourseoffering(courseOfferId)
        println("EASY QUESTION"+mcqAllData.difficultylevel.name)
        def easy =mcqAllData.difficultylevel.name as List
        def levelname = DifficultyLevel.list()
        def diffcultyLevelData = [:]
        for(DifficultyLevel l:levelname)
        {
            diffcultyLevelData[l.name] = easy.count(l.name)
        }
        println(diffcultyLevelData)
        def mcqBasedata = MCQBasedExamPattern.findAllByCourseOffering(courseOfferId)
        println("alanmcqbaseData::::::::"+mcqBasedata+"offerid"+courseOfferId)
        //render (diffcultyLevelData as JSON)
        [diffcultyLevelData:diffcultyLevelData,mcqBasedata:mcqBasedata]
    }
    def saveMCQBasedPattern()
    {
        println("saveMCQBasedPattern"+params)
        println("userName"+session.uid)
        println("courseId"+session.cid)
        String cid = session.cid
        String uid = session.uid
        Course c = Course.findById(cid)
        CourseOffering courseOffer = CourseOffering.findByCourse(c)
        Instructor ins = Instructor.findByUid(uid)
        MCQExam mcq = MCQExam.findByCourseoffering(courseOffer)
        println("mcq:::::::::::::::"+mcq)
        println(courseOffer)
        println(ins)
        def dfl = DifficultyLevel.list()
        int noofquescnt = 0
        def mcqbaseData = MCQBasedExamPattern.findAllByCourseOffering(courseOffer)
        if(mcqbaseData){
            println("Data is present")
            for(DifficultyLevel d:dfl) {
                println("dfl"+d.name)
                if(params.numberofquestion[noofquescnt])
                {
                    println("erroe checking"+mcqbaseData[noofquescnt].numberOfQuestion)
                    mcqbaseData[noofquescnt].numberOfQuestion =Integer.parseInt(params.numberofquestion[noofquescnt])
                    mcqbaseData[noofquescnt].weightageToEachQuestion =Double.parseDouble(params.weightage[noofquescnt])
                    mcqbaseData[noofquescnt].courseInstructor = ins
                    mcqbaseData[noofquescnt].difficultylevel = d
                    mcqbaseData[noofquescnt].courseOffering = courseOffer
                    mcqbaseData[noofquescnt].username=uid
                    mcqbaseData[noofquescnt].updation_date = new java.util.Date()
                    mcqbaseData[noofquescnt].updation_ip_address = request.getRemoteAddr()
                    mcqbaseData[noofquescnt].mcqexam = mcq
                    mcqbaseData[noofquescnt].save(flush: true, failOnError: true)
                }
                else
                {
                    println("else"+params.numberofquestion[noofquescnt])
                    mcqbaseData[noofquescnt].numberOfQuestion =0
                    mcqbaseData[noofquescnt].weightageToEachQuestion =0.0
                    mcqbaseData[noofquescnt].courseInstructor = ins
                    mcqbaseData[noofquescnt].difficultylevel = d
                    mcqbaseData[noofquescnt].courseOffering = courseOffer
                    mcqbaseData[noofquescnt].username=uid
                    mcqbaseData[noofquescnt].updation_date = new java.util.Date()
                    mcqbaseData[noofquescnt].updation_ip_address = request.getRemoteAddr()
                    mcqbaseData[noofquescnt].mcqexam = mcq
                    mcqbaseData[noofquescnt].save(flush: true, failOnError: true)
                }
                noofquescnt++
            }
        }
        else{
        for(DifficultyLevel d:dfl) {
            println("dfl"+d.name)
            if(params.numberofquestion[noofquescnt])
            {
                println("errror in else part")
                MCQBasedExamPattern mcqBaseExam = new MCQBasedExamPattern()
                mcqBaseExam.numberOfQuestion =Integer.parseInt(params.numberofquestion[noofquescnt])
                mcqBaseExam.weightageToEachQuestion =Integer.parseInt(params.weightage[noofquescnt])
                mcqBaseExam.courseInstructor = ins
                mcqBaseExam.difficultylevel = d
                mcqBaseExam.courseOffering = courseOffer
                mcqBaseExam.username=uid
                mcqBaseExam.creation_date= new java.util.Date()
                mcqBaseExam.updation_date = new java.util.Date()
                mcqBaseExam.creation_ip_address = request.getRemoteAddr()
                mcqBaseExam.updation_ip_address = request.getRemoteAddr()
                mcqBaseExam.mcqexam = mcq
                mcqBaseExam.save(flush: true, failOnError: true)
            }
            else
            {
                println("else"+params.numberofquestion[noofquescnt])
                MCQBasedExamPattern mcqBaseExam = new MCQBasedExamPattern()
                mcqBaseExam.numberOfQuestion =0
                mcqBaseExam.weightageToEachQuestion =0
                mcqBaseExam.courseInstructor = ins
                mcqBaseExam.difficultylevel = d
                mcqBaseExam.courseOffering = courseOffer
                mcqBaseExam.username=uid
                mcqBaseExam.creation_date= new java.util.Date()
                mcqBaseExam.updation_date = new java.util.Date()
                mcqBaseExam.creation_ip_address = request.getRemoteAddr()
                mcqBaseExam.updation_ip_address = request.getRemoteAddr()
                mcqBaseExam.mcqexam = mcq
                mcqBaseExam.save(flush: true, failOnError: true)
            }
            noofquescnt++
        }}
        render("Suceesfully stored data!!!!!")
    }
    def createQuestionSchedule()
    {
        println("createQuestionSchedule")
        println("Session cid"+session.cid)
        String cid = session.cid
        String uid = session.uid
        Course c = Course.findById(cid)
        CourseOffering courseOffer = CourseOffering.findByCourse(c)
        Instructor ins = Instructor.findByUid(uid)
        def mcqbaseData = MCQBasedExamPattern.findAllByCourseOffering(courseOffer)
        MCQExamSchedule mcqSchedule1 = MCQExamSchedule.findByMcqBasedExam(mcqbaseData)
        [mcqSchedule1:mcqSchedule1]
        //render("ok")
    }
    def SetScheduleTime() {
        double totalmarks = 0.0
        String maximumTime = ""
        println("SetScheduleTime")
        println("userName" + session.uid)
        println("courseId" + session.cid)
        String cid = session.cid
        String uid = session.uid
        Course c = Course.findById(cid)
        CourseOffering courseOffer = CourseOffering.findByCourse(c)
        Instructor ins = Instructor.findByUid(uid)
        println("SetScheduleTime" + courseOffer)
        println("SetScheduleTime" + ins)
        println("params" + params)
        Date myDate = params.date('scheduleDate', 'dd-MM-yyyy')
        println("myDate" + myDate.format("dd-MM-yyyy"))
        def mcqbaseData = MCQBasedExamPattern.findAllByCourseOffering(courseOffer)
        println("mcqData wight" + mcqbaseData.weightageToEachQuestion)
        println("mcqData noofQues" + mcqbaseData.numberOfQuestion)
        for (MCQBasedExamPattern mcq : mcqbaseData) {
            totalmarks += mcq.numberOfQuestion * mcq.weightageToEachQuestion
        }
        println("Total Marks" + totalmarks);

        MCQExamSchedule mcqSchedule1 = MCQExamSchedule.findByMcqBasedExam(mcqbaseData)
        if (mcqSchedule1) {
            println("Data already present!!!!")
            mcqSchedule1.shedulDate = params.scheduleDate
            Time starttime = new Time(Integer.parseInt(params.startTimeH), Integer.parseInt(params.startTimeM), 0)
            mcqSchedule1.startTime = starttime
            Time endtime = new Time(Integer.parseInt(params.endTimeH), Integer.parseInt(params.endTimeM), 0)
            mcqSchedule1.maxTime = Double.parseDouble(params.maxtime)
            mcqSchedule1.endTime = endtime
            mcqSchedule1.totalMarks = totalmarks
            mcqSchedule1.username = uid
            mcqSchedule1.updation_date = new java.util.Date()
            mcqSchedule1.updation_ip_address = request.getRemoteAddr()
            mcqSchedule1.mcqBasedExam = mcqbaseData[0]
            mcqSchedule1.save(flush: true, failOnError: true)
        }
        else{
        MCQExamSchedule mcqSchedule = new MCQExamSchedule()
        mcqSchedule.shedulDate = params.scheduleDate
        Time starttime = new Time(Integer.parseInt(params.startTimeH), Integer.parseInt(params.startTimeM), 0)
        mcqSchedule.startTime = starttime
        Time endtime = new Time(Integer.parseInt(params.endTimeH), Integer.parseInt(params.endTimeM), 0)
        mcqSchedule.maxTime = Double.parseDouble(params.maxtime)
        mcqSchedule.endTime = endtime
        mcqSchedule.totalMarks = totalmarks
        mcqSchedule.mcqBasedExam = mcqbaseData[0]
        mcqSchedule.username = uid
        mcqSchedule.creation_date = new java.util.Date()
        mcqSchedule.updation_date = new java.util.Date()
        mcqSchedule.creation_ip_address = request.getRemoteAddr()
        mcqSchedule.updation_ip_address = request.getRemoteAddr()
        mcqSchedule.save(flush: true, failOnError: true)
        }
       render("Ok")
    }

}
