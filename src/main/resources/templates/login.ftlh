
<#--<!DOCTYPE html>-->
<#--<html xmlns:th="http://www.thymeleaf.org">-->
<#--<head>-->
<#--    <meta charset="UTF-8">-->
<#--    <title>Логин</title>-->
<#--    <link rel="stylesheet" href="/static"/>-->
<#--</head>-->
<#--<body>-->
<#--<div class="container">-->
<#--    <div class="form-container">-->
<#--        <h2>Вход в систему</h2>-->
<#--        <form action="#" th:action="@{/login}" method="post">-->
<#--            <div class="form-group">-->
<#--                <label for="username">Email:</label>-->
<#--                <input type="text" id="username" name="username" placeholder="Введите ваш email" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <label for="password">Пароль:</label>-->
<#--                <input type="password" id="password" name="password" placeholder="Введите ваш пароль" required>-->
<#--            </div>-->
<#--            <div class="form-group">-->
<#--                <button type="submit" class="btn">Войти</button>-->
<#--            </div>-->
<#--        </form>-->
<#--        <p>Нет аккаунта? <a href="/user/register">Зарегистрироваться</a></p>-->
<#--    </div>-->
<#--</div>-->
<#--</body>-->
<#--</html>-->

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <!-- Подключение Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <h2 class="text-center mb-4">Вход</h2>
            <form action="#" th:action="@{/user/login}" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Введите ваш email" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Пароль:</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Введите ваш пароль" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Войти</button>
            </form>
            <p class="text-center mt-3">Еще не зарегистрированы? <a href="/user/register">Регистрация</a></p>
        </div>
    </div>
</div>
</body>
</html>