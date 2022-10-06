var score = 0

fun score(listaTiradas: MutableList<Int>): Int {
    var position = 0
    while (position < listaTiradas.size) {
        if (position == listaTiradas.size - 1) {
            if (listaTiradas.get(position) == 10) { score += 10}
            position++
        } else if (position < listaTiradas.size &&
            listaTiradas.get(position) == 10) {
            frameStrike(position, listaTiradas)
            position++

        } else if (position < listaTiradas.size &&
            (listaTiradas.get(position) + listaTiradas.get(position + 1)) < 10
        ) {
            frameOpen(position, listaTiradas)
            position += 2

        } else if (position < listaTiradas.size &&
            (listaTiradas.get(position) + listaTiradas.get(position + 1)) == 10
        ) {
            frameSpare(position, listaTiradas)
            position += 2
        }
    }
    return score
}

fun frameStrike(position: Int, listaTiradas: MutableList<Int>) {
    if (position+2 < listaTiradas.size) {
        score+= listaTiradas.get(position) + listaTiradas.get(position + 1) + listaTiradas.get(position + 2)
    } else if (position + 1 == listaTiradas.size - 1) {
        score+= listaTiradas.get(position) + listaTiradas.get(position + 1)
    }
}

fun frameSpare(position: Int, listaTiradas: MutableList<Int>) {
    score+= listaTiradas.get(position) + listaTiradas.get(position + 1) + listaTiradas.get(position + 2)
}

fun frameOpen(position: Int, listaTiradas: MutableList<Int>) {
    score+= listaTiradas.get(position) + listaTiradas.get(position + 1)
}

fun main() {
    var frame = 21
    var tirada = 0
    var aux = 0
    var spare = false
    val numeros = mutableListOf<Int>()

    while (frame > 0) {
        tirada = readLine()!!.toInt()
        if (spare == false) {
            if (tirada == 10 && tirada >= 0) { //strike
                numeros.add(tirada)
                frame -= 2
            } else if (tirada < 10 && tirada >= 0) { //spare
                aux = tirada
                numeros.add(tirada)
                spare = true
                frame--
            }
        } else {
            if (tirada + aux == 10 || tirada + aux < 10) { //open
                numeros.add(tirada)
                spare = false
                frame--
            }
        }
    }
    println(score(numeros))
}