<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создать мероприятие</title>
    <link rel="stylesheet" href="static/create_event_page_styles.css">
</head>
<body>
<div class="container">
    <h1>Создать мероприятие</h1>
    <form action="create_event" method="post" class="create_button">
        <label for="title">Название:</label>
        <input type="text" id="title" name="title" required>

        <label for="description">Описание:</label>
        <textarea id="description" name="description" required></textarea>

        <label for="eventDate">Дата:</label>
        <input type="datetime-local" id="eventDate" name="eventDate" required>

        <label for="location">Место:</label>
        <input type="text" id="location" name="location" required>

        <button type="submit">Создать</button>
    </form>
    <form action="/profile" method="get" class="back-button">
        <button type="submit">Назад</button>
    </form>
</div>
</body>
</html>
