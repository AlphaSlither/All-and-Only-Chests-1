# All & Only Chests

## 📝 Beschreibung
**All & Only Chests** ist ein Minecraft Plugin für PaperMC (Version 1.21.4), das auf der beliebten Challenge basiert, die vom YouTuber und Streamer BastiGHG gespielt wurde. Das Plugin stellt Spieler vor eine spannende Herausforderung:

- 🔨 **Keine Items durch Blockabbau:** Items droppen nicht, wenn Blöcke abgebaut werden, es sei denn, sie wurden zuvor vom Spieler platziert.
- 🎒 **Items nur aus Truhen:** Items können ausschließlich aus Truhen in Strukturen gesammelt werden.
- 🏰 **Strukturen nacheinander looten:** Spieler wählen eine Struktur aus, die sie plündern möchten. Sobald alle Items aus dieser Struktur gefunden wurden, kann die nächste Struktur ausgewählt werden.
- 🏆 **Ziel:** Alle Items aus allen Strukturen zu finden.

---

## ⚙️ Installation
1. 📥 Lade die neueste Version von **All & Only Chests** herunter.
2. 📂 Lege die Plugin-Datei (`.jar`) in den `plugins`-Ordner deines PaperMC-Servers.
3. 🔄 Starte den Server neu.
4. ✅ Stelle sicher, dass die Berechtigungen korrekt konfiguriert sind (siehe unten).

---

## 🛠️ Befehle

### `/structures`
- 🗂️ **Beschreibung:** Öffnet ein GUI, in dem eine Struktur ausgewählt werden kann.
- 🔑 **Permission:** `AllAndOnlyChests.command.structures`

### `/structure finish`
- 🛑 **Beschreibung:** Beendet die aktuell ausgewählte Struktur, z. B. wenn ein Item verbuggt ist.
- 🔑 **Permission:** `AllAndOnlyChests.command.structure.finish`

### `/scoreboard toggle`
- 📊 **Beschreibung:** Aktiviert oder deaktiviert das Scoreboard.
- 🚫 **Permission:** Keine erforderlich.

### `/save`
- 💾 **Beschreibung:** Speichert den aktuellen Fortschritt manuell.
- 🔑 **Permission:** `AllAndOnlyChests.command.save`

---

## 🔄 Automatisches Speichern
- Der Fortschritt wird automatisch gespeichert, wenn:
  - Der letzte Spieler den Server verlässt.
  - Der Server mit dem Befehl `/stop` angehalten wird.

---

## 🖼️ GUI

![Strukturauswahl GUI](pictures/GUI-Structures.png)

![Items GUI](pictures/GUI-Items.png)

---

## 📜 Lizenz
Dieses Werk steht unter der Creative Commons Attribution-NonCommercial 4.0 International Lizenz.
Mehr Informationen unter: https://creativecommons.org/licenses/by-nc/4.0/


---

Viel Spaß beim Spielen der **All & Only Chests**-Challenge!

