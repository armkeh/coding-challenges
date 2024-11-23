package day01

import (
	"slices"
)

func emptyElf() Elf {
	return Elf{}
}

func inventoriesEqual(elf1 []Elf, elf2 []Elf) bool {
	if len(elf1) != len(elf2) {
		return false
	}

	for i := range elf1 {
		if !slices.Equal(elf1[i], elf2[i]) {
			return false
		}
	}

	return true
}
