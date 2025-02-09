<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/static/reg_page_styles.css">

    <script>
        window.onload = function() {
            var errorMessage = "${error!''}";
            if (errorMessage) {
                alert(errorMessage);
            }
        }
    </script>

</head>
<body>
<div class="form-container">
    <h2>Регистрация</h2>
    <form action="/register" method="POST">
        <label for="first_name">Имя:</label>
        <input type="text" id="first_name" name="first_name" required>

        <label for="last_name">Фамилия:</label>
        <input type="text" id="last_name" name="last_name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <label for="age">Возраст:</label>
        <input type="number" id="age" name="age" required>

        <label for="room_number">Номер комнаты:</label>
        <input type="number" id="room_number" name="room_number" required>

        <label for="group_number">Номер группы:</label>
        <input type="text" id="group_number" name="group_number" required>

        <button type="submit">Зарегистрироваться</button>
    </form>

    <p>Есть аккаунт? <a href="/login">Войти</a></p> <!-- Кнопка для перехода на страницу логина -->
</div>
</body>
</html>
