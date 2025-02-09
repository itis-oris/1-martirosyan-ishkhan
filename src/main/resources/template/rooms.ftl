<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Комнаты</title>
    <link rel="stylesheet" href="/static/rooms_page_styles.css">
</head>
<body>
<div class="container">
    <header>
        <!-- Поле поиска -->
        <div class="search-bar">
            <form action="/rooms" method="post">
                <label for="name">Поиск комнаты:</label>
                <input id="name" name="name" type="text" value="${name!""}">
                <button type="submit">Искать</button>
            </form>
        </div>

        <div class="actions">
            <a href="/profile" class="home">Назад</a>
        </div>
    </header>

    <div class="content">
        <div class="pagination">
            <a href="/rooms?page=${page - 1}&name=${name!""}" class="pagination-btn left"
               style="visibility: ${left_visibility! 'hidden'};">&#8592; Назад</a>
            <a href="/rooms?page=${page + 1}&name=${name!""}" class="pagination-btn right"
               style="visibility: ${right_visibility! 'visible'};">Вперед &#8594;</a>
        </div>

        <!-- Секция мероприятий -->
        <section class="rooms-section">
            <h2>Комнаты общежития</h2>

            <div class="add-room-button-container">
                <a href="/create_room.ftl" class="create_room">Добавить комнату</a>
            </div>

            <div class="rooms-list">
                <#list rooms as room>
                    <div class="room-item">
                        <a href="/room?id=${room.roomNumber}" class="event-link">
                            <div class="room-content">
                                <h3 class="room-number">Комната №${room.roomNumber}</h3>
                                <p>
                                    <a class="room-capacity">Вместимость: ${room.capacity} человек</a>
                                </p>
                            </div>
                        </a>
                    </div>
                </#list>
            </div>
        </section>
    </div>
</div>
</body>
</html>
