## properties 파일


    spring.application.name=payday
    
    spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/PayDayDB?createDatabaseIfNotExist=true
    spring.datasource.username=${DB_USERNAME:Mysql DB이름}
    spring.datasource.password=${DB_PASSWORD:비밀번호}
    spring.jpa.hibernate.ddl-auto=update

