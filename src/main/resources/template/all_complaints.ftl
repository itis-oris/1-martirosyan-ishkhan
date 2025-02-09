<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Жалобы</title>
    <link rel="stylesheet" href="/static/complaints_page_styles.css">
</head>
<body>
<div class="container">
    <header>
        <!-- Поле поиска -->
        <div class="search-bar">
            <form action="/complaints" method="post">
                <label for="name">Поиск жалоб:</label>
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
            <a href="/check_complaints?page=${page - 1}&name=${name!""}" class="pagination-btn left"
               style="visibility: ${left_visibility! 'hidden'};">&#8592; Назад</a>
            <a href="/check_complaints?page=${page + 1}&name=${name!""}" class="pagination-btn right"
               style="visibility: ${right_visibility! 'visible'};">Вперед &#8594;</a>
        </div>

        <!-- Секция мероприятий -->
        <section class="complaints-section">
            <h2>Жалобы</h2>

            <div class="complaints-list">
                <#list complaints as complaintItem>
                    <div class="complaint-item">

                        <div class="complaint-content">
                            <a class="complaint-link" href="/complaint?id=${complaintItem.id}">
                                <h3 class="complaint-status">${complaintItem.status}</h3>
                            </a>
                            <span class="complaint-text">${complaintItem.complaintText}</span>
                        </div>

                    </div>
                </#list>
            </div>
        </section>
    </div>
</div>
</body>
</html>
