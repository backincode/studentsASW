#!/bin/sh

sudo cp /vagrant/database/pg_hba.conf /etc/postgresql/9.4/main/
sudo cp /vagrant/database/postgresql.conf /etc/postgresql/9.4/main/
sudo service postgresql restart
sudo cp /vagrant/database/init.sql /home/vagrant/
sudo cp /vagrant/database/setup_portale.sql /home/vagrant/
sudo cp /vagrant/database/data.sql /home/vagrant/
sudo -u postgres psql -f init.sql
sudo -u postgres psql -d portale -f setup_portale.sql
sudo -u postgres psql -d portale -f data.sql