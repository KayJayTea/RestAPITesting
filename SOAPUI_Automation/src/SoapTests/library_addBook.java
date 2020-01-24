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

public class library_addBook {
	
	@Test
	public void addBookTest() throws XmlException, IOException, SoapUIException {
		
		WsdlProject project = new WsdlProject("C:\\Users\\AAO8676\\Documents\\SOAPUI\\LibraryAPI.xml");
		WsdlTestSuite testSuite = project.getTestSuiteByName("LibraryTestSuite");
		
		WsdlTestCase testCase = testSuite.getTestCaseByName("Addbook");
		TestRunner runner = testCase.run(new PropertiesMap(), false);
		Assert.assertEquals(Status.FINISHED, runner.getStatus());
		
	}

}
