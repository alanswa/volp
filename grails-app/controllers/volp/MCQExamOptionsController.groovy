package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MCQExamOptionsController {

    MCQExamOptionsService MCQExamOptionsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQExamOptionsService.list(params), model:[MCQExamOptionsCount: MCQExamOptionsService.count()]
    }

    def show(Long id) {
        respond MCQExamOptionsService.get(id)
    }

    def create() {
        respond new MCQExamOptions(params)
    }

    def save(MCQExamOptions MCQExamOptions) {
        if (MCQExamOptions == null) {
            notFound()
            return
        }

        try {
            MCQExamOptionsService.save(MCQExamOptions)
        } catch (ValidationException e) {
            respond MCQExamOptions.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQExamOptions.label', default: 'MCQExamOptions'), MCQExamOptions.id])
                redirect MCQExamOptions
            }
            '*' { respond MCQExamOptions, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQExamOptionsService.get(id)
    }

    def update(MCQExamOptions MCQExamOptions) {
        if (MCQExamOptions == null) {
            notFound()
            return
        }

        try {
            MCQExamOptionsService.save(MCQExamOptions)
        } catch (ValidationException e) {
            respond MCQExamOptions.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQExamOptions.label', default: 'MCQExamOptions'), MCQExamOptions.id])
                redirect MCQExamOptions
            }
            '*'{ respond MCQExamOptions, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQExamOptionsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQExamOptions.label', default: 'MCQExamOptions'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQExamOptions.label', default: 'MCQExamOptions'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
