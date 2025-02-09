<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${newsItem.title} - Новости общежития</title>
    <link rel="stylesheet" href="/static/news_detail_page_styles.css">
</head>
<body>
<div class="container">
    <div class="content">
        <!-- Секция новости -->
        <section class="news-detail">
            <h2>${newsItem.title}</h2>
            <div class="news-detail-content">
                <img src="/static/images/${newsItem.imageUrl}" alt="Фото новости" class="news-image">
                <p class="news-description">${newsItem.description}</p>
            </div>
        </section>

        <!-- Кнопка на главную -->
        <div class="back_button">
            <a href="/home" class="to_home_button">На главную</a>
        </div>
    </div>
</div>
</body>
</html>
