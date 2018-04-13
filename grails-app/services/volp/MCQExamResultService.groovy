package volp

import grails.gorm.services.Service

@Service(MCQExamResult)
interface MCQExamResultService {

    MCQExamResult get(Serializable id)

    List<MCQExamResult> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQExamResult save(MCQExamResult MCQExamResult)

}