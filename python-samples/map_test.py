import sys
from utils import ElapsedTimeMonitor

uuid_file_path1 = sys.argv[1]
uuid_file_path2 = sys.argv[2]

uuid_list = []
uuid_map = dict()

with open(uuid_file_path1) as file:
    while line := file.readline():
        uuid_list.append(line.rstrip('\n'))

with open(uuid_file_path2) as file:
    while line := file.readline():
        uuid_map[line.rstrip('\n')] = 1

iterations = 20

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    iteration_sum = 0

    for uuid in uuid_list:
        map_value = uuid_map[uuid]
        if map_value is not None:
            iteration_sum += map_value

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("iteration sum:", iteration_sum)
    print("iteration duration:", iteration_duration, "ms")

print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")
