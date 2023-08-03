# Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

Build Restful CRUD API for a blog using Spring Boot, Mysql, JPA and Hibernate.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/brandy-kay/ThoughtHub
```

**2. Create Mysql database**
```bash
create database thoughthub
```
- run `src/main/resources/thoughthubapi.sql`

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

> Make sure your database is on

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8089>

## Explore Rest APIs

Once you run the project successfully, you can access the swagger docs using the following link:

<http://localhost:8089/swagger-ui.html>

The app defines following CRUD APIs.

### Auth

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /api/auth/signup | Sign up | [JSON](#signup) |
| POST   | /api/auth/signin | Log in | [JSON](#signin) |

### Users

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/me | Get logged in user profile | |
| GET    | /api/users/{username}/profile | Get user profile by username | |
| GET    | /api/users/{username}/posts | Get posts created by user | |
| GET    | /api/users/{username}/albums | Get albums created by user | |
| GET    | /api/users/checkUsernameAvailability | Check if username is available to register | |
| GET    | /api/users/checkEmailAvailability | Check if email is available to register | |
| POST   | /api/users | Add user (Only for admins) | [JSON](#usercreate) |
| PUT    | /api/users/{username} | Update user (If profile belongs to logged in user or logged in user is admin) | [JSON](#userupdate) |
| DELETE | /api/users/{username} | Delete user (For logged in user or admin) | |
| PUT    | /api/users/{username}/giveAdmin | Give admin role to user (only for admins) | |
| PUT    | /api/users/{username}/TakeAdmin | Take admin role from user (only for admins) | |
| PUT    | /api/users/setOrUpdateInfo | Update user profile (If profile belongs to logged in user or logged in user is admin) | [JSON](#userinfoupdate) |

### Posts

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/posts | Get all posts | |
| GET    | /api/posts/{id} | Get post by id | |
| POST   | /api/posts | Create new post (By logged in user) | [JSON](#postcreate) |
| PUT    | /api/posts/{id} | Update post (If post belongs to logged in user or logged in user is admin) | [JSON](#postupdate) |
| DELETE | /api/posts/{id} | Delete post (If post belongs to logged in user or logged in user is admin) | |

### Comments

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/posts/{postId}/comments | Get all comments which belongs to post with id = postId | |
| GET    | /api/posts/{postId}/comments/{id} | Get comment by id if it belongs to post with id = postId | |
| POST   | /api/posts/{postId}/comments | Create new comment for post with id = postId (By logged in user) | [JSON](#commentcreate) |
| PUT    | /api/posts/{postId}/comments/{id} | Update comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | [JSON](#commentupdate) |
| DELETE | /api/posts/{postId}/comments/{id} | Delete comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | |

### Albums

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/albums | Get all albums | |
| GET    | /api/albums/{id} | Get album by id | |
| POST   | /api/albums | Create new album (By logged in user) | [JSON](#albumcreate) |
| PUT    | /api/albums/{id} | Update album (If album belongs to logged in user or logged in user is admin) | [JSON](#albumupdate) |
| DELETE | /api/albums/{id} | Delete album (If album belongs to logged in user or logged in user is admin) | |
| GET    | /api/albums/{id}/photos | Get all photos which belongs to album with id = id | |

### Photos

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/photos | Get all photos | |
| GET    | /api/photos/{id} | Get photo by id | |
| POST   | /api/photos | Create new photo (By logged in user) | [JSON](#photocreate) |
| PUT    | /api/photos/{id} | Update photo (If photo belongs to logged in user or logged in user is admin) | [JSON](#photoupdate) |
| DELETE | /api/photos/{id} | Delete photo (If photo belongs to logged in user or logged in user is admin) | |

### Todos

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/todos | Get all todos which belongs to logged in user | |
| GET    | /api/todos/{id} | Get todo by id (If todo belongs to logged in user) | |
| POST   | /api/todos | Create new todo (By logged in user) | [JSON](#todocreate) |
| PUT    | /api/todos/{id} | Update todo (If todo belongs to logged in user) | [JSON](#todoupdate) |
| DELETE | /api/todos/{id} | Delete todo (If todo belongs to logged in user) | |
| PUT    | /api/todos/{id}/complete | Mark todo as complete (If todo belongs to logged in user) | |
| PUT    | /api/todos/{id}/unComplete | Mark todo as uncomplete (If todo belongs to logged in user) | |

Test them using postman or any other rest client.

## Sample Valid JSON Request Bodys

##### <a id="signup">Sign Up -> /api/auth/signup</a>
```json
{
	"firstName": "brandy",
	"lastName": "kay",
	"username": "brandy-kay",
	"password": "password",
	"email": "brandy.kay@gmail.com"
}
```

##### <a id="signin">Log In -> /api/auth/signin</a>
```json
{
	"usernameOrEmail": "brandy-kay",
	"password": "password"
}
```

##### <a id="usercreate">Create User -> /api/users</a>
```json
{
	"firstName": "john",
	"lastName": "doe",
	"username": "john-doe",
	"password": "password",
	"email": "john.doe@gmail.com",
	"address": {
		"street": "Tom Mboya",
		"suite": "Suite 100",
		"city": "Nairobi",
		"zipcode": "0100",
		"geo": {
			"lat": "-43.9509",
			"lng": "-34.4618"
		}
	},
	"phone": "0712345678",
	"website": "http://johndoe.com",
	"company": {
		"name": "Down-Crist",
		"catchPhrase": "Proactive didactic contingency",
		"bs": "synergize scalable supply-chains"
	}
}
```

##### <a id="userupdate">Update User -> /api/users/{username}</a>
```json
{
	"firstName": "john",
	"lastName": "Doe",
	"username": "john-doe",
	"password": "updatedpassword",
	"email": "john.doe@gmail.com",
	"address": {
		"street": "Moi Avenue",
		"suite": "Suite 0100",
		"city": "Nairobi",
		"zipcode": "01000",
		"geo": {
			"lat": "-43.9509",
			"lng": "-34.4618"
		}
	},
	"phone": "010-692-6593 x09125",
	"website": "http://johnDoe.com",
	"company": {
		"name": "Denmack",
		"catchPhrase": "production of the app",
		"bs": "blah,blah"
	}
}
```

##### <a id="userinfoupdate">Update User Profile -> /api/users/setOrUpdateInfo</a>
```json
{
	"street": "Moi Avenue",
	"suite": "Suite 0100",
	"city": "Nairobi",
	"zipcode": "01000",
	"companyName": "Apatmentty",
	"catchPhrase": "Book apatments",
	"bs": "E-platform strategic applications",
	"website": "http://apartment.info",
	"phone": "1-463-123-4447",
	"lat": "-68.6102",
	"lng": "-47.0653"
}
```

##### <a id="postcreate">Create Post -> /api/posts</a>
```json
{
	"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	"body": "quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut ut quas totam nostrum rerum est autem sunt rem eveniet architecto"
}
```

##### <a id="postupdate">Update Post -> /api/posts/{id}</a>
```json
{
	"title": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED",
	"body": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED "
}
```

##### <a id="commentcreate">Create Comment -> /api/posts/{postId}/comments</a>
```json
{
	"body": "laudantium enim quasi est quidem magnam voluptate ipsam eos tempora quo necessitatibus dolor quam autem quasi reiciendis et nam sapiente accusantium"
}
```

##### <a id="commentupdate">Update Comment -> /api/posts/{postId}/comments/{id}</a>
```json
{
	"body": "UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED UPDATED "
}
```

##### <a id="albumcreate">Create Album -> /api/albums</a>
```json
{
	"title": "quidem molestiae enim"
}
```

##### <a id="albumupdate">Update Album -> /api/albums/{id}</a>
```json
{
	"title": "quidem molestiae enim UPDATED"
}
```

##### <a id="photocreate">Create Photo -> /api/photos</a>
```json
{
	"title": "accusamus beatae ad facilis cum similique qui sunt",
	"url": "https://via.placeholder.com/600/92c952",
	"thumbnailUrl": "https://via.placeholder.com/150/92c952",
	"albumId": 2
}
```

##### <a id="photoupdate">Update Photo -> /api/photos{id}</a>
```json
{
	"title": "accusamus beatae ad facilis ",
	"url": "https://via.placeholder.com/600/771796",
	"thumbnailUrl": "https://via.placeholder.com/150/771796",
	"albumId": 4
}
```

##### <a id="todocreate">Create Todo -> /api/todos</a>
```json
{
	"title": "delectus aut autem",
	"completed": false
}
```

##### <a id="todoupdate">Update Todo -> /api/todos{id}</a>
```json
{
	"title": "delectus aut autem Updated",
	"completed": true
}
```
