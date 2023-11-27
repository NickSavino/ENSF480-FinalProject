package flightapp.domain.pattern;

import java.util.Map;

public class LoginSingleton {
    private static LoginSingleton onlyInstance;
    private Map<String, String> customerIdToPassword;
    private Map<Integer, String> employeeIdToPassword;

    public static LoginSingleton getOnlyInstance()
    {
        if (onlyInstance == null)
        {
            onlyInstance = new LoginSingleton();
        }
        return onlyInstance;
    }

    // Method to add an employee to the system
    public void addEmployee(int employeeId, String password)
    {
        this.employeeIdToPassword.put(employeeId, password);
    }

    // Method to add a customer to the system
    public void addCustomer(String customerUsername, String password)
    {
        this.customerIdToPassword.put(customerUsername, password);
    }

    // Method to authenticate a customer
    public boolean authenticateCustomer(String customerId, String password)
    {
        if (this.customerIdToPassword.get(customerId).equals(password))
        {
            return true;
        }
        return false;
    }

    // Method to authenticate an employee
    public boolean authenticateEmployee(int employeeId, String password)
    {
        if (this.employeeIdToPassword.get(employeeId).equals(password))
        {
            return true;
        }
        return false;
    }
}
