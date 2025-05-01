package retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class RetryAnalyzer implements IRetryAnalyzer {

	//initial count which will be 0, this applies only when a test fail
	private int initialRetryCount=0;
	//max count to retry
	private static final int maxRetryCount=3;
	
	//ITestResult check if the test fail then execute the below method
	@Override
	public boolean retry(ITestResult result) {
		
		if (initialRetryCount<maxRetryCount) {
			
			initialRetryCount++;
			
			return true;
		}
		//no need to retry the passed test cases
		return false;
	}
  
}
