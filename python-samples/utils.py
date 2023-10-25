from datetime import datetime

class ElapsedTimeMonitor:
    start_time: datetime

    def __init__(self):
        self.start_time = datetime.now()

    def get_elapsed_time_millis(self):
        current_time = datetime.now()
        return ((current_time - self.start_time).seconds * 1000) + int((current_time - self.start_time).microseconds / 1000)

def get_logs(n, file_path):
    log_lines = []

    with open(file_path, 'r') as log_file:
        for n in range(0, n):
            line = log_file.readline()
            processed_line = line.rstrip('\n')
            log_lines.append(processed_line)

    return log_lines

class CustomMath:

    def do_sum(self, a, b):
        return a + b
