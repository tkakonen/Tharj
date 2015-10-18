Ohjelman k�ytt�ohjeet:

1. Ohjelmalla ei voi suorittaa analyysej� ennen kuin olet sy�tt�nyt datan.
2. Data sy�tet��n joko lukemalla data tiedostosta tai sy�tt�m�ll� se k�sin sy�t� data -kohdassa.
3. Jos luet datan tiedostosta, tiedoston t�ytyy olla muodossa, jossa
	1. kaikki muuttujien nimet ja data-alkiot on erotettu toisistaan puolipisteell� ;
	2. muuttujien nimet ovat tiedoston ensimm�isell� rivill�
	3. data-alkiot ovat riveitt�in siten, ett� yhdell� rivill� yksi jokaisen muuttujan yksi arvo
	4. data-alkiot ovat desimaalilukuja
	5. jokainen muuttuja saa yht� monta arvoa
4. Esimerkki luettavasta datasta: src/pikkudata.txt. Kirjoita luettavan tiedoston nimi tekstikentt��n t�ss� muodossa.
5. Jos lis��t datan k�sin, varmista, ett�:
	1. nime�t kaikki muuttujat
	2. kaikki muuttujat saavat yht� monta arvoa
	3. arvot ovat desimaalilukuja tai kokonaislukuja

4. Testien suorittamisesta ja tunnuslukujen laskemisesta:
	1. Tunnuslukujen laskeminen tai testien suorittaminen ei v�ltt�m�tt� onnistu liian pienell� datalla (esim. 1 alkio)
5. Kahden otoksen t-testi vertailee datan kahden eri muuttujan v�lisi� keskiarvoja.
2. Varianssianalyysin suorittamisesta
	1. Jotta varianssianalyysin suorittaminen onnistuu, toisen muuttujan on oltava niin sanottu RYHMITTELEV� MUUTTUJA. 
	   Ryhmittelev� muuttuja kertoo, mihin ryhm��n tietty havainto kuuluu. Esimerkki ryhmitellyst� datasta:
	
m1	m2
1.3	1
1.5	1
3.4	1
3.6	1
6.0	2
4.3	2
3.4	2
9.6	2

Esimerkiss� m2 on ryhmittelev� muuttuja. M1-muuttujan arvoista ensimm�iset nelj� kuuluvat ryhm��n 1 ja viimeiset nelj� ryhm��n 2. Varianssianalyysi vertaa n�iden ryhmien keskiarvoja. Ryhmi� voi olla useampi kuin kaksi. Ryhmiss� on oltava yht� monta alkiota.


		