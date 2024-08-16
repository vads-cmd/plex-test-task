import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Random;

public class MainPage {

    private final Page page;
    private final Locator cookieBanner;
    private final Locator mainMenuButton;
    private final Locator mainMenuItems;

    public MainPage(Page page) {
        this.page = page;
        this.cookieBanner = page.locator("//button[@id='btn-cookie-allow']");
        this.mainMenuButton = page.locator("//span[@class='d-none d-md-block']");
        this.mainMenuItems  =
                page.locator("//li[contains(@class, 'level0') and contains(@class, 'mega_left')]");
    }

    public void closeCookieBanner() {
        cookieBanner.click();
    }

    public void openMainMenu() {
        mainMenuButton.click();
    }

    public ProductListPage selectRandomMenuItem() {
        Random random = new Random();
        mainMenuItems.nth(random.nextInt(mainMenuItems.count())).click();
        return new ProductListPage(this.page);
    }
}
