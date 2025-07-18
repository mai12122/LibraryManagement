package user;

public class users {
    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public users(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public users() {
    }
    public String getId() {
        System.out.println("getId() called, returning: " + id);
        return id;
    }

    public String getName() {
        System.out.println("getName() called, returning: " + name);
        return name;
    }

    public String getEmail() {
        System.out.println("getEmail() called, returning: " + email);
        return email;
    }

    public String getPassword() {
        System.out.println("getPassword() called, returning: " + password);
        return password;
    }
    protected void setId(String id) {
        System.out.println("setId() called, setting to: " + id);
        this.id = id;
    }

    protected void setName(String name) {
        System.out.println("setName() called, setting to: " + name);
        this.name = name;
    }

    protected void setEmail(String email) {
        System.out.println("setEmail() called, setting to: " + email);
        this.email = email;
    }

    protected void setPassword(String password) {
        System.out.println("setPassword() called, setting to: " + password);
        this.password = password;
    }

    public void displayInfo() {
        System.out.println("User ID  : " + id);
        System.out.println("Name     : " + name);
        System.out.println("Email    : " + email);
    }
}
