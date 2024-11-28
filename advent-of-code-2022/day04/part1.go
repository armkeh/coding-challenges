package day04

import (
	"fmt"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part1(c settings.Config) (string, error) {
	res, err := subsumedAssignmentCount(c)

	return fmt.Sprintf("There are %d elves whose cleaning assignment is fully subsumed by their partner's.", res), err
}

func assignmentSubsumed(start1, end1, start2, end2 int, logger utils.Logger) bool {
	if start2 <= start1 && end1 <= end2 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is subsumed by their partner's (%d-%d)",
			start1, end1, start2, end2)
		return true
	} else if start1 <= start2 && end2 <= end1 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is subsumed by their partner's (%d-%d)",
			start2, end2, start1, end1)
		return true
	}

	return false
}

func subsumedAssignmentCount(c settings.Config) (int, error) {
	return countOccurancesOverRanges(c, assignmentSubsumed)
}
