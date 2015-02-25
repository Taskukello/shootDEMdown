# *Rakennekuvaus*



## Luokka-Paaohjelma



Ohjelman Main. Luo luokat logiikka ja kayttoliittyma jonka j‰lkeen aloittaa pelin.



## Luokka-Logiikka


Pelin ydin. hoitaa koko pelin toimintaa ja yll‰pit‰‰ suurimpia osista muita luokkia 
Listaus Logiikka luokan hoitamista luokista:


  - OsumanTarkistaja luokka, yll‰pit‰‰ ja k‰ytt‰‰ kyseist‰ luokkaa tarkistaakseen osumat alukseen joka vuorolla

  - VihollisObjekti luokka, Logiikka muistaa ja hallitsee kerralla mahdollisesti useita vihollisia olioita. tarpeen tullen tuhoten tai luoden uuden

  - Alus luokka, yll‰pit‰‰ ja k‰ytt‰‰ aluksen l‰hett‰m‰‰ dataa hyv‰kseen.

  - Ammus luokka, hallitsee ammuksia joita voi kerralla olla useita. tarpeentullen tuhoaa. Ei vastaa ammusten synnyst‰

  - Objektinarpoja luokka, ei varsinaisesti kontrolloi tai ole riippuvainen olemassaolosta. Logiikka luokka pyyt‰‰ arpojalta tiedot kun haluaa luoda uuden objektin.

  - Kayttoliittyma, kommunikoi k‰yttˆliittym‰n kanssa ja hallitsee suurinta osaa ominaisuuksista mit‰ t‰m‰ piirt‰‰. tarpeentullen l‰hett‰‰ pelinlopetuskomennon

  

## Luokka-OsumanTarkistaja



tarkistaa jokasen vuoron lopussa logiikan k‰skyst‰ onko alukseen osunut vihollisobjekti vai ei. k‰ytt‰‰ luokkia Alus, ja VihollisObjekti



## Luokka-VihollisObjekti



Pelin viholliset. k‰ytˆst‰ vastaa Logiikka luokka. On myˆs tekemisiss‰ OsumanTarkistajan kanssa l‰hett‰en t‰lle dataa



## Luokka-Alus



Pelaajan alus. muistaa ammukset, mutta ei pysty poistamaan niit‰ tai lis‰‰m‰‰n itse. OsumanTarkistaja pyyt‰‰ luokalta dataa tarvittaessa luokka Logiikka hoitaa osittain luokkaa alus, mutta liikkumisen hoitaa luokka itse Nappaimistonkuuntelijan avustuksella.



## Luokka-Ammus

Pelaajan

 ampumat ammukset tiet‰‰ miss‰ alus on ammuttaessa mutta ei muuten pysty hallitsemaan tai k‰ytt‰m‰‰n t‰t‰. Ammuksia voi olla useita kerralla Logiikka vastaa poistamisesta, mutta ei luomisesta. Luomisen hoitaa Luokka n‰pp‰imistˆnkuuntelija. 



## Luokka-Kayttoliittyma



Luokkaa kayttoliittyma tukee monta muuta luokkaa. Kayttoliittyma yll‰pit‰‰ ja vastaa n‰iden toiminnasta. Logiikka luokka l‰hett‰‰ kayttoliittymalle dataa jonka pohjalta kayttoliittyma opastaa muita luokkiaan piirt‰m‰‰n.



## Luokka-Nappaimistonkuuntelija



yksi Kayttoliittyman hoitamista luokista joka kuitenkin toimii osittain erillisen‰ luokkanaan. L‰hett‰‰ painamistiedon alukselle ja liikuttaa t‰t‰ ja luo ammukset
