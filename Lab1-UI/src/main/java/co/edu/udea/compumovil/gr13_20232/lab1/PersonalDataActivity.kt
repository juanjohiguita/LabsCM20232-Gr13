package co.edu.udea.compumovil.gr13_20232.lab1

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PersonalDataActivity : ComponentActivity(), DatePickerDialog.OnDateSetListener{

    private val calendar = Calendar.getInstance()
    private val formatter = SimpleDateFormat("d/MM/yyyy", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personal_data_activity_layout)
        findViewById<TextView>(R.id.birthDate).setOnClickListener {
            DatePickerDialog(this,this,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        findViewById<Button>(R.id.nextButton).setOnClickListener {
            printUserInfo()
            // Sofi
        }

        val nameField = findViewById<EditText>(R.id.nameInput)
        nameField.setOnClickListener{
            nameField.requestFocus()
        }
        val lastnameField = findViewById<EditText>(R.id.lastnameInput)
        lastnameField.setOnClickListener{
            lastnameField.requestFocus()
        }
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year,month,dayOfMonth)
        displayFormattedDate(calendar.timeInMillis)
    }

    private fun displayFormattedDate(timestamp: Long){
        findViewById<Button>(R.id.birthDate).text = formatter.format(timestamp)
    }

    private fun printUserInfo(){
        val name = findViewById<EditText>(R.id.nameInput).text.toString()
        val lastname = findViewById<EditText>(R.id.lastnameInput).text.toString()
        val selectedGenderId = findViewById<RadioGroup>(R.id.genderRadioButton).checkedRadioButtonId
        val gender = if(selectedGenderId==-1) "\n" else "\n ${findViewById<RadioButton>(selectedGenderId).text} \n"
        val birthdate = findViewById<Button>(R.id.birthDate).text.toString()
        val grades = findViewById<Spinner>(R.id.gradeSpinner).selectedItem.toString()

        if(name.isNullOrEmpty()){
            Toast.makeText(this@PersonalDataActivity,R.string.messagename,Toast.LENGTH_LONG).show()
        }
        else if(lastname.isNullOrEmpty()){
            Toast.makeText(this@PersonalDataActivity,R.string.messagelastname,Toast.LENGTH_LONG).show()
        }
        else if(birthdate.isNullOrEmpty()){
            Toast.makeText(this@PersonalDataActivity,resources.getString(R.string.messagebirthdate),Toast.LENGTH_LONG).show()
        }else {
            Log.i("My activity", "${getString(R.string.title)}: \n $name \n $lastname $gender $birthdate" +
                    "${if(grades == getString(R.string.chooseeducation)) "\n" else "\n $grades \n"}")
        }
    }
}
