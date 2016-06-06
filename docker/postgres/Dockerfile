# Portale studenti database

FROM ubuntu:14.04

# Add the PostgreSQL PGP key to verify their Debian packages.
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys B97B0AFCAA1A47F044F244A07FCC7D46ACCC4CF8 &&\
    echo "deb http://apt.postgresql.org/pub/repos/apt/ precise-pgdg main" > /etc/apt/sources.list.d/pgdg.list &&\
    apt-get update && apt-get -yf install postgresql-9.4 &&\
	echo "host all  all    0.0.0.0/0  md5" >> /etc/postgresql/9.4/main/pg_hba.conf &&\
    echo "listen_addresses='*'" >> /etc/postgresql/9.4/main/postgresql.conf

EXPOSE 5432

# Add files for database setup
ADD database/setup_portale.sql /temp/setup_portale.sql
ADD database/data.sql /temp/data.sql

# Setup database
USER postgres
RUN    /etc/init.d/postgresql start &&\
    psql --command "ALTER USER postgres WITH PASSWORD 'postgres';" &&\
    createdb -O postgres portale &&\
    psql --dbname=portale --file=/temp/setup_portale.sql &&\
    psql --dbname=portale --file=/temp/data.sql


# Start the container
CMD ["/usr/lib/postgresql/9.4/bin/postgres", "-D", "/var/lib/postgresql/9.4/main", "-c", "config_file=/etc/postgresql/9.4/main/postgresql.conf"]
