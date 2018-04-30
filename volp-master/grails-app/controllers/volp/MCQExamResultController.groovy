package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MCQExamResultController {

    MCQExamResultService MCQExamResultService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQExamResultService.list(params), model:[MCQExamResultCount: MCQExamResultService.count()]
    }

    def show(Long id) {
        respond MCQExamResultService.get(id)
    }

    def create() {
        respond new MCQExamResult(params)
    }

    def save(MCQExamResult MCQExamResult) {
        if (MCQExamResult == null) {
            notFound()
            return
        }

        try {
            MCQExamResultService.save(MCQExamResult)
        } catch (ValidationException e) {
            respond MCQExamResult.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQExamResult.label', default: 'MCQExamResult'), MCQExamResult.id])
                redirect MCQExamResult
            }
            '*' { respond MCQExamResult, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQExamResultService.get(id)
    }

    def update(MCQExamResult MCQExamResult) {
        if (MCQExamResult == null) {
            notFound()
            return
        }

        try {
            MCQExamResultService.save(MCQExamResult)
        } catch (ValidationException e) {
            respond MCQExamResult.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQExamResult.label', default: 'MCQExamResult'), MCQExamResult.id])
                redirect MCQExamResult
            }
            '*'{ respond MCQExamResult, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQExamResultService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQExamResult.label', default: 'MCQExamResult'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQExamResult.label', default: 'MCQExamResult'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
