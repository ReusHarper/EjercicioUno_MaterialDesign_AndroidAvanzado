package dgtic.unam.modulosiete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class CollapsingToolbarLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collapsing_toolbar_layout)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("Collapsing Toolbar Layout")

        val boton: FloatingActionButton = findViewById(R.id.floating_compra)
        boton.setOnClickListener{
            Toast.makeText(
                this,
                "Evento",
                Toast.LENGTH_SHORT
            ).show()
        }

        val formato = SimpleDateFormat("d MMM yyyy", Locale("mx", "es"))
        val fecha = formato.format(Date())
        val textoFecha: TextView = findViewById(R.id.fecha)
        textoFecha.text = fecha
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}