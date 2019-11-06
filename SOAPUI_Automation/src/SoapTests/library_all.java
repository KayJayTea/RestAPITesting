package SoapTests;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.junit.Assert;
import org.junit.Test;

import com.eviware.soapui.impl.wsdl.WsdlProject;
import com.eviware.soapui.impl.wsdl.WsdlTestSuite;
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestCase;
import com.eviware.soapui.model.iface.Submit.Status;
import com.eviware.soapui.model.support.PropertiesMap;
import com.eviware.soapui.model.testsuite.TestRunner;
import com.eviware.soapui.support.SoapUIException;

public class library_all {

		// TODO Auto-generated method stub
	@Test
	public void SoapTest() throws XmlException, IOException, SoapUIException {
		
		WsdlProject project = new WsdlProject("C:\\Users\\AAO8676\\Documents\\SOAPUI\\LibraryAPI.xml");
		WsdlTestSuite testSuite = project.getTestSuiteByName("LibraryTestSuite");
		for (int i = 0; i < testSuite.getTestCaseCount(); i++) {
			WsdlTestCase testCase = testSuite.getTestCaseAt(i);
			
			// code to execute
			TestRunner runner = testCase.run(new PropertiesMap(), false);			
			// Assert.assertEquals(Status.FINISHED, runner.getStatus());
			Assert.assertEquals(String.valueOf(Status.FINISHED), String.valueOf(runner.getStatus()));
		}
		
	}

}
