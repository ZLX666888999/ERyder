package ERyder;
public class RegularUser extends RegisteredUsers {
    public RegularUser(String fullName, String emailAddress, String dateOfBirth, long cardNumber, String cardExpiryDate, String cardProvider, int cvv, String userType) {
        super(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, "Regular");
    }
    @Override
    public double calculateFare(double baseFare) {
        return baseFare;
    }
    @Override
    public void displayUserType() {
        System.out.println("Regular User");
    }
}