<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Информация о пользователе ${user.firstName} ${user.lastName}</title>
    <link rel="stylesheet" href="/static/user_detail_page_styles.css">
</head>


<body>
<div class="container">
    <div class="content">
        <!-- Секция информации о пользователе -->
        <section class="user-detail">
            <h2>${user.firstName} ${user.lastName}</h2>
            <div class="user-detail-content">
                <p><strong>ID:</strong> ${user.id}</p>
                <p><strong>Имя:</strong> ${user.firstName}</p>
                <p><strong>Фамилия:</strong> ${user.lastName}</p>
                <p><strong>Возраст:</strong> ${user.age}</p>
                <p><strong>Группа:</strong> ${user.groupNumber}</p>
                <p><strong>Номер комнаты:</strong> ${user.roomNumber! "Не назначен"}</p>
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Администратор:</strong> ${user.administratorRights?string("Да", "Нет")}</p>
            </div>
        </section>

        <!-- Кнопки действий -->
        <div class="action_buttons">
            <div class="edit_button">
                <a href="/edit_user?id=${user.id}" class="to_edit_button">Изменить данные</a>
            </div>
            <div class="back_button">
                <a href="/users" class="to_users_button">К списку пользователей</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
