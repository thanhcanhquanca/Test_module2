package view;

import controller.ControllerManager;
import model.Contact;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        ControllerManager manager = new ControllerManager();

        int choice = 0;

        do{
            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
            System.out.println("Chọn chức năng theo số ( để tiếp tục ) ");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc file");
            System.out.println("7. Ghi file");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng (1-8): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    manager.displayContacts();
                    break;

                case 2:
                    System.out.print("Nhập họ tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập số điện thoại: ");
                    String phone = scanner.nextLine();
                    System.out.print("Nhập nhóm: ");
                    String group = scanner.nextLine();
                    System.out.print("Nhập giới tính: ");
                    String gender = scanner.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String address = scanner.nextLine();
                    System.out.print("Nhập ngày sinh: ");
                    String dateOfBirth = scanner.nextLine();
                    System.out.print("Nhập email: ");
                    String email = scanner.nextLine();

                    Contact newContact = new Contact(name, phone, group, gender, address, dateOfBirth, email);
                    manager.addContact(newContact);
                    System.out.println("Liên hệ đã được thêm.");
                    break;

                case 3:
                    System.out.print("Nhập số điện thoại liên hệ cần cập nhật: ");
                    String phoneToUpdate = scanner.nextLine();
                    System.out.print("Nhập họ tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhập nhóm mới: ");
                    String newGroup = scanner.nextLine();
                    System.out.print("Nhập giới tính mới: ");
                    String newGender = scanner.nextLine();
                    System.out.print("Nhập địa chỉ mới: ");
                    String newAddress = scanner.nextLine();
                    System.out.print("Nhập ngày sinh mới: ");
                    String newDateOfBirth = scanner.nextLine();
                    System.out.print("Nhập email mới: ");
                    String newEmail = scanner.nextLine();

                    Contact updatedContact = new Contact(newName, phoneToUpdate, newGroup, newGender, newAddress, newDateOfBirth, newEmail);
                    if (manager.updateContact(phoneToUpdate, updatedContact)) {
                        System.out.println("Liên hệ đã được cập nhật.");
                    } else {
                        System.out.println("Không tìm thấy liên hệ với số điện thoại này.");
                    }
                    break;

                case 4:
                    System.out.print("Nhập số điện thoại liên hệ cần xóa: ");
                    String phoneToDelete = scanner.nextLine();
                    manager.deleteContact(phoneToDelete);
                    break;

                case 5:
                    System.out.print("Nhập số điện thoại hoặc tên cần tìm: ");
                    String phoneOrName = scanner.nextLine();
                    Contact foundContact = manager.searchContact(phoneOrName);
                    if (foundContact != null) {
                        System.out.println("Tìm thấy: " + foundContact);
                    }
                    break;

                case 6:
                    manager.loadContactsFromFile();
                    break;

                case 7:
                    manager.saveContactsToFile();
                    System.out.println("Đã lưu danh bạ vào file.");
                    break;

                case 8:
                    scanner.close();
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }

        }while (choice != 8);
    }
}