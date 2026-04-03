package ERyder;

import java.time.*;
import java.util.*;

public class BikeService{
    private Stack<ERyderLog> logStack = new Stack<>();
    private String validateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(location.equals(bike.getLocation()) && bike.getIsAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                String logMessage = "Bike available at location " + location;
                logStack.push(new ERyderLog(bike.getBikeID(), logMessage, LocalDateTime.now()));
                return bike.getBikeID();
            }
        }
        System.out.println("Sorry, no bike are available at the loaction you requested");
        return null;
    }
    private void reserveBike(String bikeID){
        if(bikeID.isEmpty()){
            System.out.println("Sorry, we're unable to reserve a bike at this time. Please try again later.");
        }
        
        else {
            for(Bike bike : BikeDatabase.bikes){
                if(bikeID.equals(bike.getBikeID())){
                    RentalService rentalService = new RentalService();
                    rentalService.startRentalProcess();
                    break;
                }
                else {
                    Queue<BikeRequest> bikeRequestQueue = new LinkedList<>();
                    bikeRequestQueue.add(new BikeRequest(bikeID, bike.getLocation(), LocalDateTime.now()));
                }
            }
        }
    }
    public void viewSystemLogs(){
        for (ERyderLog log : logStack){
            System.out.println(log);
        }
    }
}