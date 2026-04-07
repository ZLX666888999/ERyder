package ERyder;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UserRegistration {
    static final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    static final double VIP_DISCOUNT = 20.0;
    static final double VIP_BASE_FEE = 100.0;

    private String fullName;
    private String emailAddress;
    private String dateOfBirth;
    private long cardNumber;
    private String cardProvider;
    private String cardExpiryDate;
    private double feeToCharge;
    private int cvv;
    private String userType;
    private boolean emailValid;
    private boolean minorAndBirthday = false;
    private boolean minor;
    private boolean ageValid;
    private boolean cardNumberValid;
    private boolean cardStillValid;
    private boolean validCVV;

    public void registration() {
        System.out.println("Welcome to ERyder Registration.\nHere are your two options: \n1. Register as a Regular User \n2. Register as a VIP User\nPlease enter your choice(1 or 2):");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            userType = "Regular User";
        } else if (choice == 2) {
            userType = "VIP User";
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
            registration();
            return;
        }

        System.out.println("Let's proceed with the registration...\n");
        System.out.println("Please enter your full name:");
        fullName = scanner.nextLine();
        System.out.println("Please enter your Email Address");
        emailAddress = scanner.nextLine();
        System.out.println("Checking email validity...");
        emailValid = analyseEmail(emailAddress);
        System.out.println("Please enter your date of birth as YYYY-MM-DD:");
        dateOfBirth = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBirth);
        ageValid = analyseAge(dob);
        System.out.println("Please enter your card number:");
        System.out.println("Only Visa, MasterCard, and American Express are accepted.");
        cardNumber = scanner.nextLong();
        scanner.nextLine();
        cardNumberValid = analyseCardNumber(cardNumber);
        System.out.println("Please enter your card expiry date:");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);
        System.out.println("Please enter your card's cvv:");
        cvv = scanner.nextInt();
        scanner.nextLine();
        validCVV = analyseCVV(cvv);
        finalCheckpoint();
        scanner.close();
    }

    private boolean analyseEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            System.out.println("Email is valid.");
            return true;
        } else {
            System.out.println("Invalid email address. Going back to registration.");
            registration();
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob) {
        int age = (int) ChronoUnit.YEARS.between(dob, LocalDate.now());
        boolean isBirthdayToday = (LocalDate.now().getMonthValue() == dob.getMonthValue()
                && LocalDate.now().getDayOfMonth() == dob.getDayOfMonth());

        if (age < 0 || age > 120) {
            System.out.println("Looks like you are either too young or already dead. Sorry you can't be our user. Have a nice day.");
            System.exit(0);
        }

        if (0 <= age && age < 18) {
            minor = true;
            if (isBirthdayToday) {
                minorAndBirthday = true;
            }
        } else if (age >= 18) {
            minor = false;
            if (isBirthdayToday) {
                minorAndBirthday = false;
            }
        }

        if ("VIP User".equals(userType)) {
            if (age > 12 && age < 18 && isBirthdayToday) {
                System.out.println("Happy Birthday!\nYou get 25% subscription fee for being born today and under 18!");
                feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
            } else if (age > 12 && age < 18 && !isBirthdayToday) {
                System.out.println("You get 20% subscription fee for being under 18!");
                feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT / 100);
            } else if (age <= 12) {
                System.out.println("Looks like you are either too young or already dead. Sorry you can't be our user. Have a nice day.");
                System.exit(0);
            } else {
                feeToCharge = VIP_BASE_FEE;
            }
        } else {
            feeToCharge = 0.0;
        }
        ageValid = true;
        return ageValid;
    }

    private boolean analyseCardNumber(long cardNumber) {
        String cardNumStr = Long.toString(cardNumber);
        if (cardNumStr.length() < 2) {
            System.out.println("Sorry, but we accept only Visa, MasterCard, and American Express.\nGoing back to registration.");
            registration();
            return false;
        }
        int firstTwoDigits = Integer.parseInt(cardNumStr.substring(0, 2));
        int firstFourDigits = cardNumStr.length() >=4 ? Integer.parseInt(cardNumStr.substring(0, 4)) : 0;

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.charAt(0) == '4') {
            cardProvider = "Visa";
            return true;
        }
        else if (cardNumStr.length() == 16 && (firstTwoDigits >= 51 && firstTwoDigits <= 55 || (firstFourDigits >= 2221 && firstFourDigits <= 2720))) {
            cardProvider = "MasterCard";
            return true;
        }
        else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))) {
            cardProvider = "American Express";
            return true;
        }
        else {
            System.out.println("Sorry, but we accept only Visa, MasterCard, and American Express.\nGoing back to registration.");
            registration();
            return false;
        }
    }

    private boolean analyseCardExpiryDate(String expiryDate) {
        try {
            int month = Integer.parseInt(expiryDate.substring(0, 2));
            int year = Integer.parseInt(expiryDate.substring(3, 5)) + 2000;
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();

            if (month < 1 || month > 12) {
                throw new NumberFormatException();
            }
            if (year > currentYear || (year == currentYear && month >= currentMonth)) {
                System.out.println("The card is still valid.");
                return true;
            } else {
                System.out.println("Sorry, your card has expired. Please use a different card.\nGoing back to registration.");
                registration();
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid expiry date format. Please enter correct format.\nGoing back to registration.");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv) {
        String cvvStr = Integer.toString(cvv);
        if (cardProvider == null) {
            System.out.println("Card provider not found. Invalid CVV.\nGoing back to the start of registration process.");
            registration();
            return false;
        }
        if ((cardProvider.equals("American Express") && cvvStr.length() == 4)
                || (cardProvider.equals("Visa") && cvvStr.length() == 3)
                || (cardProvider.equals("MasterCard") && cvvStr.length() == 3)) {
            System.out.println("Card CVV is valid.");
            return true;
        } else {
            System.out.println("Invalid CVV for given card.\nGoing back to the start of registration process.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint() {
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV) {
            chargeFee();
        } else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reasons:");
            if (!emailValid) System.out.println("Invalid email address");
            if (!ageValid) System.out.println("Invalid age");
            if (!cardNumberValid) System.out.println("Invalid card number");
            if (!cardStillValid) System.out.println("Card has expired");
            if (!validCVV) System.out.println("Invalid CVV");
            System.out.println("Going back to the registration process.");
            registration();
        }
    }

    private void chargeFee() {
        if (minorAndBirthday) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        }
        else if (minor) {
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT / 100);
        }
        else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = Long.toString(cardNumber);
        String lastFourDigits = cardNumStr.substring(cardNumStr.length() - 4);

        System.out.println("Thank you for your payment.");
        System.out.printf("A fee of %.2f has been charged to your card ending with %s%n", feeToCharge, lastFourDigits);
    }

    @Override
    public String toString() {
        String cardNumberStr = Long.toString(cardNumber);
        String censoredPart = cardNumberStr.substring(0, cardNumberStr.length() - 4).replaceAll(".", "*");
        String lastFourDigits = cardNumberStr.substring(cardNumberStr.length() - 4);
        String censoredNumber = censoredPart + lastFourDigits;

        return "Registration successful! Here are your details:\n"
                + "User Type: " + userType + "\n"
                + "Full Name: " + fullName + "\n"
                + "Email Address: " + emailAddress + "\n"
                + "Date of Birth: " + dateOfBirth + "\n"
                + "Card Number: " + censoredNumber + "\n"
                + "Card Provider: " + cardProvider + "\n"
                + "Card Expiry Date: " + cardExpiryDate;
    }
}