package ERyder;

public class main{
    public static void main (String[] args) {
        ERyder bike1 = new ERyder("BIKE001", 80, true, 5.0);
        bike1.printBikeDetails();
        bike1.printRideDetails(0);
        ERyder bike2 = new ERyder("BIKE002", 60, false, 10.5, "0987654321", "ACCOUNT002");
        bike2.ride();
        bike2.printBikeDetails();
        bike2.printRideDetails(30);
        System.out.println("Calculated Fare: $" + bike2.getCalulatedFare(30));
    }
}