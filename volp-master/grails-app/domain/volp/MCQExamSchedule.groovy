package volp

import java.sql.Time

class MCQExamSchedule {
    Date shedulDate
    Time startTime,endTime
    double maxTime //session time
    double totalMarks
    String username
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address
    static belongsTo=[mcqBasedExam:MCQBasedExamPattern]
    static constraints = {
    }
}
