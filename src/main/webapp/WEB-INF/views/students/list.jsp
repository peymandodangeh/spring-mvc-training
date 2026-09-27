<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<c:set var="activePage" value="list"/>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>لیست دانشجویان</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<div class="page">
    <h1 class="page-title">لیست و جستجوی دانشجویان</h1>
    <p class="page-subtitle">بر اساس نام، شماره دانشجویی، کد ملی یا رشته جستجو کنید.</p>

    <c:if test="${param.created == '1'}">
        <div class="alert alert-success">✅ دانشجو با موفقیت ثبت شد.</div>
    </c:if>
    <c:if test="${param.updated == '1'}">
        <div class="alert alert-success">✅ اطلاعات دانشجو با موفقیت به‌روزرسانی شد.</div>
    </c:if>

    <form class="search-bar" action="${pageContext.request.contextPath}/students" method="get">
        <input type="text" name="q" placeholder="جستجو بر اساس نام، شماره دانشجویی، کد ملی یا رشته" value="${q}">
        <button type="submit" class="btn btn-primary">جستجو</button>
        <c:if test="${not empty q}">
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">پاک کردن</a>
        </c:if>
    </form>

    <c:choose>
        <c:when test="${empty students}">
            <div class="card">
                <div class="empty-state">
                    <div class="icon">🔍</div>
                    <p>نتیجه‌ای یافت نشد.</p>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <p class="result-count">${fn:length(students)} نتیجه یافت شد</p>
            <div class="table-wrap">
                <table>
                    <thead>
                    <tr>
                        <th>ردیف</th>
                        <th>نام و نام خانوادگی</th>
                        <th>شماره دانشجویی</th>
                        <th>کد ملی</th>
                        <th>رشته</th>
                        <th>ایمیل</th>
                        <th>تلفن</th>
                        <th>عملیات</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="student" items="${students}" varStatus="item">
                        <tr>
                            <td>${item.count}</td>
                            <td>${student.fullName}</td>
                            <td>${student.studentNumber}</td>
                            <td>${student.nationalId}</td>
                            <td>${student.major}</td>
                            <td>${student.email}</td>
                            <td>${student.phone}</td>
                            <td><a href="${pageContext.request.contextPath}/students/edit/${student.publicId}">ویرایش</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
