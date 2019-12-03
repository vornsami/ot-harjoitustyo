
Ohjelma rakentuu toistaiseksi seuraavanlaisesti:

__systems__

- Run

- Trader

Systems-kansio hallitsee ohjelman suoritusta ja logiikkaa. Run käynnistää ohjelman ja hallitsee sen suoritusta. Trader käsittelee kaupan logiikkaa.

__people__

- MarketActor

- Person

- Company

People-kansiossa on markkinoilla toimijoihin liittyvät metodit. MarketActor määrittelee markkinoiden toimijoiden toiminnot. 

Person rakenteena kuvaa markkinoilla toimivia ihmisiä. Company-luokka toimii markkinoilla myyjänä. Sillä on alaisuudessaan Person-luokkia, joille maksetaan palkkaa. 

Person ja Company laajentavat MarketActor-luokkaa.

__objects__

- Item

- ItemType

Objects-kansiossa on markkinoilla myytäviin tavaroihin liittyvät tiedot. Item viittaa myytävään tavaraan. ItemType on taavran tyypin enum-luokka.


