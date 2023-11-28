package flightapp.domain.pattern;

public class ContentShort implements ContentStrategy {
    @Override
    public String generateContent() {
        return "This is short promotional content.";
    }
}
