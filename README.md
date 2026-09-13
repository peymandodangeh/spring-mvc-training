# Spring MVC Training

پروژه‌ی آموزشی ثبت‌نام و جستجوی دانشجویان — یک نمونه‌ی ساده و کاملاً annotation-based از Spring MVC، مناسب برای یادگیری گام‌به‌گام.

## پشته‌ی فنی

- Java 17
- Spring Framework 6 (jakarta.servlet, بدون web.xml)
- Hibernate ORM 6 (JPA annotations)
- MySQL 8 (از طریق Docker Compose)
- JSP + JSTL برای view layer
- SLF4J + Logback برای لاگ

## اجرای لوکال

```bash
docker compose up -d
mvn jetty:run
```

سپس:
- صفحه‌ی اصلی: http://localhost:8080/
- ثبت‌نام دانشجو: http://localhost:8080/students/new
- لیست و جستجو: http://localhost:8080/students

## ساختار برنچ‌ها

این پروژه به‌صورت جلسه‌به‌جلسه پیش می‌رود:

- `main` — نسخه‌های پایدار و نهایی
- `develop` — شاخه‌ی یکپارچه‌سازی؛ بعد از هر جلسه، برنچ همان جلسه به اینجا merge می‌شود
- `session-N` — کار انجام‌شده در جلسه‌ی N
