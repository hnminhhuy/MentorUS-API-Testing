<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Create task</name>
   <tag></tag>
   <elementGuidId>a96a443c-869f-40d6-88e4-70fd99728008</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n  \&quot;title\&quot;: \&quot;${title}\&quot;,\n  \&quot;description\&quot;: \&quot;${description}\&quot;,\n  \&quot;deadline\&quot;: \&quot;${deadline}\&quot;,\n  \&quot;parentTask\&quot;: \&quot;\&quot;,\n  \&quot;groupId\&quot;: \&quot;${groupId}\&quot;,\n  \&quot;userIds\&quot;: [${userIds}]\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>05927c78-1649-4398-8fea-66bf97d6ffb9</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.6.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>${GlobalVariable.baseUrl}/api/tasks</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>'Prepare document for the final test'</defaultValue>
      <description></description>
      <id>2f10dddd-cc1f-4229-8483-b6a35fe0135a</id>
      <masked>false</masked>
      <name>title</name>
   </variables>
   <variables>
      <defaultValue>'Design a collection of questions for team to review all knowledge'</defaultValue>
      <description></description>
      <id>458eb1a6-1afe-44a7-92f6-a1f3b80337cd</id>
      <masked>false</masked>
      <name>description</name>
   </variables>
   <variables>
      <defaultValue>'2024-08-22T16:43:45.691Z'</defaultValue>
      <description></description>
      <id>68c827eb-fef9-4768-8376-e0b01547a358</id>
      <masked>false</masked>
      <name>deadline</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.defaultChannel</defaultValue>
      <description></description>
      <id>fce9a026-7a71-46c4-8424-67843b3ab320</id>
      <masked>false</masked>
      <name>groupId</name>
   </variables>
   <variables>
      <defaultValue>'&quot;6f9525d5-5948-47d3-8576-1dbbe1522f3b&quot;'</defaultValue>
      <description></description>
      <id>99b495ad-cd4d-41d1-b25a-4c887777b6ab</id>
      <masked>false</masked>
      <name>userIds</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
