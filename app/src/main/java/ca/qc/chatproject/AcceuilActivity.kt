package ca.qc.chatproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.qc.chatproject.databinding.ActivityAcceuilBinding
import ca.qc.chatproject.databinding.ActivityMainBinding

class AcceuilActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAcceuilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcceuilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnConnexion.setOnClickListener{
            val intent = Intent(this@AcceuilActivity,MainActivity::class.java)
            startActivity(intent)
        }



    }
}