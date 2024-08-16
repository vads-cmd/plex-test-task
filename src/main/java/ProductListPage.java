import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Random;

public class ProductListPage {

    private final Page page;
    private final Locator productOnPage;

    public ProductListPage(Page page) {
        this.page = page;
        this.productOnPage = page.locator("//ol[contains(@class, 'row')]//div[@class='product-item-info']");
    }

    public ProductDetailsPage selectRandomProduct() {
        Random random = new Random();
        productOnPage.nth(random.nextInt(productOnPage.count() - 1)).click();
        return new ProductDetailsPage(this.page);
    }
}
