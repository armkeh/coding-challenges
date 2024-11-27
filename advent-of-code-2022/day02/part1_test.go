package day02

import (
	"testing"

	"github.com/armkeh/coding-challenges/advent-of-code-2022/settings"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/testutils"
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

func TestPart1(t *testing.T) {
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
			expected:    15,
		},

		"NEGATIVE: Their play invalid": {
			input:       invalidTheirPlay,
			errExpected: true,
		},

		"NEGATIVE: Our play invalid": {
			input:       invalidOurPlay,
			errExpected: true,
		},

		"NEGATIVE: Incomplete instruction": {
			input:       incompleteInstruction,
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

			actual, err := playTicTacToe(conf)

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
