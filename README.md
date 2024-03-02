<h3>Демонстрационное RESTful приложение, реализующее управление проектами.</h3>

Реализовано с использованием Spring Boot, Maven, PostgreSQL. <br> Запуск осуществляется через pers.nefedov.selsuptestapp.MotiwareTestappApplication. <br>
Для работы приложения необходимо запустить PostgreSQL в контейнере Docker с помощью команды  <br> <i>'docker run -d --name testapp_db -p 5432:5432 -e POSTGRES_USER=test_app -e  POSTGRES_PASSWORD=password -e POSTGRES_DB=testapp_db postgres:latest'</i> <br>
SQL-скрипт для создания БД находится в файле schema.sql (src/main/resources/schema.sql), скрипт для наполнения БД тестовыми данными - в файле data.sql (src/main/resources/data.sql). <br>
Описание эндпойнтов с примерами запросов реализовано с помощью Swagger, интерфейс которого находится по адресу http://localhost:8080/swagger-ui. <br>
Нагрузочное тестирование осуществляется с помощью Gatling, запуск (при запущенном приложении) осуществляется в Intellij Idea через панель Maven - Plugins - gatling:test, или командой mvn gatling:test.<br>
Файлы для загрузки контрольных точек checkpoints.csv и checkpoints1.csv находятся в корневом каталоге проекта.
