INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'informatica teorica','presentare la teoria dei linguaggi e, parallelamente, la teoria degli automi.',12);

INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'architetture dei sistemi software','processo di definizione dell architettura software.',6);

INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'ricerca operativa 2','fornire conoscenze di base, sia metodologiche che quantitative.',6);

INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'infrastrutture delle reti','fornire competenze avanzate sulle reti di calcolatori con contributi metodologici e tecnici.',9);

INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'basi di dati 2','presentare modelli, metodi e sistemi fondamentali per la tecnologia delle basi di dati insieme ad alcune recenti direzioni di evoluzione della tecnologia stessa.',9);

INSERT INTO esame (id,nome,descrizione,cfu) 
VALUES (NEXTVAL('sequenza_id'),'telecomunicazioni wireless','acquisire conoscenze generali sui sistemi mobili cellulari come parte integrante di reti di comunicazione.',6);


INSERT INTO studente (matricola,nome,cognome) 
VALUES (123456,'giorgio','lauzi');

INSERT INTO studente (matricola,nome,cognome) 
VALUES (654321,'marco','cappello');

INSERT INTO studente (matricola,nome,cognome) 
VALUES (111111,'marco','sbaffoni');

INSERT INTO studente (matricola,nome,cognome) 
VALUES (222222,'andrea','barbadoro');

INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (1,123456);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (2,123456);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (3,123456);

INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (2,654321);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (4,654321);

INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (1,111111);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (2,111111);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (3,111111);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (4,111111);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (5,111111);
INSERT INTO pianodistudi(id_esame,id_studente)
VALUES (6,111111);