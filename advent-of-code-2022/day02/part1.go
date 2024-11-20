package day02

import (
	"strings"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
	"fmt"
)

func Part1(c settings.Config) (string, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return "", err
	}

	score := 0
	for line, s := range input {
		// Skip empty lines
		if s == "" {
			continue
		}

		plays := strings.Split(s, " ")
		if len(plays) != 2 {
			return "", fmt.Errorf("Encountered input %s on line %d which does not match the play format!", s, line)
		}

		theirPlay, err := parsePlay(plays[0])
		if err != nil {
			return "", err
		}

		yourPlay, err := parsePlay(plays[1])
		if err != nil {
			return "", err
		}

		score += throw(yourPlay, theirPlay)
	}

	return fmt.Sprintf("Your total score would be %d", score), nil
}
