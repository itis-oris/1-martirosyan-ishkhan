<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная страница общежития</title>
    <link rel="stylesheet" href="/static/home_page_styles.css">
</head>
<body>
<div class="container">
    <header>
        <!-- Информация о пользователе -->
        <div class="user-info">
            <a href="/profile" class="user-name">${user.firstName}</a>
        </div>

        <div class="actions">
            <a href="/events" class="events">Мероприятия</a>
        </div>

        <!-- Поле поиска -->
        <div class="search-bar">
            <form action="/home" method="post">
                <label for="name">Поиск:</label>
                <input id="name" name="name" type="text" value="${name!""}">
                <button type="submit">Искать</button>
            </form>
        </div>

    </header>

    <div class="content">
        <div class="pagination">

            <a href="/home?page=${page - 1}&name=${name!""}" class="pagination-btn left"
               style="visibility: ${left_visibility! 'hidden'};">&#8592; Назад</a>
            <a href="/home?page=${page + 1}&name=${name!""}" class="pagination-btn right"
               style="visibility: ${right_visibility! 'hidden'};">Вперед &#8594;</a>
        </div>
        <!-- Секция новостей -->
        <section class="news-section">
            <h2>Новости общежития</h2>

            <div class="news-list">
                <#list news as newsItem>
                    <div class="news-item">
                        <a href="/news?id=${newsItem.id}" class="news-link">
                            <img src="/static/images/${newsItem.imageUrl}" alt="Фото новости" class="news-image">
                            <div class="news-content">
                                <h3 class="news-title">${newsItem.title}</h3>
                                <a class="news-description">${newsItem.description}</a>
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
