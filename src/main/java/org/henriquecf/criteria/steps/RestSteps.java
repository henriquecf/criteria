package org.henriquecf.criteria.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestSteps {

    @Given("os seguintes headers: $headers")
    public void setHeaders(String headers) {
        org.junit.Assert.assertThat(headers, equalTo("[]"));
    }

    @Given("o seguinte corpo JSON: $json")
    public void setCorpoJson(String json) {
        org.junit.Assert.assertThat(json, equalTo("[]"));
    }

    @When("eu der um $metodo na url $url")
    public void setMetodoEUrl(String metodo, String url) {
        org.junit.Assert.assertThat(metodo, equalTo("get"));
        org.junit.Assert.assertThat(url, equalTo("http://google.com"));
    }
    
}
