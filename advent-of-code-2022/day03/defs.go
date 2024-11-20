package day03

import (
	"fmt"
	"unicode"
)

type rucksack struct {
	firstCompartment string
	secondCompartment string
}

// Split the rucksack's contents into the contents of each compartment.
// By the problem statement, the first half of the rucksack contents are in the first compartment,
// and the second half are in the second compartment.
func parseRucksack(s string) rucksack {
	half := len(s) / 2

	return rucksack{
		firstCompartment: s[:half],
		secondCompartment: s[half:],
	}
}

func priority(r rune) (int, error) {
	if r > unicode.MaxASCII {
		return 0, fmt.Errorf("%s is not an ASCII character", string(r))
	}

	if unicode.IsLower(r) {
		return int(r - 'a') + 1, nil
	} else if unicode.IsUpper(r) { 
		return int(r - 'A') + 27, nil
	}

	return 0, fmt.Errorf("%s is not an upper or lowercase letter", string(r))
}
