package volp

import grails.gorm.services.Service

@Service(MCQExamSchedule)
interface MCQExamScheduleService {

    MCQExamSchedule get(Serializable id)

    List<MCQExamSchedule> list(Map args)

    Long count()

    void delete(Serializable id)

    MCQExamSchedule save(MCQExamSchedule MCQExamSchedule)

}