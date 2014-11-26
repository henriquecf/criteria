package org.henriquecf.criteria.steps;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;

public class RestSteps {

    HttpResponse<JsonNode> jsonResponse;
    private HashMap<String, String> requestHeaders = new HashMap<String, String>();
    private String requestBody = "";

    @Given("o seguinte header $chave com valor $valor")
    public void setHeaders(String chave, String valor) {
        requestHeaders.put(chave, valor);
    }

    @Given("o seguinte corpo JSON: $json")
    public void setCorpoJson(String json) {
        requestBody = json;
    }

    @When("eu der um $metodo na url $url")
    public void sendMetodoEUrl(String metodo, String url) {
        if (metodo.equals("get")) {
            GetRequest request = Unirest.get(url);
            request.headers(requestHeaders);
            try {
                jsonResponse = request.asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        else if (metodo.equals("post")) {
            HttpRequestWithBody request = Unirest.post(url);
            request.body(requestBody);
            request.headers(requestHeaders);
            try {
                jsonResponse = request.asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException("Metodo de requisicao http nao existe: " + metodo);
        }
    }

    @Then("eu deveria receber o seguinte header $chave com valor $valor")
    public void checkHeaders(String chave, String valor) {
        Assert.assertTrue(jsonResponse.getHeaders().containsKey(chave));
        Assert.assertTrue(jsonResponse.getHeaders().containsValue(valor));
    }

    @Then("o seguinte corpo JSON: $json")
    public void checkJson(String json){
        Assert.assertEquals(jsonResponse.getBody(), new JsonNode(json));
    }

    @Then("o codigo de status $status")
    public void checkStatus(Integer status) {
        Assert.assertEquals((Integer)jsonResponse.getCode(), status);
    }
}
