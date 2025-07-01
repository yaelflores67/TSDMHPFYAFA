package com.example.TSDMHPFYAFA

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class ExamenFragment : Fragment() {

    private lateinit var tvNumeroPregunta: TextView
    private lateinit var tvPregunta: TextView
    private lateinit var layoutOpciones: LinearLayout
    private lateinit var btnSiguienteOFinalizar: Button

    private lateinit var listaPreguntas: List<Pregunta>
    private var indicePreguntaActual: Int = 0
    private var respuestasCorrectas: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_examen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNumeroPregunta = view.findViewById(R.id.text_view_numero_pregunta)
        tvPregunta = view.findViewById(R.id.text_view_pregunta)
        layoutOpciones = view.findViewById(R.id.layout_opciones)
        btnSiguienteOFinalizar = view.findViewById(R.id.btn_siguiente_o_finalizar)

        listaPreguntas = generarPreguntasExamen()
        cargarPregunta()

        btnSiguienteOFinalizar.setOnClickListener {
            verificarRespuesta()
        }
    }

    private fun generarPreguntasExamen(): List<Pregunta> {
        return listOf(
            // Preguntas sobre IDE y Android Studio
            Pregunta(
                textoPregunta = "🧠 ¿Qué es un IDE en el desarrollo de software?",
                opciones = listOf(
                    "📱 Un sistema operativo móvil",
                    "📲 Un dispositivo virtual de prueba",
                    "💻 Un entorno que reúne herramientas para programar",
                    "🧾 Un tipo de lenguaje de programación"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "🔧 ¿Cuáles son funciones principales del IDE Android Studio?",
                opciones = listOf(
                    "🎨 Diseñar pantallas con modo visual",
                    "🍏 Crear apps para iOS",
                    "📳 Simular dispositivos Android",
                    "✍️ Escribir código en Java o Kotlin",
                    "▶️ Ejecutar apps en emuladores"
                ),
                respuestaCorrectaIndex = -1,
                indicesRespuestasMultiples = listOf(0, 2, 3, 4),
                esMultiple = true
            ),
            Pregunta(
                textoPregunta = "🏆 ¿Cuál es el IDE oficial para desarrollar aplicaciones Android?",
                opciones = listOf(
                    "💡 Visual Studio Code",
                    "🌀 Eclipse",
                    "📐 Android Studio",
                    "🔷 NetBeans"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "🎨 ¿Qué puedes hacer con el modo visual de Android Studio?",
                opciones = listOf(
                    "💬 Ejecutar código en C++",
                    "🖌️ Diseñar pantallas con arrastrar y soltar",
                    "📚 Crear bases de datos",
                    "🌐 Usar JavaScript en interfaces"
                ),
                respuestaCorrectaIndex = 1
            ),
            Pregunta(
                textoPregunta = "🔍 ¿Cuál de las siguientes NO es una parte del entorno de Android Studio?",
                opciones = listOf(
                    "🛠️ Barra de herramientas",
                    "🖥️ Consola",
                    "📐 Diseñador visual",
                    "📷 Cámara frontal del teléfono"
                ),
                respuestaCorrectaIndex = 3
            ),
            Pregunta(
                textoPregunta = "🚀 ¿Qué ventaja ofrece Android Studio a los desarrolladores?",
                opciones = listOf(
                    "🚫 Oculta todos los errores automáticamente",
                    "📡 Funciona sin necesidad de internet",
                    "👀 Permite ver y probar los cambios en tiempo real",
                    "🤖 Solo sirve para programadores expertos"
                ),
                respuestaCorrectaIndex = 2
            ),

            // Preguntas sobre Layouts
            Pregunta(
                textoPregunta = "📐 ¿Qué es un Layout en Android?",
                opciones = listOf(
                    "🖼️ Un archivo que guarda imágenes de la app",
                    "📱 El sistema operativo del dispositivo",
                    "📊 Una estructura que organiza visualmente los elementos en pantalla",
                    "🗄️ Una base de datos interna"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "📚 ¿Qué tipo de layout organiza los elementos en filas y columnas, como una tabla?",
                opciones = listOf(
                    "🔁 RelativeLayout",
                    "🔢 GridLayout",
                    "📦 FrameLayout",
                    "📏 LinearLayout"
                ),
                respuestaCorrectaIndex = 1
            ),
            Pregunta(
                textoPregunta = "🧱 ¿Cuál de los siguientes layouts permite colocar elementos uno encima del otro?",
                opciones = listOf(
                    "🧮 GridLayout",
                    "📐 ConstraintLayout",
                    "📦 FrameLayout",
                    "📏 LinearLayout horizontal"
                ),
                respuestaCorrectaIndex = 2
            ),
            Pregunta(
                textoPregunta = "⚠️ ¿Qué se recomienda evitar al trabajar con layouts anidados?",
                opciones = listOf(
                    "🎨 Usar colores claros",
                    "🔀 Anidar demasiados layouts, para no afectar el rendimiento",
                    "🖼️ Agregar imágenes en el XML",
                    "📊 Usar LinearLayout vertical"
                ),
                respuestaCorrectaIndex = 1
            ),
            Pregunta(
                textoPregunta = "🎯 ¿Cuál de los siguientes layouts es el más adecuado para interfaces complejas con muchas restricciones?",
                opciones = listOf(
                    "📦 FrameLayout",
                    "📐 ConstraintLayout",
                    "📏 LinearLayout",
                    "🔁 RelativeLayout"
                ),
                respuestaCorrectaIndex = 1
            ),
            Pregunta(
                textoPregunta = "📱 ¿Cómo se puede adaptar un layout para distintas orientaciones del dispositivo?",
                opciones = listOf(
                    "📁 Usando una sola carpeta res/layout",
                    "📂 Utilizando carpetas específicas como layout y layout-land",
                    "🎨 Cambiando los colores del fondo automáticamente",
                    "🔧 Modificando el archivo AndroidManifest.xml"
                ),
                respuestaCorrectaIndex = 1
            )
        )
    }
    private fun cargarPregunta() {
        if (indicePreguntaActual >= listaPreguntas.size) return

        val preguntaActual = listaPreguntas[indicePreguntaActual]
        tvNumeroPregunta.text = "Pregunta ${indicePreguntaActual + 1}/${listaPreguntas.size}"
        tvPregunta.text = preguntaActual.textoPregunta

        layoutOpciones.removeAllViews()

        for ((index, textoOpcion) in preguntaActual.opciones.withIndex()) {
            val opcionView = if (preguntaActual.esMultiple) {
                CheckBox(requireContext()).apply {
                    text = textoOpcion
                    textSize = 18f
                    id = index
                    setPadding(16, 12, 16, 12)
                }
            } else {
                RadioButton(requireContext()).apply {
                    text = textoOpcion
                    textSize = 18f
                    id = index
                    setPadding(16, 12, 16, 12)
                }
            }
            layoutOpciones.addView(opcionView)
        }

        // Aplicar lógica de selección única manualmente para CheckBox
        if (preguntaActual.esMultiple) {
            for (i in 0 until layoutOpciones.childCount) {
                val view = layoutOpciones.getChildAt(i)
                if (view is CheckBox) {
                    view.setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            // Desmarcar todas las demás
                            for (j in 0 until layoutOpciones.childCount) {
                                val otraView = layoutOpciones.getChildAt(j)
                                if (otraView is CheckBox && otraView != view) {
                                    otraView.isChecked = false
                                }
                            }
                        }
                    }
                }
            }
        } else {
            // Lógica equivalente para RadioButton fuera de RadioGroup
            for (i in 0 until layoutOpciones.childCount) {
                val view = layoutOpciones.getChildAt(i)
                if (view is RadioButton) {
                    view.setOnClickListener {
                        for (j in 0 until layoutOpciones.childCount) {
                            val otraView = layoutOpciones.getChildAt(j)
                            if (otraView is RadioButton && otraView != view) {
                                otraView.isChecked = false
                            }
                        }
                    }
                }
            }
        }

        btnSiguienteOFinalizar.text =
            if (indicePreguntaActual == listaPreguntas.size - 1) "Finalizar Examen" else "Siguiente Pregunta"
    }

    private fun verificarRespuesta() {
        val pregunta = listaPreguntas[indicePreguntaActual]
        val seleccionados = mutableListOf<Int>()

        for (i in 0 until layoutOpciones.childCount) {
            val view = layoutOpciones.getChildAt(i)
            if ((view is RadioButton || view is CheckBox) && view.isChecked) {
                seleccionados.add(view.id)
            }
        }

        if (seleccionados.isEmpty()) {
            Toast.makeText(context, "Selecciona una opción.", Toast.LENGTH_SHORT).show()
            return
        }

        if (seleccionados.size > 1) {
            Toast.makeText(context, "Solo puedes seleccionar una opción.", Toast.LENGTH_SHORT).show()
            return
        }

        val seleccion = seleccionados.first()

        if (!pregunta.esMultiple && seleccion == pregunta.respuestaCorrectaIndex) {
            respuestasCorrectas++
        } else if (pregunta.esMultiple) {
            val correctos = pregunta.indicesRespuestasMultiples ?: emptyList()
            if (correctos.contains(seleccion)) {
                respuestasCorrectas++
            }
        }

        indicePreguntaActual++
        if (indicePreguntaActual < listaPreguntas.size) {
            cargarPregunta()
        } else {
            mostrarCalificacion()
        }
    }

    private fun mostrarCalificacion() {
        val totalPreguntas = listaPreguntas.size
        val calificacion = (respuestasCorrectas.toFloat() / totalPreguntas.toFloat()) * 100

        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CalificacionFragment.newInstance(calificacion))
            .addToBackStack(null)
            .commit()
    }
}