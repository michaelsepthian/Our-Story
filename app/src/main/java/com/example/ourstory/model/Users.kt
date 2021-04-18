package com.example.ourstory.Model

class Users {
    private var uid: String = ""
    private var firstname: String = ""
    private var lastname: String = ""
    private var profile: String = ""
    private var cover: String = ""
    private var status: String = ""
    private var search: String = ""

    constructor()
    constructor(uid: String, firstname: String, lastname: String, profile: String, cover: String, status: String, search: String) {
        this.uid = uid
        this.firstname = firstname
        this.lastname = lastname
        this.profile = profile
        this.cover = cover
        this.status = status
        this.search = search
    }

    fun getUID(): String?{
        return uid
    }

    fun setUID(uid: String){
        this.uid = uid
    }

    fun getFirstName(): String?{
        return firstname
    }

    fun setFirstName(firstname: String){
        this.firstname = firstname
    }

    fun getLastName(): String?{
        return lastname
    }

    fun setLastName(lastname: String){
        this.lastname = lastname
    }

    fun getProfile(): String?{
        return profile
    }

    fun setProfile(profile: String){
        this.profile = profile
    }

    fun getCover(): String?{
        return cover
    }

    fun setCover(cover: String){
        this.cover = cover
    }

    fun getStatus(): String?{
        return status
    }

    fun setStatus(status: String){
        this.status = status
    }

    fun getSearch(): String?{
        return search
    }

    fun setSearch(search: String){
        this.search = search
    }
}