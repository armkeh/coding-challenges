object Flattener {
    fun flatten(source: Collection<Any?>): List<Any> {
        return source.fold(emptyList()) { acc, e ->
            when(e) {
                null -> acc
                is Collection<Any?> -> acc + flatten(e)
                else -> acc + e
            }
        }
    }
}
