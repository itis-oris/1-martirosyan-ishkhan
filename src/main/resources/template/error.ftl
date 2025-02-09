<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ошибка</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f4f4f4;
            color: #333;
            margin: 0;
            padding: 0;
        }

        .error-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            flex-direction: column;
        }

        .error-code {
            font-size: 100px;
            font-weight: bold;
            color: #e74c3c;
        }

        .error-message {
            font-size: 24px;
            margin: 20px 0;
            color: #555;
        }

        .error-description {
            font-size: 18px;
            margin-bottom: 30px;
            color: #777;
        }

        .error-link {
            text-decoration: none;
            color: #3498db;
            font-size: 18px;
        }

        .error-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-code">404</div>
    <div class="error-message">Страница не найдена</div>
    <div class="error-description">
        Извините, но мы не смогли найти запрашиваемую страницу.
    </div>
    <a href="/" class="error-link">Перейти на главную страницу</a>
</div>
</body>
</html>
