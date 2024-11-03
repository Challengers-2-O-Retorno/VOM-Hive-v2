package br.com.vom.hive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.vom.hive.model.Campanha
import br.com.vom.hive.recyclerView.adapter.CampanhaAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var campanhaAdapter: CampanhaAdapter
    private val listaCampanhas = mutableListOf<Campanha>()

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }

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

        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            startActivity(Intent(this, CriarCampanhaActivity::class.java))
        }


    }

    override fun onResume() {
        super.onResume()
        listaCampanhas.clear()

        carregarCampanhas()

        val recyclerView =
            findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.campaignRecyclerView)
        campanhaAdapter = CampanhaAdapter(listaCampanhas)
        campanhaAdapter.setOnItemClickListener(object : CampanhaAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@HomeActivity, CampanhaActivity::class.java)
                intent.putExtra("id", listaCampanhas[position].id)
                startActivity(intent)
            }
        })
        recyclerView.adapter = campanhaAdapter
    }

    fun carregarCampanhas() {
        db.collection("campaigns")
            .get()
            .addOnSuccessListener { results ->
                for (document in results) {
                    Log.i("teste", "${document.id} => ${document.data}")
                    val campanha = Campanha(
                        id = document.id,
                        name = document.data["name"].toString(),
                        category = document.data["category"].toString(),
                        prod = document.data["prod"].toString(),
                        target = document.data["target"].toString(),
                        tags = listOf(document.data["tags"].toString()),
                        logo = document.data["logo"].toString()
                    )
                    Log.i("teste", "${campanha.name}")
                    listaCampanhas.add(campanha)
                    campanhaAdapter.notifyDataSetChanged()

                }
            }
    }
}