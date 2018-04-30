package volp

class MCQBasedExamPattern    //MCQBasedExamPattern
{
    int numberOfQuestion
    double weightageToEachQuestion
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[courseInstructor:Instructor,courseOffering:CourseOffering,difficultylevel:DifficultyLevel,mcqexam:MCQExam]
    static constraints = {
    }


}
