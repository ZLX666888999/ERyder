public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public void analyseFeedback(boolean isConcatenation, String sent1, String sent2,
                                String sent3, String sent4, String sent5) {
        if (isConcatenation) {
            this.completeFeedback = feedbackUsingConcatenation(sent1, sent2, sent3, sent4, sent5);
        } else {
            this.completeFeedback = feedbackUsingStringBuilder(sent1, sent2, sent3, sent4, sent5).toString();
        }
        this.longFeedback = checkFeedbackLength(this.completeFeedback);
        createReviewID(firstName, lastName, completeFeedback);
    }
    private String feedbackUsingConcatenation(String sent1, String sent2, String sent3,
                                              String sent4, String sent5) {
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }
    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3,
                                                     String sent4, String sent5) {
        StringBuilder sb = new StringBuilder();
        sb.append(sent1).append(sent2).append(sent3).append(sent4).append(sent5);
        return sb;
    }
    private boolean checkFeedbackLength(String completeFeedback) {
        return completeFeedback.length() > 500;
    }
    private void createReviewID(String firstName, String lastName, String completeFeedback) {
        String nameCombined = firstName + lastName;
        String nameSub = nameCombined.substring(2, 6).toUpperCase();
        String feedbackSub = completeFeedback.substring(10, 15).toLowerCase();
        int feedbackLen = completeFeedback.length();
        String lenPart = feedbackLen + "_";
        long timestamp = System.currentTimeMillis();
        String reviewIdTemp = nameSub + feedbackSub + lenPart + timestamp;
        this.reviewID = reviewIdTemp.replace(" ", "");
    }

    @Override
    public String toString() {
        return "Feedback Information:\n" +
                "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Email: " + email + "\n" +
                "Complete Feedback: " + completeFeedback + "\n" +
                "Is Long Feedback" + longFeedback + "\n" +
                "Review ID: " + reviewID;
    }
}