# launch it for the first time with the following command:
# WINDOWS
# vagrant plugin install vagrant-trigger & vagrant up
# *nix
# vagrant plugin install vagrant-trigger ; vagrant up


Vagrant.configure("2") do |config|
  config.vm.define "db" do |db|
    db.vm.box = "hashicorp/precise64"
    db.vm.provision :shell, inline: "echo =========== DB ============="
    db.vm.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql-9.1"
    #setting postgresql
    db.vm.provision :shell, path: "setup_db.sh"

 end

 config.vm.define "client" do |client|
   client.vm.box = "hashicorp/precise64"
   client.vm.provision :shell, inline: "echo ============ CLIENT ==============="
   client.vm.network "forwarded_port", guest: 8080, host: 8080
   client.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
   client.vm.provision :shell, inline: "sudo apt-get update"
   client.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-7-jdk"
   client.vm.provision :shell, inline: "sudo apt-get -yf install gradle"
   client.vm.provision :shell, inline: "sudo dpkg-reconfigure -f noninteractive tzdata"
   client.vm.provision :shell, inline: "wget http://it.apache.contactlab.it/tomcat/tomcat-7/v7.0.69/bin/apache-tomcat-7.0.69.tar.gz"
   client.vm.provision :shell, inline: "tar -zxvf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
   client.vm.provision :shell, inline: "rm -rf /home/vagrant/apache-tomcat-7.0.69.tar.gz"
   client.vm.provision :shell, inline: "sudo cp /vagrant/tomcat-users.xml /home/vagrant/apache-tomcat-7.0.69/conf/"
   client.vm.provision :shell, inline: "sudo cp /vagrant/catalina.sh /home/vagrant/apache-tomcat-7.0.69/bin/"
   client.vm.provision :shell, inline: "sudo cp /vagrant/project.war /home/vagrant/apache-tomcat-7.0.69/webapps/"  
   client.vm.provision :shell, inline: "sudo /home/vagrant/apache-tomcat-7.0.69/bin/startup.sh"
 end
 config.trigger.after :up, :vm => ["client"] do
  link = "http://localhost:8080/project"
   if RbConfig::CONFIG['host_os'] =~ /mswin|mingw|cygwin/
     system "start #{link}"
   elsif RbConfig::CONFIG['host_os'] =~ /darwin/
     system "open #{link}"
   elsif RbConfig::CONFIG['host_os'] =~ /linux|bsd/
     system "xdg-open #{link}"
  end
 end
end