<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Вход</title>
    <link rel="stylesheet" href="/static/reg_page_styles.css">

    <script>
        window.onload = function() {
            var errorMessage = "${message!''}";
            if (errorMessage) {
                alert(errorMessage);
            }
        }
    </script>

</head>
<body>
<div class="form-container">
    <h2>Вход</h2>
    <form action="/usercheck" method="POST">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Войти</button>
    </form>

    <p>Нет аккаунта? <a href="/register">Зарегистрироваться</a></p> <!-- Кнопка для перехода на страницу регистрации -->
</div>
</body>
</html>
