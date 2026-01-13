# 📚SmartLibraryPlus
Hibernate ORM Tabanlı Akıllı Kütüphane Otomasyon Sistemi

## 🎯1. Projenin Amacı
Bu projenin amacı, Nesneye Yönelik Programlama dersi kapsamında öğrencinin:

- OOP prensiplerini,
- ORM (Object Relational Mapping) mantığını,
- Hibernate framework’ünü,
- Entity–Relationship yapılarını,
- CRUD işlemlerini

uygulamalı olarak gerçekleştirebildiğini göstermektir.

---

## 2. Proje Senaryosu
Bir üniversite, mevcut kütüphane otomasyon sistemini geliştirerek Hibernate ORM tabanlı, daha sürdürülebilir bir yapıya geçmek istemektedir.

Geliştirilen sistem:
- Masaüstü tabanlı bir **konsol uygulamasıdır**.
- **Java, Hibernate ve SQLite** teknolojileri kullanılmıştır.
- JDBC ile manuel SQL yazımı yapılmamıştır.
- Tüm veritabanı işlemleri Hibernate ORM üzerinden gerçekleştirilmiştir.

---

## 📁 3. Proje Yapısı
SmartLibraryPlus/
├── src/
│ ├── entity → Veritabanı entity sınıfları
│ ├── dao → Veri erişim katmanı (DAO)
│ ├── util → Hibernate yardımcı sınıfları
│ └── app → Uygulama giriş noktası
├── hibernate.cfg.xml
├── pom.xml
└── README.md



---

## 📦 4. Kullanılan Teknolojiler
- Java
- Hibernate ORM
- SQLite
- Maven
- Annotation tabanlı mapping (@Entity)

---

## 5. Entity Sınıfları

### 5.1 Book
Kitap bilgilerini tutan entity sınıfıdır.

| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| title | Kitap adı |
| author | Yazar |
| year | Basım yılı |
| status | AVAILABLE / BORROWED |

---

### 5.2 Student
Öğrenci bilgilerini tutan entity sınıfıdır.

| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| name | Öğrenci adı |
| department | Bölüm |

Bir öğrenci birden fazla ödünç alma kaydına sahip olabilir.

---

### 5.3 Loan
Kitap ödünç alma işlemlerini temsil eden entity sınıfıdır.

| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| borrowDate | Alış tarihi |
| returnDate | Teslim tarihi |

---

## 6. Entity İlişkileri
- **Student – Loan** : OneToMany / ManyToOne
- **Loan – Book** : OneToOne

İlişkiler Hibernate annotation’ları ile tanımlanmıştır.

---

## 🗄️ 7. Veritabanı Yapısı
- SQLite veritabanı kullanılmıştır.
- Tablolar Hibernate tarafından otomatik olarak oluşturulmaktadır.
- `hbm2ddl.auto=update` ayarı aktiftir.

---

## 8. DAO Katmanı
Her entity için ayrı bir DAO sınıfı oluşturulmuştur:

- BookDao  
- StudentDao  
- LoanDao  

DAO sınıflarında aşağıdaki metotlar yer almaktadır:
- save
- update
- delete
- getById
- getAll

Tüm işlemler Hibernate **Session** ve **Transaction** kullanılarak gerçekleştirilmiştir.

---

## 9. Konsol Menü ve İşlevler
Uygulama çalıştırıldığında kullanıcıya aşağıdaki menü sunulur:

1 - Kitap Ekle
2 - Kitapları Listele
3 - Öğrenci Ekle
4 - Öğrencileri Listele
5 - Kitap Ödünç Ver
6 - Ödünç Listesini Görüntüle
7 - Kitap Geri Teslim Al
0 - Çıkış

- Ödünç verilen kitap tekrar ödünç verilemez.
- Kitap teslim edildiğinde durumu AVAILABLE olarak güncellenir.

---

## 10. Projenin Çalıştırılması
1. Proje bilgisayara indirilir veya klonlanır.
2. IDE (IntelliJ IDEA vb.) ile açılır.
3. Maven bağımlılıkları yüklenir.
4. `app` paketi içindeki `Main` sınıfı çalıştırılır.
5. Uygulama konsol üzerinden kullanılmaya başlanır.

---

## 11. Kısıtlamalar
- JDBC ile SQL yazılmamıştır.
- Spring / Spring Boot kullanılmamıştır.
- GUI teknolojileri (Swing, JavaFX) kullanılmamıştır.
- Tüm kodlar tek bir sınıfta toplanmamıştır.
