# Book and Author REST API

This is a simple REST API built using Spring Boot that manages books and authors. It allows you to create, read, update, and delete authors and books. The API provides endpoints for basic CRUD operations and returns data in JSON format.

## Table of Contents

- [Getting Started](#getting-started)
- [Endpoints](#endpoints)
  - [Author Endpoints](#author-endpoints)
    - [POST /authors](#post-authors)
    - [GET /authors/{id}](#get-authorsid)
    - [GET /authors](#get-authors)
    - [PUT /authors/{id}](#put-authorsid)
    - [DELETE /authors/{id}](#delete-authorsid)
  - [Book Endpoints](#book-endpoints)
    - [POST /books](#post-books)
    - [GET /books/{id}](#get-booksid)
    - [GET /books](#get-books)
    - [PUT /books/{id}](#put-booksid)
    - [DELETE /books/{id}](#delete-booksid)
- [Data Models](#data-models)
  - [Author](#author)
  - [Book](#book)

## Getting Started

To get started with this API, clone the repository and run the Spring Boot application. You can test the API endpoints using a tool like Postman or cURL.

## Endpoints

### Author Endpoints

#### POST /authors

**Description**: Create a new author along with their books.

**Request Body**:
```json
{
  "name": "Author Name",
  "country": "Country Name",
  "books": [
    {
      "name": "Book 1",
      "price": 19.99
    },
    {
      "name": "Book 2",
      "price": 29.99
    }
  ]
}
```

## GET:- /authors/{id}
# Description: Retrieve the details of an author by their ID.

## GET /authors
# Description: Retrieve a list of all authors.

## PUT /authors/{id}
# Description: Update an author's details.

```json
{
  "name": "Updated Author Name",
  "country": "Updated Country Name"
}
```

## DELETE /authors/{id}
# Description: Delete an author by their ID

## POST /books
# Description: Create a new book with existing author.

```json
{
  "name": "Book Name",
  "price": 19.99,
  "author": {
    "id": 1
  }
}
```

## POST /books
# Description: Create a new book with new author.

```json
{
  "name": "Book Name",
  "price": 19.99,
  "author": {
    "name": "author name"
    "country": "country code/name"
  }
}
```

## GET /books/{id}
# Description: Retrieve the details of a book by its ID.

## GET /books
# Description: Retrieve a list of all books.

## PUT /books/{id}
# Description: Update a book's details.

``` json
{
  "name": "Updated Book Name",
  "price": 24.99,
  "author": {
    "id": 1
  }
}

```

## DELETE /books/{id}
# Description: Delete a book by its ID.



## Data Models
## Author
# id: Integer (Auto-generated)
# name: String
# country: String
# books: List of Book objects associated with this author

## Book
# id: Integer (Auto-generated)
# name: String
# price: Double
# author: Author object associated with this book


