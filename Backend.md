Вот пример схемы базы данных, написанный с использованием DBML для dbdiagram.io:

```dbml
Table poll {
  id integer [pk, increment] // Уникальный идентификатор
  title varchar [not null] // Название опроса
  created_at timestamp [not null, default: `now()`] // Время создания
}

Table question {
  id integer [pk, increment] // Уникальный идентификатор
  order_index integer [not null] // Порядковый номер вопроса
  text text [not null] // Текст вопроса
  type varchar [not null] // Тип вопроса (OPEN, SINGLE_CHOICE, MULTIPLE_CHOICE)
  poll_id integer [not null] // ID опроса
  created_at timestamp [not null, default: `now()`] // Время создания
}

Table option {
  id integer [pk, increment] // Уникальный идентификатор
  text text [not null] // Текст варианта ответа
  question_id integer [not null] // ID вопроса
}

Table poll_session {
  id integer [pk, increment] // Уникальный идентификатор сессии
  poll_id integer [not null] // ID опроса
  start_time timestamp [not null, default: `now()`] // Время начала сессии
  end_time timestamp // Время окончания сессии
  is_completed bool [not null, default: 'false'] // Статус сессии
  current_order_index integer // Текущий вопрос сессии
}

Table response {
  id integer [pk, increment] // Уникальный идентификатор
  poll_session_id integer [not null] // ID сессии опроса
  question_id integer [not null] // ID вопроса
  text text // Ответ пользователя (для открытых вопросов)
  timestamp timestamp [not null, default: `now()`] // Время отправки ответа
}

Table selected_option {
  id integer [pk, increment] // Уникальный идентификатор
  response_id integer [not null] // ID ответа
  option_id integer [not null] // ID варианта выбора
}

Ref: question.poll_id > poll.id
Ref: option.question_id > question.id
Ref: response.question_id > question.id
Ref: response.poll_session_id > poll_session.id
Ref: selected_option.response_id > response.id
Ref: selected_option.option_id > option.id
Ref: poll_session.poll_id > poll.id
Ref: poll_session.current_order_index > question.order_index

```

Схема базы данных:

```sql
-- Таблица для хранения информации об опросах
CREATE TABLE poll (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор опроса
    title VARCHAR(255) NOT NULL, -- Название опроса
    active BOOLEAN NOT NULL DEFAULT FALSE, -- Статус активности опроса
    created_at TIMESTAMP NOT NULL DEFAULT NOW() -- Время создания опроса
);

-- Таблица для хранения вопросов, связанных с опросами
CREATE TABLE question (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор вопроса
    text TEXT NOT NULL, -- Текст вопроса
    type VARCHAR(50) NOT NULL, -- Тип вопроса (OPEN, SINGLE_CHOICE, MULTIPLE_CHOICE)
    poll_id INTEGER NOT NULL, -- ID опроса, к которому принадлежит вопрос
    created_at TIMESTAMP NOT NULL DEFAULT NOW(), -- Время создания вопроса
    FOREIGN KEY (poll_id) REFERENCES poll(id) ON DELETE CASCADE
);

-- Таблица для хранения вариантов ответов на вопросы
CREATE TABLE option (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор варианта ответа
    text TEXT NOT NULL, -- Текст варианта ответа
    question_id INTEGER NOT NULL, -- ID вопроса, к которому относится вариант ответа
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

-- Таблица для хранения сессий опросов
CREATE TABLE poll_session (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор сессии опроса
    poll_id INTEGER NOT NULL, -- ID опроса
    start_time TIMESTAMP NOT NULL DEFAULT NOW(), -- Время начала сессии
    end_time TIMESTAMP, -- Время окончания сессии
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE', -- Статус сессии (ACTIVE, COMPLETED)
    current_question_id INTEGER, -- Текущий вопрос, который администратор показывает участникам
    FOREIGN KEY (poll_id) REFERENCES poll(id) ON DELETE CASCADE,
    FOREIGN KEY (current_question_id) REFERENCES question(id) ON DELETE SET NULL
);

-- Таблица для хранения ответов пользователей
CREATE TABLE response (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор ответа
    poll_session_id INTEGER NOT NULL, -- ID сессии опроса
    question_id INTEGER NOT NULL, -- ID вопроса
    text TEXT, -- Текст ответа пользователя (для открытых вопросов)
    timestamp TIMESTAMP NOT NULL DEFAULT NOW(), -- Время отправки ответа
    FOREIGN KEY (poll_session_id) REFERENCES poll_session(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);

-- Таблица для связи "многие ко многим" между ответами и вариантами выбора
CREATE TABLE selected_option (
    id SERIAL PRIMARY KEY, -- Уникальный идентификатор
    response_id INTEGER NOT NULL, -- ID ответа
    option_id INTEGER NOT NULL, -- ID варианта выбора
    FOREIGN KEY (response_id) REFERENCES response(id) ON DELETE CASCADE,
    FOREIGN KEY (option_id) REFERENCES option(id) ON DELETE CASCADE
);
```

- Опросы (`poll`) и вопросы (`question`) связаны через `poll_id`.
- Вопросы и их варианты ответов (`option`) связаны через `question_id`.
- Сессии опросов (`poll_session`) позволяют отслеживать каждый запуск опроса во времени и управлять текущим отображаемым
  вопросом через `current_question_id`.
- Ответы (`response`) связаны с вопросами и сессиями опросов, позволяя точно отслеживать, когда и в рамках какой сессии
  был дан ответ.
- Таблица `selected_option` реализует связь "многие ко многим" между ответами и вариантами ответов, позволяя участникам
  выбирать несколько вариантов ответа на вопросы с множественным выбором.

# SPRING SECURITY:

При использовании Spring Security для реализации механизма аутентификации и авторизации администратора опросов, вам
потребуется добавить несколько таблиц в вашу схему базы данных для хранения информации о пользователях, их ролях и
возможно о разрешениях (если вы планируете использовать более детальную модель разрешений). Вот как могла бы выглядеть
модифицированная схема:

### Таблица `users`:

```plaintext
Table users {
  id integer [pk, increment] // Уникальный идентификатор пользователя
  username varchar [not null, unique] // Имя пользователя
  password varchar [not null] // Хэшированный пароль
  enabled boolean [not null, default: true] // Статус активности пользователя
}
```

### Таблица `roles`:

```plaintext
Table roles {
  id integer [pk, increment] // Уникальный идентификатор роли
  name varchar [not null, unique] // Название роли
}
```

### Таблица `user_roles` (связь "многие ко многим" между пользователями и ролями):

```plaintext
Table user_roles {
  user_id integer [not null] // ID пользователя
  role_id integer [not null] // ID роли
  Ref: user_id > users.id
  Ref: role_id > roles.id
}
```

### Интеграция с Spring Security

- **Хранение пользователей и ролей**: Spring Security может использовать таблицы `users`, `roles` и `user_roles` для
  хранения информации о пользователях и их ролях. Вы можете настроить Spring Security для работы с вашей схемой базы
  данных с помощью `UserDetailsService` и `GrantedAuthority`.

- **Кодирование паролей**: Для безопасного хранения паролей рекомендуется использовать хороший механизм хеширования,
  например, BCrypt. Spring Security предоставляет класс `BCryptPasswordEncoder`, который может быть использован для этой
  цели.

- **Конфигурация Spring Security**: Вам нужно будет настроить Spring Security в вашем приложении для использования
  указанных таблиц для аутентификации и авторизации. Это включает в себя конфигурацию `WebSecurityConfigurerAdapter`,
  где вы определяете, какие URL требуют аутентификации, какая форма аутентификации используется (например, форма входа),
  и как пользователи и их роли загружаются из базы данных.

- **Аутентификация и авторизация**: С помощью Spring Security вы сможете легко определить, какие действия разрешены для
  разных ролей в вашем приложении, используя аннотации `@PreAuthorize`, `@Secured` или конфигурацию HTTP Security для
  определения правил доступа к URL.

Добавление этих таблиц и настройка Spring Security значительно улучшат безопасность вашего приложения, предоставив
гибкие и мощные средства для управления доступом пользователей и администраторов к различным функциям системы опросов.