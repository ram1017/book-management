# Book Management System


# Technology stack used:
1. Frontend - NextJS, CSS, Bootstrap  
2. Backend - SpringBoot  
3. Database - MongoDb database (MongoDB Atlas)  


# Features Implemented

* Book creation with validation (title, author, ISBN, etc.) on both backend and frontend.
* Book list with search, sorting, pagination, and delete options
* Book details page with tabbed view (basic info + more info)
* Book cover upload (stored in  base64 format)
* Unique ID generation for each book (B-001, B-002 etc...)
* API integration with Google Books to fetch description, preview link, page count, etc.

# Bonus Points Covered

* GraphQL API gateway (Also created REST)
* Server-Side Rendering (SSR) using Next.js App Router
* Added functionality to upload custom book covers.

---

# Setting Up project:

*Download the project as separate folder for frontend and backend and open them using VSCode, IntelliJ, etc...*  

### Dependencies & Libraries used

- Install core dependencies
** npm install next react react-dom **

- Apollo Client & GraphQL
npm install @apollo/client graphql

- Apollo support for Next.js App Router
npm install @apollo/experimental-nextjs-app-support @apollo/client-integration-nextjs

- Bootstrap and related UI libraries
npm install bootstrap react-bootstrap react-icons

- TypeScript and type definitions
npm install --save-dev typescript @types/react @types/react-dom @types/node

- (Optional) Initialize TypeScript if not already done
npx tsc --init


---

### Dev Setup Commands

- Install all dependencies  
  **`npm install`**

- Run the frontend in dev mode  
  **`npm run dev`**


### Backend  
Java 17+ and Maven should be installed in the local system

- spring-boot-starter-data-mongodb  
- spring-boot-starter-web  
- spring-boot-starter-graphql  
- graphql-java-extended-scalars  
- jakarta.validation-api  
- spring-boot-devtools  
- lombok  
- spring-boot-starter-test  

*All dependencies are already included in the pom.xml.*  
To install them, run:  
**`mvn clean install`**

To run the application:  
**`mvn spring-boot:run`**

Or use the Maven wrapper if available:  
**`./mvnw clean install`**  
**`./mvnw spring-boot:run`**


# Environmental variables  
(Defined in `application.properties`)  
1. MongoDb uri = `mongodb+srv://bookadmin:bookpass123@cluster0.kdkgqnu.mongodb.net/bookdb?retryWrites=true&w=majority&tls=true&appName=Cluster0`  
2. MongoDb database = `bookdb`  
3. Google Books Api key = `AIzaSyBNk40xCquhLP1W7y4mIsnO1vzOTJF1g7g`  
4. Google Books URL = `https://www.googleapis.com/books/v1/volumes`  


# Run Application  
1. Frontend - `npm run dev`  
2. Backend - `mvn spring-boot:run`  

> ⚠️ The standard ports used are `localhost:8080` for backend and `localhost:3000` for frontend. Any changes must be reflected in the code as well.


# Deployment

1. **Frontend (Vercel)**  
   Deployed using [Vercel](https://vercel.com)  

2. **Backend (Render)**  
   Deployed using [Render](https://render.com)


# API Documentation

## GraphQL

### Queries

```graphql
query {
  allBooks {
    title
    author
    genre
    uniqueId
  }
}
query {
  bookByUniqueId(uniqueId: "B-001") {
    title
    author
    publicationDate
    isbn
    genre
    rating
    coverImage
    description
    previewLink
    pageCount
  }
}

```
### Mutations
```graphql
mutation {
  addBook(
    title: "1984",
    author: "George Orwell",
    publicationDate: "1949-06-08",
    isbn: "9780451524935",
    genre: "Fiction",
    rating: 5,
    coverImage: "base64string"
  )
}

mutation {
  deleteBook(uniqueId: "B-001")
}
```
# REST API Endpoints
```
(Also available for experimental purposes.)

1. Create a Book
Method: POST

URL: /createbook

Input Payload:

{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "publicationDate": "1988-04-01",
  "isbn": "9780061122415",
  "genre": "Fiction",
  "rating": 5,
  "coverImage": "base64string"
}
Response:
{
  "id": "generatedId",
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "genre": "Fiction",
  "publicationDate": "1988-04-01",
  "isbn": "9780061122415",
  "rating": 5,
  "coverImage": "base64string",
  "uniqueId": "B-001"
}
2. Get All Books
Method: GET

URL: /books

Response:
[
  {
    "title": "The Alchemist",
    "author": "Paulo Coelho",
    "genre": "Fiction",
    "publicationDate": "1988-04-01",
    "isbn": "9780061122415",
    "rating": 5,
    "uniqueId": "B-001"
  },{.....},{.....}...
]
3. Get Book Details by Unique ID
Method: GET

URL: /books/{uniqueId}

Response:

{
  "title": "The Alchemist",
  "author": "Paulo Coelho",
  "genre": "Fiction",
  "publicationDate": "1988-04-01",
  "isbn": "9780061122415",
  "rating": 5,
  "coverImage": "base64string",
  "description": "A novel about following your dreams.",
  "previewLink": "https://...",
  "pageCount": 208,
  "uniqueId": "B-001"
}
4. Delete a Book
Method: DELETE

URL: /books/{uniqueId}

Response:
204 No Content

```
# MongoDb Schema
```bash
Each book in this project is stored in a single collection.
Database: bookdb
Collection: books

{
  "_id": "ObjectId",
  "title": "string",
  "author": "string",
  "publicationDate": "string (ISO date format)",
  "isbn": "string (13-digit)",
  "genre": "string",
  "rating": number (1 to 5),
  "uniqueId": "string (e.g., B-001)",
  "coverImage": "string (Base64 or image URL)"
}
```
# Project Folder Structure
```bash
The project has two main parts:

* bookapp/: Spring Boot backend
* book-list/: Next.js frontend

$ Backend (Spring Boot)
book-management/
├── bookapp/                       
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/bookapp/
│   │       │   ├── config/               # CORS configuration-To allow frontend to access the urls and http requests
│   │       │   ├── controller/           # REST controller (BookController) - Mapping for Rest Api is done here
│   │       │   ├── graphql/              # GraphQL controller (BookControllerGraphQL) - Mapping for Graphql is done
│   │       │   ├── dto/                  # Data Transfer Objects - To transfer data between java classes and enable dynamic and response friendly data
│   │       │   ├── model/                # Book entity mapped to MongoDB - The Book Collection is made using @Document from spring -mongodb dependency
│   │       │   ├── repository/           # MongoDB repository interface - To enable db based operations using ORMs
│   │       │   └── service/              # Business logic & Google API integration - Contains BooksService(To handle methods mentioned in mapping) and GooglebooksAPIservice(proxy Googlebooks api and sets data from API )
│   │       └── resources/
│   │           ├── graphql/              # GraphQL schema file 
│   │           ├── static/               # Static resources
│   │           ├── templates/            # Optional templates
│   │           └── application.properties  # Spring Boot config - Contains every environment variavles like mogodb uri and google books api etc...
│   ├── pom.xml                          # Maven dependencies 
│   ├── mvnw / mvnw.cmd                  # Maven wrapper scripts
│   └── README.md                        # Backend readme (if separate)


 $ Frontend (Next.js)
├── book-list/                     
│   ├── public/                    
│   ├── src/
│   │   ├── app/                    # Next.js App Router
│   │   │   ├── book/[id]/          # Book details route (dynamic) 
│   │   │   │          └── page.tsx # Book details - Next provides routing via folder system
│   │   │   ├── layout.tsx
│   │   │   └── page.tsx            # Home page 
│   │   ├── components/             # Reusable components (form, list, details)- these contain components with dynamic pages using CSS,Bootstrap and react based hooks !!These components are called from pages inside app/!
│   │   │   ├── BookDetialsClient.tsx                                                                                                                                                                                
│   │   │   ├── BookListClient.tsx
│   │   │   └── Bookform.tsx 
│   │   ├── graphql/                # Apollo GraphQL queries
│   │   ├── lib/                    # Apollo Client setup files
│   │   ├── styles/                 # CSS files
│   │   └── providers.tsx          # Apollo provider wrapper
│   ├── package.json                # Project metadata and scripts
│   ├── tsconfig.json               # TypeScript config
│   └── README.md                   # Frontend readme (if separate)

└── README.md                       # Root README
```
