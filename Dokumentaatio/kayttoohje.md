Ohjelman käyttöohjeet:

1. Ohjelmalla ei voi suorittaa analyysejä ennen kuin olet syöttänyt datan.
2. Data syötetään joko lukemalla data tiedostosta tai syöttämällä se käsin syötä data -kohdassa.
3. Jos luet datan tiedostosta, tiedoston täytyy olla muodossa, jossa
	1. kaikki muuttujien nimet ja data-alkiot on erotettu toisistaan puolipisteellä ;
	2. muuttujien nimet ovat tiedoston ensimmäisellä rivillä
	3. data-alkiot ovat riveittäin siten, että yhdellä rivillä yksi jokaisen muuttujan yksi arvo
	4. data-alkiot ovat desimaalilukuja
	5. jokainen muuttuja saa yhtä monta arvoa
4. Esimerkki luettavasta datasta: src/pikkudata.txt. Kirjoita luettavan tiedoston nimi tekstikenttään tässä muodossa.
5. Jos lisäät datan käsin, varmista, että:
	1. nimeät kaikki muuttujat
	2. kaikki muuttujat saavat yhtä monta arvoa
	3. arvot ovat desimaalilukuja tai kokonaislukuja

4. Testien suorittamisesta ja tunnuslukujen laskemisesta:
	1. Tunnuslukujen laskeminen tai testien suorittaminen ei välttämättä onnistu liian pienellä datalla (esim. 1 alkio)
5. Kahden otoksen t-testi vertailee datan kahden eri muuttujan välisiä keskiarvoja.
2. Varianssianalyysin suorittamisesta
	1. Jotta varianssianalyysin suorittaminen onnistuu, toisen muuttujan on oltava niin sanottu RYHMITTELEVÄ MUUTTUJA. 
	   Ryhmittelevä muuttuja kertoo, mihin ryhmään tietty havainto kuuluu. Esimerkki ryhmitellystä datasta:
	
m1	m2
1.3	1
1.5	1
3.4	1
3.6	1
6.0	2
4.3	2
3.4	2
9.6	2

Esimerkissä m2 on ryhmittelevä muuttuja. M1-muuttujan arvoista ensimmäiset neljä kuuluvat ryhmään 1 ja viimeiset neljä ryhmään 2. Varianssianalyysi vertaa näiden ryhmien keskiarvoja. Ryhmiä voi olla useampi kuin kaksi. Ryhmissä on oltava yhtä monta alkiota.


		