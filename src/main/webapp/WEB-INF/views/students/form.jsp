<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="activePage" value="form"/>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ثبت‌نام دانشجو</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<div class="page narrow">
    <h1 class="page-title">ثبت‌نام دانشجوی جدید</h1>
    <p class="page-subtitle">اطلاعات دانشجو را وارد کنید. فیلدهای ستاره‌دار الزامی هستند.</p>

    <div class="card">
        <form action="${pageContext.request.contextPath}/students" method="post">
            <div class="form-grid">
                <div class="field full">
                    <label>نام و نام خانوادگی *</label>
                    <input type="text" name="fullName" required="required" placeholder="مثلاً علی رضایی">
                </div>
                <div class="field">
                    <label>شماره دانشجویی *</label>
                    <input type="text" name="studentNumber" required="required" placeholder="مثلاً 9912345">
                </div>
                <div class="field">
                    <label>کد ملی</label>
                    <input type="text" name="nationalId" placeholder="۱۰ رقم">
                </div>
                <div class="field full">
                    <label>رشته تحصیلی</label>
                    <input type="text" name="major" placeholder="مثلاً مهندسی کامپیوتر">
                </div>
                <div class="field">
                    <label>ایمیل</label>
                    <input type="email" name="email" placeholder="example@mail.com">
                </div>
                <div class="field">
                    <label>شماره تماس</label>
                    <input type="text" name="phone" placeholder="۰۹xxxxxxxxx">
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">ثبت اطلاعات</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">انصراف</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
