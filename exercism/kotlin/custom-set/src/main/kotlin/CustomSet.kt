// In practice, we should made this a polymorphic type, rather than hardcoding it to store Ints.
class CustomSet(vararg initElems: Int) {
    // This exercise is not of much interest if we use the set or even the map type,
    // since it becomes trivial. So we use a list instead.
    private var contents: List<Int>

    init {
        contents = initElems.fold(emptyList()) { l, e -> l + e }
    }

    fun isEmpty(): Boolean {
        return contents.isEmpty()
    }

    fun isSubset(other: CustomSet): Boolean {
        return contents.all { e -> other.contains(e)  }
    }

    fun isDisjoint(other: CustomSet): Boolean {
        return contents.none { e -> other.contains(e) }
    }

    fun contains(elem: Int): Boolean {
        return contents.any { e -> e == elem }
    }

    fun intersection(other: CustomSet): CustomSet {
        var new = CustomSet()
        contents.forEach { e -> if (other.contains(e)) { new.add(e) } }
        
        return new
    }

    fun add(elem: Int) {
        if (!this.contains(elem)) { contents += elem }
    }

    fun remove(elem: Int) {
        contents = contents.fold(emptyList()) { acc, e -> if (e == elem) { acc } else { acc + e } }
    }

    override fun equals(other: Any?): Boolean {
        return other is CustomSet
            && this.isSubset(other) && other.isSubset(this)
    }

    operator fun plus(other: CustomSet): CustomSet {
        var new = other.clone()
        contents.forEach { e -> new.add(e) }

        return new
    }

    operator fun minus(other: CustomSet): CustomSet {
        var new = this.clone()
        contents.forEach { e -> if (other.contains(e)) { new.remove(e) } }

        return new
    }

    fun clone(): CustomSet {
        return CustomSet(*contents.toIntArray())
    }
}
