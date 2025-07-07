package manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    Logger logger = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        //вызывается при запуске любого теста
        ITestListener.super.onTestStart(result);
        logger.info("Start test -->"+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        //вызывается при успешном завершении любого теста
        ITestListener.super.onTestSuccess(result);
        logger.info("Success test -->"+ result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        //вызывается при не удачном завершении любого теста
        ITestListener.super.onTestFailure(result);
        logger.info("Failed test -->"+ result.getName());

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        //вызывается при пропуске любого теста
        ITestListener.super.onTestSkipped(result);
        logger.info("Skipped test -->"+ result.getName());

    }

    @Override
    public void onStart(ITestContext context) {
        //вызывается при запуске любого теста
        ITestListener.super.onStart(context);
        logger.info("Start testing on date -->" + context.getStartDate());
    }

    @Override
    public void onFinish(ITestContext context) {
        //вызывается после выполнения всех тестов
        ITestListener.super.onFinish(context);
        logger.info("Finish testing on date -->" + context.getEndDate());

    }
}