package day03

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
		"PASS: Empty input": {
			input:       "",
			errExpected: false,
			expected:    0,
		},

		"PASS: Test input": {
			input:       testInput,
			errExpected: false,
			expected:    157,
		},

		"PASS: Even inventory size": {
			input:       elfWithOddInventorySize,
			errExpected: false,
			expected:    1,
		},

		"PASS: Odd inventory size": {
			input:       elfWithEvenInventorySize,
			errExpected: false,
			expected:    1,
		},

		"PASS: No duplicate items": {
			input:       elfWithNoDuplicateItems,
			errExpected: false,
			expected:    0,
		},

		"FAIL: Duplicate item is non-letter ASCII character": {
			input:       "aa",
			errExpected: false,
			expected:    0,
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

			actual, err := duplicateItemPriorities(conf)

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
