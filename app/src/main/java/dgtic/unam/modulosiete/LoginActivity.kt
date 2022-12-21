package dgtic.unam.modulosiete

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.text.TextUtils.replace
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.*
import dgtic.unam.modulosiete.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // ========== Fragments ==========
    private lateinit var signupFragment: SignupFragment
    private lateinit var loginFragment: LoginFragment
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verificacion de inicio de sesion
        sesiones()

        // Control y manejo de cada Fragment
        signupFragment = SignupFragment()
        loginFragment = LoginFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_container, loginFragment).commit()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun sesiones() {
        val preferencias = getSharedPreferences(getString(R.string.file_preferencia), Context.MODE_PRIVATE)
        var email:String? = preferencias.getString("email", null)
        var proovedor:String? = preferencias.getString("proovedor", null)

        if (email != null && proovedor != null) {
            showHome(email, proovedor)
            finish()
        }
    }

    fun click(view: View) {
        fragmentTransaction = supportFragmentManager.beginTransaction()

        // Cambio entre fragments dependiendo si se selecciona iniciar sesion o crear una cuenta
        when(view.id){
            R.id.btn_signup_alt -> {
                supportFragmentManager.commit {
                    replace<SignupFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                    //addToBackStack("signup")
                }

            }

            R.id.btn_login_alt -> {
                supportFragmentManager.commit {
                    replace<LoginFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                    //addToBackStack("login")
                }
            }
        }
    }

    private fun showHome(email: String, provider: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider)
        }

        // Se asigna una bandera que indique se queda limpio el stack de activities
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(homeIntent)
    }
}