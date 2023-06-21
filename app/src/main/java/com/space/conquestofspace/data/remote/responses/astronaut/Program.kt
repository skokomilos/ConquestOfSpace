package com.space.conquestofspace.data.remote.responses.astronaut

data class Program(
    val agencies: List<com.space.conquestofspace.data.remote.responses.astronaut.Agency>,
    val description: String,
    val end_date: String,
    val id: Int,
    val image_url: String,
    val info_url: String,
    val mission_patches: List<com.space.conquestofspace.data.remote.responses.astronaut.MissionPatche>,
    val name: String,
    val start_date: String,
    val url: String,
    val wiki_url: String
)
