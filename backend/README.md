# Приложение Blog
Данное приложение реализует функционал для выкладывания постов с REST API. 
# Методы API
## User
### POST api/user/register - создание пользователя. 

Тело запроса (Request Body):

    {
      "username": "string (3-20 символов)",
      "password": "string",
      "email": "string (валидный email)"
    }
Ответ (Response):

    {
      "id": "number",
      "username": "string",
      "email": "string"
    }

Ошибки:

    409 Conflict – если пользователь уже существует.
    400 Bad Request – если данные не прошли валидацию.


### GET api/user/{id} - получение пользователя по id. 
  
Параметры:
    
    id – ID пользователя.

Ответ:

    {
      "id": "number",
      "username": "string",
      "email": "string"
    }
Ошибки:

    404 Not Found – если пользователь не найден.

### PUT api/user/{id}/put - изменение пользователя 

Тело запроса:

    {
      "username": "string (новый username)",
      "email": "string (новый email)"
    }

Ответ:

    {
      "id": "number",
      "username": "string",
      "email": "string"
    }

Ошибки:

    404 Not Found – если пользователь не найден.
    400 Bad Request – если данные не прошли валидацию.

### DELETE api/user/{id}/delete - удаление пользователя.

Ответ:
 
    204 No Content – если удаление успешно.
    Ошибки:
    404 Not Found – если пользователь не найден.
## Post
### GET api/post/all - получение всех постов.
  Ответ:

      [
        {
          "id": "number",
          "title": "string",
          "content": "string",
          "userId": "number",
          "createTime": "string (дата в формате ISO)"
        },
        ...
      ]
### GET api/post/{id} - получение поста по id.  

Ответ:

    {
        "id": "number",
        "title": "string",
        "content": "string",
        "userId": "number",
        "createTime": "string (дата в формате ISO)"
    }

Ошибки:

    404 Not Found – если пост не найден.
### POST api/post/create - создание нового поста  (требуется авторизация).

Тело запроса:

    {
      "title": "string (5-200 символов)",
      "content": "string (минимум 10 символов)"
    }

Ответ:

    {
      "id": "number",
      "title": "string",
      "content": "string",
      "userId": "number",
      "createTime": "string (дата в формате ISO)"
    }

Ошибки:

    401 Unauthorized – если пользователь не авторизован.
    400 Bad Request – если данные не прошли валидацию.

### PUT api/post/{id}/update - обновление поста по id (только автор). 

Тело запроса:

    {
        "title": "string (новый заголовок)",
        "content": "string (новый контент)"
    }

Ответ:

    {
        "id": "number",
        "title": "string",
        "content": "string",
        "userId": "number",
        "createTime": "string (дата в формате ISO)"
    }

Ошибки:

    403 Forbidden – если пользователь не автор.
    404 Not Found – если пост не найден.

### DELETE api/post/{id}/delete - удаление поста по id (только автор).  

Ответ:

    204 No Content – если удаление успешно.
Ошибки:

    403 Forbidden – если пользователь не автор.
    404 Not Found – если пост не найден.