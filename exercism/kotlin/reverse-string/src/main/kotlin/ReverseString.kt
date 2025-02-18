fun reverse(input: String): String {
    // Kotlin stdlib includes CharSequence.reversed(); we re-implement our own for interest.
    return input.fold("") { c, acc -> acc + c }
}
