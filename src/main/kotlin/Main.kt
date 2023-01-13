val konten: MutableList<Konto> = mutableListOf(
    Konto("Fetullah Andug", 1000000.97),
    Konto("Maxi Ellendorf", 500.00),
    Konto("Alex Maier", 120.59),
    Konto("Martin Engels", 759.45),
)

fun main(){
    kontenAusgeben()
    ueberweisung("Fetullah Andug", "Maxi Ellendorf", 1000000.00)
    kontenAusgeben()

}

fun getKontostand(k: Konto): Double{
    return k.kontostand
}

fun neuesKonto(n: String, k: Double): Boolean{
    var konto = findeKonto(n)

    if(konto != null){
        println("Es existiert bereits ein Konto auf diesen Namen!\n")
        return false
    }

    konten.add(Konto(n, k))
    println("Das Konto wurde erstellt!\n")
    return true
}

fun kontoLoeschen(n: String): Boolean{
    var konto = findeKonto(n)

    if(konto != null){
        konten.remove(konto)
        println("Das Konto wurde gelöscht!\n")
        return true
    }

    println("Es wurde kein Konto gefunden!\n")
    return false
}

fun auszahlen(n: String, betrag: Double): Boolean{
    var konto = findeKonto(n)

    if(konto != null) {
        if (konto.kontostand >= betrag) {
            konto.kontostand -= betrag
            println("Erfolgreiche Abbuchung -")
            println("Neuer Kontostand: ${String.format("%.2f", konto.kontostand)} €\n")
            return true
        }else if(konto.kontostand < betrag){
            println("Kontostand unzureichend!")
            return false
        }
    }

    println("Es gibt ein Problem mit Ihrem Konto!\nWenden Sie sich an Ihren Berater\n")
    return false
}

fun einzahlen(n: String, betrag: Double): Boolean{
    var konto = findeKonto(n)

    if(konto != null) {
        konto.kontostand += betrag
        println("Erfolgreiche Einzahlung -")
        println("Neuer Kontostand: ${String.format("%.2f", konto.kontostand)} €\n")
        return true
    }

    println("Es wird ein neues Konto erstellt ..")
    Thread.sleep(2500)
    neuesKonto(n,betrag)
    return true
}

fun ueberweisung(sender: String, empfaenger: String, betrag: Double): Boolean{
    var sender: Konto? = findeKonto(sender)
    var empfaenger: Konto? = findeKonto(empfaenger)

    if(sender != null && empfaenger != null){
        if(sender.kontostand >= betrag){
            sender.kontostand -= betrag
            empfaenger.kontostand += betrag
            println("Betrag erfolgreich überwiesen!\n")
            return true
        }else{
            println("Dein Kontostand genügt nicht der Überweisung!\n")
            return false
        }
    }

    println("Ein Problem ist aufgetreten!\nBitte versuche es erneut und überprüfe die eingegebenen Daten.\n")
    return true
}

fun findeKonto(inhaber: String): Konto?{
    for(k in konten){
        if(k.inhaber == inhaber) {
           return k
        }
    }
    return null
}

fun kontenAusgeben(){
    for(k in konten){
        println("Inhaber: ${k.inhaber}")
        println("Kontostand: ${String.format("%.2f", k.kontostand)} €\n")
    }
}

