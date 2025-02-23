package com.example.rectangulo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val cuadradoView: View = findViewById(R.id.rectanguloX)
        val cuadrado: Rectangulo=Rectangulo(ContextCompat.getColor(this, R.color.blue),100,100)

        val buttonArriba: Button = findViewById(R.id.buttonArriba)
        val buttonAbajo: Button = findViewById(R.id.buttonAbajo)
        val buttonIzquierda: Button = findViewById(R.id.buttonIzquierda)
        val buttonDerecha: Button = findViewById(R.id.buttonDerecha)
        val buttonCambiarTamano: Button = findViewById(R.id.buttonCambiarTamano)
        val buttonCambiarColor: Button = findViewById(R.id.buttonCambiarColor)

        buttonArriba.setOnClickListener {
            cuadrado.MoverArriba()
        }
        buttonAbajo.setOnClickListener {
            cuadrado.MoverAbajo()
        }
        buttonIzquierda.setOnClickListener {
            cuadrado.MoverIzquierda()
        }
        buttonDerecha.setOnClickListener {
            cuadrado.MoverDerecha()
        }
        buttonCambiarTamano.setOnClickListener {
            cuadrado.CambiarTamano()
        }
        buttonCambiarColor.setOnClickListener {
            cuadrado.color = ContextCompat.getColor(R.color.blue)
        }

    }
}

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