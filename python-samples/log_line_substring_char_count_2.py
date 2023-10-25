import sys

log_file_path = sys.argv[1]

char_count = 0

with open(log_file_path, 'r') as log_file:
    while line := log_file.readline():
        processed_line = line.rstrip('\n')
        request_on_index = processed_line.find("REQUEST on")
        if request_on_index != -1:
            substring = processed_line[request_on_index:].upper()
            for c in substring:
                if c == 'A' or c == 'E':
                    char_count += 1

print("char count:", char_count)
