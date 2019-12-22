__Yksikkötestaukset__
-----
Ohjelman testaus on toteutettu automaattisilla testeillä. 
Paketin "ui" sisältö on jätetty pois testeistä niiden käyttöliittymän hyödyntämisen vuoksi.
Paketissa "actors" testataan "MarketActor"-luokan toiminta sitä laajentavan "Person"-luokan testien kautta. Testeissä tarkastetaan ostaminen, myyminen rahamäärät, tuotemäärät ja näihin liittyvien toimintojen toiminnat testeillä testPersonMoney(), testPersonBuying(), testPersonSellingAndProdCounts() ja testPersonGeneral.
"Company"-luokasta tarkastetaan palkat, työntekijät ja näihin liittyvät toiminnot testeillä testCompanyMoneyAndWages() ja testCompanyEmployees().
"Objects"-paketissa testataan "Item"-luokan luonti ja sen nimen ja tyypin hakeminen testillä testItem().
"Systems"-paketin "Trader" ja "Simulation" -luokkia testataan niiden päämetodia kutsumalla niitä testeissä testTrader() ja testSimulation().

Ohjelma saavuttaa testeillä 89% testikattavuuden ja 75% haaraumakattavuuden.

![](testikattavuus.png)

------

__Järjestelmätestaus__

---

Järjestelmätestaus on suoritettu manuaalisesti suorittamalla ohjelma ja antamalla sille erilaisia syötteitä. Kielletyissä syötteissä ohjelman oma virheilmoitus tulee näkyviin.
Ohjelmasta on testattu kaikki määrittelydokumentissa määritellyt toiminnot.

