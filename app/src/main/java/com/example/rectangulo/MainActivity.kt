package com.example.rectangulo

import android.graphics.Color
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

            val cuadrado: Rectangulo = Rectangulo(ContextCompat.getColor(this, R.color.red),inicialAncho,inicialAlto).apply {
                x = inicialX
                y = inicialY
            }

            val buttonArriba: Button = findViewById(R.id.buttonArriba)
            val buttonAbajo: Button = findViewById(R.id.buttonAbajo)
            val buttonIzquierda: Button = findViewById(R.id.buttonIzquierda)
            val buttonDerecha: Button = findViewById(R.id.buttonDerecha)
            val buttonCambiarTamano: Button = findViewById(R.id.buttonCambiarTamano)
            val buttonCambiarColor: Button = findViewById(R.id.buttonCambiarColor)

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
        }
    }

    private fun generarColorAleatorio():Int{
        val random = Random.Default

        val rojo = Random.nextInt(256)
        val verde = Random.nextInt(256)
        val azul = Random.nextInt(256)

        return Color.rgb(rojo, verde, azul)
    }

    private fun actualizarVista(cuadrado:Rectangulo, cuadradoView:View){

        cuadradoView.layoutParams.width = cuadrado.ancho
        cuadradoView.layoutParams.height = cuadrado.alto

        cuadradoView.setBackgroundColor(cuadrado.color)

        cuadradoView.x = cuadrado.x.toFloat()
        cuadradoView.y = cuadrado.y.toFloat()

        cuadradoView.requestLayout()
    }
}

class Rectangulo(var color:Int, var ancho:Int, var alto:Int) {
    var x:Int=0
    var y:Int=0

    fun MoverArriba(){
        if (y-10 > 0) y-=10
    }

    fun MoverAbajo(){
        if (y+10 < 1000) y+=10
    }

    fun MoverIzquierda(){
        if (x-10 > 0) x-=10
    }

    fun MoverDerecha(){
        if (x+10 < 800) x+=10
    }

    fun CambiarTamano(){
        if(ancho==100){
            ancho=50
            alto=50
        } else
        {
            ancho=100
            alto=100
        }
    }
}