# Architetture dei Sistemi Software - Portale Studenti

## Componenti del gruppo

* Andrea Barbadoro
* Marco Cappello
* Giorgio Lauzi
* Marco Sbaffoni

## Dominio di interesse

L'applicazione permette di gestire un piccolo portale degli studenti universitari: visualizzazione ed inserimento di studenti, esami e gestione dei piani di studio.

## Tecnologie usate
* Ubuntu 15.10 (Wily Werewolf)
* Vagrant 1.8.1

### Macchina webserver
* Gradle 2.5
* Apache Tomcat 7.0.64

### Macchina database
* Postgresql 9.4.7

## Utilizzo

Lanciare il comando

```bash
vagrant up
```

Successivamente, visitare il sito:
```
http://localhost:8080/portale
```