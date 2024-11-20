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

func Part2(c settings.Config) (string, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return "", err
	}

	const (
		most       = 1
		secondmost = 2
		thirdmost  = 3
	)
	maxElves := map[int]Elf{
		most:       {},
		secondmost: {},
		thirdmost:  {},
	}
	maxCals := map[int]int{
		most:       0,
		secondmost: 0,
		thirdmost:  0,
	}
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
				return "", fmt.Errorf("Inventory entry %s on line %d could not be parsed as integer; %w", s, line, err)
			}

			currentElf = append(currentElf, cals)
			currentElfCals += cals
		}
	}

	totalMaxCals := 0
	for _, cals := range maxCals {
		totalMaxCals += cals
	}

	return fmt.Sprintf("The three elves with the maximum calories carry %d calories, their calorie counts are %v, and their inventory contents are: %v.", totalMaxCals, maxCals, maxElves), nil
}
