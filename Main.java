public class Main {
    public static void main(String[] args) {
        ERyder bike1 = new ERyder(101, 85.0, true, 120.5);
        ERyder bike2 = new ERyder(102, 70.0, false, 95.3, "john_doe", "123-456-7890");

        bike1.printRideDetails(20);
        bike2.printRideDetails(30);
    }
}
