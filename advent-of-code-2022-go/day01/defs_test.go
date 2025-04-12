package day01

import (
	"slices"
)

const (
	sampleInput = `1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
`

	multipleNewlinesInput = `1000
2000
3000



4000

5000
6000


7000
8000
9000




10000
`

	singleElfInput = `7000
8000
9000
`

	twoElfInput = `7000
8000
9000

5000
6000
`

	// Note accompanying maximum inventory functions below.
	testMaximumCals    = 24000
	testSecondmostCals = 11000
	testThirdmostCals  = 10000

	negativeInput = `-100
200
300
`

	nonIntegerInput = `100
abcd
500
`
)

func emptyElf() Elf {
	return Elf{}
}

func testMaximumElf() Elf {
	return []int{7000, 8000, 9000}
}

func testSecondmostElf() Elf {
	return []int{5000, 6000}
}

func testThirdmostElf() Elf {
	return []int{10000}
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
