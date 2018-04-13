package volp

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class MCQExamScheduleController {

    MCQExamScheduleService MCQExamScheduleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MCQExamScheduleService.list(params), model:[MCQExamScheduleCount: MCQExamScheduleService.count()]
    }

    def show(Long id) {
        respond MCQExamScheduleService.get(id)
    }

    def create() {
        respond new MCQExamSchedule(params)
    }

    def save(MCQExamSchedule MCQExamSchedule) {
        if (MCQExamSchedule == null) {
            notFound()
            return
        }

        try {
            MCQExamScheduleService.save(MCQExamSchedule)
        } catch (ValidationException e) {
            respond MCQExamSchedule.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'MCQExamSchedule.label', default: 'MCQExamSchedule'), MCQExamSchedule.id])
                redirect MCQExamSchedule
            }
            '*' { respond MCQExamSchedule, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond MCQExamScheduleService.get(id)
    }

    def update(MCQExamSchedule MCQExamSchedule) {
        if (MCQExamSchedule == null) {
            notFound()
            return
        }

        try {
            MCQExamScheduleService.save(MCQExamSchedule)
        } catch (ValidationException e) {
            respond MCQExamSchedule.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'MCQExamSchedule.label', default: 'MCQExamSchedule'), MCQExamSchedule.id])
                redirect MCQExamSchedule
            }
            '*'{ respond MCQExamSchedule, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        MCQExamScheduleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'MCQExamSchedule.label', default: 'MCQExamSchedule'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'MCQExamSchedule.label', default: 'MCQExamSchedule'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
