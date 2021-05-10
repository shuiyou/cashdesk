package com.weihui.cashdesk.debug;


import com.weihui.cashdesk.selenium.BrowserUtil;
import com.weihui.cashdesk.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class Selenium {

    private WebDriver driver;

    @BeforeMethod
    private void beforeTest() {
        driver = DriverFactory.getInstance().getChromeDriver();
        BrowserUtil.browserInit(driver,10,2);
        BrowserUtil.openUrl(driver,"https://www.baidu.com");
    }

    /**
     * xpah定位，绝对路径写法 单个"/"代表绝对路径 例如定位百度首页输入框 定位值为：html/body/div[1]/div[1]/div/div[1]/div/form/span[1]/input
     */
    @Test(priority = 0,description = "Xpath绝对路径定位，严禁使用该方式!!",enabled = false)
    public void testXpath1() {
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div/div[1]/div/form/span[1]/input")).sendKeys("testXpath1");
        System.out.println("xpath绝对路径定位");
    }

    /**
     * 使用xpath属性定位，"//"代表相对路径，比如：//[@id='kw'] 即查找页面id为kw的input标签
     */
    @Test(priority = 1,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath2() {
        driver.findElement(By.xpath("//input[@id='kw']")).sendKeys("testXpath2");
    }


    /**
     * 使用xpath属性定位，当多个元素标签具有相同某一属性，可以多个与的关系，比如：.//*[@id='kw' and @name = 'wd'] 即查找页面id为kw的input标签
     */
    @Test(priority = 2,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath3() {
        driver.findElement(By.xpath("//input[@id='kw' and @name='wd']")).sendKeys("testXpath3");
    }

    /**
     * 使用部份属性值匹配(最强大的方法，强烈推荐) 比如定位百度首页新闻超链接 By.xpath("//a[starts-with(@href,'http://news.baidu.')")
     */
    @Test(priority = 3,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath4() {
        driver.findElement(By.xpath("//a[starts-with(@href,'http://news.baidu')]")).click();
    }

    /**
     * 使用部分属性值匹配(最强大的方法，强烈推荐) 比如定位百度首页新闻超链接 By.xpath("//a[ends-with(@href,'trnews')")
     * starts-with 顾名思义，匹配一个属性开始位置的关键字。
     * contains 匹配一个属性值中包含的字符串。
     * text（） 匹配的是显示文本信息，此处也可以用来做定位用。
     */
    @Test(priority = 4,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath5() {
        driver.findElement(By.xpath("//a[ends-with(@name,'trnews')]")).click();
    }

    /**
     * 使用部分属性值匹配(最强大的方法，强烈推荐) 比如定位百度首页新闻超链接 By.xpath("//a[contains-with(@href,'trnews')")
     * starts-with 顾名思义，匹配一个属性开始位置的关键字。
     * contains 匹配一个属性值中包含的字符串。
     * text（） 匹配的是显示文本信息，此处也可以用来做定位用。
     */
    @Test(priority = 5,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath6() {
        driver.findElement(By.xpath("//a[contains(@name,'tj_trnews')]")).click();
    }

    /**
     * text() 即标签键值 //a[contains(text(),'新闻')  或者 //a[text()='新闻']  推荐用第一种，其实就是包含第二种
     */
    @Test(priority = 6,description = "Xpath属性定位方式，推荐",enabled = false)
    public void testXpath7() {
        driver.findElement(By.xpath("//a[contains(text(),'新闻')]")).click();
    }


    /**
     * 相对路径定位 定位id为u1的标签下第一个a标签元素  所以a//b代表a标签下的子元素b标签
     */
    @Test(priority = 7,description = "相对路径定位" ,enabled =  false)
    public void testXpath8() {
        driver.findElement(By.xpath("//[@id='u1']/a[1]")).click();
    }
/****以上就是xpath常见定位方式，严禁使用绝对路径定位，注意上述定位可以组合，比如：//*[@id='kw' and contains(@name,'trnews')]  可以多个and并列***************/
//  xpath定位方式比较慢，个人推荐css定位 语法简洁 而且所有元素都可用css定位




    /**
     * css使用id属性定位  input#kw,其中#后标签id值  也可以input(id='kw') input#su
     */
    @Test(priority = 8,enabled =  false)
    public void testCss9() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("input[id = 'su']")).click();
    }

    /**
     * css使用多个属性定位 推荐
     */
    @Test(priority = 9,enabled =  false)
    public void testCss10() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("input[id = 'su'][value='百度一下']")).click();
    }

    /**
     * css使用（开头^=   包含*=   结尾$= ）
     */
    @Test(priority = 10,enabled =  false)
    public void testCss11() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("input[id = 'su'][value^='百度一']")).click();
    }

    /**
     * css使用（开头^=   包含*=   结尾$= ）
     */
    @Test(priority = 11,enabled =  false)
    public void testCss12() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("input[value*='度一']")).click();
    }

    /**
     * css使用（开头^=   包含*=   结尾$= ）
     */
    @Test(priority = 12,enabled =  false)
    public void testCss13() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("input[value$='度一下']")).click();
    }

    /**
     * css也可使用父子元素形式 空格或者>表示父子关系 强烈建议使用>  注意标签后.代表的是class属性   属性值有空格用.代替
     * 选中 li 下第 n 个 a  xpath一样     li>a:nth-child() /    span[class = 'bg s_btn_wr']
     */
    @Test(priority = 14,enabled =  false)
    public void testCss14() {
        driver.findElement(By.cssSelector("input#kw")).sendKeys("weihui");
        driver.findElement(By.cssSelector("span.bg.s_btn_wr>input[value^='百度一']")).click();
    }


    /****以上为常用的css定位方式，针对收银台界面元素定位css和xpath结合已足够用！ 更多模糊定位请自行百度，各位可以结合firepath定位方式使用 ***/



    @AfterMethod
    public void afterTest() {
        BrowserUtil.sleep(2);
        BrowserUtil.closeUrl(driver);
    }
}
