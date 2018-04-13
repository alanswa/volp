package volp

import grails.gorm.services.Service

@Service(MCQExamSubmission)
interface MCQExamSubmissionService {

    MCQExamSubmission get(Serializable id)

    List<MCQExamSubmission> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQExamSubmission save(MCQExamSubmission MCQExamSubmission)

}