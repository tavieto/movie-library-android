package dev.tavieto.movielibrary.core.commons.util

enum class AccountType(val id: String, val levelAccess: Int) {
    Coach(id = "league_director", levelAccess = 4),
    League(id = "league_director", levelAccess = 4),
    Manager(id = "manager", levelAccess = 3),
    Staff(id = "staff", levelAccess = 2),
    Player(id = "player", levelAccess = 1);

    companion object {
        fun getById(id: String): AccountType {
            return values().first { id == it.id }
        }

        fun getByLevelAccess(levelAccess: Int): AccountType {
            return values().first { levelAccess == it.levelAccess }
        }

        fun getByIdOrNull(id: String): AccountType? {
            return values().firstOrNull { id == it.id }
        }

        fun getByLevelAccessOrNull(levelAccess: Int): AccountType? {
            return values().firstOrNull { levelAccess == it.levelAccess }
        }
    }
}
