package com.example.contacts.ModelClass

data class ContactModel(
    var contactId: String,
    var name: String? = null,
    var number: String? = null,
    var email: String? = null,
    var address: String? = null,
    var profile: String? = null
) {}
