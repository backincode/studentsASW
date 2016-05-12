Vagrant.configure("2") do |config|
  config.vm.define "client" do |client|
  client.vm.box = "hashicorp/precise64"
  client.vm.provision :shell, inline: "echo ============ CLIENT ==============="
  #bind porte guest => host
  client.vm.network "forwarded_port", guest: 8080, host: 8080
  #set ip
  client.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
  #update apt-get [importante]
  client.vm.provision :shell, inline: "sudo apt-get update"
  #installa openJDK7 e Gradle
  client.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-7-jdk"
  client.vm.provision :shell, inline: "sudo apt-get -yf install gradle"
  #riconfiguro orario
  client.vm.provision :shell, inline: "sudo dpkg-reconfigure -f noninteractive tzdata"
  #scarica, scompatta tomcat e pulisci
  client.vm.provision :shell, inline: "wget http://it.apache.contactlab.it/tomcat/tomcat-7/v7.0.69/bin/apache-tomcat-7.0.69.tar.gz"
  client.vm.provision :shell, inline: "tar -zxvf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  client.vm.provision :shell, inline: "rm -rf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  #permessi di accesso GUI e path JAVA_HOME
  client.vm.provision :shell, inline: "sudo cp /vagrant/tomcat-users.xml /home/vagrant/apache-tomcat-7.0.69/conf/"
  client.vm.provision :shell, inline: "sudo cp /vagrant/catalina.sh /home/vagrant/apache-tomcat-7.0.69/bin/"
  #sposto .WAR
  client.vm.provision :shell, inline: "sudo cp /vagrant/project.war /home/vagrant/apache-tomcat-7.0.69/webapps/"  
  #avvio tomcat [autodeploy]
  client.vm.provision :shell, inline: "sudo /home/vagrant/apache-tomcat-7.0.69/bin/startup.sh start"

 end

  config.vm.define "db" do |db|
    db.vm.box = "hashicorp/precise64"
    db.vm.provision :shell, inline: "echo =========== DB ============="
    db.vm.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql-9.1"
    #setting postgresql
    db.vm.provision :shell, path: "setup_db.sh"
    #db.vm.provision :shell, inline: "sudo cp /vagrant/pg_hba.conf /etc/postgresql/9.1/main/"
    #db.vm.provision :shell, inline: "sudo cp /vagrant/postgresql.conf /etc/postgresql/9.1/main/"
    #http://dba.stackexchange.com/questions/100564/cant-connect-to-remote-postgresql-database
 end
end