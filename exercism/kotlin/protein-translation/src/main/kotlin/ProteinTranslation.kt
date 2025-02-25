fun translate(rna: String?): List<String> {
    tailrec fun translateCodons(codons: List<String>, proteins: List<String>): List<String> {
        val first = codons.firstOrNull()
        if (first == null) {
            return proteins
        }

        val rest = codons.drop(1)
        when (first) {
            "AUG" ->
                return translateCodons(rest, proteins + listOf("Methionine"))
            "UUU", "UUC" ->
                return translateCodons(rest, proteins + listOf("Phenylalanine"))
            "UUA", "UUG" ->
                return translateCodons(rest, proteins + listOf("Leucine"))
            "UCU", "UCC", "UCA", "UCG" ->
                return translateCodons(rest, proteins + listOf("Serine"))
            "UAU", "UAC" ->
                return translateCodons(rest, proteins + listOf("Tyrosine"))
            "UGU", "UGC" ->
                return translateCodons(rest, proteins + listOf("Cysteine"))
            "UGG" ->
                return translateCodons(rest, proteins + listOf("Tryptophan"))
            "UAA", "UAG", "UGA" ->
                return proteins // STOP codons
            else -> throw IllegalArgumentException("Non-cordon portion \"$first\" of RNA sequence \"$rna\" encountered!")
        }
    }

    if (rna == null) {
        return emptyList()
    }

    return translateCodons(rna.windowed(3, step=3, partialWindows=true), emptyList())
}
