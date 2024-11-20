package settings

import (
	"github.com/armkeh/coding-challenges/advent-of-code-2022/utils"
)

type Config struct {
	InputPath string // Path to the input for the day

	Logger utils.Logger // Logger, which includes a minimum logging level
}
