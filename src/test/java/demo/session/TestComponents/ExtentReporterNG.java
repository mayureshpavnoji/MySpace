package demo.session.TestComponents;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	ExtentReports extent1;
	
	public static ExtentReports getExtentReportObject () {
		String path = System.getProperty("user.dir")+"\\report\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter (path);
		reporter.config ().setReportName("Web Automation Results");
		reporter.config ().setDocumentTitle ("Test Results");
		ExtentReports extent1 =new ExtentReports();
		extent1 = new ExtentReports ();
		extent1.attachReporter (reporter);
		extent1.setSystemInfo("Test Lead", "Mayuresh Pavnoji");
		return extent1;
	}
}
