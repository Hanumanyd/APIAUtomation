//package utils;
//
//import org.testng.IAnnotationTransformer;
//import org.testng.IRetryAnalyzer;
//import org.testng.ITestResult;
//import org.testng.annotations.ITestAnnotation;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Method;
//
//public class RetryListener implements IAnnotationTransformer {
//
//    @Override
//    public void transform(ITestAnnotation testAnnotation, Class testClass,
//                          Constructor testConstructor, Method testMethod)
//    {
//
//        Class<? extends IRetryAnalyzer> retry = testAnnotation.getRetryAnalyzerClass();
//
//        //https://www.javadoc.io/static/org.testng/testng/7.0.0-beta3/org/testng/ITestNGMethod.html#getRetryAnalyzer--
//
//        if (retry == null)    {
//            testAnnotation.setRetryAnalyzer(FailRetry.class);//pass the class name created in Step-1
//        }
//
//    }
//}
