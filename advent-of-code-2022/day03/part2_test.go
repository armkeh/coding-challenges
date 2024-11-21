package day03

import (
	"os"
	"testing"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func TestPart2(t *testing.T) {
	testLogger := utils.Logger{
		MinLevel: utils.Debug,
	}

	cases := map[string]struct{
		input string
		errExpected bool
		expected int
	}{
		"PASS: Empty input": {
			input: "",
			errExpected: false,
			expected: 0,
		},

		"PASS: Test input": {
			input: `vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw`,
			errExpected: false,
			expected: 70,
		},

		"FAIL: Incomplete group, only one elf": {
			input: `vJrwpWtwJgWrhcsFMMfFFhFp`,
			errExpected: true,
		},

		"FAIL: Incomplete group, only two elves": {
			input: `vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL`,
			errExpected: true,
		},

		"FAIL: Incomplete group, one extra elf": {
			input: `vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn`,
			errExpected: true,
		},

		"FAIL: Incomplete group, two extra elves": {
			input: `vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT`,
			errExpected: true,
		},
	}

	for name, tc := range cases {
		testLogger.Debugf("Running test case %s", name)

		t.Run(name, func(t *testing.T) {
			
			// Create temporary input file
			f, err := os.CreateTemp("/tmp/", "aoc2022-test-input-*.txt")
			if err != nil {
				t.Fatalf("Failed to create temporary input file; %s", err)
			}

			// Defer its cleanup
			cleanupTempFile := func() {
				err := os.Remove(f.Name())
				if err != nil {
					t.Fatalf("Failed to cleanup temporary input file: %s", err)
				}
			}
			defer cleanupTempFile()

			// Write the test input to the file
			_, err = f.Write([]byte(tc.input))
			if err != nil {
				t.Fatalf("Failed to write test input to temporary file: %s", err)
			}

			conf := settings.Config{
				Logger: testLogger,
				InputPath: f.Name(),
			}

			actual, err := priorityOfBadges(conf)

			if tc.errExpected {
				if err == nil {
					t.Fatalf("Error expected, but no error returned.")
				}
			} else {
				if err != nil {
					t.Fatalf("Returned unexpected error: %s", err)
				}
				if actual != tc.expected {
					t.Fatalf("Actual result (%d) does not match expected result (%d).", actual, tc.expected)
				}
			}
		})
	}
}
