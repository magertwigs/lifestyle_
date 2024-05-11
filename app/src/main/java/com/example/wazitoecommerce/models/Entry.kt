package com.example.wazitoecommerce.models

class Entry {
    var date:String = ""
    var thought:String = ""
    var imageUrl:String = ""
    var id:String = ""

    constructor(date: String, thought: String, imageUrl: String, id: String) {
        this.date = date
        this.thought = thought
        this.imageUrl = imageUrl
        this.id = id
    }

    constructor()
}