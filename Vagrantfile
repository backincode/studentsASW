# launch it for the first time with the following command:
# WINDOWS
# vagrant plugin install vagrant-trigger & vagrant up
# *nix
# vagrant plugin install vagrant-trigger ; vagrant up

os_box = "ubuntu/wily64"  #xenial64 does not work with vagrant at this moment [may 2016] 

Vagrant.configure("2") do |config|
  config.vm.define "db" do |db|
    db.vm.box = os_box
    db.vm.hostname = "db"
    db.vm.post_up_message  = "============ DB ==============="
    db.vm.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql-9.4"
    #setup postgresql
    db.vm.provision :shell, path: "./database/setup_db.sh"

 end

  config.vm.define "appserver" do |appserver|
    appserver.vm.box = os_box
    appserver.vm.hostname = "appserver"
    appserver.vm.post_up_message  = "============ APPSERVER ==============="
    
    appserver.vm.network "forwarded_port", guest: 8080, host: 8080
    appserver.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
    appserver.vm.provision :shell, inline: "sudo apt-get update"
    appserver.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-8-jdk-headless gradle tomcat7"

    appserver.vm.provision :shell, inline: "cp -rf /vagrant/portale/ /home/vagrant/"
    appserver.vm.provision :shell, inline: "gradle -p portale/ war"
    appserver.vm.provision :shell, inline: "mv -f portale/build/libs/portale.war /var/lib/tomcat7/webapps/"
    appserver.vm.provision :shell, inline: "rm -r /home/vagrant/portale/"
 end
 config.trigger.after :up, :vm => ["appserver"] do
  link = "http://localhost:8080/portale"
   if RbConfig::CONFIG['host_os'] =~ /mswin|mingw|cygwin/
     system "start #{link}"
   elsif RbConfig::CONFIG['host_os'] =~ /darwin/
     system "open #{link}"
   elsif RbConfig::CONFIG['host_os'] =~ /linux|bsd/
     system "xdg-open #{link}"
  end
 end
end