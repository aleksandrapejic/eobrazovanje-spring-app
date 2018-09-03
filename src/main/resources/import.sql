insert into user (jmbg, ime, prezime, password, role, username, adresa) values ("0306996810013","Sabi","Franjo","$2a$04$X7vevLhbNGjVFp3bO7EjDuSq3.Q9v.PeuUagIFxkw/RKOzXmNsOxi","ADMINISTRATOR","sabi", "Kraljevica Marka 6");
insert into user (jmbg, ime, prezime, password, role, username, adresa) values ("1009996810013","Aleksandra","Pejic","zuta","NASTAVNIK","zuta", "Djordja Jovanovica 11");
insert into user (jmbg, ime, prezime, password, role, username, adresa) values ("0211996810013","Nikola","Trtic","trle","STUDENT","trle", "Zrtava Fasizma 30");

insert into admin (id) values (1);
insert into nastavnik (id) values (2);
insert into student (id, broj_indexa, stanje, tekuci_racun) values (3,"SF1-2018",0,"12345");

insert into predmet (espb, naziv, oznaka) values (4, "Upravljanje projektima", "SH1T");
insert into predmet (espb, naziv, oznaka) values (4, "Metodologije i sistemi za upravljanje IT resursima", "SH2T");
insert into predmet (espb, naziv, oznaka) values (4, "Tehnologije i sistemi eUprave", "SH3T");

insert into ispit (datum, rok_za_prijavu, ukupno_bodova, usmeni_ukupno_bodova, nastavnik_id, predmet_id) values ("2018-07-15","2018-07-13", 100, 100, 2, 1);