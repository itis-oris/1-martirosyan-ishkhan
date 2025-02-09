<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Изменить информацию о пользователе</title>
    <link rel="stylesheet" href="/static/edit_user_page_styles.css">
</head>

<body>
<div class="container">
    <div class="content">
        <!-- Секция формы изменения информации -->
        <section class="edit-user-form">
            <h2>Изменить информацию о пользователе</h2>
            <form action="/update_user" method="post">
                <input type="hidden" name="id" value="${user.id}">

                <label for="firstName">Имя:</label>
                <input type="text" id="firstName" name="firstName" value="${user.firstName}" required>

                <label for="lastName">Фамилия:</label>
                <input type="text" id="lastName" name="lastName" value="${user.lastName}" required>

                <label for="age">Возраст:</label>
                <input type="number" id="age" name="age" value="${user.age}" min="1" required>

                <label for="groupNumber">Группа:</label>
                <input type="text" id="groupNumber" name="groupNumber" value="${user.groupNumber}" required>

                <label for="roomNumber">Номер комнаты:</label>
                <input type="number" id="roomNumber" name="roomNumber" value="${user.roomNumber!''}">

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.email}" required>

                <label for="administratorRights">Администратор:</label>
                <select id="administratorRights" name="administratorRights" required>
                    <option value="true" ${user.administratorRights?string("selected", "")}>Да</option>
                    <option value="false" ${user.administratorRights?string("", "selected")}>Нет</option>
                </select>

                <div class="form-buttons">
                    <button type="submit" class="save-button">Сохранить</button>
                    <a href="/users" class="cancel-button">Отмена</a>
                </div>
            </form>
        </section>
    </div>
</div>
</body>
</html>
