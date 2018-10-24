# PizzaQueen
* [Bevezetés](README.md#bevezetsbevezetes)
* [Rendelők felülete](README.md#rendelk-felleterendelok)
* [Dolgozók felülete](README.md#dolgozk-felletedolgozok)
* [Nem funkcionális követelmények](README.md#nem-funkcionlis-kvetelmnyeknemfunc)
* [Szerepkörök](README.md#szerepkrkszerep)
* [Adatbázis terv](README.md#adatbzis-tervadatbazis)
* [Use case](README.md#use-caseusecase)
* [Végpontok](README.md#vgpontokvegpont)

## [Bevezetés](#bevezetes)

A projekt célja egy étterem online rendelés rendszerének megvalósítása.

## [Rendelők felülete](#rendelok)

A megrendeléseket a vásárlók a webes felületen keresztül adhatják le.
A weblap főoldalán megjelennek a kategóriák,
valamint a 10 legnépszerűbb étel/ital.
A kategóriát kiválasztva listázódnak a tételek (név és ár kíséretében),
amelyek szűrhetőek név(részlet)re. Ételek esetén leírás is van. Külön meg
vannak jelölve a csípős, illetve vegetáriánus ételek.
Ételek és italok tetszőleges számban helyezhetőek a kosárba egy adott
felső korlátig, afelett több terméket nem lehet a kosárba
helyezni.
A kosár tartalma bármikor megtekinthető, ekkor látszódnak a felvett
tételek, illetve látható az összár. Bármely tétel kivehető a kosárból.
A rendelést törölhetjük, illetve leadhatjuk. Utóbbi esetben meg kell
adnunk a nevünket, címünket, illetve telefonszámunkat, majd elküldhetjük
a rendelést.

## [Dolgozók felülete](#dolgozok)

A grafikus felületet az alkalmazottak használják a rendelések, illetve a weblap
tartalmának adminisztrálására.
Az alkalmazott bejelentkezhet (felhasználónév és jelszó megadásával) a
programba, illetve kijelentkezhet.
Bejelentkezve listázódnak a leadott, illetve teljesített rendeléseket (leadás
időpontja, teljesítés időpontja, név, cím, telefonszám, összeg), egy
rendelést kiválasztva pedig listázódnak a tételeket. A leadott rendelés
teljesítettnek jelölhető, ekkor a rendszer rögzíti a teljesítés időpontját is. A
lista szűrhető csak teljesített, illetve csak leadott rendelésekre, továbbá a
rendelő nevére, illetve cím(részlet)re.
Lehetőség van új étel, illetve ital hozzáadására (név, ár, illetve étel esetén
leírás, csípős/vegetáriánus tulajdonságok megadásával). Az egyértelműség
miatt nem engedélyezett több ugyanolyan nevű étel/ital felvitele.

## [Nem funkcionális követelmények](#nemfunc)

* Clean code
* Felhasználóbarát környezet

## [Szerepkörök](#szerep)
Később...

## [Adatbázis terv](#adatbazis)

![Database](/db.png)

## [Use case](#usecase)

![Use case](/usecase.jpg)

## [Végpontok](#vegpont)

#### Guest
| Metódus | Request | Leírás |
|---|---|---|
|GET|/menu|összes étel és ital|
|GET|/menu/food|összes étel|
|GET|/menu/food/{id}|adott ID-vel rendelkező étel|
|GET|/menu/drink|összes ital|
|GET|/menu/drink/{id}|adott ID-vel rendelkező ital|
|POST|/users|regisztrál az oldalra a megadott felhasználói adatokkal|

#### User
| Metódus | Request | Leírás |
|---|---|---|
|GET|/menu|összes étel és ital|
|GET|/menu/food|összes étel|
|GET|/menu/food/{id}|adott ID-vel rendelkező étel|
|GET|/menu/drink|összes ital|
|GET|/menu/drink/{id}|adott ID-vel rendelkező ital|
|GET|/my/orders|a belépett felhasználó összes rendelése
|GET|/my/orders/{id}|a belépett felhasználó adott ID-vel rendelkező rendelése
|POST|/my/orders|a belépett felhasználónak létrehoz egy új rendelést
|DELETE|/my/orders/{id}|a belépett felhasználó adott ID-vel rendelkező rendelését törli
|PATCH|/my/data|a belépett felhasználó adatainak módosítása

#### Admin
TODO...