package day01

import (
	"fmt"
	"strconv"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part2(c settings.Config) (string, error) {
	topCals, topElves, err := topCalories(c)
	if err != nil {
		return "", err
	}

	totalTopCals := 0
	for _, cals := range topCals {
		totalTopCals += cals
	}

	return fmt.Sprintf("The three elves with the maximum calories carry %d calories, "+
		"their calorie counts are %v, and their inventory contents are: %v.",
		totalTopCals, topCals, topElves), nil
}

func topCalories(c settings.Config) ([]int, []Elf, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return []int{}, []Elf{}, err
	}

	// We will use slices to store the elves with the topmost calories in their inventory.
	// We define aliases to use as the array indices to improve code clarity.
	const (
		most       = 0
		secondmost = 1
		thirdmost  = 2
	)

	// Initialize the arrays as elves with empty inventories.
	maxElves := []Elf{
		most:       {},
		secondmost: {},
		thirdmost:  {},
	}
	maxCals := []int{
		most:       0,
		secondmost: 0,
		thirdmost:  0,
	}

	// This function checks a new elf againt the existing elves in the topmost slices,
	// and fits them in if they beat one of the topmost.
	checkAgainstMaxElves := func(e Elf, cals int) {
		if cals > maxCals[most] {
			maxElves[thirdmost] = maxElves[secondmost]
			maxElves[secondmost] = maxElves[most]
			maxElves[most] = e

			maxCals[thirdmost] = maxCals[secondmost]
			maxCals[secondmost] = maxCals[most]
			maxCals[most] = cals
			c.Logger.Debugf("Replaced firstmost; new maximum calories are %v", maxCals)
		} else if cals > maxCals[secondmost] {
			maxElves[thirdmost] = maxElves[secondmost]
			maxElves[secondmost] = e

			maxCals[thirdmost] = maxCals[secondmost]
			maxCals[secondmost] = cals
			c.Logger.Debugf("Replaced second most; new maximum calories are %v", maxCals)
		} else if cals > maxCals[thirdmost] {
			maxElves[thirdmost] = e

			maxCals[thirdmost] = cals
			c.Logger.Debugf("Replaced thirdmost; new maximum calories are %v", maxCals)
		}
	}

	currentElf := Elf{}
	currentElfCals := 0
	for line, s := range input {
		// Elf inventories are split by single empty lines.
		if s == "" {
			c.Logger.Debugf("Elf with inventory %v is carrying %d calories.", currentElf, currentElfCals)
			checkAgainstMaxElves(currentElf, currentElfCals)

			// Reset current elf
			currentElf = Elf{}
			currentElfCals = 0
		} else {
			cals, err := strconv.Atoi(s)
			if err != nil {
				return []int{}, []Elf{}, fmt.Errorf("Inventory entry %s on line %d could not be parsed as integer; %w", s, line, err)
			}

			if cals < 0 {
				return []int{}, []Elf{}, fmt.Errorf("Inventory entry %s on line %d was negative!", s, line)
			}

			currentElf = append(currentElf, cals)
			currentElfCals += cals
		}
	}

	return maxCals, maxElves, nil
}
