# API-ADMIN
 
 Api responsável pelos administradores, bem como geração e validação de token das apis - USER e - ORDER.


## Instruções para roda

### Porta utilizada
    A aplicação será iniciada na porta 8085
    
   
### Acesso DB, através de environment

  * ${DATABASE_USER}
  * ${DATABASE_PASSWORD}
  * ${DATABASE_URL}


#### SQL
```
    CREATE DATABASE api_admin;

    drop table administrators;

    create table administrators(
    id serial not null primary key,
    name varchar(50) not null,
    email varchar(50) not null unique,
    password text not null
    );

    insert into administrators (name, email, password) values ('Frankilns','Frankilns@email.com','senhaqualquer');
    insert into administrators (name, email, password) values ('Bianca','Bianca@email.com','senhaqualquer');
    insert into administrators (name, email, password) values ('Gabrielli','Gabrielli@email.com', 'senhaqualquer');
    insert into administrators (name,email,  password) values ('Mayanna','Mayanna@email.com', 'senhaqualquer');
    insert into administrators (name,email,  password) values ('Lukas','Lukas@email.com','senhaqualquer');
    insert into administrators (name,email,  password) values ('teste','teste@email.com','senhaqualquer');

```

### Lombok
   Estamos usando o lombok precisa baixar e instalar na ide [Lombok](https://projectlombok.org/download)
   
   	
### Build
    mvn clean compile package -Dmaven.test.skip -DskipTests -Dmaven.javadoc.skip=true

### Iniciar a aplicação
    java -jar target/group5-0.0.1-SNAPSHOT.jar


-------------------

# Endpoints

###### NOTA:
> Os retornos de erros dos endpoint retornam no padrão:

- status,
- code,
- message,
- timestamp,
- path

```
 Exemplo retorno de um elemento não encontrado:

{
    "status": "NOT_FOUND",
    "code": 404,
    "message": "Administrdor não encontrado.",
    "timestamp": 1655138136662,
    "path": "/administrators/6"
}
```
-------------------
## - Login
#### POST /login

###### Efetuar login na api.

**Campos necessários:**
* body
	{
		"email":"name@email",    
    	"password":"password"
	}

**Retorno:**
* Status
    * 200 (sucess)
    * 401 (unauthorized)


```
// Exemplo de retorno:

{  	
    "token": "Bearer xxxx.ttttxx.sss"    
}

```

#### GET /validateToken
###### Validação do token

**Campos necessários:**
* Header
    * Token

**Retorno:**
> retorno somente status:

  * 204 (sucess)
  * 401 (unauthorized)


-------------------
## - Administrators
#### GET /administrators?page={qtd}&size={qtd}
###### Listando administradores.

**Campos necessários:**
* Header
    * Token

** Exemplo req: **

	http://localhost:8085/administrators?page=0&size=15

> Está solicitando a primeira página (page=0), com no máximo 15 itens(size=15).

> O "size" não é obrigatório, por padrão vem 5 elementos.


**Retorno:**
* Status    
    * 200 (sucess)
    * 401 (unauthorized)


```
// Exemplo de retorno:
{
    "content": [
        {
            "id": 1,
            "name": "Admilsson",
            "email": "admilsson@admin"
        },
        {
            "id": 2,
            "name": "Franklins",
            "email": "frank@email.com"
        },
        {
            "id": 3,
            "name": "Lucas",
            "email": "Lfasafaas@email.com"
        },
        {
            "id": 4,
            "name": "Maria Pereira ",
            "email": "mariaper@email.com"
        },
        {
            "id": 5,
            "name": "Julius Betto",
            "email": "juliusBett@email.com"
        }
    ],
    "pageable": {
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 2,
    "totalElements": 10,
    "last": false,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 5,
    "first": true,
    "empty": false
}
```

#### GET /administrators/{id}
###### Listando um administrador pelo id.


**Campos obrigatório:**
* Header
    * Token


** Exemplo req: **

http://localhost:8085/administrators/2


**Retorno:**
* Status    
    * 200 (sucess)
    * 404 (not found)
    * 401 (unauthorized)


```
// Exemplo de retorno:

{
    "id": 2,
    "name": "Franklins",
    "email": "frank@email.com"
}

```

#### POST /administrators
###### Criando novo administrador.

**Campos Obrigatórios:**
* body
	{
        "name": "name",
		"email":"name@email",    
    	"password":"password"
	}

* Header
    * Token

**Retorno:**
* Status
    * 201 (create)
    * 400 (bad request)
    * 401 (unauthorized)


```
// Exemplo de retorno:

{
    "id": 11,
    "name": "Jhon",
    "email": "jhon@email.com"
}

```

#### PUT /administrators/{id}
###### Atualizar administrador pelo id.

**Campos Obrigatórios:**
* body
	{
        "name": "Jhon BeGod",
		"email":"jhon.begod@email",    
    	"password":"password"
	}

* Header
    * Token

** Exemplo req: **

http://localhost:8085/administrators/11

**Retorno:**
* Status
    * 200 (sucess)
    * 400 (bad request)
    * 401 (unauthorized)
    * 404 (not found)


```
// Exemplo de retorno:

{
    "id": 11,
    "name": "Jhon BeGod",
    "email": "jhon.begod@email.com"
}

```


#### DELETE /administrators/{id}
###### Excluir administrador pelo id.

**Campos obrigatório:**
* Header
    * Token


** Exemplo req: **

http://localhost:8085/administrators/11

**Retorno:**
* Status
    * 204 (no content)
    * 400 (bad request)
    * 401 (unauthorized)
    * 404 (not found)

