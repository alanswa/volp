package volp

import grails.gorm.services.Service

@Service(MCQExam)
interface MCQExamService {

    MCQExam get(Serializable id)

    List<MCQExam> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQExam save(MCQExam MCQExam)

}