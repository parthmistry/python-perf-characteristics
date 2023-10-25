import re
import sys

log_file_path = sys.argv[1]

log_line_count = 0

pattern = re.compile("[\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+\.[\\d]+ .* REQUEST on (.*)")

with open(log_file_path, 'r') as log_file:
    while line := log_file.readline():
        processed_line = line.rstrip('\n')
        result = pattern.search(processed_line)
        if result is not None:
            log_line_count += 1

print("matching log line count:", log_line_count)
