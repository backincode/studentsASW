#!/bin/bash
echo Installing software-properties-common
sudo apt-get install -f software-properties-common
#
echo add repository WebUpd8
sudo add-apt-repository ppa:webupd8team/java
#
echo update repository
sudo apt-get update
#install java8
sudo apt-get install -f oracle-java8-installer
#set path
#sudo apt-get install oracle-java8-set-default
./installTomcat.sh
