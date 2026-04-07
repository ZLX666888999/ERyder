package ERyder;
import java.util.*;

public class UserService{
    public UserService() {
    }
    private ArrayList<RegisteredUsers> registeredUsersList = new ArrayList<>();
    public void addNewUsers() {
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

            RegisteredUsers user; 
            if (userType.equalsIgnoreCase("VIP")) {
                user = new VIPUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv);}
            else {
                user = new RegularUser(fullName, emailAddress, dateOfBirth, cardNumber, cardExpiryDate, cardProvider, cvv, "Regular");
            }
            registeredUsersList.add(user);

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
        scanner.close();
    }
    public void removeRegisteredUsers() {
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
        scanner.close();
    }

    public void updateRegisteredUsers() {
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
        scanner.close();
    }
    
}