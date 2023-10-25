import sys

log_file_path = sys.argv[1]

log_line_count = 0
line_size_sum = 0

with open(log_file_path, 'r') as log_file:
    while line := log_file.readline():
        log_line_count = log_line_count + 1
        line_size_sum += len(line.rstrip('\n'))

print("log line count:", log_line_count)
print("log line size sum:", line_size_sum)
