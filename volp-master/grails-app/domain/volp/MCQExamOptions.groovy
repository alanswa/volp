package volp

class MCQExamOptions {
    String option_name
    String option_value
    String option_image_path
    String option_image_name
    String option_link
    boolean is_correct
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[mcqexam:MCQExam]
    static constraints = {
        option_link nullable:true
        option_image_path nullable:true
        option_image_name nullable:true
    }
}
