package ERyder;
import java.time.LocalDateTime;
import java.util.*;

public class AdminPanel {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;
    private UserRegistration userRegistration;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalsList;
    private Bike bike;
    private ArrayList<RegisteredUsers> registeredUsersList = new ArrayList<>();

    public void simulateApplicationInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter true or false to demonstrate the Registered User: ");
        isRegisteredUser = scanner.nextBoolean();
        scanner.nextLine();
        System.out.println("Please enter the email address: ");
        emailAddress = scanner.nextLine();
        System.out.println("Please enter the location: ");
        location = scanner.nextLine();
        System.out.println("Simulating the analysis of the rental request.");
        bikeID = analyseRequest(isRegisteredUser, emailAddress, location);
        if(locationValid){
            return;
        }
        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);
        System.out.println("Displaying the active rentals…");
        viewActiveRentals();
        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);
        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();
    }

    public void userManagementOptions() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to E-Ryder Administrator Panel.");
        System.out.println("What do you want to do?");
        System.out.println("\t1. Add New Users");
        System.out.println("\t2. View Registered Users");
        System.out.println("\t3. Remove Registered Users");
        System.out.println("\t4. Update Registered Users");
        System.out.println("\t6. Demo the Bike Rental System");
        System.out.println("\t7. View System Logs");
        System.out.println("\t8. Manage Pending Bike Requests");
        System.out.println("\t5. EXIT");
        
        //BikeRental bikeRental = new BikeRental();
        int choice = scanner.nextInt();
        scanner.nextLine();
        UserService userService = new UserService();
        switch (choice) {
            case 1:
                userService.addNewUsers();
                break;
            case 2:
                RentalService rentalService = new RentalService();
                rentalService.viewActiveRentals();
                break;
            case 3:
                RentalService rentalService2 = new RentalService();
                rentalService2.endRental("B001");
                break;
            case 4:
                userService.updateRegisteredUsers();
                break;
            case 5:
                System.out.println("Exiting...");
                break;
            case 6:
                simulateApplicationInput();
            case 7:
                BikeService bikeService = new BikeService();
                bikeService.viewSystemLogs();
            case 8:
                System.out.println("1. View Queue");
                System.out.println("2. Update Queue");
                System.out.println("3. EXIT");
                int queueChoice = scanner.nextInt();
                scanner.nextLine();
                switch(queueChoice){
                    case 1:
                        for(BikeRequest request : bikeRequestQueue){
                            System.out.println(request);
                        }
                        break;
                    case 2:
                        if(bikeRequestQueue.isEmpty()){
                            System.out.println("No pending bike requests at the moment.");
                        }
                        else {
                            bikeRequestQueue.poll();
                            System.out.println("The oldest bike request has been removed from the queue.");
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again");
                }
            default:
                System.out.println("Invalid choice. Please try again");
        }

        scanner.close();
    }

    
}
    /**private void addNewUsers() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How many users the admin would like to add");
        int count = scanner.nextInt();

        while (count > 0) {
            scanner.nextLine();

            System.out.println("Please enter the name: ");
            String name = scanner.nextLine();

            System.out.println("Please enter the email address: ");
            String email = scanner.nextLine();

            System.out.println("Please enter the date of birth: ");
            String date = scanner.nextLine();

            System.out.println("Please enter the card number: ");
            Long cardNum = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Please enter the card provider: ");
            String cardProvider = scanner.nextLine();

            System.out.println("Please enter the card expiry date: ");
            String expirydate = scanner.nextLine();

            System.out.println("Please enter the cvv: ");
            int cvv = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Please enter the user type: ");
            String userType = scanner.nextLine();

            RegisteredUsers user = new RegisteredUsers(name, email, date, cardNum, expirydate, cardProvider, cvv, userType);

            for (int i = 0; i < 3; i++) {
                System.out.println("\nTrip " + (i + 1));

                System.out.println("Please enter the date of trip (YYYY-MM-DD): ");
                String dateOfTrip = scanner.nextLine();

                System.out.println("Please enter the source: ");
                String source = scanner.nextLine();

                System.out.println("Please enter the destination: ");
                String destination = scanner.nextLine();

                System.out.println("Please enter the fare: ");
                double cost = scanner.nextDouble();
                scanner.nextLine();

                System.out.println("Please enter your feedback: ");
                String feedback = scanner.nextLine();

                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("Date: ").append(dateOfTrip)
                        .append(", Source: ").append(source)
                        .append(", Destination: ").append(destination)
                        .append(", Fare (€): ").append(cost)
                        .append(", Feedback: ").append(feedback);

                user.lastThreeTrips[i] = tripInfo.toString();
            }

            registeredUsersList.add(user);
            System.out.println("User added successfully!");
            count--;
        }
    }

    private void viewRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to display");
            return;
        }

        System.out.println("\nAll Registered Users");

        for (RegisteredUsers user : registeredUsersList) {
            System.out.println("Name: " + user.getFullname());
            System.out.println("Email: " + user.getEmailAddress());
            System.out.println("DOB: " + user.getDateOfBirth());
            System.out.println("Card No: " + user.getCardNumber());
            System.out.println("Card Provider: " + user.getCardProvider());
            System.out.println("Expiry: " + user.getCardExpiryDate());
            System.out.println("CVV: " + user.getCvv());
            System.out.println("User Type: " + user.getUserType());

            System.out.println("Last Three Trips");
            for (String trip : user.lastThreeTrips) {
                System.out.println("- " + trip);
            }
        }
    }

    private void removeRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to remove");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email to remove: ");
        String email = scanner.nextLine();

        Iterator<RegisteredUsers> iterator = registeredUsersList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            RegisteredUsers user = iterator.next();
            if (user.getEmailAddress().equals(email)) {
                iterator.remove();
                found = true;
                System.out.println("User removed successfully");
                break;
            }
        }

        if (!found) {
            System.out.println("No user found with this email address");
        }
    }

    private void updateRegisteredUsers() {
        if (registeredUsersList.isEmpty()) {
            System.out.println("No registered users to update");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email to update: ");
        String email = scanner.nextLine();

        RegisteredUsers target = null;

        for (RegisteredUsers user : registeredUsersList) {
            if (user.getEmailAddress().equals(email)) {
                target = user;
                break;
            }
        }

        if (target == null) {
            System.out.println("No user found with this email address");
            return;
        }

        System.out.println("\nUpdate User");

        System.out.println("New name");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            target.setFullname(name);
        }

        System.out.println("New email");
        String newEmail = scanner.nextLine();
        if (!newEmail.isEmpty()) {
            target.setEmailAddress(newEmail);
        }

        System.out.println("New DOB");
        String dob = scanner.nextLine();
        if (!dob.isEmpty()) {
            target.setDateOfBirth(dob);
        }

        System.out.println("New card number");
        long card = scanner.nextLong();
        scanner.nextLine();
        if (card != 0) {
            target.setCardNumber(card);
        }

        System.out.println("New card provider");
        String provider = scanner.nextLine();
        if (!provider.isEmpty()) {
            target.setCardProvider(provider);
        }

        System.out.println("New expiry (ENTER for no change): ");
        String expiry = scanner.nextLine();
        if (!expiry.isEmpty()) {
            target.setCardExpiryDate(expiry);
        }

        System.out.println("New CVV");
        int cvv = scanner.nextInt();
        scanner.nextLine();
        if (cvv != 0) {
            target.setCvv(cvv);
        }

        System.out.println("New user type");
        String type = scanner.nextLine();
        if (!type.isEmpty()) {
            target.setUserType(type);
        }

        System.out.println("User updated successfully!");
    }
}*/