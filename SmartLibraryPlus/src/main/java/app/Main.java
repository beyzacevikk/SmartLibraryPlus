package app;

import dao.BookDao;
import dao.LoanDao;
import dao.StudentDao;
import entity.Book;
import entity.Loan;
import entity.Student;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final BookDao bookDao = new BookDao();
    private static final StudentDao studentDao = new StudentDao();
    private static final LoanDao loanDao = new LoanDao();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> kitapEkle(sc);
                case "2" -> kitaplariListele();
                case "3" -> ogrenciEkle(sc);
                case "4" -> ogrencileriListele();
                case "5" -> kitapOduncVer(sc);
                case "6" -> oduncListesiGoster();
                case "7" -> kitapGeriTeslimAl(sc);
                case "0" -> {
                    System.out.println("Çıkış yapılıyor...");
                    HibernateUtil.shutdown();
                    sc.close();
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n==============================");
        System.out.println(" SmartLibraryPlus Menü");
        System.out.println("==============================");
        System.out.println("1 - Kitap Ekle");
        System.out.println("2 - Kitapları Listele");
        System.out.println("3 - Öğrenci Ekle");
        System.out.println("4 - Öğrencileri Listele");
        System.out.println("5 - Kitap Ödünç Ver");
        System.out.println("6 - Ödünç Listesini Görüntüle");
        System.out.println("7 - Kitap Geri Teslim Al");
        System.out.println("0 - Çıkış");
        System.out.print("Seçim: ");
    }


    private static void kitapEkle(Scanner sc) {
        System.out.print("Kitap adı: ");
        String title = sc.nextLine();

        System.out.print("Yazar: ");
        String author = sc.nextLine();

        System.out.print("Yıl: ");
        int year = readInt(sc);

        Book book = new Book(title, author, year);
        bookDao.save(book);

        System.out.println("✅ Kitap eklendi. Status = AVAILABLE");
    }


    private static void kitaplariListele() {
        List<Book> books = bookDao.getAll();
        if (books.isEmpty()) {
            System.out.println("Kayıtlı kitap yok.");
            return;
        }

        System.out.println("\n--- Kitap Listesi ---");
        for (Book b : books) {
            System.out.printf("ID: %d | %s - %s (%d) | Durum: %s%n",
                    b.getId(), b.getTitle(), b.getAuthor(), b.getYear(), b.getStatus());
        }
    }


    private static void ogrenciEkle(Scanner sc) {
        System.out.print("Öğrenci adı: ");
        String name = sc.nextLine();

        System.out.print("Bölüm: ");
        String dept = sc.nextLine();

        Student student = new Student(name, dept);
        studentDao.save(student);

        System.out.println("✅ Öğrenci eklendi.");
    }


    private static void ogrencileriListele() {
        List<Student> students = studentDao.getAll();
        if (students.isEmpty()) {
            System.out.println("Kayıtlı öğrenci yok.");
            return;
        }

        System.out.println("\n--- Öğrenci Listesi ---");
        for (Student s : students) {
            System.out.printf("ID: %d | %s | Bölüm: %s%n",
                    s.getId(), s.getName(), s.getDepartment());
        }
    }


    private static void kitapOduncVer(Scanner sc) {
        System.out.print("Student ID: ");
        long studentId = readLong(sc);

        System.out.print("Book ID: ");
        long bookId = readLong(sc);

        Student student = studentDao.getById(studentId);
        if (student == null) {
            System.out.println("❌ Öğrenci bulunamadı.");
            return;
        }

        Book book = bookDao.getById(bookId);
        if (book == null) {
            System.out.println("❌ Kitap bulunamadı.");
            return;
        }

        if (book.getStatus() == Book.Status.BORROWED) {
            System.out.println("⚠️ Bu kitap zaten BORROWED. İşlem yapılmadı.");
            return;
        }

        LocalDate borrowDate = LocalDate.now();


        book.setStatus(Book.Status.BORROWED);
        bookDao.update(book);


        Loan loan = new Loan(student, book, borrowDate);
        loanDao.save(loan);

        System.out.println("✅ Kitap ödünç verildi. Tarih: " + borrowDate);
    }


    private static void oduncListesiGoster() {
        List<Loan> loans = loanDao.getAll();
        if (loans.isEmpty()) {
            System.out.println("Ödünç kaydı yok.");
            return;
        }

        System.out.println("\n--- Ödünç Listesi ---");
        for (Loan l : loans) {
            String studentName = l.getStudent().getName();
            String bookTitle = l.getBook().getTitle();
            System.out.printf("LoanID: %d | Öğrenci: %s | Kitap: %s | Alış: %s | İade: %s%n",
                    l.getId(), studentName, bookTitle, l.getBorrowDate(), l.getReturnDate());
        }
    }

    
    private static void kitapGeriTeslimAl(Scanner sc) {
        System.out.print("Teslim alınacak Loan ID: ");
        long loanId = readLong(sc);

        Loan loan = loanDao.getById(loanId);
        if (loan == null) {
            System.out.println("❌ Loan kaydı bulunamadı.");
            return;
        }

        if (loan.getReturnDate() != null) {
            System.out.println("⚠️ Bu loan zaten iade edilmiş.");
            return;
        }

        LocalDate returnDate = LocalDate.now();
        loan.setReturnDate(returnDate);
        loanDao.update(loan);

        
        Book book = loan.getBook();
        book.setStatus(Book.Status.AVAILABLE);
        bookDao.update(book);

        System.out.println("✅ Kitap geri teslim alındı. İade tarihi: " + returnDate);
    }

    private static int readInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (Exception e) {
                System.out.print("Geçerli sayı gir: ");
            }
        }
    }

    private static long readLong(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try {
                return Long.parseLong(s);
            } catch (Exception e) {
                System.out.print("Geçerli ID gir: ");
            }
        }
    }
}
