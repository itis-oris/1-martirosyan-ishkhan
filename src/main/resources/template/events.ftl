<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Мероприятия общежития</title>
    <link rel="stylesheet" href="/static/events_page_styles.css">

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
<div class="container">
    <header>
        <!-- Поле поиска -->
        <div class="search-bar">
            <form action="/events" method="post">
                <label for="name">Поиск мероприятий:</label>
                <input id="name" name="name" type="text" value="${name!""}">
                <button type="submit">Искать</button>
            </form>
        </div>

        <div class="actions">
            <a href="/home" class="home">Главная</a>
        </div>
    </header>

    <div class="content">
        <div class="pagination">
            <a href="/events?page=${page - 1}&name=${name!""}" class="pagination-btn left"
               style="visibility: ${left_visibility! 'hidden'};">&#8592; Назад</a>
            <a href="/events?page=${page + 1}&name=${name!""}" class="pagination-btn right"
               style="visibility: ${right_visibility! 'visible'};">Вперед &#8594;</a>
        </div>

        <!-- Секция мероприятий -->
        <section class="events-section">
            <h2>Мероприятия общежития</h2>

            <div class="events-list">
                <#list events as eventItem>
                    <div class="event-item">
                        <a href="/event?id=${eventItem.id}" class="event-link">
                            <div class="event-content">
                                <h3 class="event-title">${eventItem.title}</h3>
                                <a class="event-date">${eventItem.eventDate}</a>
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
