package day01

import (
	"slices"
	"testing"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/testutils"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func TestPart2(t *testing.T) {
	testLogger := utils.Logger{
		MinLevel: utils.Debug,
	}

	cases := map[string]struct {
		input            string
		errExpected      bool
		expectedTopCals  []int
		expectedTopElves []Elf
	}{
		"POSITIVE: Empty input": {
			input:            "",
			errExpected:      false,
			expectedTopCals:  []int{0, 0, 0},
			expectedTopElves: []Elf{emptyElf(), emptyElf(), emptyElf()},
		},

		"POSITIVE: Sample input": {
			input:            sampleInput,
			errExpected:      false,
			expectedTopCals:  []int{testMaximumCals, testSecondmostCals, testThirdmostCals},
			expectedTopElves: []Elf{testMaximumElf(), testSecondmostElf(), testThirdmostElf()},
		},

		"POSITIVE: Multiple newlines input": {
			input:            multipleNewlinesInput,
			errExpected:      false,
			expectedTopCals:  []int{testMaximumCals, testSecondmostCals, testThirdmostCals},
			expectedTopElves: []Elf{testMaximumElf(), testSecondmostElf(), testThirdmostElf()},
		},

		"POSITIVE: Single elf input": {
			input:            singleElfInput,
			errExpected:      false,
			expectedTopCals:  []int{testMaximumCals, 0, 0},
			expectedTopElves: []Elf{testMaximumElf(), emptyElf(), emptyElf()},
		},

		"POSITIVE: Two elf input": {
			input:            twoElfInput,
			errExpected:      false,
			expectedTopCals:  []int{testMaximumCals, testSecondmostCals, 0},
			expectedTopElves: []Elf{testMaximumElf(), testSecondmostElf(), emptyElf()},
		},

		"NEGATIVE: Non-integer input": {
			input:       nonIntegerInput,
			errExpected: true,
		},

		"NEGATIVE: Negative input": {
			input:       negativeInput,
			errExpected: true,
		},
	}

	for name, tc := range cases {
		testLogger.Debugf("Running test case %s", name)

		t.Run(name, func(t *testing.T) {
			inputFilename, cleanupTempFile, err := testutils.CreateTempInputFile(tc.input)
			defer cleanupTempFile()
			if err != nil {
				t.Fatal(err.Error())
			}

			conf := settings.Config{
				Logger:    testLogger,
				InputPath: inputFilename,
			}

			actualTopCals, actualTopElves, err := topCalories(conf)

			if tc.errExpected {
				if err == nil {
					t.Fatalf("Error expected, but no error returned.")
				}
			} else {
				if err != nil {
					t.Fatalf("Returned unexpected error: %s", err)
				}
				if !slices.Equal(actualTopCals, tc.expectedTopCals) || !inventoriesEqual(actualTopElves, tc.expectedTopElves) {
					t.Fatalf("Actual result (calories %v, inventories %v) does not match expected result (%v, %v).",
						actualTopCals, actualTopElves, tc.expectedTopCals, tc.expectedTopElves)
				}
			}
		})
	}
}
