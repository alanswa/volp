package volp

class EnrollController {

    def index() { }
    def enrollLogin(){
        println("in enrollLogin params:"+params)
        [coffr:params.coffr]
    }
    def processLogin() {
        println("in processLogin params:" + params)
        Login user = Login.findByUsernameAndPassword(params.email, params.pwd)
        println("USer:" + user)
        if (user == null) {
            flash.message = params.email + " Invalid User name or Password!!!"
            redirect(action: "login")
            return
        } else {
            if (user.isloginblocked) {
                flash.message = params.email + " blocked!!!"
                redirect(action: "login")
                return
            }

            if (user.usertype.type == "Learner" && user.usertype.application_type.application_type == "VOLP")
                redirect(controller: "enroll", action: "payFee" ,params: [coffr: params.coffr])
            session.uid = user.username
            return
        }
    }
    def payFee(){
        println("in payFee params:" + params)
        println("in payFee session:" + session)
        CourseOffering coffr = CourseOffering.findById(params.coffr)
        [coffr:coffr]
    }
}
