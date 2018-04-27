package volp

class MCQExamSubmission {
    static belongsTo=[courseofferinglearner:CourseOfferingLearner,mcqexam:MCQExam]
    static hasMany =[mcqexamoption:MCQExamOptions]
    String username
    Date creation_date
    String remark
    double weightage
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static constraints = {
        mcqexamoption nullable: true
    }
}
