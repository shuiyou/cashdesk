package com.weihui.cashdesk.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;

import static com.weihui.cashdesk.utils.ConfigUtil.currentDir;

public class ScreenShot {
    private WebDriver driver;
    ReportUtil log = new ReportUtil();
    Logger logger = LogManager.getLogger(ScreenShot.class);
    private String OSType = System.getProperty("os.name");//得到当前操作系统
    private static String saveVerityCode_path =currentDir + "/imagesaving";;
    public WebDriver getDriver() {
        return driver;
    }


    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * @return void
     * @Title: takeScreenShot
     * @Description: 失败截图 TestNGListener调用
     * @author: Yin
     * @throws:
     * @date Apr 18, 2017 9:34:53 AM
     * @version: V1.0
     */
    public void takeScreenShot(String name) {
        String path;
        if (OSType.contains("Mac")) {
            path = System.getProperty("user.dir").replace("\\", "/");
            path = path + "/test-output/失败截图/" +name + ".png";
        } else {
            path = System.getProperty("user.dir") + "\\test-output\\失败截图\\" +  name + ".png";
        }

        takeScreenShot((TakesScreenshot) this.getDriver(), path);

    }

    /**
     * @param path
     * @return void
     * @Title: takeScreenShot
     * @Description:
     * @author: Yin
     * @throws:
     * @date Apr 18, 2017 9:34:58 AM
     * @version: V1.0
     */
    public void takeScreenShot(TakesScreenshot driverName, String path) {
        File scrFile = driverName.getScreenshotAs(OutputType.FILE);
        File picFile = new File(path);
        try {
            FileUtils.copyFile(scrFile, picFile);
            log.info("当前失败用例截图保存路径:" + path);
            logger.info("当前失败用例截图保存路径:" + path);

        } catch (Exception e) {
            String msg = "截图发生异常！";
            ReportUtil.logRecord.add(msg);
            log.error(msg);
            logger.error(msg);
        }
//        log.screenShotLog(picFile);
    }

    public byte[] takescreenshot(WebDriver driver) throws IOException{
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        return  screenshot;
    }

    public BufferedImage getElementImage(WebDriver driver,WebElement webElement,int x, int y){
        BufferedImage cutedImage =null;
        try
        {   Dimension size = webElement.getSize();
            BufferedImage orginImage = ImageIO.read(new ByteArrayInputStream(
					takescreenshot(driver)));
            cutedImage = orginImage.getSubimage(x,y,size.getWidth(),size.getHeight() );

        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("图片未获取成功");
        }
        return cutedImage;
    }

    public String getVerifycode(WebDriver webDriver,WebElement webElement){
        String path = saveVerityCode_path;
        File imageFile = new File( path );
        BufferedImage image =getElementImage(webDriver,webElement,100,100);
        try
        {
            ImageIO.write(image, "png", imageFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        ITesseract instance = new Tesseract();//调用Tesseract
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getPath());
//        URL url = ClassLoader.getSystemResource("tessdata");//获得Tesseract的文字库
//        String tesspath = url.getPath().substring(1);
//        instance.setDatapath(tesspath);
        String result = null;
        try
        {
            result = instance.doOCR(imageFile).replace( "[^a-z^A-Z^0-9]", "" );
        }
        catch (TesseractException e)
        {
            e.printStackTrace();
        }
        return result;
    }


}

