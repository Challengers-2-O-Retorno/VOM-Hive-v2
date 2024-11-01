package br.com.vom.hive

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class CriarCampanhaActivity : AppCompatActivity() {

    private val db by lazy {
        FirebaseFirestore.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_criar_campanha)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val backArrow = findViewById<ImageView>(R.id.backArrow)
        backArrow.setOnClickListener {
            finish()
        }

        val saveCampaignButton = findViewById<Button>(R.id.saveCampaignButton)
        saveCampaignButton.setOnClickListener {

            val newCampaign = mapOf(
                "name" to findViewById<EditText>(R.id.campaignNameEditText).text.toString(),
                "prod" to findViewById<EditText>(R.id.campaignProductEditText).text.toString(),
                "prod" to findViewById<EditText>(R.id.campaignCategoryEditText).text.toString(),
                "target" to findViewById<EditText>(R.id.campaignTargetEditText).text.toString(),
                "tags" to findViewById<EditText>(R.id.campaignTagsEditText).text.toString().split(",").toList()
            )

            db.collection("campaigns")
                .add(newCampaign)
                .addOnSuccessListener {
                    Toast.makeText(this, "Campanha salva com sucesso!", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .addOnFailureListener { e ->
                    AlertDialog.Builder(this)
                        .setTitle("Erro ao cadastrar campanha")
                        .setMessage(e.message)
                        .setPositiveButton("OK", null)
                        .create().show()
                }


        }
    }
}