fun transcribeToRna(dna: String): String = dna.fold("") { rna, nucleotide ->
    rna + when (nucleotide) {
        'G' -> 'C'
        'C' -> 'G'
        'T' -> 'A'
        'A' -> 'U'
        else -> throw IllegalArgumentException("Non-DNA nucleotide $nucleotide encounted in given DNA strand $dna!")
    }
}
