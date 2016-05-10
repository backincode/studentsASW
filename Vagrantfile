Vagrant.configure("2") do |config|
  config.vm.define "client" do |client|
  client.vm.box = "hashicorp/precise64"
  client.vm.provision :shell, inline: "echo ============ CLIENT ==============="
  client.vm.network "forwarded_port", guest: 8080, host: 8080
  client.vm.network "private_network", ip: "10.11.1.101", virtualbox__intnet: true
  client.vm.provision :shell, inline: "sudo apt-get update"
  client.vm.provision :shell, inline: "sudo apt-get -yf install openjdk-7-jdk"
  client.vm.provision :shell, inline: "sudo apt-get -yf install gradle"
  client.vm.provision :shell, inline: "sudo apt-get -yf install libtomcat7-java"
  client.vm.provision :shell, inline: "sudo apt-get -yf install tomcat7"
  client.vm.provision :shell, inline: "sudo service tomcat7 start"
  end

  config.vm.define "db" do |db|
    db.vm.box = "hashicorp/precise64"
    db.vm.provision :shell, inline: "echo =========== DB ============="
    db.network "private_network", ip: "10.11.1.201", virtualbox__intnet: true
    db.vm.provision :shell, inline: "sudo apt-get update"
    db.vm.provision :shell, inline: "sudo apt-get -yf install postgresql"
  end
end
