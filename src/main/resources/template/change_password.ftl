<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Изменить пароль</title>
    <link rel="stylesheet" href="/static/change_password_styles.css">

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
<div class="container">
    <div class="content">
        <section class="password-change">
            <h2>Изменить пароль</h2>
            <form action="/update_password" method="post">
                <input type="hidden" name="id" value="${user.id}">

                <div class="form-group">
                    <label for="currentPassword">Текущий пароль</label>
                    <input type="password" id="currentPassword" name="currentPassword" required>
                </div>

                <div class="form-group">
                    <label for="newPassword">Новый пароль</label>
                    <input type="password" id="newPassword" name="newPassword" required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Подтвердите новый пароль</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>
                </div>

                <button type="submit" class="submit-button">Изменить пароль</button>
            </form>
        </section>

        <div class="action_buttons">
            <div class="back_button">
                <a href="/profile" class="to_user_button">Назад</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
