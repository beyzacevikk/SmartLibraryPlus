# 📚 SmartLibraryPlus
Hibernate ORM Tabanlı Akıllı Kütüphane Sistemi

## 🎯 Ödevin Amacı
Bu projenin amacı öğrencinin aşağıdaki konulardaki bilgisini uygulamalı olarak göstermesidir:

- Nesneye Yönelik Programlama (OOP)
- ORM (Object Relational Mapping)
- Hibernate ile veritabanı işlemleri
- Entity – Relationship yapıları
- CRUD operasyonları

---

## 🌍 Senaryo
Bir üniversite, mevcut SmartLibrary sistemini geliştirerek Hibernate ORM kullanan daha sürdürülebilir bir yapıya geçmek istemektedir.

- Masaüstü **konsol uygulaması**
- **Java + Hibernate + SQLite**
- JDBC ile doğrudan SQL yazılmaz

---

## 📁 Proje Yapısı
SmartLibraryPlus/
├── src/
│ ├── entity/
│ ├── dao/
│ ├── util/
│ └── app/
├── hibernate.cfg.xml
├── pom.xml
└── README.md

yaml
Kodu kopyala

---

## 📦 Kullanılan Teknolojiler
- Java
- Hibernate ORM
- SQLite
- Maven
- Annotation tabanlı mapping (@Entity)

---

## 🧱 Entity Sınıfları

### Book (Kitap)
| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| title | Kitap adı |
| author | Yazar |
| year | Basım yılı |
| status | AVAILABLE / BORROWED |

---

### Student (Öğrenci)
| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| name | Öğrenci adı |
| department | Bölüm |

Bir öğrencinin birden fazla ödünç alma kaydı olabilir.

---

### Loan (Ödünç Alma)
| Alan | Açıklama |
|----|----|
| id | Birincil anahtar |
| borrowDate | Alış tarihi |
| returnDate | Teslim tarihi |

---

## 🔗 Nesne İlişkileri
| İlişki | Tür |
|------|------|
| Student → Loan | OneToMany |
| Loan → Student | ManyToOne |
| Loan → Book | OneToOne |

@OneToMany, @ManyToOne ve @OneToOne kullanılmıştır.

---

## 🗄️ Veritabanı
- SQLite kullanılmıştır
- Tablolar Hibernate tarafından otomatik oluşturulur
- `hbm2ddl.auto=update` aktiftir

---

## 🧰 DAO Katmanı
Her entity için ayrı DAO sınıfı oluşturulmuştur:

- BookDao
- StudentDao
- LoanDao

Zorunlu metotlar:
- save()
- update()
- delete()
- getById()
- getAll()

Hibernate Session ve Transaction kullanılmıştır.

---

## 📋 Konsol Menü
1 - Kitap Ekle
2 - Kitapları Listele
3 - Öğrenci Ekle
4 - Öğrencileri Listele
5 - Kitap Ödünç Ver
6 - Ödünç Listesini Görüntüle
7 - Kitap Geri Teslim Al
0 - Çıkış

yaml
Kodu kopyala

---

## ▶️ Proje Nasıl Çalıştırılır?
1. Proje klonlanır veya ZIP olarak indirilir.
2. IDE ile açılır.
3. Maven bağımlılıkları yüklenir.
4. `app` paketindeki `Main` sınıfı çalıştırılır.
5. Uygulama konsol üzerinden kullanılır.

---

## ⛔ Yasaklar
- JDBC ile SQL yazımı
- Statement / PreparedStatement
- Spring / Spring Boot
- GUI (Swing / JavaFX)
- Tüm kodların tek sınıfta yazılması
