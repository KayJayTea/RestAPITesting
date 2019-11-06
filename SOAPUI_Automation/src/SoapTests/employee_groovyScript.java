package SoapTests;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.model.testsuite.TestRunner.Status;
import com.eviware.soapui.support.SoapUIException;

public class employee_groovyScript {
	
	@Test
	public void employeeWithScript() throws XmlException, IOException, SoapUIException {
		
		WsdlProject project = new WsdlProject("C:\\Users\\AAO8676\\Documents\\SOAPUI\\Automation-soapui-project.xml");
		WsdlTestSuite testSuite = project.getTestSuiteByName("EmployeeTesting");
		
		WsdlTestCase testCase = testSuite.getTestCaseByName("Script");
		TestRunner runner = testCase.run(new PropertiesMap(), false);
		Assert.assertEquals(Status.FINISHED, runner.getStatus());
		
	}

}
