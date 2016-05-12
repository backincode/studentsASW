os_box = "ubuntu/wily64"  #xenial64 does not work with vagrant at this moment [may 2016] 

Vagrant.configure("2") do |config|
  config.vm.define "appserver" do |appserver|
    appserver.vm.box = os_box
    appserver.vm.hostname = "appserver"
    appserver.vm.post_up_message  = "============ APPSERVER ==============="
    
    appserver.vm.network "forwarded_port", guest: 8080, host: 8080
    appserver.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
    appserver.vm.provision :shell, inline: "sudo apt-get update"
    appserver.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-8-jdk-headless gradle tomcat7"

    appserver.vm.provision :shell, inline: "cp -rf /vagrant/Portale\\ dello\\ Studente/ /home/vagrant/"
    appserver.vm.provision :shell, inline: "gradle -p Portale\\ dello\\ Studente/ war"
    appserver.vm.provision :shell, inline: "mv -f Portale\\ dello\\ Studente/build/libs/portale-dello-studente.war /var/lib/tomcat7/webapps/"
    appserver.vm.provision :shell, inline: "rm -r /home/vagrant/Portale\\ dello\\ Studente/"
  end

  config.vm.define "dbserver" do |db|
    db.vm.box = os_box
    db.vm.hostname = "dbserver"
    db.vm.post_up_message = "=========== DBSERVER ============="
    db.vm.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql"
  end
end
