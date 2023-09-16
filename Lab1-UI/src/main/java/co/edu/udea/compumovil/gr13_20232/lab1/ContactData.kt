 package co.edu.udea.compumovil.gr13_20232.lab1

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.ComponentActivity


 class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myIntent = intent // gets the previously created intent

        val personalData = myIntent.getStringExtra("personalData")

        setContentView(R.layout.contact_data)
        val telefono: ImageView = findViewById(R.id.imagenTelefono)
        telefono.setImageResource(R.drawable.telefono_icono)
        val correoIcono: ImageView = findViewById(R.id.imagenCorreo)
        correoIcono.setImageResource(R.drawable.correo_icono)
        val ciudadIcono: ImageView = findViewById(R.id.imagenCiudad)
        ciudadIcono.setImageResource(R.drawable.ciudad_icono)
        val paisIcono: ImageView = findViewById(R.id.imagenPais)
        paisIcono.setImageResource(R.drawable.pais_icono)
        val ubicacionIcono: ImageView = findViewById(R.id.imagenUbicacion)
        ubicacionIcono.setImageResource(R.drawable.direccion_icono)



        val botonSiguiente = findViewById<Button>(R.id.botonSiguiente)

        val textoTelefono = findViewById<EditText>(R.id.textoTelefono)
        textoTelefono.setOnClickListener{
            textoTelefono.requestFocus()
        }

        val textoCorreo = findViewById<EditText>(R.id.textoCorreo)
        textoCorreo.setOnClickListener{
            textoCorreo.requestFocus()
        }

        val textoDireccion = findViewById<EditText>(R.id.textoDireccion)
        textoDireccion.setOnClickListener{
            textoDireccion.requestFocus()
        }

        val COUNTRIES = arrayOf(
            "Argentina", "Bolivia", "Brasil", "Chile", "Colombia", "Costa Rica", "Cuba", "Ecuador", "El Salvador",
            "Guatemala", "Honduras", "México", "Nicaragua", "Panamá", "Paraguay", "Perú", "Puerto Rico",
            "República Dominicana", "Uruguay" ,"Venezuela"
        )

        val CITIES = arrayOf(
            "Bogotá", "Medellín ", "Cali", "Barranquilla ", "Cartagena de Indias", "Cúcula", "Soledad", "Ibagué ", "Bucaramanga ",
            "Soacha ", "Santa Marta", "Villavicencio ", "Bello ", "Pereira ", "Valledupar", "Manizales ", "Buenaventura ",
            "Pasto ", "Montería " ,"Neiva", "Armenia"
        )

        val textoPais = findViewById<AutoCompleteTextView>(R.id.textoPais)
        textoPais.setOnClickListener{
            textoPais.requestFocus()
        }
        var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, COUNTRIES)
        textoPais.threshold = 0
        textoPais.setAdapter(adapter)

        val textoCiudad = findViewById<AutoCompleteTextView>(R.id.textoCiudad)
        textoCiudad.setOnClickListener{
            textoCiudad.requestFocus()
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, CITIES)
        textoCiudad.threshold = 0
        textoCiudad.setAdapter(adapter)



        botonSiguiente.setOnClickListener{
            if(textoTelefono.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity, R.string.mensajeTelefono, Toast.LENGTH_LONG).show()
            }else if(textoCorreo.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity, R.string.mensajeCorreo, Toast.LENGTH_LONG).show()
            }else if(!isEmail(textoCorreo.text.toString())){
                Toast.makeText(this@MainActivity, R.string.mensajeCorreoInv, Toast.LENGTH_LONG).show()
            }else if(textoPais.text.isNullOrEmpty()){
                Toast.makeText(this@MainActivity, R.string.mensajePais, Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, R.string.mensajeBien, Toast.LENGTH_LONG).show()
                Log.i("Información personal",personalData.toString())
                Log.i("Información de contacto", "${getString(R.string.informacionDeContacto)}: \n ${getString(R.string.numeroDeTelefono)}: ${textoTelefono.text} \n " +
                        "${getString(R.string.direccion)}: ${textoDireccion.text} \n " +
                        "${getString(R.string.correo)}: ${textoCorreo.text} \n ${getString(R.string.pais)}: ${textoPais.text} \n" +
                        "${getString(R.string.ciudad)}: ${textoCiudad.text} \n")
            }
        }

    }

     private fun isEmail(email: String): Boolean{
         return Patterns.EMAIL_ADDRESS.matcher(email).matches()
     }

}

