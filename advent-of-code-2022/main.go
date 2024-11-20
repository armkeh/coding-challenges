package main

import (
	"flag"
	"fmt"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/day1"
	"log"
)

func main() {
	day := 0
	part := 0
	inputPath := ""
	flag.IntVar(&day, "d", 0, "Which day's solution to run, between 1 and 25")
	flag.IntVar(&part, "p", 0, "Which part of the day's solution to run, 1 or 2")
	flag.StringVar(&inputPath, "i", "", "Alternate location of input file; defaults to day's file in ./inputs")
	
	flag.Parse()

	if day == 0 || part == 0 {
		log.Fatal("Use `-d` to specify the day to run and `-p` to specify the part; `-h` for more options.")
	}
	
	if inputPath == "" {
		inputPath = fmt.Sprintf("./inputs/day%d.txt", day)
	}

	unimplemented := func(string)(string, error) {
		return "", fmt.Errorf("Day %d, part %d not yet implemented!", day, part)
	}

	var runner func(string)(string, error)
	switch {
		case day == 1  && part == 1: runner = day1.Part1
		case day == 1  && part == 2: runner = unimplemented
		case day == 2  && part == 1: runner = unimplemented
		case day == 2  && part == 2: runner = unimplemented
		case day == 3  && part == 1: runner = unimplemented
		case day == 3  && part == 2: runner = unimplemented
		case day == 4  && part == 1: runner = unimplemented
		case day == 4  && part == 2: runner = unimplemented
		case day == 5  && part == 1: runner = unimplemented
		case day == 5  && part == 2: runner = unimplemented
		case day == 6  && part == 1: runner = unimplemented
		case day == 6  && part == 2: runner = unimplemented
		case day == 7  && part == 1: runner = unimplemented
		case day == 7  && part == 2: runner = unimplemented
		case day == 8  && part == 1: runner = unimplemented
		case day == 8  && part == 2: runner = unimplemented
		case day == 9  && part == 1: runner = unimplemented
		case day == 9  && part == 2: runner = unimplemented
		case day == 10 && part == 1: runner = unimplemented
		case day == 10 && part == 2: runner = unimplemented
		case day == 11 && part == 1: runner = unimplemented
		case day == 11 && part == 2: runner = unimplemented
		case day == 12 && part == 1: runner = unimplemented
		case day == 12 && part == 2: runner = unimplemented
		case day == 13 && part == 1: runner = unimplemented
		case day == 13 && part == 2: runner = unimplemented
		case day == 14 && part == 1: runner = unimplemented
		case day == 14 && part == 2: runner = unimplemented
		case day == 15 && part == 1: runner = unimplemented
		case day == 15 && part == 2: runner = unimplemented
		case day == 16 && part == 1: runner = unimplemented
		case day == 16 && part == 2: runner = unimplemented
		case day == 17 && part == 1: runner = unimplemented
		case day == 17 && part == 2: runner = unimplemented
		case day == 18 && part == 1: runner = unimplemented
		case day == 18 && part == 2: runner = unimplemented
		case day == 19 && part == 1: runner = unimplemented
		case day == 19 && part == 2: runner = unimplemented
		case day == 20 && part == 1: runner = unimplemented
		case day == 20 && part == 2: runner = unimplemented
		case day == 21 && part == 1: runner = unimplemented
		case day == 21 && part == 2: runner = unimplemented
		case day == 22 && part == 1: runner = unimplemented
		case day == 22 && part == 2: runner = unimplemented
		case day == 23 && part == 1: runner = unimplemented
		case day == 23 && part == 2: runner = unimplemented
		case day == 24 && part == 1: runner = unimplemented
		case day == 24 && part == 2: runner = unimplemented
		case day == 25 && part == 1: runner = unimplemented
		case day == 25 && part == 2: runner = unimplemented

		default: log.Fatal(fmt.Sprintf("Day (%d) or part (%d) out of range; day must be between 1 and 25, part either 1 or 2!", day, part))
	}

	result, err := runner(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(result)
}
