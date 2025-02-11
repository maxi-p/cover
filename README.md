# Cover Generator

This microservice will allow to compile and generate a cover letter based on textual input (contents) provided by users.

## Getting Started

### Running the Server

#### With Docker

- Make sure you have Docker installed
- Make sure you are not using ports 8080, 9090, and 5434
- Run the app. This builds and starts the postgres database, pgadmin, and spring boot app:
  ```
  docker-compose up --build
  ```
- To run in detatched mode (prod)
  ```
  docker-compose up --build -d
  ```
- Stop the cluster:
  ```
  docker-compose down
  ```

## SQL Schemas

### User Profile

Store your default attributes in a profile

```
CREATE TABLE profile (
  id SERIAL PRIMARY KEY,
  profile_name VARCHAR(255),
  default_template_source TEXT,
  default_name VARCHAR(255),
  default_greeting VARCHAR(255),
  default_email VARCHAR(320),
  default_phone VARCHAR(255),
  default_linkedin VARCHAR(255),
  default_github VARCHAR(255),
  default_website VARCHAR(255),
  default_signature_url VARCHAR(2048),
  default_complimentary_close VARCHAR(255)
);
```

Table for default paragraphs for your profile:

```
  CREATE TABLE paragraph (
    id SERIAL PRIMARY KEY,
    content TEXT,
    profile_id INT NOT NULL,
    position INT,
    FOREIGN KEY (profile_id)
        REFERENCES profile(id)
        ON DELETE CASCADE
  );
```

WIP!
