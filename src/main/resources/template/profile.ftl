<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="/static/profile_page_styles.css">
</head>
<body>
<div class="container">
    <header>

        <h1>Профиль пользователя</h1>
        <div class="back_button">
            <a href="/home" class="to_home_button">На главную</a>
            <a href="/logout" class="to_home_button">Выход</a>
        </div>
    </header>

    <div class="content">
        <div class="profile-info">
            <p><strong>Имя:</strong> ${user.firstName}</p>
            <p><strong>Фамилия:</strong> ${user.lastName}</p>
            <p><strong>Возраст:</strong> ${user.age}</p>
            <p><strong>Номер группы:</strong> ${user.groupNumber}</p>
            <p><strong>Номер комнаты:</strong> ${user.roomNumber}</p>
        </div>


        <div class="profile-actions">
            <a href="/my_events" class="myEvents">Мои мероприятия</a>
            <a href="/complaints" class="complaints">Мои жалобы</a>
            <a href="/change_password.ftl" class="complaints">Изменить пароль</a>
        </div>

        <div class="profile-actions" style="visibility: ${admin_visibility! 'hidden'};">
            <p>
                <a href="/create_event.ftl" class="createEvents"
                   style="visibility: ${admin_visibility! 'hidden'};">Создать мероприятие</a>
                <a href="/check_complaints" class="createEvents"
                   style="visibility: ${admin_visibility! 'hidden'};">Жалобы</a>
                <a href="/rooms" class="createEvents"
                   style="visibility: ${admin_visibility! 'hidden'};">Комнаты</a>
                <a href="/users" class="createEvents"
                   style="visibility: ${admin_visibility! 'hidden'};">Проживающие</a>
            </p>
            <a href="/create_news.ftl" class="createEvents"
               style="visibility: ${admin_visibility! 'hidden'};">Создать новость</a>

        </div>

    </div>
</div>
</body>
</html>