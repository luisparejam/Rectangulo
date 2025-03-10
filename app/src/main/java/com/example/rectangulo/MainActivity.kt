package com.example.rectangulo

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val cuadradoView: View = findViewById(R.id.rectanguloX)

        cuadradoView.post {
            val inicialX = cuadradoView.x.toInt()
            val inicialY = cuadradoView.y.toInt()

            val inicialAncho = cuadradoView.width.toInt()
            val inicialAlto = cuadradoView.height.toInt()

            val cuadrado: RectanguloConBordes = RectanguloConBordes(ContextCompat.getColor(this, R.color.red),inicialAncho,inicialAlto).apply {
                dimensiones.x = inicialX
                dimensiones.y = inicialY
                bordeColor=ContextCompat.getColor(this@MainActivity, R.color.black)
            }

            val buttonArriba: Button = findViewById(R.id.buttonArriba)
            val buttonAbajo: Button = findViewById(R.id.buttonAbajo)
            val buttonIzquierda: Button = findViewById(R.id.buttonIzquierda)
            val buttonDerecha: Button = findViewById(R.id.buttonDerecha)
            val buttonCambiarTamano: Button = findViewById(R.id.buttonCambiarTamano)
            val buttonCambiarColor: Button = findViewById(R.id.buttonCambiarColor)

            val buttonCambiarColorBorde: Button = findViewById(R.id.buttonCambiarColorBorde)

            buttonArriba.setOnClickListener {
                cuadrado.MoverArriba()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonAbajo.setOnClickListener {
                cuadrado.MoverAbajo()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonIzquierda.setOnClickListener {
                cuadrado.MoverIzquierda()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonDerecha.setOnClickListener {
                cuadrado.MoverDerecha()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonCambiarTamano.setOnClickListener {
                cuadrado.CambiarTamano()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonCambiarColor.setOnClickListener {
                //cuadrado.color = ContextCompat.getColor(this,R.color.blue)
                cuadrado.color=generarColorAleatorio()
                actualizarVista(cuadrado,cuadradoView)
            }
            buttonCambiarColorBorde.setOnClickListener {
                cuadrado.cambiarColorBorde(RectanguloConBordes.ManejoColor.ObtenerColorAleatorio())
                actualizarVista(cuadrado,cuadradoView)
            }
        }
    }

    private fun generarColorAleatorio():Int{
        val random = Random.Default

        val rojo = Random.nextInt(256)
        val verde = Random.nextInt(256)
        val azul = Random.nextInt(256)

        return Color.rgb(rojo, verde, azul)
    }

    private fun actualizarVista(cuadrado:RectanguloConBordes, cuadradoView:View){
        val drawable=GradientDrawable()

        drawable.setColor(cuadrado.color)
        drawable.setStroke(10, cuadrado.bordeColor)

        cuadradoView.background=drawable

        cuadradoView.layoutParams.width = cuadrado.dimensiones.ancho
        cuadradoView.layoutParams.height = cuadrado.dimensiones.alto

        //cuadradoView.setBackgroundColor(cuadrado.color)

        cuadradoView.x = cuadrado.dimensiones.x.toFloat()
        cuadradoView.y = cuadrado.dimensiones.y.toFloat()

        cuadradoView.requestLayout()
    }
}

data class MiDimension(var x:Int, var y:Int, var ancho:Int, var alto:Int){

}

class RectanguloConBordes(color:Int, alto:Int, ancho:Int, bordeColor:Int=Color.BLACK): Rectangulo(color, ancho, alto) {

    var bordeColor:Int=Color.BLACK

    class ManejoColor{
        companion object {
            val rojo=Color.RED
            val azul=Color.BLUE
            val verde=Color.GREEN
            val negro=Color.BLACK

            fun ObtenerColorAleatorio():Int{
                val colores = listOf(rojo, azul, verde, negro)
                return colores.random()
            }
        }
    }

    fun cambiarColorBorde(nuevoColorBorde:Int){
        bordeColor = nuevoColorBorde
    }
}

open class Rectangulo(open var color:Int, open var ancho:Int, open var alto:Int) {

    /*
    var x:Int = 0
    var y:Int = 0
    */

    var dimensiones = MiDimension(0,0, ancho, alto)

    var flagTamano:Int = 0

    fun MoverArriba(){
        if (dimensiones.y-40 > 40) dimensiones.y-=40
    }

    fun MoverAbajo(){
        if (dimensiones.y+40 < 1100) dimensiones.y+=40
    }

    fun MoverIzquierda(){
        if (dimensiones.x-40 > 0) dimensiones.x-=40
    }

    fun MoverDerecha(){
        if (dimensiones.x+40 < 820) dimensiones.x+=40
    }

    fun CambiarTamano(){
        if(flagTamano==0){
            flagTamano = 1
            dimensiones.ancho /= 2
            dimensiones.alto /=  2
        } else
        {
            flagTamano = 0
            dimensiones.ancho *= 2
            dimensiones.alto *= 2
        }
    }
}