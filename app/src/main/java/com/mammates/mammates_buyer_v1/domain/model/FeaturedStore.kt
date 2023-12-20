package com.mammates.mammates_buyer_v1.domain.model

data class FeaturedStore(
    val id: Int,
    val name: String,
    val address: String,
    val image: String?,
)

val featuredStore = listOf(
    FeaturedStore(
        id = 1,
        name = "Cafe Bro Waw",
        address = "Jl. Machine Learning Bangkit III No. 9",
        image = "https://nibble-images.b-cdn.net/nibble/original_images/cafe-di-menteng-00.jpg"
    ),
    FeaturedStore(
        id = 2,
        name = "Depot Pak Tude II",
        address = "Jl. Cloud Computing Bangkit II No. 9",
        image = "https://fastly.4sqi.net/img/general/600x600/3811338_W2ggvB3t3-q2AWO2dgmgFNwmYbKPk_y99pn0aXexol0.jpg"
    ),
    FeaturedStore(
        id = 3,
        name = "Lounge Mas Kusuma",
        address = "Jl. Mobile Developer Bangkit I No. 9",
        image = "https://cms.disway.id/uploads/01a9dfb0e4c3b0593f1f0051ce3f774c.jpg"
    ),
)