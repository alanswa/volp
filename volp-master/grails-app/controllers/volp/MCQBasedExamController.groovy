package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MCQBasedExamController {

    MCQBasedExamService MCQBasedExamService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQBasedExamService.list(params), model:[MCQBasedExamCount: MCQBasedExamService.count()]
    }

    def show(Long id) {
        respond MCQBasedExamService.get(id)
    }

    def create() {
        respond new MCQBasedExamPattern(params)
    }

    def save(MCQBasedExamPattern MCQBasedExam) {
        if (MCQBasedExam == null) {
            notFound()
            return
        }

        try {
            MCQBasedExamService.save(MCQBasedExam)
        } catch (ValidationException e) {
            respond MCQBasedExam.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQBasedExamPattern.label', default: 'MCQBasedExamPattern'), MCQBasedExam.id])
                redirect MCQBasedExam
            }
            '*' { respond MCQBasedExam, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQBasedExamService.get(id)
    }

    def update(MCQBasedExamPattern MCQBasedExam) {
        if (MCQBasedExam == null) {
            notFound()
            return
        }

        try {
            MCQBasedExamService.save(MCQBasedExam)
        } catch (ValidationException e) {
            respond MCQBasedExam.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQBasedExamPattern.label', default: 'MCQBasedExamPattern'), MCQBasedExam.id])
                redirect MCQBasedExam
            }
            '*'{ respond MCQBasedExam, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQBasedExamService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQBasedExamPattern.label', default: 'MCQBasedExamPattern'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQBasedExamPattern.label', default: 'MCQBasedExamPattern'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
