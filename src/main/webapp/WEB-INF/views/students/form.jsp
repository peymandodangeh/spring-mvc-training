<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="activePage" value="form"/>
<c:choose>
    <c:when test="${editMode}">
        <c:set var="formAction" value="${pageContext.request.contextPath}/students/edit/${studentPublicId}"/>
        <c:set var="pageHeading" value="ویرایش اطلاعات دانشجو"/>
        <c:set var="submitLabel" value="به‌روزرسانی اطلاعات"/>
    </c:when>
    <c:otherwise>
        <c:set var="formAction" value="${pageContext.request.contextPath}/students"/>
        <c:set var="pageHeading" value="ثبت‌نام دانشجوی جدید"/>
        <c:set var="submitLabel" value="ثبت اطلاعات"/>
    </c:otherwise>
</c:choose>
<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${pageHeading}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<div class="page narrow">
    <h1 class="page-title">${pageHeading}</h1>
    <p class="page-subtitle">اطلاعات دانشجو را وارد کنید. فیلدهای ستاره‌دار الزامی هستند.</p>

    <spring:hasBindErrors name="student">
        <div class="alert alert-error">⚠️ لطفاً خطاهای مشخص‌شده در فرم را برطرف کنید.</div>
    </spring:hasBindErrors>

    <div class="card">
        <form:form modelAttribute="student" action="${formAction}" method="post" data-validate="true">
            <div class="form-grid">
                <div class="field full">
                    <label for="fullName">نام و نام خانوادگی *</label>
                    <form:input path="fullName" required="required" maxlength="255"
                                cssErrorClass="input-invalid" placeholder="مثلاً علی رضایی"
                                data-msg-required="نام و نام خانوادگی الزامی است."/>
                    <form:errors path="fullName" cssClass="field-error"/>
                </div>
                <div class="field">
                    <label for="studentNumber">شماره دانشجویی *</label>
                    <form:input path="studentNumber" required="required" pattern="[0-9]{5,12}"
                                inputmode="numeric" cssErrorClass="input-invalid" placeholder="مثلاً 9912345"
                                data-msg-required="شماره دانشجویی الزامی است."
                                data-msg-invalid="شماره دانشجویی باید ۵ تا ۱۲ رقم انگلیسی باشد."/>
                    <form:errors path="studentNumber" cssClass="field-error"/>
                </div>
                <div class="field">
                    <label for="nationalId">کد ملی</label>
                    <form:input path="nationalId" pattern="[0-9]{10}" inputmode="numeric"
                                cssErrorClass="input-invalid" placeholder="۱۰ رقم"
                                data-msg-invalid="کد ملی باید ۱۰ رقم انگلیسی باشد."/>
                    <form:errors path="nationalId" cssClass="field-error"/>
                </div>
                <div class="field full">
                    <label for="major">رشته تحصیلی</label>
                    <form:input path="major" maxlength="255" cssErrorClass="input-invalid"
                                placeholder="مثلاً مهندسی کامپیوتر"/>
                    <form:errors path="major" cssClass="field-error"/>
                </div>
                <div class="field">
                    <label for="email">ایمیل</label>
                    <form:input path="email" type="email" maxlength="255" cssErrorClass="input-invalid"
                                placeholder="example@mail.com"
                                data-msg-invalid="ایمیل واردشده معتبر نیست."/>
                    <form:errors path="email" cssClass="field-error"/>
                </div>
                <div class="field">
                    <label for="phone">شماره تماس</label>
                    <form:input path="phone" pattern="09[0-9]{9}" inputmode="numeric"
                                cssErrorClass="input-invalid" placeholder="09123456789"
                                data-msg-invalid="شماره تماس باید مانند 09123456789 باشد."/>
                    <form:errors path="phone" cssClass="field-error"/>
                </div>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn btn-primary">${submitLabel}</button>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/students">انصراف</a>
            </div>
        </form:form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/js/form.js"></script>
</body>
</html>
