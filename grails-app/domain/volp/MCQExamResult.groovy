package volp

class MCQExamResult {
    double score
    Date exam_given_date
    static belongsTo=[courseofferinglearner:CourseOfferingLearner]
    static constraints = {
    }
}
