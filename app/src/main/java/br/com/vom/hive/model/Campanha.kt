package br.com.vom.hive.model


data class Campanha(
    val id: String,
    val name: String,
    val category: String,
    val prod: String,
    val target: String,
    val tags: List<String>,
    val logo: String,
)
