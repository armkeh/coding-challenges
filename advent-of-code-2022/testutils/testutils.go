package testutils

import (
	"fmt"
	"os"
)

// A CleanupTempFile function cleans up a temporary test file.
type CleanupTempFile = func() error

func emptyCleanup() error {
	return nil
}

// CreateTempInputFile creates a temporary file containing the given `input`.
// It returns the file's name (path) and a cleanup function to remove it.
func CreateTempInputFile(input string) (string, CleanupTempFile, error) {
	f, err := os.CreateTemp("/tmp/", "aoc2022-test-input-*.txt")
	if err != nil {
		return "", emptyCleanup, fmt.Errorf("Error creating temporary input file %w", err)
	}

	cleanup := func() error {
		err := os.Remove(f.Name())
		if err != nil {
			return fmt.Errorf("Failed to cleanup temporary input file: %s", err)
		}
		return nil
	}

	// Write the test input to the file
	_, err = f.Write([]byte(input))
	if err != nil {
		return "", emptyCleanup, fmt.Errorf("Failed to write input to temporary input file: %s", err)
	}

	return f.Name(), cleanup, nil
}
