Vagrant.configure("2") do |config|
  config.vm.define "appserver" do |appserver|
  appserver.vm.box = "hashicorp/precise64"
  appserver.vm.hostname = "appserver"
  config.vm.post_up_message  = "============ APPSERVER ==============="
  appserver.vm.network "forwarded_port", guest: 8080, host: 8080
  appserver.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
  appserver.vm.provision :shell, inline: "sudo apt-get update"
  appserver.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-7-jdk"
  appserver.vm.provision :shell, inline: "sudo apt-get -yf install gradle"
  appserver.vm.provision :shell, inline: "wget http://it.apache.contactlab.it/tomcat/tomcat-7/v7.0.69/bin/apache-tomcat-7.0.69.tar.gz"
  appserver.vm.provision :shell, inline: "tar -zxvf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  appserver.vm.provision :shell, inline: "rm -rf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  appserver.vm.provision :shell, inline: "sudo wget https://github.com/backincode/studentsASW/blob/sbaffoni/project.war -P /home/vagrant/apache-tomcat-7.0.69/webapps/"
  #appserver.vm.provision :shell, inline: "/home/vagrant/apache-tomcat-7.0.69/bin/startup.sh start"
#https://github.com/mitchellh/vagrant/issues/5973 
 end

  config.vm.define "dbserver" do |db|
    db.vm.box = "hashicorp/precise64"
    db.vm.hostname = "dbserver"
    db.vm.provision :shell, inline: "echo =========== DB ============="
    db.vm.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql"
 end
end
