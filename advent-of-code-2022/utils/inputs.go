package utils

import (
	"fmt"
	"os"
	"path/filepath"
	"slices"
	"strings"
)

func GetInput(path string) (string, error) {

	absInputPath, err := filepath.Abs(path)
	if err != nil {
		return "", fmt.Errorf("Could not convert relative input path %s to absolute path; %w", path, err)
	}

	contents, err := os.ReadFile(absInputPath)
	if err != nil {
		return "", fmt.Errorf("Could not open input file, which was expected at %s; %w", absInputPath, err)
	}

	return string(contents), nil
}

func GetInputByLines(path string) ([]string, error) {
	input, err := GetInput(path)
	if err != nil {
		return nil, err
	}

	return strings.Split(input, "\n"), err
}

func RemoveEmptyLines(lines []string) []string {
	isEmptyLine := func(s string) bool { return s == "" }

	return slices.DeleteFunc(lines, isEmptyLine)
}

func GetInputByLinesLessEmptyLines(path string) ([]string, error) {
	input, err := GetInputByLines(path)
	if err != nil {
		return nil, err
	}

	return RemoveEmptyLines(input), nil
}
