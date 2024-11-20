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

	var runner func(string)(string, error)
	switch {
		case day == 1 && part == 1: runner = day1.Part1
		default: log.Fatal("Day and part out of range, or not yet implemented!")
	}

	result, err := runner(inputPath)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(result)
}
