<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новая комната</title>
    <link rel="stylesheet" href="/static/create_event_page_styles.css">
</head>
<body>
<div class="container">
    <h1>Новая комната</h1>
    <form action="create_room" method="post">
        <label for="roomNumber">Номер комнаты:</label>
        <input type="number" id="roomNumber" name="roomNumber" required>

        <label for="capacity">Вместимость:</label>
        <input type="number" id="capacity" name="capacity" required>

        <button type="submit">Добавить</button>
    </form>
    <form action="/profile" method="get" class="back-button">
        <button type="submit">Назад</button>
    </form>
</div>
</body>
</html>
