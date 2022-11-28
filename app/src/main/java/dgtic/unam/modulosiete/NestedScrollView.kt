package dgtic.unam.modulosiete

import MaterialAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NestedScrollView : AppCompatActivity() {
    private lateinit var lenguajes:Array<String?>
    private lateinit var colores:IntArray
    private lateinit var recyclerView: RecyclerView
    private var adapter:MaterialAdapter?=null
    private var listaTarjetas=ArrayList<Card>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll_view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setTitle("Nested Scroll View")

        lenguajes=resources.getStringArray(R.array.lenguajes)
        colores=resources.getIntArray(R.array.inicio_colores)
        iniciarTarjetas()
        if(adapter==null) run {
            adapter = MaterialAdapter(this, listaTarjetas)
        }
        recyclerView=findViewById(R.id.recycler_view)
        recyclerView.adapter=adapter
        recyclerView.layoutManager= LinearLayoutManager(this)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    private fun iniciarTarjetas(){
        for (i in lenguajes.indices){
            val card=Card()
            card.id=i.toLong()
            card.nombre=lenguajes[i]
            card.color_recurso=colores[i]
            listaTarjetas.add(card)
        }
    }
}