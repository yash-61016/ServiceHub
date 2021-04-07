package uk.ac.tees.w9336459.servicehub;

public class Versions {

    private String Question,Answer;
    private boolean expandable;

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Versions(String question, String answer) {
        Question = question;
        Answer = answer;
        this.expandable=false;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "Question='" + Question + '\'' +
                ", Answer='" + Answer + '\'' +
                '}';
    }
}
