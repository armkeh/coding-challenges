package utils

import (
	logger "log"
)

type Level int

const (
	Debug Level = iota
	Print
)

func printLevel(level Level) string {
	switch level {
	case Debug: return "[debug]"
	case Print: return "[print]"
	default: return "[UNKNOWN LEVEL]"
	}
}

type Logger struct{
	MinLevel Level
}

func (l Logger) log(level Level, msg string, args ...any) {
	if l.MinLevel <= level {
		logger.Printf(printLevel(level) + " " + msg, args...)
	}
}

func (l Logger) Debugf(msg string, args ...any) {
	l.log(Debug, msg, args...)
}

func (l Logger) Printf(msg string, args ...any) {
	l.log(Print, msg, args...)
}
