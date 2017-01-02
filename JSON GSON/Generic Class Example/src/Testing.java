import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Created by msrabon on 12-Nov-16.
 * Project Name: JSON GSON.
 */
public class Testing {
    public static void main(String[] args) {
        Address address = new Address("43/5/1","Abdul High Road","Jhigatola,West Dhanmondi","Dhaka - 1209");
        Key<Address> addressKey = new Key<>(address,"Address");

        Gson gson = new Gson();

        String json = gson.toJson(addressKey);

        System.out.println(addressKey);

        System.out.println(json);
    }
}