import model.NumberItem;
import service.FileService;
import service.NumberManager;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static NumberManager manager = new NumberManager();
    static int autoId = 1;

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = readInt("Chọn: ");

            switch (choice) {
                case 1 -> inputData();
                case 2 -> System.out.println("Tổng = " + manager.getSum());
                case 3 -> {
                    NumberItem max = manager.getMax();
                    if (max != null)
                        System.out.println("Max: ID=" + max.getId() + ", Value=" + max.getValue());
                }
                case 4 -> manager.display();
                case 5 -> FileService.saveAll(manager.getList());
                case 6 -> manager.setList(FileService.readAll());
                case 0 -> System.out.println("Thoát chương trình");
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        } while (choice != 0);
    }
    static void showMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Nhập dữ liệu");
        System.out.println("2. Tính tổng");
        System.out.println("3. Tìm giá trị lớn nhất");
        System.out.println("4. Hiển thị danh sách");
        System.out.println("5. Lưu JSON và XML");
        System.out.println("6. Đọc dữ liệu từ file");
        System.out.println("0. Thoát chương trình");
        System.out.println("Chọn chức năng bạn muốn sử dụng (0-6): ");
    }

    static void inputData() {
        double value = readDouble("Nhập giá trị (>0): ");
        manager.add(new NumberItem(autoId++, value));
    }

    // VALIDATE INT
    static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số nguyên!");
            }
        }
    }

    static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                double value = Double.parseDouble(scanner.nextLine());
                if (value <= 0) {
                    System.out.println("❌ Giá trị phải > 0!");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Vui lòng nhập số!");
            }
        }
    }
}
