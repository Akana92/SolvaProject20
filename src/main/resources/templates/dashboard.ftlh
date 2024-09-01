<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Панель управления</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #343a40;
            color: #f8f9fa;
        }
        .container {
            background-color: #495057;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        a {
            color: #f8f9fa;
        }
        a:hover {
            color: #adb5bd;
        }
        .form-label {
            color: #ced4da;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2>Добро пожаловать, <span>${user.name}!</span></h2>
    <div>
        <h3>Ваш баланс: <span>${user.amount} ${user.currency}</span></h3>
    </div>
    <div>
        <h3>Номер вашего счёта: <span>${user.bankAccount}</span></h3>
    </div>
    <h3>Курсы валют обновляются каждый день в 01:00 PM:</h3>
    <div>

            <h3>Курс USD/KZT: <span>${usdToKztRate.rate!"Курсы пока не обновлены"}</span></h3>
            <h3>Курс USD/RUB: <span>${usdToRubRate.rate!"Курсы пока не обновлены"}</span></h3>

        <form action="/user/update-rates" method="post">
            <button type="submit" class="btn btn-primary">Обновить курсы валют</button>
        </form>
    </div>

    <h3>Перевод средств:</h3>
    <form action="/user/transfer" method="post" class="mb-4">
        <div class="mb-3">
            <label for="receiverId" class="form-label">Выберите получателя:</label>
            <select id="receiverId" name="receiverId" class="form-select">
                <#list users as optionUser>
                    <option value="${optionUser.id}">${optionUser.name} (#${optionUser.bankAccount})</option>
                </#list>
            </select>
        </div>
        <div class="mb-3">
            <label for="amount" class="form-label">Сумма:</label>
            <input type="number" id="amount" name="amount" class="form-control" required>
        </div>
        <div class="mb-3">
            <label for="currency" class="form-label">Валюта:</label>
            <select id="currency" name="currency" class="form-select">
                <option value="USD">USD</option>
                <option value="KZT">KZT</option>
                <option value="RUB">RUB</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Перевести</button>
    </form>

    <h3>История транзакций:</h3>
    <table class="table table-dark table-striped">
        <thead>
        <tr>
            <th>Дата</th>
            <th>От кого/Кому</th>
            <th>Сумма</th>
            <th>Валюта</th>
        </tr>
        </thead>
        <tbody>
        <#if transactions?size == 0>
            <tr>
                <td colspan="4">Нет транзакций для отображения</td>
            </tr>
        </#if>
        <#list transactions as trans>
            <tr>
                <td>${trans.dateTime}</td>
                <td>${trans.accountFrom.name} к ${trans.accountTo.name}</td>
                <td>${trans.sum}</td>
                <td>${trans.currency_shortname}</td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>