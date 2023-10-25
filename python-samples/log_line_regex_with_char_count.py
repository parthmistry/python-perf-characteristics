import re
import sys

log_file_path = sys.argv[1]

char_count = 0

pattern = re.compile("[\\d]+-[\\d]+-[\\d]+ [\\d]+:[\\d]+:[\\d]+\.[\\d]+ .* REQUEST on (.*)")

with open(log_file_path, 'r') as log_file:
    while line := log_file.readline():
        processed_line = line.rstrip('\n')
        result = pattern.search(processed_line)
        if result is not None:
            matched_group_str = result.groups()[0].upper()
            for c in matched_group_str:
                if c == 'A' or c == 'E':
                    char_count += 1

print("char count:", char_count)
