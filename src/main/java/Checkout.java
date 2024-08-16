import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Locale;

public class Checkout {

    private final Page page;
    private final Locator checkoutProductName;
    private final Locator checkoutPrice;

    public Checkout(Page page) {
        this.page = page;
        checkoutProductName = page.locator("//tr[@class='item-info']//strong[@class='product-item-name']//a");
        checkoutPrice = page.locator("//td[@class='col price']//span[@class='price']");
    }

    public String getProductName() {
        return checkoutProductName.innerText().toLowerCase(Locale.ROOT);
    }

    public String getProductPrice() {
        return checkoutPrice.innerText().toLowerCase(Locale.ROOT);
    }
}
