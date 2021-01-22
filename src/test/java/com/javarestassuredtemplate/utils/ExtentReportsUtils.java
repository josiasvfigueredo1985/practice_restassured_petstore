package com.javarestassuredtemplate.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.enums.AuthenticationType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.ITestResult;

import java.util.Map;

public class ExtentReportsUtils
{
    public static ExtentReports EXTENT_REPORT = null;
    public static ExtentTest TEST;
    public static ExtentHtmlReporter HTML_REPORTER = null;
    static String reportName = GlobalParameters.REPORT_NAME + "_" + GeneralUtils.getNowDate("yyyy-MM-dd_HH-mm-ss");
    static String reportsPath = GlobalParameters.REPORT_PATH;
    static String fileName = reportName+".html";
    static String fullReportFilePath = reportsPath + "/"+ reportName +"/" + fileName;

    public static void createReport(){
        if (EXTENT_REPORT == null){
            HTML_REPORTER = new ExtentHtmlReporter(fullReportFilePath);
            EXTENT_REPORT = new ExtentReports();
            EXTENT_REPORT.attachReporter(HTML_REPORTER);
        }
    }

    public static void addTest(String testName, String testCategory){
        TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests",""));
    }

    public static void addRestTestInfo(int methodLevel, String text){
        TEST.log(Status.PASS, GeneralUtils.getMethodNameByLevel(methodLevel) + " || " + text);
    }

    public static void addRestTestInfo(String url, String requestService, String method, Map<String, String> headers, Map<String, String> cookies, Map<String, String> queryParameters, String jsonBody, AuthenticationType authenticationType, String authenticatorUser, String authenticatorPassword, Response response){
        String allHeaders = "";
        String allCookies = "";
        String allParameters = "";
        String allResponseHeaders = "";

        for(Map.Entry<String, String> queryParameter : queryParameters.entrySet()){
            allParameters = allParameters + "\n" + "<i>" + queryParameter.getKey() + "</i>" + " = " + queryParameter.getValue();
        }

        for(Map.Entry<String, String> header : headers.entrySet()){
            allHeaders = allHeaders + "\n" + "<i>" + header.getKey() + "</i>" + " = " + header.getValue();
        }

        for(Map.Entry<String, String> cookie : cookies.entrySet()){
            allCookies = allCookies + "\n" + "<i>" + cookie.getKey() + "</i>" + " = " + cookie.getValue();
        }

        for(Header responseHeader : response.getHeaders().asList()){
            allResponseHeaders = allResponseHeaders + "\n" + responseHeader.getName() + ": " + responseHeader.getValue();
        }

        TEST.log(Status.INFO, "<pre>" + "<b>URL: </b>" + url + "</pre>");
        TEST.log(Status.INFO, "<pre>" + "<b>REQUEST: </b>" + requestService + "</pre>");
        TEST.log(Status.INFO, "<pre>" + "<b>METHOD: </b>" + method + "</pre>");

        if (!allParameters.equals("")){
            TEST.log(Status.INFO, "<pre>" + "<b>PARAMETERS: </b>" + "\n" + allParameters + "</pre>");
        }

        if(jsonBody!=null){
            TEST.log(Status.INFO, "<pre>" + "<b>JSON BODY: </b>" + "\n" + jsonBody + "</pre>");
        }

        if (!allHeaders.equals("")){
            TEST.log(Status.INFO, "<pre>" + "<b>HEADERS: </b>" + "\n" + allHeaders + "</pre>");
        }

        if (!allCookies.equals("")){
            TEST.log(Status.INFO, "<pre>" + "<b>COOKIES: </b>" + "\n" + allCookies + "</pre>");
        }

        if (!authenticationType.equals(AuthenticationType.NONE)){
            TEST.log(Status.INFO, "<pre>" + "<b>AUTHENTICATOR TYPE: "+authenticationType.toString()+"</b>" + "\n" + "<b>USER: </b>"+ authenticatorUser + "\n" + "<b>PASSWORD: </b>" + authenticatorPassword + "</pre>");
        }

        TEST.log(Status.INFO, "<pre>" + "<b>STATUS CODE: </b>" + response.statusCode() + "</pre>");
        TEST.log(Status.INFO, "<pre>" + "<b>RESPONSE HEADERS: </b>" + "\n" + allResponseHeaders + "</pre>");
        try {
            TEST.log(Status.INFO, "<pre>" + "<b>PAYLOAD: </b>" + "\n" + GeneralUtils.formatJson(response.body().jsonPath().get().toString()) + "</pre>");
        }catch(io.restassured.path.json.exception.JsonPathException e){

        }
    }

    public static void addSoapTestInfo(String url, Map<String, String> headers, Map<String, String> cookies, String xmlBody, AuthenticationType authenticationType, String authenticatorUser, String authenticatorPassword, Response response){
        String allHeaders = "";
        String allCookies = "";
        String allResponseHeaders = "";

        for(Map.Entry<String, String> header : headers.entrySet()){
          allHeaders = allHeaders + "\n" + "<i>" + header.getKey() + "</i>" + " = " + header.getValue();
        }

        for(Map.Entry<String, String> cookie : cookies.entrySet()){
          allCookies = allCookies + "\n" + "<i>" + cookie.getKey() + "</i>" + " = " + cookie.getValue();
        }

        for(Header responseHeader : response.getHeaders().asList()){
          allResponseHeaders = allResponseHeaders + "\n" + responseHeader.getName() + ": " + responseHeader.getValue();
        }

        TEST.log(Status.INFO, "<pre>" + "<b>URL: </b>" + url + "</pre>");
        TEST.log(Status.INFO, "<pre>" + "<b>REQUEST: </b><textarea>" + xmlBody + "</textarea>");

        if (!allHeaders.equals("")){
            TEST.log(Status.INFO, "<pre>" + "<b>HEADERS: </b>" + "\n" + allHeaders + "</pre>");
        }

        if (!allCookies.equals("")){
            TEST.log(Status.INFO, "<pre>" + "<b>COOKIES: </b>" + "\n" + allCookies + "</pre>");
        }

        if (!authenticationType.equals(AuthenticationType.NONE)){
            TEST.log(Status.INFO, "<pre>" + "<b>AUTHENTICATOR TYPE: "+authenticationType.toString()+"</b>" + "\n" + "<b>USER: </b>"+ authenticatorUser + "\n" + "<b>PASSWORD: </b>" + authenticatorPassword + "</pre>");
        }

        TEST.log(Status.INFO, "<pre>" + "<b>STATUS CODE: </b>" + response.statusCode() + "</pre>");
        TEST.log(Status.INFO, "<pre>" + "<b>RESPONSE HEADERS: </b>" + "\n" + allResponseHeaders + "</pre>");
        TEST.log(Status.INFO, "<b>PAYLOAD: </b> <textarea>" + "\n" + GeneralUtils.formatJson(response.body().jsonPath().get().toString()) + "</textarea>");
    }

    public static void addTestResult(ITestResult result){
        switch (result.getStatus())
        {
            case ITestResult.FAILURE:
                TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
                break;
            case ITestResult.SKIP:
                TEST.log(Status.SKIP, "Test Result: " + Status.SKIP + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
                break;
            default:
                TEST.log(Status.PASS, "Test Result: " + Status.PASS);
                break;
        }
    }

    public static void generateReport(){
        EXTENT_REPORT.flush();
    }
}
