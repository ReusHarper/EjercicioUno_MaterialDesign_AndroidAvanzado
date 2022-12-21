package dgtic.unam.modulosiete

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import dgtic.unam.modulosiete.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Observador de acciones de usuario:
        observer()
    }

    private fun observer() {
        buttonSignUp = binding.btnSingup
        buttonLogin = binding.btnLoginAlt

        // Observacion de acciones en botones:
        buttonSignUp.setOnClickListener {
            // Proceso de autenticacion por Firebase
            dataInput()
        }
    }

    private fun message(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun dataInput() {
        firebaseAuth = FirebaseAuth.getInstance()

        val email = binding.tietEmail.text.toString()
        val pass = binding.tietPassword.text.toString()
        val confPass = binding.tietRepeatpassword.text.toString()
        val message : String?
        val typeResult = verifyAuth(email, pass, confPass)

        // Envio, comprobacion de datos ingresados correctamente y creacion de cuenta
        if (typeResult.equals("SUCCESS")) {
            if (confirmPass(pass, confPass)) {
                createAccount(email, pass)
            }
            else {
                message = "Las contraseñas no coinciden, por favor verifique e ingrese de nuevo."
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        else {
            message = "Algunos campos se encuentran vacios, por favor ingrese todos los datos."
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyAuth(email: String?, pass: String?, confPass: String?) : String {
        if (email?.isEmpty() == true || pass?.isEmpty() == true || confPass?.isEmpty() == true)
            return "FAIL_EMPTY"
        return "SUCCESS"
    }

    private fun confirmPass(pass: String, confPass: String) : Boolean {
        return pass.equals(confPass)
    }

    private fun createAccount(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                showHome(it.result.user!!.email!!, ProviderType.BASIC)
            } else {
                showAlert("Error", "Se ha producido un error autenticando al usuario", "Aceptar")
            }
        }
    }

    private fun showAlert(title: String, message: String, buttonAction: String) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(buttonAction, null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(requireContext(), HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }

        // Se asigna una bandera que indique se queda limpio el stack de activities
        homeIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }

}