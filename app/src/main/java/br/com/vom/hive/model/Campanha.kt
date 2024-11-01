package br.com.vom.hive.model


data class Campanha(
    val id: String,
    val nome: String,
    val categoria: String,
    val produto: String,
    val target: String,
    val tags: List<String>
)
