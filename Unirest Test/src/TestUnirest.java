import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Created by msrabon on 25-Dec-16.
 * Project Name: Unirest Test.
 */
public class TestUnirest {
    public static void main(String[] args) throws UnirestException {
        String helo = "hello";

        String url =   "http://www.mocky.io/v2/586025e60f0000a0179c9a25";//"cyborn13x.byethost18.com/php-crud-api/api.php";

        HttpResponse<String> jsonResponse = Unirest.get(url).asString();
//        HttpResponse<JsonNode> response = Unirest.get(url).getBody().

        System.out.println(jsonResponse.getStatusText());
        System.out.println(jsonResponse.getBody());
    }
}
