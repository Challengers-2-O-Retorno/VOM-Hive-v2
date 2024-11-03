package br.com.vom.hive

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class AtualizaCampanhaActivity : AppCompatActivity(R.layout.activity_criar_campanha) {

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            finish()
        }

        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        headerTitle.text = "Atualizar Campanha"

        val idCampanha = intent.getStringExtra("id")

        findViewById<TextView>(R.id.campaignNameEditText).text = intent.getStringExtra("name")
        findViewById<TextView>(R.id.campaignCategoryEditText).text = intent.getStringExtra("category")
        findViewById<TextView>(R.id.campaignProductEditText).text = intent.getStringExtra("prod")
        findViewById<TextView>(R.id.campaignTargetEditText).text = intent.getStringExtra("target")
        findViewById<TextView>(R.id.campaignTagsEditText).text = intent.getStringExtra("tags")

        val saveCampaignButton = findViewById<TextView>(R.id.saveCampaignButton)
        saveCampaignButton.setOnClickListener {
            val newCampaign = mapOf(
                "name" to findViewById<EditText>(R.id.campaignNameEditText).text.toString(),
                "category" to findViewById<EditText>(R.id.campaignCategoryEditText).text.toString(),
                "prod" to findViewById<EditText>(R.id.campaignProductEditText).text.toString(),
                "target" to findViewById<EditText>(R.id.campaignTargetEditText).text.toString(),
                "tags" to findViewById<EditText>(R.id.campaignTagsEditText).text.toString().split(",").toList()
            )
            db.collection("campaigns").document(idCampanha!!).set(newCampaign).addOnSuccessListener {
                Toast.makeText(this, "Campanha atualizada com sucesso!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

    }
}