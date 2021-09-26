package com.example.ibtechbootcamphmfour.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ibtechbootcamphmfour.R
import com.example.ibtechbootcamphmfour.base.BaseCallBack
import com.example.ibtechbootcamphmfour.model.LoginRequest
import com.example.ibtechbootcamphmfour.model.LoginResponse
import com.example.ibtechbootcamphmfour.model.User
import com.example.ibtechbootcamphmfour.service.ServiceConnector
import com.example.ibtechbootcamphmfour.utils.USER_TOKEN
import com.example.ibtechbootcamphmfour.utils.statusBarColor
import kotlinx.android.synthetic.main.layout_fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statusBarColor(R.color.sweet_pink2)
        return inflater.inflate(R.layout.layout_fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton.setOnClickListener{
            val emailToSend = email.text.toString()
            val passwordToSend = password.text.toString()

            if(allFieldsAreValid(emailToSend, passwordToSend)){

                val loginRequest = LoginRequest(emailToSend, passwordToSend)

                ServiceConnector.restInterface.login(loginRequest).enqueue(object: BaseCallBack<LoginResponse>(){

                    override fun onSuccess(loginResponse: LoginResponse) {
                        super.onSuccess(loginResponse)
                        val token: String? = loginResponse.token
                        User.getCurrentInstance().token = token
                        saveToken(token!!)
                        Toast.makeText(requireContext(), "Giriş işlemi Başarılı", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                    }

                    override fun onFailure() {
                        super.onFailure()
                        Toast.makeText(requireContext(), "Giriş başarısız, e-posta veya şifreniz hatalı", Toast.LENGTH_LONG).show()
                    }
                })
            }
            else{
                Toast.makeText(requireContext(), "Lütfen doğru formatta email adresi ve şifrenizi girin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun allFieldsAreValid(
        email: String,
        password: String
    ): Boolean {

        var allFieldsAreValid = true

        if (email.isEmpty() || !isValidEmail(email)) allFieldsAreValid = false

        if (password.isEmpty() || password.length < 3) allFieldsAreValid = false

        return allFieldsAreValid
    }

    private fun isValidEmail(target: CharSequence): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun saveToken(token: String){
        val sharedPreferences = this.activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putString(USER_TOKEN, token)
        }?.apply()
    }
}