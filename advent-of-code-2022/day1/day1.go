package day1

import (
	"fmt"
	"strconv"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

// Elves are represented by the list of the calories in each item of their inventories.
type Elf = []int

// The group of elves is a list of `Elf`s.
type Elves = []Elf

func Part1(c settings.Config) (string, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return "", err
	}

	maxElf := Elf{}
	maxCals := 0
	checkAgainstMaxElf := func(e Elf, cals int) {
		if cals > maxCals {
			maxElf = e
			maxCals = cals
		}
	}

	currentElf := Elf{}
	currentElfCals := 0
	for line, s := range input {
		// Elf inventories are split by single empty lines.
		if s == "" {
			checkAgainstMaxElf(currentElf, currentElfCals)

			// Reset current elf
			currentElf = Elf{}
			currentElfCals = 0
		} else {
			cals, err := strconv.Atoi(s)
			if err != nil {
				return "", fmt.Errorf("Inventory entry %s on line %d could not be parsed as integer; %w", s, line, err)
			}

			currentElf = append(currentElf, cals)
			currentElfCals += cals
		}
	}

	return fmt.Sprintf("The elf with the maximum calories has %d calories and inventory contents %v.", maxCals, maxElf), nil
}
