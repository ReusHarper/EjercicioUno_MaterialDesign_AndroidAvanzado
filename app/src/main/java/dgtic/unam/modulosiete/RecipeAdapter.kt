package dgtic.unam.modulosiete
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class RecipeAdapter(context: Context,private val list:ArrayList<ModeloStreaming>): BaseAdapter() {
    private lateinit var inflter: LayoutInflater

    init {
        inflter = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 1
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view = inflter.inflate(R.layout.list_item, null)
        //
        val image: ImageView = view.findViewById(R.id.image_pel)
        val file: TextView = view.findViewById(R.id.title_pel)
        file.text=list[p0].namefile
        image.setImageResource(list[p0].nameImage)
        return view
    }
}