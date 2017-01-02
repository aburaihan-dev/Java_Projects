package jsonToGsonUsingModelClass;

/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class Person {
    private String person_Name;
    private String person_gender;
    private int person_Age;
    private Address address;

    public Person(String person_Name, String person_gender, int person_Age, Address address) {
        this.person_Name = person_Name;
        this.person_gender = person_gender;
        this.person_Age = person_Age;
        this.address = address;
    }

    public String getPerson_Name() {
        return person_Name;
    }

    public void setPerson_Name(String person_Name) {
        this.person_Name = person_Name;
    }

    public int getPerson_Age() {
        return person_Age;
    }

    public void setPerson_Age(int person_Age) {
        this.person_Age = person_Age;
    }

    public String getPerson_gender() {
        return person_gender;
    }

    public void setPerson_gender(String person_gender) {
        this.person_gender = person_gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("Personal Information: ###########\nName: %s\nGender: %s\nAge: %d\n%s",person_Name,person_gender,person_Age,address);
    }
}
