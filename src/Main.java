
import models.Reader;
import utils.Menu;
import utils.OutputManager;

public class Main {
    public static void main(String[] args) {
       Reader r1 = new Reader("mohamed","mohamed","mohamed","mohamed","mohamed","mohamed");
       r1.orderBook("The Great Gatsby");
       r1.showreceipt();
       r1.removefromShoppingbag("The Great Gatsby");
    }
}
