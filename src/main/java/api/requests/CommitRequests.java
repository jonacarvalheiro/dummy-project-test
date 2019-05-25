package api.requests;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.json.simple.parser.ParseException;
import api.RestAssuredWrapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CommitRequests extends RestAssuredWrapper {

    private Map<String, Object> commitConfiguration;

    public CommitRequests() throws IOException, ParseException {
        commitConfiguration = getConfigurator().getService("commit");
    }

    public Response getStatus() {
        String baseUrl = getConfigurator().getBaseUrl();
        String url = baseUrl
                .concat("//" + commitConfiguration.get("username"))
                .concat("/" + commitConfiguration.get("projectName"))
                .concat("/commit/" + commitConfiguration.get("uuid"));
        Response response = getRestClient()
                .get(url);
        try {
            response.then().statusCode(200);
        }
        catch (AssertionError e){
            System.out.println(response.then().contentType(ContentType.JSON).extract().response().jsonPath().get().toString());
            throw e;
        }
        return response;
    }
}
