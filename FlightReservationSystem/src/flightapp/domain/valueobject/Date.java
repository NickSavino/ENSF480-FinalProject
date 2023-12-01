package flightapp.domain.valueobject;

public class Date {

    private int day;
    private int month;
    private int year;

    private int hour = -1;
    private int minutes = -1;

    public Date(int day, int month, int year) 
    {
        if (day > 31 || day < 1)
        {
            throw new IllegalArgumentException("Invalid day");
        }
        if (month > 12 || month < 1)
        {
            throw new IllegalArgumentException("Invalid month");
        }
        if (year < 0)
        {
            throw new IllegalArgumentException("Invalid year");
        }
        this.day = day;
        this.month = month;
        this.year = year;

    }

    public Date(int day, int month, int year, int hour, int minutes) 
    {
        if (day > 31 || day < 1)
        {
            throw new IllegalArgumentException("Invalid day");
        }
        if (month > 12 || month < 1)
        {
            throw new IllegalArgumentException("Invalid month");
        }
        if (year < 0)
        {
            throw new IllegalArgumentException("Invalid year");
        }
        if (hour > 23 || hour < 0)
        {
            throw new IllegalArgumentException("Invalid hour");
        }
        if (minutes > 59 || minutes < 0)
        {
            throw new IllegalArgumentException("Invalid minutes");
        }
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minutes = minutes;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public int getDay() {
        return this.day;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minutes;
    }
}
