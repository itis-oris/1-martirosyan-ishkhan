<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новая жалоба</title>
    <link rel="stylesheet" href="/static/create_complaint_page_styles.css">
</head>
<body>
<h1>Новая жалоба</h1>
<form method="post" action="/submitComplaint">

    <label for="complaintText">Текст жалобы:</label><br>
    <textarea id="complaintText" name="complaintText" rows="8" required></textarea><br><br>

    <button type="submit">Отправить</button>
    <button type="button" onclick="window.history.back()">Назад</button>
</form>
</body>
</html>
