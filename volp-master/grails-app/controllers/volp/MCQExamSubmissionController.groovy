package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MCQExamSubmissionController {

    MCQExamSubmissionService MCQExamSubmissionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQExamSubmissionService.list(params), model:[MCQExamSubmissionCount: MCQExamSubmissionService.count()]
    }

    def show(Long id) {
        respond MCQExamSubmissionService.get(id)
    }

    def create() {
        respond new MCQExamSubmission(params)
    }

    def save(MCQExamSubmission MCQExamSubmission) {
        if (MCQExamSubmission == null) {
            notFound()
            return
        }

        try {
            MCQExamSubmissionService.save(MCQExamSubmission)
        } catch (ValidationException e) {
            respond MCQExamSubmission.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQExamSubmission.label', default: 'MCQExamSubmission'), MCQExamSubmission.id])
                redirect MCQExamSubmission
            }
            '*' { respond MCQExamSubmission, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQExamSubmissionService.get(id)
    }

    def update(MCQExamSubmission MCQExamSubmission) {
        if (MCQExamSubmission == null) {
            notFound()
            return
        }

        try {
            MCQExamSubmissionService.save(MCQExamSubmission)
        } catch (ValidationException e) {
            respond MCQExamSubmission.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQExamSubmission.label', default: 'MCQExamSubmission'), MCQExamSubmission.id])
                redirect MCQExamSubmission
            }
            '*'{ respond MCQExamSubmission, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQExamSubmissionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQExamSubmission.label', default: 'MCQExamSubmission'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQExamSubmission.label', default: 'MCQExamSubmission'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
