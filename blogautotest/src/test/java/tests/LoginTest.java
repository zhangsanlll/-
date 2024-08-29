package tests;

import common.Utils;
import org.openqa.selenium.By;

import java.io.IOException;

//登录界面自动化测试
public class LoginTest extends Utils {
    public static String url = "";
    public LoginTest(){
        super(url);
    }
    /*
    检查页面是否加载成功
    * */
    public void loginTestRight(){
        //通过查看页面元素是否存在来检查页面加载成功
        driver.findElement(By.cssSelector(""));
        //登录输入框
        driver.findElement(By.cssSelector(""));

    }

    //检查登录功能---成功登录
    public void LoginSuccess() throws IOException, InterruptedException {
        driver.findElement(By.cssSelector("#")).clear();
        driver.findElement(By.cssSelector("#")).clear();

        driver.findElement(By.cssSelector("#")).sendKeys("");
        driver.findElement(By.cssSelector("#")).sendKeys("");
        //点击登录查看是否登录成功
        driver.findElement(By.cssSelector("#")).click();
        //？
        driver.findElement(By.cssSelector(""));
        //页面标题来检查是否登录成功
        String expect = driver.getTitle();
        assert expect.equals("博客列表");

        //？
        getScreenShot(getClass().getName());
        //若是登录成功则会跳转到另外一个页面，所以需要回退页面
        driver.navigate().back();
    }

    /*检查登录功能--登录失败
    * */
    public void LoginFail() throws IOException, InterruptedException {
        //有两种方法可以确保输入框没有文字，一种使通过clear(),另一种是刷新页面
        //此处采用第二种
        driver.navigate().refresh();

        driver.findElement(By.cssSelector("#")).sendKeys("");
        driver.findElement(By.cssSelector("#")).sendKeys("");
        //点击登录查看是否登录成功
        driver.findElement(By.cssSelector("#")).click();
        String res = driver.findElement(By.cssSelector("")).getText();
        getScreenShot(getClass().getName());
        assert res.equals("用户名或密码错误！");

    }

}
