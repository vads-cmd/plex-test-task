import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {

    protected static String url = "https://plexsupply.com/";
    protected static Playwright playwright;
    protected static Browser browser;
    protected static Page page;

    @BeforeAll
    protected static void SetUp() {
        playwright = Playwright.create();
        browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate(url);
        assertThat(page).hasTitle("Plexsupply.com | Wholesale and Retail Distribution of Quality Goods");
    }

    @AfterAll
    protected static void tearDown() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(timeStamp.concat(".png"))));
        playwright.close();
    }
}
