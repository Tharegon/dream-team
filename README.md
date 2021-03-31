# dream-team
This is a Spring based backend project with [PSQL](http://postgresguide.com/utilities/psql.html).The project is based on a card game, where you can collect [LEC](https://www.google.com/search?client=opera&q=LEC&sourceid=opera&ie=UTF-8&oe=UTF-8) pro players as cards similar to baseball cards and battle with your own fantasy team.

How to setup:<br>
First you need a [database](https://www.postgresql.org/docs/9.1/app-createdb.html) on your device.<br>
```bash 
createdb mydb
```
<br>

Second [connect](https://www.jetbrains.com/help/idea/database-tool-window.html) with your IDE im useing intelliJ IDEA
- Database<br>
- new data source + <br>
- PSQL<br>
- Then [setup](https://www.jetbrains.com/help/idea/connecting-to-a-database.html#connect-to-cassandra-database) the variables<br>

Third setup [environment variables](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html) in your IDE, you will need these variables:
- PSQL_DB_HOST : your host
- PSQL_DB_PORT  : something like 8888
- PSQL_DB_NAME  : name of your database ex.: mydb
- PSQL_DB_USERNAME : your PSQL user name
- PSQL_DB_PASSWORD : your PSQL password
