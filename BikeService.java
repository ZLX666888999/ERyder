package ERyder;

import java.time.*;
import java.util.*;

public class BikeService{
    private String validateLocation(String location){
        for(Bike bike : BikeDatabase.bikes){
            if(location.equals(bike.getLocation()) && bike.getIsAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
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
                
            }
        }
    }
}