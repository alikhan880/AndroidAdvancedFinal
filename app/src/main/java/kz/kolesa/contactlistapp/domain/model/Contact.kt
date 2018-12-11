package kz.kolesa.contactlistapp.domain.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(
    tableName = "contact",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = ContactGroup::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("id"),
            onDelete = CASCADE
        )
    )
)
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "mPhone") val mPhone: String,
    @ColumnInfo(name = "hPhone") val hPhone: String,
    @ColumnInfo(name = "wPhone") val wPhone: String,
    @ColumnInfo(name = "imgUri") val imgUri: String,
    @ColumnInfo(name = "contactGroupId") val contactGroup: Int
)