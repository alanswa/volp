package volp

class MCQExam {
    int sno
    String question_statement
    String question_statement_image_path
    String question_statement_image_name
    String question_statement_link
    double weightage    //dont use this field
    boolean isapproved
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[courseoffering:CourseOffering,difficultylevel:DifficultyLevel]
    static constraints = {
        question_statement_link nullable:true
        question_statement_image_path nullable:true
        question_statement_image_name nullable:true
    }
}
