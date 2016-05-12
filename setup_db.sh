#!/bin/sh

sudo cp /vagrant/pg_hba.conf /etc/postgresql/9.1/main/
sudo cp /vagrant/postgresql.conf /etc/postgresql/9.1/main/
sudo service postgresql restart
sudo cp /vagrant/init.sql /home/vagrant/
sudo cp /vagrant/setup_portale.sql /home/vagrant/
sudo -u postgres psql -f init.sql
sudo -u postgres psql -d portale -f setup_portale.sql