package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGeneration {
	
	public static ExtentReports getReportObject() {
		
		
		String path=System.getProperty("user.dir")+"\\Output\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		
		reporter.config().setReportName("Flipkar Automation Report");
		reporter.config().setDocumentTitle("Test Result");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Akhilesh");
		
		return extent;
	}

}
