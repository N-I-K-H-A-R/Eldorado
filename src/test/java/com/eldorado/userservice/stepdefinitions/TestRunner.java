package com.eldorado.userservice.stepdefinitions;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/registration.feature",
							  "src/test/resources/features/forgotPassword.feature" }, glue = {
				"com.eldorado.userservice.stepdefinitions" }, monochrome = true, plugin = { "pretty",
						"junit:target/JUnitReports/report.xml", "pretty", "html:target/HtmlReports/index.html",
						"pretty", "json:target/JsonReports/report.json" })
public class TestRunner {

}
