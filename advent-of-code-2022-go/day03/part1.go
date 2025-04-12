package day03

import (
	"fmt"
	"strings"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part1(c settings.Config) (string, error) {
	sumOfPriorities, err := duplicateItemPriorities(c)
	if err != nil {
		return "", err
	}

	return fmt.Sprintf("The sum of priorities of items accidentally included in both compartments of a knapsack is %d.", sumOfPriorities), err
}

func duplicateItemPriorities(c settings.Config) (int, error) {
	input, err := utils.GetInputByLinesLessEmptyLines(c.InputPath)
	if err != nil {
		return 0, err
	}

	sumOfPriorities := 0
	for line, s := range input {
		rucksack := parseRucksack(s)

		for _, r := range rucksack.firstCompartment {
			if strings.Contains(rucksack.secondCompartment, string(r)) {
				p, err := priority(r)

				if err != nil {
					return 0, fmt.Errorf("Error checking priority on line %d; %w", line, err)
				}

				c.Logger.Debugf("On line %d, duplicate item is %s, and priority is %d.", line, string(r), p)

				sumOfPriorities += p

				// There is only one item improperly in both compartments per knapsack,
				// though it may appear multiple times; hence we stop searching each knapsack
				// once we find the first improper item.
				break
			}
		}
	}

	return sumOfPriorities, nil
}
