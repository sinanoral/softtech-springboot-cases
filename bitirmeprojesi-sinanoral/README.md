<div id="top"></div>
<div>
  <h2> Softtech & Patika Java Spring Bootcamp</h2>
    <h3>Graduate Project</h3>
</div>

<!-- ABOUT THE PROJECT -->
## About The Project
* A new user can be created, updated and deleted.
* A new product can be created, updated and deleted.
* Products price can be updated.
* When one of the categories vat rate updated, price of the products of the category also will update.
* All products can be listed.
* All products can be listed by their category.
* All products Products in a certain price range can be listed.
* You can find detailed information about the rules of the project [here](https://github.com/165-Softtech-Patika-Java-Spring/BitirmeProjesi).

### Built With
* [Docker](https://www.docker.com/)
* [Java 11](https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html)
* [IntelliJ Idea](https://www.jetbrains.com/idea)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Validation](https://spring.io/guides/gs/validating-form-input)
* [Developer Tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
* [Postgre SQL](https://www.postgresql.org/)
* [Open API](https://springdoc.org)
* [Lombok](https://projectlombok.org)

<!-- GETTING STARTED -->
## Getting Started
### Installation
1. Clone the repo
   ```sh
   git clone https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-sinanoral.git
   ```
2. Install JDK-11 and switch to it.
3. Import the project to your IDE.
4. Create a new database named **softtechgraduate** on PostgreSQL.
5. Get your ip address via cmd.
   ```sh
   ipconfig
   ```
6. Go to project file structure and find the application.properties file under resources.
7. You must edit local address section on application.properties file.
   ```
    spring.datasource.url=jdbc:postgresql://YOUR_IP:5432/softtechgraduate
   ```
8. You must edit username and password sections on application.properties file.
9. Run project and then database tables will be generated.
10. After the tables generated, run [this](https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-sinanoral/blob/main/src/main/resources/import_categories.sql) sql script.
### And you are ready...

### For docker

1. Go to 'C:\Program Files\PostgreSQL\14\data' and add following line to end of the 'pg_hba.conf' file.
    ![postgre-path](https://user-images.githubusercontent.com/10232721/151714910-0d9f2a7f-5246-47b8-a8ea-bd5223e1b5e8.png)
    ```sh
    host    all             all             0.0.0.0/0            	md5
    ```
2. Run the clean and install to create target files.
    ```
    mvn clean instal
    ```
3. Run the following docker commands:
   ```sh
   docker build . -t softtech-graduation-project:1.0
   ```
   ```sh
   docker run -p 8080:8080 softtech-graduation-project:1.0
   ```
4. Open the swagger ui on your browser. Default link -> http://localhost:8080/swagger-ui/index.html

<!-- CONTRIBUTING -->
## Contributing
Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
   <h4 align="center"> OR </h4>
<p align="center"><a href="https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-sinanoral/issues">Create an issue</a> </p>

<!-- LICENSE -->
## License
Distributed under the MIT License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact
Sinan ORAL: https://www.linkedin.com/in/sinanorl/

Project Link: https://github.com/165-Softtech-Patika-Java-Spring/bitirmeprojesi-sinanoral
