package day01

import (
	"fmt"
	"strconv"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part1(c settings.Config) (string, error) {
	maxCals, maxElf, err := maxCalories(c)
	if err != nil {
		return "", err
	}

	return fmt.Sprintf("The elf with the maximum calories has %d calories and inventory contents %v.", maxCals, maxElf), nil
}

func maxCalories(c settings.Config) (int, Elf, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return 0, Elf{}, err
	}

	maxElf := Elf{}
	maxCals := 0
	checkAgainstMaxElf := func(e Elf, cals int) {
		if cals >= maxCals {
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
				return 0, Elf{}, fmt.Errorf("Inventory entry %s on line %d could not be parsed as integer; %w", s, line, err)
			}

			if cals < 0 {
				return 0, Elf{}, fmt.Errorf("Inventory entry %s on line %d was negative!", s, line)
			}

			currentElf = append(currentElf, cals)
			currentElfCals += cals
		}
	}

	return maxCals, maxElf, nil
}
