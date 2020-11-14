# Site of the tattoo parlor "Bullfinch" <a name="english"> </a>
### Author: Borikov Oleg
### [Functionality demonstration](https://youtu.be/13-piVMcKw0)
### [RUS](#russian)
#
## Short description
Website for maintaining the working capacity of the tattoo parlor.
The client, having replenished the balance, can leave an order for a tattoo session.
The administrator has the ability to confirm or cancel the order.
After confirming the order, the specified amount is debited from the client's account.
The client can offer his own sketches to the salon, for that he can get a discount.
The administrator decides if the content is suitable for the salon, and if it does, he adds a sketch to the catalog.
#
## Actors
### A guest
An unauthorized user can go to the site and see the catalog of tattoos,
as well as register or authorize to be able to place an order.
* Description of guest actions
  * Check in
  * Authorization
  * Browse catalog
  * Search in catalog
  * View tattoo information
  * Change of localization
### User
An authorized user has access to the catalog of tattoos, can make an order and offer his own tattoo.
It is also possible to replenish the balance, view discounts and order statuses in the personal profile, change personal information.
A user account can have the following statuses: not activated, if email is not confirmed,
blocked, if the administrator has restricted access to the site,
and active, if the user has confirmed the email, and the administrator has not restricted access to the site.
The first two statuses prohibit the user from logging into their account.
* Description of user actions
  * Tattoo offer
  * Checkout
  * View order
  * Cancellations
  * View personal profile
  * View all orders
  * View available discounts
  * Balance replenishment
  * Editing profile information
  * Browse catalog
  * Search in catalog
  * View tattoo information
  * Change of localization
### Administrator
Manages the system: users and tattoo catalog.
He works with the proposed content, also monitors the relevance of goods and prices.
* Description of user actions
  * Add tattoo
  * Accept or refuse the proposed tattoo
  * Tattoo archive management
  * Tattoo editing
  * View all users
  * Search by users
  * View user profile
  * View user's orders
  * View order
  * Acceptance or refusal of an order
  * View user's discounts
  * Add or remove discounts
  * User lock control
  * Browse catalog
  * View all orders
  * Search for all orders
  * View all tattoos
  * Search all tattoos
  * View tattoo information
  * Change of localization
#
## Objects of subject area
### Tattoo
Tattooing is the main subject area of the site.
All actions on the site mainly affect her.
A tattoo can have several statuses: archived, if the administrator added the tattoo to the archive,
suggested, if the tattoo is suggested by the user and not confirmed by the admin,
and active, if the tattoo is in the catalog.
In the case of the first two statuses, only the administrator can view the tattoo.
### Order
The user leaves the order in case he wants to have a tattoo session.
Discounts that the user has are applicable to the order.
After adding an order, the administrator needs to confirm (or cancel) it.
Before confirming the order, the user can cancel it himself.
The cost of the order will be deducted from the user's account only after confirmation by the administrator.
---
# Сайт тату-салона "Bullfinch" <a name="russian"></a>
### Автор: Бориков Олег
### [Демонстрация функциональности](https://youtu.be/13-piVMcKw0)
### [ENG](#english)
# 
## Краткое описание
Сайт по поддержанию работоспособности тату-салона.
Клиент, пополнив баланс, может оставить заказ на проведение тату-сеанса.
Администратор в свою очередь имеет возможность подтвердить или отменить заказ. 
После подтверждения заказа указанная сумма списывается со счёта клиента.
Клиент может предлагать собственные эскизы салону, за что может получить скидку.
Администратор решает, подходит ли контент для салона, и если подходит, то добавляет эскиз в каталог.
#
## Актёры
### Гость 
Неавторизованный пользователь может зайти на сайт и ознакомиться с каталогом татуировок, 
а также пройти регистрацию или авторизацию для возможности сделать заказ.
* Описание действий гостя
  * Регистрация
  * Авторизация
  * Просмотр каталога
  * Поиск по каталогу
  * Просмотр информации о татуировке
  * Смена локализации
### Пользователь
Авторизованный пользователь имеет доступ к каталогу татуировок, может сделать заказ и предложить свою татуировку.
А также возможно пополнение баланса, просмотр скидок и статусов заказов в личном профиле, изменение личной информации.
Аккаунт пользователя может иметь следующие статусы: не активированный, если почта не подтверждена, 
заблокированный, если администратор ограничил доступ к сайту, 
и активный, если пользователь подтвердил почту, и администратор не ограничил доступ к сайту.
Первые два статуса запрещают пользователю зайти в свой аккаунт.
* Описание действий пользователя
  * Предложение татуировки
  * Оформление заказа
  * Просмотр заказа
  * Отмена заказа
  * Просмотр личного профиля
  * Просмотр всех заказов
  * Просмотр доступных скидок
  * Пополнение баланса
  * Редактирование информации профиля
  * Просмотр каталога
  * Поиск по каталогу
  * Просмотр информации о татуировке
  * Смена локализации
### Администратор
Управляет системой: пользователями и каталогом татуировок.
Работает с предложенным контентом, также следит за актуальностью товаров и цен. 
* Описание действий администратора
  * Добавление татуировки
  * Прием или отказ предложенной татуировки
  * Управление архивом татуировок
  * Редактирование татуировки
  * Просмотр всех пользователей
  * Поиск по пользователям
  * Просмотр профиля пользователя
  * Просмотр заказов пользователя
  * Просмотр заказа
  * Прием или отказ заказа
  * Просмотр скидок пользователя
  * Добавление или удаление скидки
  * Управление блокировкой пользователя
  * Просмотр каталога
  * Просмотр всех заказов
  * Поиск по всем заказам
  * Просмотр всех татуировок
  * Поиск по всем татуировкам
  * Просмотр информации о татуировке
  * Смена локализации
#
## Объекты предметной области
### Татуировка
Татуировка является основной предметной областью сайта. 
Все действия на сайте в основном ее затрагивают.
Татуировка может иметь несколько статусов: архивированная, если администратор добавил татуировку в архив,
предложенная, если татуировка предложена пользователем и не подтверждена админом,
и активная, если татуировка находится в каталоге.
В случае первых двух статусов татуировку может просматривать только администратор.
### Заказ
Заказ оставляет пользователь в случае, если хочет провести тату-сеанс.
К заказу применимы скидки, которые имеет пользователь.
После добавления заказа, администратору необходимо его подтвердить(или отменить).
До подтверждения заказа, пользователь может отменить его самостоятельно.
Стоимость заказа будет вычтена со счёта пользователя только после подтверждения администратора.
---
