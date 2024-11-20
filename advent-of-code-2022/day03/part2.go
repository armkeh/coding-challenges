package day03

import (
	"fmt"
	"strings"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

const returnMsg = "The sum of priorities of group badges is %d."

func Part2(c settings.Config) (string, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return "", err
	}

	sumOfPriorities := 0
	for i := 0; i < len(input); i += 3 {
		if i + 2 >= len(input) {
		  // Check any remaining lines are empty
			for j := i; i < len(input); i++ {
				if input[j] != "" {
					return "", fmt.Errorf("Not enough input lines left to form a group, but some remaining lines are non-empty: %v", input[i:])
				}
			}
			break
		}

		group := elfGroup {
			firstInventory: input[i],
			secondInventory: input[i+1],
			thirdInventory: input[i+2],
		}

		for _, r := range group.firstInventory {
			if strings.Contains(group.secondInventory, string(r)) && strings.Contains(group.thirdInventory, string(r)) {
				p, err := priority(r)

				if err != nil {
					return "", fmt.Errorf("Error checking priority on line %d; %w", i, err)
				}

				c.Logger.Debugf("For group starting on line %d, badge is %s, and priority is %d.", i, string(r), p)

				sumOfPriorities += p

				// There is only one badge per group; hence we stop searching each group
				// once we find the badge.
				break
			}
		}
	}

	return fmt.Sprintf(returnMsg, sumOfPriorities), nil
}
