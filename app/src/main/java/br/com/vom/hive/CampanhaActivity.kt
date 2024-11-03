package br.com.vom.hive

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class CampanhaActivity : AppCompatActivity() {

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }

    fun carregarCampanha(idCampanha: String) {
        db.collection("campaigns").document(idCampanha).get().addOnSuccessListener {
            val campaignNameTextView = findViewById<TextView>(R.id.campaignNameTextView)
            val campaignCategoryTextView =
                findViewById<TextView>(R.id.campaignCategoryTextView)
            val campaignProductTextView =
                findViewById<TextView>(R.id.campaignProductTextView)
            val campaignTargetTextView = findViewById<TextView>(R.id.campaignTargetTextView)
            val campaignTagsTextView = findViewById<TextView>(R.id.campaignTagsTextView)



            campaignNameTextView.text = it.getString("name").toString()
            campaignCategoryTextView.text = it.getString("category").toString()
            campaignProductTextView.text = it.getString("prod").toString()
            campaignTargetTextView.text = it.getString("target").toString()
            campaignTagsTextView.text =
                it.get("tags").toString().replace("[", "").replace("]", "")
        }


    }

    fun deleteCampanha(idCampanha: String) {
        db.collection("campaigns").document(idCampanha).delete().addOnSuccessListener {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_campanha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            finish()
        }
        val idCampanha = intent.getStringExtra("id")
        Log.d("idCampanha", idCampanha.toString())

        if (idCampanha != null) {
            carregarCampanha(idCampanha)
        }

        val btnDelete = findViewById<TextView>(R.id.btnDelete)
        btnDelete.setOnClickListener {
            if (idCampanha != null) {

                AlertDialog.Builder(this).setTitle("Deletar campanha")
                    .setMessage("Tem certeza que deseja deletar essa campanha?")
                    .setPositiveButton("Sim") { dialog, id ->

                        deleteCampanha(idCampanha)
                        finish()


                    }.setNegativeButton("Não") { dialog, id ->}.show()

            }

        }

        val btnUpdate = findViewById<TextView>(R.id.btnUpdate)
        btnUpdate.setOnClickListener {
            if (idCampanha != null) {
                val intent = android.content.Intent(this,AtualizaCampanhaActivity::class.java)
                intent.putExtra("id", idCampanha)
                intent.putExtra("name", findViewById<TextView>(R.id.campaignNameTextView).text.toString())
                intent.putExtra("category", findViewById<TextView>(R.id.campaignCategoryTextView).text.toString())
                intent.putExtra("prod", findViewById<TextView>(R.id.campaignProductTextView).text.toString())
                intent.putExtra("target", findViewById<TextView>(R.id.campaignTargetTextView).text.toString())
                intent.putExtra("tags", findViewById<TextView>(R.id.campaignTagsTextView).text.toString())
                startActivity(intent)


    }}}}






