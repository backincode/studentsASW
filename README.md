# Architetture dei Sistemi Software - Portale Studenti

## Componenti del gruppo

* Andrea Barbadoro
* Marco Cappello
* Giorgio Lauzi
* Marco Sbaffoni

## Dominio di interesse

L'applicazione permette di gestire un piccolo portale degli studenti universitari: visualizzazione ed inserimento di studenti, esami e gestione dei piani di studio.

## Tecnologie usate
* Ubuntu 15.10 (Wily Werewolf) con Vagrant
* Ubuntu 14.04 (Trusty Tahr) con Docker
* Vagrant 1.8.1
* Docker 1.10.3

##### Macchina webserver
* Gradle 2.10
* Apache Tomcat 7.0.64 con Vagrant
* Apache Tomcat 8.0.35 con Docker

##### Macchina database
* Postgresql 9.4.7

## Primo progetto - applicazione client-server con Vagrant

Lanciare il comando

```bash
vagrant up
```

Successivamente, visitare il sito:
```
http://localhost:8080/portale
```

## Secondo progetto - applicazione client-server con Docker e gestione entita' con chiamate REST

Lanciare lo script dal terminale:

```bash
./startup.sh
```

Successivamente, si possono gestire le seguenti chiamate REST:

* Lista degli esami

```
http://localhost:8080/portale/api/esami
```

* Dettagli di un esame tramite il suo ID

```
http://localhost:8080/portale/api/esami/{id}
```

* Ricerca di esami dato il nome

```
http://localhost:8080/portale/api/esami/nome/{nome}
```

* Inserimento di un nuovo esame

```
curl --data "nome=Name&descr=Descr&cfu=CFU" http://localhost:8080/portale/api/esami/inserisciEsame

```

* Aggiornamento di un esame tramite il suo ID

```
curl -X PUT --data "nome=NewName&descr=NewDescr&cfu=newCFU" http://localhost:8080/portale/api/esami/aggiornaEsame/{id}

```

* Cancellazione di uno esame tramite il suo ID

```
curl -X DELETE http://localhost:8080/portale/api/esami/cancellaEsame/{id}

```

* Lista degli studenti

```
http://localhost:8080/portale/api/studenti
```

* Ricerca degli studenti dato il nome

```
http://localhost:8080/portale/api/studenti/nome/{nome}
```

* Ricerca degli studenti dato il cognome

```
http://localhost:8080/portale/api/studenti/cognome/{cognome}
```

* Inserimento di un nuovo studente

```
curl --data "nome=Name&cognome=Surname&matricola=Matricola" http://localhost:8080/portale/api/studenti/inserisciStudente

```

* Aggiornamento di uno studente tramite la sua matricola

```
curl -X PUT --data "nome=NewName&cognome=NewSurname" http://localhost:8080/portale/api/studenti/aggiornaStudente/{matricola}

```

* Cancellazione di uno studente tramite la sua matricola

```
curl -X DELETE http://localhost:8080/portale/api/studenti/cancellaStudente/{matricola}

```
