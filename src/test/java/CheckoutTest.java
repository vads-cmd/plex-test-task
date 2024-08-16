import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckoutTest extends BaseTest{

    @Test
    public void checkoutBasicTest(){
        MainPage mainPage = new MainPage(page);

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
    }
}
