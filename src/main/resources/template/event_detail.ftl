<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${event.title} - Информация о мероприятии</title>
    <link rel="stylesheet" href="/static/event_detail_page_styles.css">
</head>

<script>
    window.onload = function() {
        var message = "${message!''}";
        if (message) {
            alert(message);
        }
    }
</script>

<body>
<div class="container">
    <div class="content">
        <!-- Секция мероприятия -->
        <section class="event-detail">
            <h2>${event.title}</h2>
            <div class="event-detail-content">
                <div class="event-description">
                    <p><strong>Описание:</strong> ${event.description}</p>
                    <p><strong>Дата:</strong> ${event.eventDate}</p>
                    <p><strong>Место проведения:</strong> ${event.location}</p>
                    <div class="register_button">
                        <a href="" class="to_register_button"
                           style="visibility: ${registered! 'hidden'};"
                        >Вы записаны</a>
                        <p><a href="/register_to_event?eventId=${event.id}" class="to_register_button"
                           style="visibility: ${not_registered! 'visible'};"
                        >Записаться</a></p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Кнопки действий -->
        <div class="action_buttons">
            <div class="back_button">
                <a href="/events" class="to_events_button">К мероприятиям</a>
            </div>

        </div>
    </div>
</div>
</body>
</html>