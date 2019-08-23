FROM mariadb
ENV MYSQL_DATABASE=world MYSQL_ROOT_PASSWORD=pass
COPY world.sql /docker-entrypoint-initdb.d/
EXPOSE 3306
