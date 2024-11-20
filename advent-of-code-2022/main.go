package main

import (
	"flag"
	"fmt"
	"log"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/day01"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/day02"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/day03"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func main() {
	conf := settings.Config{}

	day := 0
	part := 0
	flag.IntVar(&day, "d", 0, "Which day's solution to run, between 1 and 25")
	flag.IntVar(&part, "p", 0, "Which part of the day's solution to run, 1 or 2")
	flag.StringVar(&conf.InputPath, "i", "", "Alternate location of input file; defaults to day's file in ./inputs")
	var debug *bool = flag.Bool("debug", false, "Whether to run in debug mode and print debug logs")
	
	flag.Parse()

	if day == 0 || part == 0 {
		log.Fatal("Use `-d` to specify the day to run and `-p` to specify the part; `-h` for more options.")
	}
	
	if conf.InputPath == "" {
		conf.InputPath = fmt.Sprintf("./inputs/day%d.txt", day)
	}

	if *debug {
		conf.Logger.MinLevel = utils.Debug
	}

	unimplemented := func(settings.Config)(string, error) {
		return "", fmt.Errorf("Day %d, part %d not yet implemented!", day, part)
	}

	var runner func(settings.Config)(string, error)
	switch {
		case day == 1  && part == 1: runner = day01.Part1
		case day == 1  && part == 2: runner = day01.Part2
		case day == 2  && part == 1: runner = day02.Part1
		case day == 2  && part == 2: runner = day02.Part2
		case day == 3  && part == 1: runner = day03.Part1
		case day == 3  && part == 2: runner = day03.Part2
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

	result, err := runner(conf)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(result)
}
