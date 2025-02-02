package model

class Rectangulo(var color:Int, var ancho:Int, var alto:Int) {
    var x:Int=0
    var y:Int=0

    fun MoverArriba(){
        if (y!=0) y-=10
    }

    fun MoverAbajo(){
        if (y!=100) y+=10
    }

    fun MoverIzquierda(){
        if (x!=0) x-=10
    }

    fun MoverDerecha(){
        if (x!=100) x+=10
    }

    fun CambiarTamano(){
        if(ancho==10){
            ancho=5
            alto=5
        } else
        {
            ancho=10
            alto=10
        }
    }
}