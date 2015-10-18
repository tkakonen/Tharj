Ohjelman rakennekuvaus:

Tällä hetkellä ohjelma ei vastaa täysin luokkakaaviota2, mutta on melko lähellä tätä rakennetta.

Ohjelma on jaettu neljään pakettiin: käyttöliittymään, tiedostonlukijan sisältävään reader-pakettiin, exceptions-pakettiin sekä sovelluslogiikan sisältävään pakettiin (luokat Muuttuja, Data, ANOVA yms.). Koska testiluokat jakavat niin paljon metodeja, tarkoitus oli tehdä niille joku rajapinta toteutettavaksi tms, mutta se ei ole tässä toteutunut.

Data on tärkeä luokka. Se sisältää tällä hetkellä datataulukon muotoa Double[][] sekä listan muuttujien nimistä ja havaintoyksiköistä. Datasta voi metodinsa avulla luoda muuttujia. Muitakin mahdollisia tietorakenteita tässä olisi varmasti ollut, esim HashMap<String, ArrayList<Double>>.

Muuttuja sisältää tärkeitä metodeja, joilla voi laskea keskiarvoja yms. tilastollisissa testeissä tarvittavia arvoja.

Otos on tällä hetkellä luokka, joka ei ole käytössä. Main-metodi voisi ehkä olla omassa paketissaan.

Käyttöliittymä sisältää kolme luokkaa: itse Käyttöliittymän, KlikkaustenKuuntelijan sekä ValintaKuuntelijan. Käyttöliittymä käyttää lukijaa datan lukemiseen tiedostosta.



