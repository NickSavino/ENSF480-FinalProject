package flightapp.domain.pattern;

import java.util.HashMap;
import java.util.Map;

public class LoginSingleton {
    private static LoginSingleton onlyInstance;
    private Map<String, String> usernameToPassword;
    private Map<Integer, String> employeeIdToPassword;

    private LoginSingleton() {
        usernameToPassword = new HashMap<>();
        employeeIdToPassword = new HashMap<>();

        onlyInstance = this;
    }

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
        this.usernameToPassword.put(customerUsername, password);
    }

    // Method to authenticate a customer
    public boolean authenticateCustomer(String username, String password)
    {
        String storedPassword = this.usernameToPassword.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }

    // Method to authenticate an employee
    public boolean authenticateEmployee(int employeeId, String password)
    {
        String storedPassword = this.employeeIdToPassword.get(employeeId);
        return storedPassword != null && storedPassword.equals(password);
    }
}
