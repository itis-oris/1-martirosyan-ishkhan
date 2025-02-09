<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создать новость</title>
    <link rel="stylesheet" href="/static/create_news_styles.css">
</head>
<body>
<div class="container">
    <div class="content">
        <section class="news-create">
            <h2>Создать новость</h2>
            <form action="/create_news" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="title">Заголовок</label>
                    <input type="text" id="title" name="title" required>
                </div>

                <div class="form-group">
                    <label for="description">Описание</label>
                    <textarea id="description" name="description" required></textarea>
                </div>

                <div class="form-group">
                    <label for="image">Загрузить изображение</label>
                    <input type="file" id="image" name="image" accept="image/*" required>
                </div>

                <button type="submit" class="submit-button">Создать новость</button>
            </form>
        </section>

        <div class="action_buttons">
            <div class="back_button">
                <a href="/profile" class="to_news_button">Назад</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
