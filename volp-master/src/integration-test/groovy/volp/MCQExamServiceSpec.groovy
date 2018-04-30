package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MCQExamServiceSpec extends Specification {

    MCQExamService MCQExamService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MCQExam(...).save(flush: true, failOnError: true)
        //new MCQExam(...).save(flush: true, failOnError: true)
        //MCQExam MCQExam = new MCQExam(...).save(flush: true, failOnError: true)
        //new MCQExam(...).save(flush: true, failOnError: true)
        //new MCQExam(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //MCQExam.id
    }

    void "test get"() {
        setupData()

        expect:
        MCQExamService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MCQExam> MCQExamList = MCQExamService.list(max: 2, offset: 2)

        then:
        MCQExamList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        MCQExamService.count() == 5
    }

    void "test delete"() {
        Long MCQExamId = setupData()

        expect:
        MCQExamService.count() == 5

        when:
        MCQExamService.delete(MCQExamId)
        sessionFactory.currentSession.flush()

        then:
        MCQExamService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MCQExam MCQExam = new MCQExam()
        MCQExamService.save(MCQExam)

        then:
        MCQExam.id != null
    }
}
