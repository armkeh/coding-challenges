package day02

import (
	"fmt"
	"strings"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func Part1(c settings.Config) (string, error) {
	score, err := playTicTacToe(c)
	if err != nil {
		return "", nil
	}

	return fmt.Sprintf("Your total score would be %d", score), nil
}

func playTicTacToe(c settings.Config) (int, error) {
	input, err := utils.GetInputByLines(c.InputPath)
	if err != nil {
		return 0, err
	}

	score := 0
	for line, s := range input {
		// Skip empty lines
		if s == "" {
			continue
		}

		plays := strings.Split(s, " ")
		if len(plays) != 2 {
			return 0, fmt.Errorf("Encountered input %s on line %d which does not match the play format!", s, line)
		}

		theirPlay, err := parsePlay(plays[0])
		if err != nil {
			return 0, err
		}

		yourPlay, err := parsePlay(plays[1])
		if err != nil {
			return 0, err
		}

		score += throw(yourPlay, theirPlay)
	}

	return score, nil
}
