package day01

import (
	"slices"
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
		input        string
		errExpected  bool
		expectedCals int
		expectedInv  Elf
	}{
		"PASS: Empty input": {
			input:        "",
			errExpected:  false,
			expectedCals: 0,
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

			actualCals, actualInv, err := maxCalories(conf)

			if tc.errExpected {
				if err == nil {
					t.Fatalf("Error expected, but no error returned.")
				}
			} else {
				if err != nil {
					t.Fatalf("Returned unexpected error: %s", err)
				}
				if actualCals != tc.expectedCals || !slices.Equal(actualInv, tc.expectedInv) {
					t.Fatalf("Actual results (calories %d, inventory %v) does not match expected result (%d, %v).", actualCals, actualInv, tc.expectedCals, tc.expectedInv)
				}
			}
		})
	}
}
