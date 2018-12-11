package kz.kolesa.contactlistapp.domain

import kz.kolesa.contactlistapp.domain.model.Contact
import kz.kolesa.contactlistapp.domain.model.ContactGroup

/**
 * Created by Alikhan on 12/11/2018.
 */
data class MappedContact(
    val contact: Contact,
    val contactGroup: ContactGroup
)