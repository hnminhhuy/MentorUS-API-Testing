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
import java.util.UUID as UUID
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.ResponseObject as ResponseObject

String projectFolder = RunConfiguration.getProjectDir()
String filePath = file != '' ? (projectFolder + '/') + file : ''
RequestObject req = null
if (file == '') {
	req = findTestObject('API_Send image to a channel - Without file', [('groupId') : groupId, ('senderId') : senderId, ('id') : UUID.randomUUID().toString()])
} else {
	req = findTestObject('API_Send image to a channel', [('id') : generateID ? UUID.randomUUID().toString() : '', ('groupId') : groupId, ('senderId') : senderId, ('filePath') : filePath])
}
// Modify the Authorization header
TestObjectProperty authorizationHeader = new TestObjectProperty('Authorization', ConditionType.EQUALS, 'Bearer ' + GlobalVariable.accessToken)

// Replace the existing headers or add if not present
req.getHttpHeaderProperties().removeIf({ 
        it.getName() == 'Authorization'
    })

req.getHttpHeaderProperties().add(authorizationHeader)

res = WS.sendRequest(req)

WS.verifyResponseStatusCode(res, statusCode.intValue())


