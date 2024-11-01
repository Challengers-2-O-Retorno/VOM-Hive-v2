package br.com.vom.hive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.vom.hive.model.Campanha
import br.com.vom.hive.recyclerView.adapter.CampanhaAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var campanhaAdapter: CampanhaAdapter
    private val listaCampanhas = mutableListOf<Campanha>(Campanha("1","teste", "teste", "teste", "teste", listOf("a")))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val logout = findViewById<ImageView>(R.id.logout)
        logout.setOnClickListener {
            finish()
        }

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.campaignRecyclerView)
        campanhaAdapter = CampanhaAdapter(listaCampanhas)
        recyclerView.adapter = campanhaAdapter

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            startActivity(Intent(this, CriarCampanhaActivity::class.java))
        }
    }
}