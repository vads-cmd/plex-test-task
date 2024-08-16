import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();
            page.navigate("https://plexsupply.com/");
            assertThat(page).hasTitle("Plexsupply.com | Wholesale and Retail Distribution of Quality Goods");

            MainPage mainPage = new MainPage(page);
            mainPage.closeCookieBanner();
            mainPage.openMainMenu();
            ProductListPage plp = mainPage.selectRandomMenuItem();
            ProductDetailsPage pdp = plp.selectRandomProduct();

            String productName = pdp.getProductName();
            String productPrice = pdp.getProductPrice();
            pdp.addProductToCart();
            Checkout checkout = pdp.gotoCheckout();

            String checkoutProductName = checkout.getProductName();
            String checkoutPrice = checkout.getProductPrice();

            Assertions.assertEquals(productName, checkoutProductName);
            Assertions.assertEquals(productPrice, checkoutPrice);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(timeStamp.concat(".png"))));
        }
    }
}
