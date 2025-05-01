package retry;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import org.testng.annotations.Test;

//TestNG Listener-IAnnotationTransformer, to get the concept of retry globally, to apply automatic retry mechanism globally
public class RetryListener implements IAnnotationTransformer{
	
	@Override
	public void transform(ITestAnnotation annotation,Class testClass,Constructor testConstructor,Method testMethod) {
		
		annotation.setRetryAnalyzer(RetryAnalyzer.class);
		//ITestAnnotation----Represent @Test annotation
		//Class ---represent test classes
		//Constructor---represent test constructor, optional so give null
		//Method--- to get details of test methods
		
	}
 
}
