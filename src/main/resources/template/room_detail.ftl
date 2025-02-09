<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Информация о комнате ${room.roomNumber}</title>
    <link rel="stylesheet" href="/static/room_detail_page_styles.css">
</head>

<body>
<div class="container">
    <div class="content">
        <!-- Секция информации о комнате -->
        <section class="room-detail">
            <h2>Комната №${room.roomNumber}</h2>
            <div class="room-detail-content">
                <p><strong>Вместимость комнаты:</strong> ${room.capacity}</p>
                <h3>Проживающие пользователи:</h3>
                <ul class="user-list">
                    <#list users as user>
                        <li>${user.firstName} ${user.lastName}</li>
                    </#list>
                </ul>
            </div>
        </section>

        <!-- Кнопки действий -->
        <div class="action_buttons">
            <div class="back_button">
                <a href="/rooms" class="to_rooms_button">К списку комнат</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
