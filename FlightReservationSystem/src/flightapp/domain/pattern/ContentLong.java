package flightapp.domain.pattern;

public class ContentLong implements ContentStrategy {
    @Override
    public String generateContent() {
        return "This is long, detailed promotional content.";
    }
}
