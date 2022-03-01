
# Case 3

Bir online alışveriş sitesinin kullanıcı, ürün ve yorumlarını yöneten api

## API Kullanımı

#### Base url

```http
  localhost:8080/api/v1
```
## USERS

#### Kullanıcı oluştur

```http
  POST /users
```

```javascript
  {
    "email": "string",
    "phoneNumber": "string",
    "userName": "string",
    "userType": "INDIVIDUAL" or "CORPORATION"
  }
```

#### Tüm kullanıcıları getir

```http
  GET /users
```

#### Kullanıcıyı getir

```http
  GET /users/{id}
```

#### Kullanıcıyı username ile getir

```http
  GET /users/username/{userName}
```

#### Kullanıcıyı güncelle

```http
  PUT /users/{id}
```

```javascript
  {
    "email": "string",
    "phoneNumber": "string",
    "userName": "string",
    "userType": "INDIVIDUAL" or "CORPORATION"
  }
```

#### Kullanıcıyı kullanıdı adı ve telefon numarası sil

```http
  DELETE /users
```
#### query
| Parametre | Tip       | Açıklama       |
| :-------- | :-------  | :------------  |
| `userName` | `string` | **required**   |
| `phoneNumber` | `string` | **required**   |

## PRODUCTS

#### Ürün oluştur

```http
  POST /products
```

```javascript
  {
    "name": "string",
    "price": 0
  }
```

#### Tüm ürünleri getir

```http
  GET /products
```

#### Ürünü getir

```http
  GET /products/{id}
```

#### Ürün fiyatını güncelle

```http
  PUT /products/{id}
```

```javascript
  {
    "price": 0
  }
```

#### Ürünü sil

```http
  DELETE /products/{id}
```

## COMMENTS

#### Yorum oluştur

```http
  POST /comments
```

```javascript
  {
    "context": "string",
    "productId": 0,
    "userId": 0
  }
```

#### Bir kullanıcının yorumlarını getir

```http
  GET /comments/users/{id}
```

#### Bir ürünün yorumlarını getir

```http
  GET /comments/products/{id}
```

#### Yorumu sil

```http
  DELETE /comments/{id}
```