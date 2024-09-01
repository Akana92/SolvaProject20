<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <!-- Подключение Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="text-center mb-4">Регистрация</h2>
            <form action="#" th:action="@{user/register}" th:object="${user}" method="post">
                <div class="mb-3">
                    <label for="name" class="form-label">Имя:</label>
                    <input type="text" class="form-control" id="name" name="name" th:field="*{name}" placeholder="Введите ваше имя" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" th:field="*{email}" placeholder="Введите ваш email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль:</label>
                    <input type="password" class="form-control" id="password" name="password" th:field="*{password}" placeholder="Введите Пароль" required>
                </div>
                <div class="mb-3">
                    <label for="currency" class="form-label">Валюта:</label>
                    <select class="form-control" id="currency" name="currency">
                        <option value="USD">USD</option>
                        <option value="KZT">KZT</option>
                        <option value="RUB">RUB</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary w-100">Зарегистрироваться</button>
            </form>
<#--            <div th:if="${message}" class="mt-3">-->
<#--                <p class="alert alert-info" th:text="${message}"></p>-->
<#--            </div>-->
            <p class="text-center mt-3">Уже зарегистрированы? <a href="/user/login">Войти</a></p>
        </div>
    </div>
</div>
</body>
</html>

<#--<!DOCTYPE html>-->
<#--<html xmlns:th="http://www.thymeleaf.org">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Регистрация</title>-->
<#--    <link rel="stylesheet" href="/css/styles.css"/>-->
<#--</head>-->
<#--<body>-->
<#--<div class="container">-->
<#--    <div class="form-container">-->
<#--        <h2>Регистрация</h2>-->
<#--        <form action="#" th:action="@{/user/register}" th:object="${user}" method="post">-->
<#--            <div class="form-group">-->
<#--                <label for="name">Имя:</label>-->
<#--                <input type="text" id="name" name="name" th:field="*{name}" placeholder="Введите ваше имя" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="email">Email:</label>-->
<#--                <input type="email" id="email" name="email" th:field="*{email}" placeholder="Введите ваш email" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="bankAccount">Номер счета (10 цифр):</label>-->
<#--                <input type="number" id="bankAccount" name="bankAccount" th:field="*{bankAccount}" placeholder="1234567890" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="amount">Сумма:</label>-->
<#--                <input type="number" step="0.01" id="amount" name="amount" th:field="*{amount}" placeholder="0.00" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="currency">Валюта:</label>-->
<#--                <input type="text" id="currency" name="currency" th:field="*{currency}" placeholder="USD" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <button type="submit" class="btn">Зарегистрироваться</button>-->
<#--            </div>-->
<#--        </form>-->
<#--        <div th:if="${message}">-->
<#--            <p th:text="${message}" class="message"></p>-->
<#--        </div>-->
<#--        <p>Уже зарегистрированы? <a href="/login">Войти</a></p>-->
<#--    </div>-->
<#--</div>-->
<#--</body>-->
<#--</html>-->