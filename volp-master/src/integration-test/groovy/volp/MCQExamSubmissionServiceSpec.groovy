package volp

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class MCQExamSubmissionServiceSpec extends Specification {

    MCQExamSubmissionService MCQExamSubmissionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new MCQExamSubmission(...).save(flush: true, failOnError: true)
        //new MCQExamSubmission(...).save(flush: true, failOnError: true)
        //MCQExamSubmission MCQExamSubmission = new MCQExamSubmission(...).save(flush: true, failOnError: true)
        //new MCQExamSubmission(...).save(flush: true, failOnError: true)
        //new MCQExamSubmission(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //MCQExamSubmission.id
    }

    void "test get"() {
        setupData()

        expect:
        MCQExamSubmissionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<MCQExamSubmission> MCQExamSubmissionList = MCQExamSubmissionService.list(max: 2, offset: 2)

        then:
        MCQExamSubmissionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        MCQExamSubmissionService.count() == 5
    }

    void "test delete"() {
        Long MCQExamSubmissionId = setupData()

        expect:
        MCQExamSubmissionService.count() == 5

        when:
        MCQExamSubmissionService.delete(MCQExamSubmissionId)
        sessionFactory.currentSession.flush()

        then:
        MCQExamSubmissionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        MCQExamSubmission MCQExamSubmission = new MCQExamSubmission()
        MCQExamSubmissionService.save(MCQExamSubmission)

        then:
        MCQExamSubmission.id != null
    }
}
