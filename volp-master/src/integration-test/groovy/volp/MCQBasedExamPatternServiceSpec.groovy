package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MCQBasedExamPatternServiceSpec extends Specification {

    MCQBasedExamService MCQBasedExamService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MCQBasedExamPattern(...).save(flush: true, failOnError: true)
        //new MCQBasedExamPattern(...).save(flush: true, failOnError: true)
        //MCQBasedExamPattern MCQBasedExamPattern = new MCQBasedExamPattern(...).save(flush: true, failOnError: true)
        //new MCQBasedExamPattern(...).save(flush: true, failOnError: true)
        //new MCQBasedExamPattern(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //MCQBasedExamPattern.id
    }

    void "test get"() {
        setupData()

        expect:
        MCQBasedExamService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MCQBasedExamPattern> MCQBasedExamList = MCQBasedExamService.list(max: 2, offset: 2)

        then:
        MCQBasedExamList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        MCQBasedExamService.count() == 5
    }

    void "test delete"() {
        Long MCQBasedExamId = setupData()

        expect:
        MCQBasedExamService.count() == 5

        when:
        MCQBasedExamService.delete(MCQBasedExamId)
        sessionFactory.currentSession.flush()

        then:
        MCQBasedExamService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MCQBasedExamPattern MCQBasedExam = new MCQBasedExamPattern()
        MCQBasedExamService.save(MCQBasedExam)

        then:
        MCQBasedExam.id != null
    }
}
