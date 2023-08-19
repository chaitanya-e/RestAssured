package testng.retry.handler;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RetryHandlerTest {
	
	
	@Test(retryAnalyzer = testng.retry.handler.RetryHandler.class)
	public void test1()
	{
		System.out.println("test1");
		Assert.assertTrue(false);
		
	}
	
	@Test
	public void test2()
	{
		System.out.println("test2");
		
	}
}
