package com.salesianostriana.dam.damkeep.entidades

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import kotlin.collections.HashSet


@Entity
data class User(

        @Column(nullable = false, unique = true)
        private var username: String,
        private var password: String,
        var fullName : String,

        @ElementCollection(fetch = FetchType.EAGER)
        val roles: MutableSet<String> = HashSet(),

        private val nonExpired: Boolean = true,
        private val nonLocked: Boolean = true,
        private val enabled: Boolean = true,
        private val credentialsNonExpired : Boolean = true,
        @JsonManagedReference @OneToMany(mappedBy="autor", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
        var notas : List<Nota>? = null,

        @Id @GeneratedValue val id : UUID? = null

) : UserDetails {

    constructor(username: String, password: String, fullName: String) :
            this(username, password, fullName, mutableSetOf("USER"), true, true, true, true)

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
            roles.map { SimpleGrantedAuthority("ROLE_$it") }.toMutableList()

    override fun isEnabled() = enabled
    override fun getUsername() = username
    override fun isCredentialsNonExpired() = credentialsNonExpired
    override fun getPassword() = password
    override fun isAccountNonExpired() = nonExpired
    override fun isAccountNonLocked() = nonLocked

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true
        if (other === null || other !is User)
            return false
        if (this::class != other::class)
            return false
        return id == other.id
    }

    override fun hashCode(): Int {
        if (id == null)
            return super.hashCode()
        return id.hashCode()
    }
}
