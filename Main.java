package Ex04;

public class Main {

    public static void main(String[] args) {

        ProductMaintenanceService service = new ProductMaintenanceService();
        System.out.println("          BẢO TRÌ & ĐIỀU CHỈNH DỮ LIỆU KHO - RIKKEI SHOP");
        service.adjustPriceByCategory("LAPTOP", 10.0, 5);
        service.removeDiscontinuedProducts();
    }
}