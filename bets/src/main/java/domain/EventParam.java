package domain;

import java.util.Date;

public class EventParam {
	public String description;
	public Date eventDate;
	public String sport;
	
	public EventParam(String pdescription, Date peventDate, String psport) {
		this.description = pdescription;
		this.eventDate = peventDate;
		this.sport = psport;
	}
}


