package day04

import (
	"fmt"
	"regexp"
	"strconv"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

type Condition func(start1, end1, start2, end2 int, logger utils.Logger) bool

func countOccurancesOverRanges(c settings.Config, condition Condition) (int, error) {
	input, err := utils.GetInputByLinesLessEmptyLines(c.InputPath)
	if err != nil {
		return 0, err
	}

	intMatcher := "([[:digit:]]*)"
	rangeMatcher := intMatcher + "-" + intMatcher
	rangeRegexp, err := regexp.Compile("^" + rangeMatcher + "," + rangeMatcher + "$")
	if err != nil {
		return 0, fmt.Errorf("Error while compiling regexp to extract range boundaries from inputs; %w", err)
	}

	count := 0
	for line, s := range input {
		rangeMatch := rangeRegexp.FindStringSubmatch(s)
		if len(rangeMatch) <= 4 {
			return 0, fmt.Errorf("Input on line %d did not match expected form (regexp %s)", line, rangeRegexp.String())
		}

		start1, err := strconv.Atoi(string(rangeMatch[1]))
		if err != nil {
			return 0, fmt.Errorf("Error parsing start of first range as integer on line %d (%s): %w", line, s, err)
		}

		end1, err := strconv.Atoi(string(rangeMatch[2]))
		if err != nil {
			return 0, fmt.Errorf("Error parsing end of first range as integer on line %d (%s): %w", line, s, err)
		}

		start2, err := strconv.Atoi(string(rangeMatch[3]))
		if err != nil {
			return 0, fmt.Errorf("Error parsing start of second range as integer on line %d (%s): %w", line, s, err)
		}

		end2, err := strconv.Atoi(string(rangeMatch[4]))
		if err != nil {
			return 0, fmt.Errorf("Error parsing end of second range as integer on line %d (%s): %w", line, s, err)
		}

		if condition(start1, end1, start2, end2, c.Logger) {
			count++
		}
	}

	return count, nil
}
