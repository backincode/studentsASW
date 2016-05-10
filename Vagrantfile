Vagrant.configure("2") do |config|
  config.vm.define "client" do |client|
  client.vm.box = "hashicorp/precise64"
  client.vm.provision :shell, inline: "echo ============ CLIENT ==============="
  client.vm.network "forwarded_port", guest: 8080, host: 8080
  client.vm.provision :shell, inline: "sudo apt-get update"
  client.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-7-jdk"
  client.vm.provision :shell, inline: "sudo apt-get -yf install gradle"
  client.vm.provision :shell, inline: "wget http://it.apache.contactlab.it/tomcat/tomcat-7/v7.0.69/bin/apache-tomcat-7.0.69.tar.gz"
  client.vm.provision :shell, inline: "tar -zxvf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  client.vm.provision :shell, inline: "rm -rf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
  client.vm.provision :shell, inline: "wget https://github.com/backincode/studentsASW/blob/sbaffoni/portale.war -P /home/vagrant/apache-tomcat-7.0.69/webapps/"
  #client.vm.provision :shell, inline: "/home/vagrant/apache-tomcat-7.0.69/bin/startup.sh start"
#https://github.com/mitchellh/vagrant/issues/5973


  
 end

  #config.vm.define "db" do |db|
    #db.vm.box = "hashicorp/precise64"
    #db.vm.provision :shell, inline: "echo =========== DB ============="
    #db.vm.provision :shell, inline: "sudo apt-get update"
    #db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql"
 #end
end
