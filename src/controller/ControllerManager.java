package controller;

import model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerManager {
    private List<Contact> contacts = new ArrayList<>();

    // phương thức thêm mới một liên hệ vào arrlisst
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    //phương thức ccập nhập thong tin của một lien hê dựa trên số điện thoại

    public boolean updateContact(String phone ,Contact newContact) {
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                contact.setName(newContact.getName());
                contact.setGroup(newContact.getGroup());
                contact.setGender(newContact.getGender());
                contact.setAddress(newContact.getAddress());
                contact.setDateOfBirth(newContact.getDateOfBirth());
                contact.setEmail(newContact.getEmail());
                return true;
            }
        }
        return false;
    }

    // phương thức xóa liên hệ theo số điện thoại

    public void deleteContact(String phone ) {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (contact.getPhone().equals(phone)) {
                contacts.remove(i);
                return;
            }
        }
        System.out.println("không thể tìm thấy liên hệ với số điện thoại nà .");
    }


    // phương thức hiện thị

    public void displayContacts(){
        if (contacts.isEmpty()) {
            System.out.println(" danh bạ hiện tại đang rỗng .");

        }else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }

    // phương thức tìm kiếm số điện thoại theo số điện thoại hoặc tên

    public Contact searchContact(String phone) {
        for (Contact contact : contacts) {
            if (contact.getPhone().equals(phone)) {
                return contact;
            }
        }
        System.out.println("không thể tìm thấy liên hệ");
        return null;
    }

    //  phương thức ghi danh baj vào file

    public void saveContactsToFile() throws IOException {
       File file = new File("data/contacts.csv");

       FileOutputStream fileOutputStream = null;
       ObjectOutputStream objectOutputStream = null;

       fileOutputStream = new FileOutputStream(file);
        try {
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(contacts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            fileOutputStream.close();
            assert objectOutputStream != null;
            objectOutputStream.close();
        }

    }

    // phương thức đọc danh bạ từ file
    public void loadContactsFromFile() {
        File file = new File("data/contacts.csv");

        // Nếu file không tồn tại, không làm gì cả
        if (!file.exists()) {
            System.out.println("Không tìm thấy file danh bạ.");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            // Đọc danh bạ từ file
            List<Contact> loadedContacts = (List<Contact>) objectInputStream.readObject();

            // Cập nhật danh sách liên hệ
            contacts.clear();
            contacts.addAll(loadedContacts);

            System.out.println("Đã tải danh bạ từ file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }


}
