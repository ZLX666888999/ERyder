package ERyder;

public class ERyder {
   private String bikeID;
   private int batteryLevel;
   private boolean isAvailable;
   private double kmDriven;



   private static final String COMPANY_NAME = "ERyder";
   private static final double BASE_FARE = 1.0;
   private static final double PER_MINUTE_FARE = 0.5;
   private final String LINK_PHONE_NUMBER;
   private final String LINKED_ACCOUNT;
   private double totalUsageInMinutes;
   private double totalFare;

   public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven){
      
      this.bikeID = bikeID;
      this.batteryLevel = batteryLevel;
      this.isAvailable = isAvailable;
      this.kmDriven = kmDriven;
      this.LINK_PHONE_NUMBER = "N/A";
      this.LINKED_ACCOUNT = "N/A";
   };
   public ERyder(String bikeID, int batteryLevel, boolean isAvailable, double kmDriven, String LINK_PHONE_NUMBER, String LINKED_ACCOUNT){
      this.bikeID = bikeID; 
      this.batteryLevel = batteryLevel;
       this.isAvailable = isAvailable;
       this.kmDriven = kmDriven;
       this.LINK_PHONE_NUMBER = LINK_PHONE_NUMBER;
       this.LINKED_ACCOUNT = LINKED_ACCOUNT;
      this.isAvailable = isAvailable;
      this.kmDriven = kmDriven;
      if (batteryLevel <= 0) {
         this.isAvailable = false;
      } else {
         this.isAvailable = true;
      }
   } 
   public void ride() {
      if (this.isAvailable) {
         this.batteryLevel -= 10;
         if (this.batteryLevel <= 0) {
            this.isAvailable = false;
         } else {
            System.out.println("Bike " + this.bikeID + " is available.");
         }
      } else {
         System.out.println("Bike " + this.bikeID + " is not available.");
      }

   }

   public void printBikeDetails() {
      String var10001 = this.bikeID;
      System.out.println("Bike ID: " + var10001);
      var10001 = String.valueOf(this.batteryLevel);
      System.out.println("Battery Level: " + var10001);
      System.out.println("Availability: " + (this.isAvailable ? "Available" : "Not Available"));
      var10001 = String.valueOf(this.kmDriven);
      System.out.println("Kilometers Driven: " + var10001);
   }
   public void printRideDetails(int usageInMinutes) {
      String var10001 = this.LINKED_ACCOUNT;
      System.out.println("Linked Phone Number: " + var10001);
      var10001 = this.LINK_PHONE_NUMBER;
      System.out.println("Linked Account: " + var10001);
      var10001 = this.bikeID;
      System.out.println("Bike ID: " + var10001);
      var10001 = String.valueOf(usageInMinutes);
      System.out.println("Usage Time (minutes): " + var10001);
      totalFare = calculateFare(usageInMinutes);
      var10001 = String.valueOf(totalFare);
      System.out.println("Total Fare: $" + var10001);

   }
   private double calculateFare(int usageInMinutes) {
      return BASE_FARE + (PER_MINUTE_FARE * usageInMinutes);
   }
   public int getBatteryLevel() {
      return this.batteryLevel;
   }
   public void setBatteryLevel(int batteryLevel) {
      this.batteryLevel = batteryLevel;
   }
   public double getCalulatedFare(int usageInMinutes) {
      return calculateFare(usageInMinutes);
   }
}