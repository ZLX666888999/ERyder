package ERyder;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class ERyderLog{
    String log;
    String event;
    LocalDateTime timestamp;
    public ERyderLog(String log, String event, LocalDateTime timestamp){
        this.log = log;
        this.event = event;
        this.timestamp = timestamp;
    }
    public Stirng getLog(){
        return log;
    }
    public String getEvent(){
        return event;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    
    @override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String formattedTimestamp = timestamp.format(formatter);
        return log + "-" + event + "-" + formattedTimestamp;
    }
}