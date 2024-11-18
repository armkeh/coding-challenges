package day1

import (
	"fmt"
	"log"
	"os"
	"path/filepath"
)

func getInput(relInputPath string) string {

	absInputPath, err := filepath.Abs(relInputPath)
	if err != nil {
		// We don't have a way to gracefully handle not being able to find input; abort.
		log.Fatal(fmt.Sprintf("Could not convert relative input path %s to absolute path; %s", relInputPath, err))
	}

	contents, err := os.ReadFile(absInputPath)
	if err != nil {
		// Again, we can't gracefully handle the input not being readable; abort.
		log.Fatal(fmt.Sprintf("Could not open input file, which was expected at %s; %s", absInputPath, err))
	}

	return string(contents)
}

func main() {
}
