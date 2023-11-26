package flightapp.domain.valueobject;

public class CompanionVoucher {

    private boolean usable;

    public CompanionVoucher() {
        this.usable = true;
    }

    public void use()
    {
        this.usable = false;
    }

    public void renew()
    {
        this.usable = true;
    }

    public boolean isUsable()
    {
        return this.usable;
    }
}
