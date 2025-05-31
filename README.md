## Приложение Blog
Данное приложение реализует фукнционал для выкладывания постов

## Методы API
### User
- api/user/{id} - получение пользователя по id. Метод GET
- api/user/register - создание пользователя. Метод POST
- api/user/{id}/put  - изменение пользователя. Метод PUT
- api/user/{id}/delete - удаление пользователя. Метод DELETE
### Post
- api/post/all - получение всех постов. метод GET
- api/post/{id} - получение поста по id. метод GET
- api/post/create - создание нового поста. метод POST
- api/post/{id}/update - обновление поста по id. метод PUT
- api/post/{id}/delete - удаление поста по id. метод DELETE