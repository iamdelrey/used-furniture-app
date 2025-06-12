package rksp.furniture.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,                             // теперь var и со значением по умолчанию

    @Column(unique = true, nullable = false)
    var username: String = "",                    // дефолтное значение

    @Column(nullable = false)
    var password: String = "",                    // дефолтное значение

    @Column(nullable = true)
    var email: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Enumerated(EnumType.STRING)
    var roles: MutableSet<Role> = mutableSetOf(Role.ROLE_USER)
)
