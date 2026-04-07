package ERyder;
public class Main2{
    public static void main(String[] args) {
        String sent1 = "I was very satisfied with the service.";
        String sent2 = "The e-Bike is quite comfortable to ride.";
        String sent3 = "The battery life of the e-Bike is impressive.";
        String sent4 = "The customer support was helpful and responsive.";
        String sent5 = "I would recommend this e-Bike to my friends and family.";

        Feedback feedback = new Feedback("Zhang", "San", "zhangsan@example.com");
        feedback.analyseFeedback(false, sent1, sent2, sent3, sent4, sent5);
        System.out.println(feedback);
    }
}