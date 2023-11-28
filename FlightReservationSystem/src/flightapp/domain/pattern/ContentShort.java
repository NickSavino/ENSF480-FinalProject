package flightapp.domain.pattern;

public class ContentShort implements ContentStrategy {
    @Override
    public String generateContent() {
        return "Book flights with our ENSF480 Airline for each today!";
    }
}
