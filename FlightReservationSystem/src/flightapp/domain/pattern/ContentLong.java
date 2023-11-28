package flightapp.domain.pattern;

public class ContentLong implements ContentStrategy {
    @Override
    public String generateContent() {
        return "New ENSF480 Airline offers everyday! Book your flight for the highest quality, best priced airline around. We offer flights all around the world and offer package deals for cheap!" + 
            " Sign up today to become an airline member and get free companion vouchers! Airline members get discounted lounge access prices!";
    }
}
