package day04

import (
	"fmt"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part2(c settings.Config) (string, error) {
	res, err := overlappingRangeCount(c)

	return fmt.Sprintf("Not yet implemented; %d", res), err
}

func assignmentsOverlap(start1, end1, start2, end2 int, logger utils.Logger) bool {
	if start2 <= start1 && start1 <= end2 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is overlaps their partner's at the start (%d-%d)",
			start1, end1, start2, end2)
		return true
	} else if start2 <= end1 && end1 <= end2 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is overlaps their partner's at the end (%d-%d)",
			start1, end1, start2, end2)
		return true
	} else if start1 <= start2 && start2 <= end1 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is overlaps their partner's at the start (%d-%d)",
			start2, end2, start1, end1)
		return true
	} else if start1 <= end2 && end2 <= end1 {
		logger.Debugf("Found an elf whose assignment (%d-%d) is overlaps their partner's at the end (%d-%d)",
			start2, end2, start1, end1)
		return true
	}

	return false
}

func overlappingRangeCount(c settings.Config) (int, error) {
	return countOccurancesOverRanges(c, assignmentsOverlap)
}
