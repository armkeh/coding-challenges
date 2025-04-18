package day04

import (
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
		input       string
		errExpected bool
		expected    int
	}{
		"POSITIVE: Empty input": {
			input:       "",
			errExpected: false,
			expected:    0,
		},

		"POSITIVE: Sample input": {
			input:       sampleInput,
			errExpected: false,
			expected:    4,
		},

		"NEGATIVE: Malformed range": {
			input:       malformedRange,
			errExpected: true,
		},

		"NEGATIVE: Incomplete range": {
			input:       incompleteRange,
			errExpected: true,
		},

		"NEGATIVE: Non-integer range": {
			input:       nonIntegerRange,
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

			actual, err := overlappingRangeCount(conf)

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
