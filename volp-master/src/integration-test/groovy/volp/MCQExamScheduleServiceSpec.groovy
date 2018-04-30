package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MCQExamScheduleServiceSpec extends Specification {

    MCQExamScheduleService MCQExamScheduleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MCQExamSchedule(...).save(flush: true, failOnError: true)
        //new MCQExamSchedule(...).save(flush: true, failOnError: true)
        //MCQExamSchedule MCQExamSchedule = new MCQExamSchedule(...).save(flush: true, failOnError: true)
        //new MCQExamSchedule(...).save(flush: true, failOnError: true)
        //new MCQExamSchedule(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //MCQExamSchedule.id
    }

    void "test get"() {
        setupData()

        expect:
        MCQExamScheduleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MCQExamSchedule> MCQExamScheduleList = MCQExamScheduleService.list(max: 2, offset: 2)

        then:
        MCQExamScheduleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        MCQExamScheduleService.count() == 5
    }

    void "test delete"() {
        Long MCQExamScheduleId = setupData()

        expect:
        MCQExamScheduleService.count() == 5

        when:
        MCQExamScheduleService.delete(MCQExamScheduleId)
        sessionFactory.currentSession.flush()

        then:
        MCQExamScheduleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MCQExamSchedule MCQExamSchedule = new MCQExamSchedule()
        MCQExamScheduleService.save(MCQExamSchedule)

        then:
        MCQExamSchedule.id != null
    }
}
