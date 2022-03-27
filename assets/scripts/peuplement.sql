/**
 * Author:  aliceb
 * Created: 27 mars 2022
 */

ALTER TABLE Bornette DISABLE CONSTRAINT FKaarjkb6vauam3pv9l9lec12ra;
ALTER TABLE Bornette DISABLE CONSTRAINT FKNVGGS4FMG75DG94ST1V8AFAFJ;
ALTER TABLE Velo DISABLE CONSTRAINT FKaukp4xw5ljdrhq3k42mapnstu;
ALTER TABLE Velo_Location DISABLE CONSTRAINT FK25IXBCO2AOTQIOGR70SWB4IBM;
ALTER TABLE HistoriqueVType DISABLE CONSTRAINT FKp75bc83wv7f3ibqo8ubtlxtpi;
ALTER TABLE Trajet DISABLE CONSTRAINT FKrkttw2833q9yj5g0hdgshsd5r;
ALTER TABLE Trajet DISABLE CONSTRAINT FK8cy7a8f10qw3bkrgv25ymilc3;
ALTER TABLE Trajet DISABLE CONSTRAINT FKcxad9wmuqlgxc3igk2bvyfila;


TRUNCATE TABLE Velo ;
TRUNCATE TABLE Bornette ;
TRUNCATE TABLE HistoriqueVType ;
TRUNCATE TABLE Station ;

ALTER TABLE Bornette ENABLE CONSTRAINT FKaarjkb6vauam3pv9l9lec12ra;
ALTER TABLE Bornette ENABLE CONSTRAINT FKNVGGS4FMG75DG94ST1V8AFAFJ;
ALTER TABLE Velo ENABLE CONSTRAINT FKaukp4xw5ljdrhq3k42mapnstu;
ALTER TABLE Velo_Location ENABLE CONSTRAINT FK25IXBCO2AOTQIOGR70SWB4IBM;
ALTER TABLE HistoriqueVType ENABLE CONSTRAINT FKp75bc83wv7f3ibqo8ubtlxtpi;
ALTER TABLE Trajet ENABLE CONSTRAINT FKrkttw2833q9yj5g0hdgshsd5r;
ALTER TABLE Trajet ENABLE CONSTRAINT FK8cy7a8f10qw3bkrgv25ymilc3;
ALTER TABLE Trajet ENABLE CONSTRAINT FKcxad9wmuqlgxc3igk2bvyfila;

INSERT INTO Station (id, adresse) VALUES (1, '1 rue Un');
INSERT INTO Station (id, adresse) VALUES (2, '2 avenue GrandDeux');
INSERT INTO Station (id, adresse) VALUES (3, '3 rue de Troyes');

INSERT INTO HistoriqueVType (id, station_id, vType, dateHeureDebut) VALUES (1, 1, 'VPLUS', '27-MAR-22 12.00');
INSERT INTO HistoriqueVType (id, station_id, vType, dateHeureDebut) VALUES (2, 2, 'VMOINS', '26-MAR-22 12.00');
INSERT INTO HistoriqueVType (id, station_id, vType, dateHeureDebut) VALUES (3, 3, 'VNUL', '25-MAR-22 12.00');

INSERT INTO Bornette (numero, station_id, etat) VALUES (1, 1, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (2, 1, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (3, 1, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (4, 1, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (5, 2, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (6, 2, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (7, 2, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (8, 2, 'HS');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (9, 3, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (10, 3, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (11, 3, 'OK');
INSERT INTO Bornette (numero, station_id, etat) VALUES  (12, 3, 'OK');

INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (1, 1, 'OK', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (2, 2, 'OK', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (3, 3, 'OK', CURRENT_DATE, 'VTT');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (4, 5, 'OK', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (5, 6, 'OK', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (6, 7, 'OK', CURRENT_DATE, 'VTT');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (7, 9, 'HS', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (8, 10, 'OK', CURRENT_DATE, 'ROUTE');
INSERT INTO Velo (numero, bornette_numero, etat, misEnService, modele) VALUES (9, 11, 'OK', CURRENT_DATE, 'VTT');

