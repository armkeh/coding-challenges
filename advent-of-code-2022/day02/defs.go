package day02

import (
	"fmt"
)

const (
	lossScore = 0
	drawScore = 3
	winScore = 6
)

type play = int

// Because the score for rock is 1, paper is 2, and scissors is 3,
// this definition serves as an enum for plays, as well as their score.
const (
	rock play = iota + 1
	paper
	scissors
)

func parsePlay(p string) (play, error) {
	switch p {
	// Opponent plays
	case "A": return rock, nil
	case "B": return paper, nil
	case "C": return scissors, nil

	// Your plays (according to part 1's rules)
	case "X": return rock, nil
	case "Y": return paper, nil
	case "Z": return scissors, nil
	}

	return rock, fmt.Errorf("Play %s does not match 'A', 'B', 'C', 'X', 'Y', or 'Z'", p)
}

func respondToPlay(theirPlay play, instruction string) (play, error) {
	switch instruction {
	case "X": // Lose
		switch theirPlay {
		case rock: return scissors, nil
		case paper: return rock, nil
		case scissors: return paper, nil
		}
	case "Y": // Draw
		return theirPlay, nil
	case "Z": // Wind
		switch theirPlay {
		case rock: return paper, nil
		case paper: return scissors, nil
		case scissors: return rock, nil
		}
	}

	return rock, fmt.Errorf("Opponent play %d was out of bounds, or your instruction %s was not one of 'X', 'Y', or 'Z'", theirPlay, instruction)
}

// Calculate your score for the round given your play and your opponent's play
func throw(yourPlay, theirPlay play) int {
	if yourPlay == theirPlay {
		return yourPlay + drawScore
	}

	if yourPlay == rock && theirPlay == scissors ||
		yourPlay == paper && theirPlay == rock ||
		yourPlay == scissors && theirPlay == paper {
		return yourPlay + winScore
	}

	return yourPlay + lossScore
}
