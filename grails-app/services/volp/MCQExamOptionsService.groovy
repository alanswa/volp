package volp

import grails.gorm.services.Service

@Service(MCQExamOptions)
interface MCQExamOptionsService {

    MCQExamOptions get(Serializable id)

    List<MCQExamOptions> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQExamOptions save(MCQExamOptions MCQExamOptions)

}