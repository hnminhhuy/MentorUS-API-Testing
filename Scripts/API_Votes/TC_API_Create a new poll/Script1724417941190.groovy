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
import java.util.List as List
import java.util.ArrayList as ArrayList
import java.util.HashMap 
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.HttpBodyContent
import com.kms.katalon.core.testobject.impl.HttpTextBodyContent
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.testobject.TestObject as TestObject
import java.util.UUID
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter
import java.time.temporal.ChronoUnit as ChronoUnit
import groovy.json.JsonSlurper

// Create a header
List <TestObjectProperty> httpHeaderProperties = new ArrayList()
httpHeaderProperties.add(new TestObjectProperty("Content-Type", ConditionType.EQUALS, 'application/json' ))
httpHeaderProperties.add(new TestObjectProperty("Authorization", ConditionType.EQUALS, 'Bearer ' + GlobalVariable.accessToken))

//Create the request body
HashMap<String, Object> resBody = new HashMap()
resBody.put('question', question)
resBody.put('groupId', groupId)
resBody.put('creatorId', creatorId)
String isoTime = ''
if (threshold != null) {
	LocalDateTime currentTime = LocalDateTime.now()
	
	LocalDateTime newTime = currentTime.plus(threshold.intValue(), ChronoUnit.MINUTES)
	
	// Convert to ISO 8601 format with milliseconds and add 'Z' manually
	isoTime = newTime.format(DateTimeFormatter.ofPattern('yyyy-MM-dd\'T\'HH:mm:ss.SSS')) + 'Z'
}
resBody.put('timeEnd', isoTime)
List<Object> choiceList = new ArrayList()
String[] opts = choices.split(', ')
if (opts.length > 0) {
	for (String opt : opts) {
		HashMap<String, Object> option = new HashMap()
		option.put('id', UUID.randomUUID().toString())
		option.put('name', opt)
		option.put('voters', new ArrayList())
		choiceList.add(option)
	}
}
resBody.put('choices', choiceList)
resBody.put('isMultipleChoice', isMultipleChoice)

// Convert into Json
def jsonBodyContent = new groovy.json.JsonBuilder(resBody)
String bodyContent = jsonBodyContent.toString()
HttpTextBodyContent httpBodyContent = new HttpTextBodyContent(bodyContent)

//// Create a request
RequestObject req = new RequestObject('Create a new poll')
req.setBodyContent(httpBodyContent)
req.setServiceType('REST')
req.setHttpHeaderProperties(httpHeaderProperties)
req.setRestUrl(GlobalVariable.baseUrl + '/api/votes')
req.setRestRequestMethod('POST')

res = WS.sendRequest(req)

WS.verifyResponseStatusCode(res, statusCode.intValue())
if (statusCode.intValue() == 200) {
	WS.verifyElementPropertyValue(res, 'groupId', groupId, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(res, 'question', question, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(res, 'creatorId', creatorId, FailureHandling.CONTINUE_ON_FAILURE)
	WS.verifyElementPropertyValue(res, 'isMultipleChoice', isMultipleChoice,FailureHandling.CONTINUE_ON_FAILURE)	
	// Parse the JSON response
	def jsonResponse = new JsonSlurper().parseText(res.getResponseBodyContent())
	List<String> responseNames = jsonResponse.choices.collect { it.name }
	println(responseNames)
	opts.sort()
	responseNames.sort()
	WS.verifyEqual(opts, responseNames, FailureHandling.CONTINUE_ON_FAILURE)
}

