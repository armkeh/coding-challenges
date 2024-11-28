package day04

import (
	"fmt"
	"regexp"
	"strconv"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part1(c settings.Config) (string, error) {
	res, err := subsumedAssignmentCount(c)

	return fmt.Sprintf("There are %d elves whose cleaning assignment is fully subsumed by their partner's.", res), err
}

func subsumedAssignmentCount(c settings.Config) (int, error) {
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

	subsumedCount := 0
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

		if start2 <= start1 && end1 <= end2 {
			c.Logger.Debugf("Found an elf whose assignment (%d-%d) is subsumed by their partner's (%d-%d): line %d, %s",
				start1, end1, start2, end2, line, s)
			subsumedCount += 1
		} else if start1 <= start2 && end2 <= end1 {
			c.Logger.Debugf("Found an elf whose assignment (%d-%d) is subsumed by their partner's (%d-%d): line %d, %s",
				start2, end2, start1, end1, line, s)
			subsumedCount += 1
		}
	}

	return subsumedCount, nil
}
