
# Weather bot
<p align="center"><img src="https://cdn-icons.flaticon.com/png/512/3750/premium/3750446.png?token=exp=1638692140~hmac=9e3375d36babb077e04387116e7ab179"
 alt="Weather" height="100" />
 
 ## Description
 
 Данный бот реализован на платформе <img src="https://cdn.freebiesupply.com/logos/large/2x/java-2-logo-png-transparent.png"
 alt="Weather" height="30" /> + <img src="https://download.logo.wine/logo/Spring_Framework/Spring_Framework-Logo.wine.png"
 alt="Weather" height="30" />. С помощью данного бота вы можете узнать погоду в своем городе. 
 
 ## Development
 
 - Язык разработки: **Java 8**
- Framework: **Spring Boot 2.5.5, Spring Cloud OpenFeign**
- Библиотеки: **Lombok, Mapstruct**
Внешние API: **[Yandex Weather API](https://yandex.com/dev/weather/doc/dg/concepts/about.html "Yandex Weather API"), [Telegram API](https://core.telegram.org/ "Telegram Api")** 
- Сборка: **Gradle**

## API

- Найти всех пользователей, кто пользуется нашим ботом и отправлял геолокацию
  - GET http://localhost:8080/find-all-users
- Найти историю всех пользователей, кто пользуется нашим ботом
  - GET http://localhost:8080/find-all-users
- Найти пользователя по id
  - GET http://localhost:8080/find-user-by-id{id}
- Найти пользователя и историю его всех отправленных геопозиций.
  - GET http://localhost:8080/find-user-by-id{id}
 
 ## How to start
1. Не забудьте установить webhook, где url - http://localhost:8080/, a mytoken - токен вашего бота. Пример https://api.telegram.org/bot{my_bot_token}/setWebhook?url={url_to_send_updates_to}
2. Написать команду на консоли, находясь внутри вашего проекта:
 ```console
 ./gradle bootrun
 ```
 



  
  
 
 
