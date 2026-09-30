package Ex04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class ProductMaintenanceService {
    public int adjustPriceByCategory(
            String category,
            double percentageIncrease,
            int maxStockThreshold
    ) {
        String sql = """
                UPDATE products
                SET price = price * (1 + ? / 100.0)
                WHERE category = ?
                AND stock_quantity <= ?
                """;
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setDouble(1, percentageIncrease);
            ps.setString(2, category);
            ps.setInt(3, maxStockThreshold);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[CẬP NHẬT GIÁ] Đã điều chỉnh tăng " + percentageIncrease + "% cho " + rowsAffected + " sản phẩm thuộc danh mục [" + category + "].");
            } else {
                System.out.println("[CẬP NHẬT GIÁ] Không có sản phẩm nào phù hợp điều kiện.");
            }
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật giá: " + e.getMessage());
            return 0;
        }
    }
    public int removeDiscontinuedProducts() {
        String sql = """
                DELETE FROM products
                WHERE status = 'DISCONTINUED'
                AND stock_quantity = 0
                """;
        try (
                Connection con = ConnectDB.openConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("[XÓA SẢN PHẨM] Đã dọn dẹp " + rowsAffected + " sản phẩm ngừng kinh doanh và hết hàng khỏi CSDL.");
            } else {
                System.out.println("[XÓA SẢN PHẨM] Không có sản phẩm nào cần xóa.");
            }
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa sản phẩm: " + e.getMessage());
            return 0;
        }
    }
}