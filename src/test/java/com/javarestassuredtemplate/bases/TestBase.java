package com.javarestassuredtemplate.bases;

import com.javarestassuredtemplate.GlobalParameters;
import com.javarestassuredtemplate.steps.AutenticacaoSteps;
import com.javarestassuredtemplate.utils.ExtentReportsUtils;
import org.codehaus.groovy.transform.tailrec.GotoRecurHereException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public abstract class TestBase {
    @BeforeSuite
    public void beforSuite(){
        new GlobalParameters();
        ExtentReportsUtils.createReport();
        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); ==> caso a geração de token deva ser feita quando iniciar a bateria de testes
    }

    @BeforeMethod
    public void beforeTest(Method method){
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
        //AutenticacaoSteps.gerarToken(GlobalParameters.AUTHENTICATOR_USER, GlobalParameters.AUTHENTICATOR_PASSWORD); ==> caso a geração de token deva ser feita antes de iniciar cada teste
    }

    @AfterMethod
    public void afterTest(ITestResult result){
        ExtentReportsUtils.addTestResult(result);
    }

    @AfterSuite
    public void afterSuite(){
        ExtentReportsUtils.generateReport();
    }
}
