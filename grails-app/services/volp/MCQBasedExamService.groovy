package volp

import grails.gorm.services.Service

@Service(MCQBasedExamPattern)
interface MCQBasedExamService {

    MCQBasedExamPattern get(Serializable id)

    List<MCQBasedExamPattern> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQBasedExamPattern save(MCQBasedExamPattern MCQBasedExam)

}