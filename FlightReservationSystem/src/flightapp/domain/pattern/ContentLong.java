package flightapp.domain.pattern;

public class ContentLong implements ContentStrategy {
    @Override
    public String generateContent() {
        return "<html><body style='text-align: center;'>" +
                "New ENSF480 Airline offers everyday!<br/>" +
                "Book your flight for the highest quality, best priced airline around.<br/>" +
                "We offer flights all around the world and offer package deals for cheap!<br/>" +
                "Sign up today to become an airline member and get free companion vouchers!<br/>" +
                "Airline members get discounted lounge access prices!" +
                "</body></html>";
    }
}
