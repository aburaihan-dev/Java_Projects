package jsonToGsonUsingModelClass;

import com.google.gson.Gson;

/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class Test {
    public static void main(String[] args) {
        Address address = new Address("43/5/1","Abdul High Road","Jhigatola,West Dhanmondi","Dhaka - 1209");
//        System.out.println(address);
        User user = new User("Abu Raihan","Male",23,address,"cyborn13x","123456","abu@gmail.com");
        System.out.println(user);

        Gson gson = new Gson();

        System.out.println(gson.toJson(user));
    }
}
