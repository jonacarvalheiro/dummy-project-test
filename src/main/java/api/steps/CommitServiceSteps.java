package api.steps;

import api.requests.CommitRequests;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.IOException;

public class CommitServiceSteps {

    private CommitRequests commitRequests = new CommitRequests();
    private Response response;

    public CommitServiceSteps() throws IOException, ParseException {
    }

    @Given("I want to get commit status")
    public void iWantToGetCommitStatus() {
        response = commitRequests.getStatus();
    }


    @Then("The status should be analysed")
    public void theStatusShouldBeCompleted() {

        Response json = response
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
        String status = json.jsonPath().getString("state");
        Assert.assertEquals("Analysed", status);
    }


}
