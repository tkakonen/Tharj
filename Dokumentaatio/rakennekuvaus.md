Ohjelman rakennekuvaus:

T�ll� hetkell� ohjelma ei vastaa t�ysin luokkakaaviota2, mutta on melko l�hell� t�t� rakennetta.

Ohjelma on jaettu nelj��n pakettiin: k�ytt�liittym��n, tiedostonlukijan sis�lt�v��n reader-pakettiin, exceptions-pakettiin sek� sovelluslogiikan sis�lt�v��n pakettiin (luokat Muuttuja, Data, ANOVA yms.). Koska testiluokat jakavat niin paljon metodeja, tarkoitus oli tehd� niille joku rajapinta toteutettavaksi tms, mutta se ei ole t�ss� toteutunut.

Data on t�rke� luokka. Se sis�lt�� t�ll� hetkell� datataulukon muotoa Double[][] sek� listan muuttujien nimist� ja havaintoyksik�ist�. Datasta voi metodinsa avulla luoda muuttujia. Muitakin mahdollisia tietorakenteita t�ss� olisi varmasti ollut, esim HashMap<String, ArrayList<Double>>.

Muuttuja sis�lt�� t�rkeit� metodeja, joilla voi laskea keskiarvoja yms. tilastollisissa testeiss� tarvittavia arvoja.

Otos on t�ll� hetkell� luokka, joka ei ole k�yt�ss�. Main-metodi voisi ehk� olla omassa paketissaan.

K�ytt�liittym� sis�lt�� kolme luokkaa: itse K�ytt�liittym�n, KlikkaustenKuuntelijan sek� ValintaKuuntelijan. K�ytt�liittym� k�ytt�� lukijaa datan lukemiseen tiedostosta.



