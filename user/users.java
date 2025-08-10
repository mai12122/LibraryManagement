package user;

import java.util.ArrayList;
import java.util.Scanner;
import minterface.Authentication;

public abstract class users implements Authentication {
    private static int accountCount = 1; 
    public static ArrayList<users> accountList = new ArrayList<>(); // Store all users

    private String id;
    private String name;
    private String password;
    private String email;
    private String phoneNumber;

    public users() {
        register();
    }

    public users(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setId(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        if (name == null || name.isEmpty()) {
            this.name = "defaultUser";
        } else {
            this.name = name;
        }
    }

    protected void setPassword(String password) {
        if (password == null || password.length() < 6) {
            System.out.println("Password must be 8 characters long.");
            this.password = "12345678";
        } else {
            this.password = password;
        }
    }

    protected void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            System.out.println("Invalid email format.");
            this.email = null;
        } else {
            this.email = email;
        }
    }

    protected void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            System.out.println("Invalid phone number format. It should be 10 digits.");
            this.phoneNumber = null;
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Registering user with ID: " + accountCount);

        setId(String.valueOf(accountCount++));
        System.out.print("Enter name: ");
        setName(sc.nextLine());

        System.out.print("Enter password: ");
        setPassword(sc.nextLine());

        System.out.print("Enter  email: ");
        setEmail(sc.nextLine());

        System.out.print("Enter phone number: ");
        setPhoneNumber(sc.nextLine());

        accountList.add(this);
        System.out.println("User registered successfully!");
    }

    @Override
    public String toString() {
        return "User [ID=" + id + ", Name=" + name + ", Password=" + password +
               ", Email=" + email + ", Phone=" + phoneNumber + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof users) {
            users other = (users) obj;
            return this.email != null && this.password != null &&
                   this.email.equals(other.email) &&
                   this.password.equals(other.password);
        }
        return false;
    }

    public void displayInfo() {
        System.out.println("User ID  : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Email    : " + email);
        System.out.println("Phone    : " + phoneNumber);
    }
}
