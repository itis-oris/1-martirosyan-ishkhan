<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Проживающие</title>
    <link rel="stylesheet" href="/static/users_page_styles.css">
</head>
<body>
<div class="container">
    <header>
        <!-- Поле поиска -->
        <div class="search-bar">
            <form action="/users" method="post">
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
            <a href="/users?page=${page - 1}&name=${name!""}" class="pagination-btn left"
               style="visibility: ${left_visibility! 'hidden'};">&#8592; Назад</a>
            <a href="/users?page=${page + 1}&name=${name!""}" class="pagination-btn right"
               style="visibility: ${right_visibility! 'visible'};">Вперед &#8594;</a>
        </div>

        <!-- Секция мероприятий -->
        <section class="users-section">
            <h2>Проживающие</h2>

            <div class="users-list">
                <#list users as user>
                    <div class="user-item">
                        <a href="/user?id=${user.id}" class="event-link">
                            <div class="user-content">
                                <h3 class="user-number">${user.lastName} ${user.firstName}</h3>
                                <p>
                                    <a class="user-room">Комната №${user.roomNumber}</a>
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
