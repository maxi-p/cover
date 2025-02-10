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

WIP!
