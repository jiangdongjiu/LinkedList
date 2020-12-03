// Create a Employee class
public class Employee implements Comparable<Employee>{
    private int employeeId;
    private String  firstName;
    private String lastName;

    // Getters
    public int getEmployeeID() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    // Constructors
    public Employee(int employeeId) {
        this.employeeId = employeeId;
        this.firstName = null;
        this.lastName = null;
    }

    public Employee(int employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int compareTo(Employee employee) {
        Integer employee1 = this.employeeId;
        Integer employee2 = employee.employeeId;

        return employee1.compareTo(employee2);
    }


    // toString

    @Override
    public String toString() {
        return employeeId + " " + firstName + " " + lastName;
    }



}