package com.example.wazitoecommerce.models

class House {
    var owner:String = ""
    var location:String = ""
    var price:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(owner: String, location: String, price: String, imageUrl: String, id: String) {
        this.owner = owner
        this.location = location
        this.price = price
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}