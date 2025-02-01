#!/bin/bash

# Define the log file location (inside the project directory)
LOGFILE="$(pwd)/git-logger/git-log-output.txt"

# Run `git log` and append the output to the log file
{
    echo "==========================================="
    echo "Timestamp: $(date)"
    echo "Git Log Output:"
    echo "-------------------------------------------"
    git log
    echo "==========================================="
    echo ""
} >> "$LOGFILE"

# Notify the user
echo "Git log output has been saved to $LOGFILE"
