package testng.retry.handler;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryHandler implements IRetryAnalyzer{
	
	int counter=0;
	int maxRetry = 3;
	
	@Override
	public boolean retry(ITestResult result) {
		if(counter<maxRetry)
		{
			counter++;
			return true;
		}
		else
			return false;
	}

}
