import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.awaitility.Awaitility;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.Callable;

public class ProductDetailsPage {

    private final Page page;
    private final Locator productName;
    private final Locator productPrice;
    private final Locator addToCartButton;
    private final Locator productCountBubble;
    private final Locator closeCheckoutPopup;
    private final Locator gotoCartButton;
    private final Locator viewCart;

    public ProductDetailsPage(Page page) {
        this.page = page;
        this.productName = page.locator("//span[@class='base']");
        this.productPrice = page.locator("//div[@class='product-info-price']//span[@class='price']");
        this.addToCartButton = page.locator("//button[@id='product-addtocart-button']");
        this.productCountBubble = page.locator("//span[@class='counter-number']");
        this.closeCheckoutPopup = page.locator("//i[@class='mbi mbi-cross']");
        this.gotoCartButton = page.locator("//a[@class='action showcart']//i");
        this.viewCart = page.locator("//a[@class='action primary viewcart']");
    }

    private void await(Locator locator, int waitTime, int repeatMilliseconds) {
        Callable<Boolean> wait = locator::isVisible;
        Awaitility.waitAtMost(Duration.ofSeconds(waitTime))
                .pollDelay(Duration.ofMillis(repeatMilliseconds))
                .until(wait);
    }

    public String getProductName() {
        return productName.innerText().toLowerCase(Locale.ROOT);
    }

    public String getProductPrice() {
        return productPrice.innerText().toLowerCase(Locale.ROOT);
    }

    public void addProductToCart() {
        addToCartButton.click();
        closeCheckoutPopup.nth(0).click();
        await(productCountBubble, 30, 500);
    }

    public Checkout gotoCheckout() {
        gotoCartButton.click();
        viewCart.click();
        return new Checkout(page);
    }
}
