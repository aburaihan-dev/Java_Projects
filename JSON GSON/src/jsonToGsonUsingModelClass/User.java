package jsonToGsonUsingModelClass;

/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class User extends Person {

    private String username;
    private String email;
    private String password;

    public User(String person_Name, String person_gender, int person_Age, Address address, String username, String email, String password) {
        super(person_Name, person_gender, person_Age, address);
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("User Info: ################\nUsername: %s\nPassword: %s\nEmail: %s\n%s",username,password,email,super.toString()) ;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
