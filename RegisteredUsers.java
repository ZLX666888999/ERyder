package ERyder;

import java.util.Arrays;

public class RegisteredUsers{
    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardExpiryDate;
    private String cardProvider;
    private int cvv;
    private String userType;
    String[] lastThreeTrips = new String[3];
    public RegisteredUsers(String fullName, String emailAddress, String dateOfBirth, long cardNumber, String cardExpiryDate, String cardProvider, int cvv, String userType) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.cardProvider = cardProvider;
        this.cvv = cvv;
        this.userType = userType;
    }
    public void setFullname(String fullName){
        this.fullName = fullName;
    }
    public String getFullname(){
        return this.fullName;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress= emailAddress;
    }
    public String getEmailAddress(){
        return this.emailAddress;
    }
    public void setDateOfBirth(String dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public String getDateOfBirth(){
        return this.dateOfBirth;
    }
    public void setCardNumber(long cardNumber){
        this.cardNumber = cardNumber;
    }
    public long getCardNumber(){
        return this.cardNumber;
    }
    public void setCardExpiryDate(String cardExpiryDate){
        this.cardExpiryDate = cardExpiryDate;
    }
    public String getCardExpiryDate(){
        return this.cardExpiryDate;
    }
    public void setCardProvider(String cardProvider){
        this.cardProvider = cardProvider;
    }
    public String getCardProvider(){
        return this.cardProvider;
    }
    public void setCvv(int cvv){
        this.cvv = cvv;
    }
    public int getCvv(){
        return this.cvv;
    }
    public void setUserType(String userType){
        this.userType = userType;
    }
    public String getUserType(){
        return this.userType;
    }
    public double calculateFare (double baseFare) {
        
        return baseFare;
    }
    public void displayUserType() {
        System.out.println("Regular User");
    }
    @Override
    public String toString(){
        return "RegisteredUsers{" +
                "fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", cardNumber=" + cardNumber +
                ", cardExpiryDate='" + cardExpiryDate + '\'' +
                ", cardProvider='" + cardProvider + '\'' +
                ", cvv=" + cvv +
                ", userType='" + userType + '\'' +
                ", lastThreeTrips=" + Arrays.toString(lastThreeTrips) +
                '}';
    }
}