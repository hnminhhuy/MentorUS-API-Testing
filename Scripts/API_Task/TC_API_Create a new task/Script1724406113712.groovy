import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.temporal.ChronoUnit as ChronoUnit

String isoTime = ''

if (deadlineThreshold != null) {
	LocalDateTime currentTime = LocalDateTime.now()
	
	LocalDateTime newTime = currentTime.plus(deadlineThreshold.intValue(), ChronoUnit.MINUTES)
	
	// Convert to ISO 8601 format with milliseconds and add 'Z' manually
	isoTime = newTime.format(DateTimeFormatter.ofPattern('yyyy-MM-dd\'T\'HH:mm:ss.SSS')) + 'Z'
}

println(isoTime)

RequestObject req = findTestObject('Create task', [('title') : title, ('description') : description, ('deadline') : isoTime, ('groupId') : groupId, ('userIds') : userIds])

// Modify the Authorization header
TestObjectProperty authorizationHeader = new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer ' + GlobalVariable.accessToken)

// Replace the existing headers or add if not present
req.getHttpHeaderProperties().removeIf({ 
        it.getName() == 'Authorization'
    })

req.getHttpHeaderProperties().add(authorizationHeader)

ResponseObject res = WS.sendRequest(req)

WS.verifyResponseStatusCode(res, statusCode.intValue())
WS.verifyElementPropertyValue(res, 'success', success, FailureHandling.CONTINUE_ON_FAILURE)

if (success) {
	WS.verifyElementPropertyValue(res, 'data.title', title, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(res, 'data.description', description, FailureHandling.CONTINUE_ON_FAILURE)
}