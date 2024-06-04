# ShopSystem
Project for the uni

# Einbindern der localen Datenbank.
1. Unter dem verzeichnis resorcen, befindet sich eine application.properties datei.
2. Tausche sie folgenden DatenÖ
     a. spring.datasource.username: durch den user ihrer datenbank. z.B root
     b. spring.datasource.password: durch das für den user vergebene passwort

3.a Falls eine Lokale datenbank verwendet wird (ansonsten 3.b):
    erstellen sie auf ihrem localen server ein neues schema mit dem namen: ShopSystem
    
3.b Falls eine extenrne datenbank verwendet wird:
     a. geben sie den Pfad zu datenbank wie folgt an: jdbc:mysql://[ip-adress zum server]:3306/ShopSystem
     b. erstellen sie ein neues Schema auf ihrem Datenbankserver mit dem name: ShopSystem
4. Starten sie den Backend-Server

5. Sollte bei dem ersten start eine verbindung zur Datenbank aufgebaut werden könne,
   Erstellt Spring Boot JPA automatisch die tabel mit den Schlüsseln.


# Erstellen eines Users
Hinweis: Wenn sich das Backend gezogen wurde ist sind die enpunkte zum vereinfachen des testens nicht gesichert. 
Anleitung zum ändern der endpunkt sicherung finden sie weiter unten.
1. Suchen sie sich den Richtigen Endpunkt (.../auth/register)
   
2. Übergeben sie die gefragten daten. Sollte alles richtig seine, Bekommen sie eine Atwort mit einem Json Web Token.
   (Diesen token sollten sie Kopieren wenn Security aktive.)


# Login eines Users
Hinweis: Wenn sich das Backend gezogen wurde ist sind die enpunkte zum vereinfachen des testens nicht gesichert. 
Anleitung zum ändern der endpunkt sicherung finden sie weiter unten.
1. Suchen sie sich den Richtigen Endpunkt (.../auth/singin)

2. Übergeben sie die gefragten daten. Sollte alles richtig seine, Bekommen sie eine Atwort mit einem Json Web Token.
   (Diesen token sollten sie Kopieren wenn Security aktive.)


# Absicher der endpunkte.
Bei diesem scenario werden 2 Zeilen im Code geändert.

Hinweis: sollten sie mit dem Teilfertigen Frontend ausprobieren, Lassen sie die Security deaktiviert.
1. Suchen sie die Klasse: SecurityConfig

2. in den Zeilen 43 und 44 wie unten zu sehen löschen sie das permitAll() raus und entkomentieren den code snippend dahinter wie unten zu sehen
       ".requestMatchers("/admin/**").hasAnyRole("ADMIN")"
       ".requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")"

viel Spaß mit dem Backend.
