package com.example.TSDMHPFYAFA

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TemarioFragment : Fragment(), TemarioAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var temarioAdapter: TemarioAdapter
    private lateinit var listaTemas: List<Tema>
    private lateinit var btnIrExamen: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_temario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listaTemas = generarDatosTemario()

        recyclerView = view.findViewById(R.id.recycler_view_temario)
        recyclerView.layoutManager = LinearLayoutManager(context)

        temarioAdapter = TemarioAdapter(listaTemas, this)
        recyclerView.adapter = temarioAdapter

        btnIrExamen = view.findViewById(R.id.btn_ir_examen)
        btnIrExamen.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExamenFragment())
                .addToBackStack(TemarioFragment::class.java.name)
                .commit()
        }
    }

    private fun generarDatosTemario(): List<Tema> {
        return listOf(
            Tema(
                "3.1",
                "📐 Layouts en Android",
                "Los layouts organizan visualmente la pantalla en una app Android. Son estructuras que permiten acomodar botones, textos, imágenes y demás componentes dentro de una Activity.\n\n🧩 Piensa en un layout como el “esqueleto” de la interfaz, definiendo cómo se ve y se comporta cada elemento.\n\n🌟 Gracias a ellos podemos crear pantallas intuitivas, responsivas y estéticamente atractivas.",
                true
            ),
            Tema(
                "3.2",
                "🧠 Subtema 1: ¿Qué es un Layout?",
                "🔹 Un Layout define cómo se posicionan elementos en la pantalla.\n📄 Android los implementa en archivos XML donde los layouts actúan como contenedores: organizan botones, imágenes, textos, etc.\n\n🎯 Claves:\n• Controlan el diseño visual\n• Adaptan la interfaz a múltiples pantallas\n• Mejoran la experiencia de usuario\n\n🪄 Tip: Una misma Activity puede tener distintos layouts según orientación, idioma o dispositivo.",
                false
            ),
            Tema(
                "3.3",
                "📊 Subtema 2: Tipos de Layouts más comunes",
                "🔸 LinearLayout → Elementos alineados en vertical u horizontal\n🔸 RelativeLayout → Elementos posicionados según otros\n🔸 ConstraintLayout → Layout avanzado con restricciones precisas\n🔸 FrameLayout → Apila elementos uno encima de otro\n🔸 GridLayout → Organiza en filas y columnas como una tabla\n\n🛠️ Cada uno se adapta a distintos tipos de diseño.",
                false
            ),
            Tema(
                "3.4",
                "🧱 Subtema 3: Estructura básica de un Layout",
                "📐 Un layout XML tiene un contenedor principal (LinearLayout, etc.)\n\n🔹 Dentro se colocan:\n• 📝 TextView\n• 📷 ImageView\n• 🎯 Button\n\n📏 Se configuran atributos como:\n• `layout_width`, `layout_height`\n• `gravity`, `layout_margin`, `padding`\n\n🎨 Ejemplo conceptual: título + imagen + botón → todos en línea vertical.",
                false
            ),
            Tema(
                "3.5",
                "🧩 Subtema 4: Layouts anidados",
                "🔀 Un layout anidado contiene otro layout dentro de sí.\n\nEjemplo: Un LinearLayout vertical con un LinearLayout horizontal de botones.\n\n📐 Esto permite interfaces más complejas y organizadas.\n\n⚠️ Precaución: muchos layouts anidados pueden disminuir el rendimiento.",
                false
            ),
            Tema(
                "3.6",
                "💡 Subtema 5: Buenas prácticas al usar Layouts",
                "🛠️ Recomendaciones:\n• Usa ConstraintLayout para diseños complejos: es más eficiente\n• Evita demasiada anidación: mejora el rendimiento\n• Usa el diseñador visual de Android Studio para validar el diseño 🎨\n• Prefiere medidas proporcionales (`match_parent`, `wrap_content`)\n• Mantén el XML ordenado y comentado 🧼\n\n📈 Un diseño bien estructurado mejora la usabilidad y mantenimiento de tu app.",
                false
            ),
            Tema(
                "3.7",
                "🔄 Subtema 6: Layouts según orientación",
                "📱 Las apps pueden cambiar entre orientación vertical (portrait) y horizontal (landscape).\n\n🧭 Puedes crear diferentes layouts para cada orientación usando carpetas como:\n• res/layout → para portrait\n• res/layout-land → para landscape\n\n👀 Android selecciona automáticamente el layout más adecuado según cómo se sostiene el dispositivo.\n\n⚙️ Esto mejora la experiencia del usuario adaptándose a su modo de uso.",
                false
            ),
            Tema(
                "3.8",
                "📦 Subtema 7: LayoutInflater: ¿cómo se carga un layout?",
                "🔍 El `LayoutInflater` es una clase en Android que permite convertir archivos XML en vistas reales que aparecen en pantalla.\n\n📤 Se utiliza comúnmente cuando necesitas inflar (cargar) un layout manualmente, por ejemplo, dentro de un RecyclerView o un Fragment.\n\n🛠️ Ejemplo típico:\n`val vista = LayoutInflater.from(context).inflate(R.layout.mi_layout, container, false)`\n\n👉 Útil para manejar layouts dinámicos y personalizados en tu app.",
                false
            )
        )

    }

    override fun onItemClick(tema: Tema) {
        // Navegación a DetalleTemaFragment para los temas
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetalleTemaFragment.newInstance(tema)) // <-- MODIFICADO AQUÍ
            .addToBackStack(null) // Esto asegura que al presionar "Atrás" desde un tema, vuelves al temario
            .commit()
    }
}