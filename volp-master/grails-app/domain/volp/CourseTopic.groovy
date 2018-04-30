package volp

class CourseTopic {
    int topic_number
	String topic
    String username    
    Date creation_date
    Date updation_date
    String creation_ip_address
    String updation_ip_address	
	static belongsTo=[courseoutline:CourseOutline]
    static constraints = {
    }
}
