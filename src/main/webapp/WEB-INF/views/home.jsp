<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="activePage" value="home"/>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>سامانه دانشجویان</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@ include file="common/header.jspf" %>

<div class="page">
    <div class="hero">
        <h1>سلام، ${name}! 👋</h1>
        <p>سامانه‌ی ساده‌ی ثبت‌نام و جستجوی دانشجویان — مبتنی بر Spring MVC، Hibernate و MySQL.</p>
        <div class="actions">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/students/new">ثبت‌نام دانشجوی جدید</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">مشاهده و جستجوی لیست</a>
        </div>
    </div>
</div>
</body>
</html>
