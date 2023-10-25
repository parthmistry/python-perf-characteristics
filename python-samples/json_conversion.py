import json
import re
import sys

from utils import ElapsedTimeMonitor
from utils import get_logs

log_file_path = sys.argv[1]
total_records_to_process = int(sys.argv[2])

raw_logs = get_logs(total_records_to_process, log_file_path)

print(len(raw_logs))

pattern = re.compile("(.*) \[(.*?)] : (.*)")

raw_json_str_list = []
parsed_dicts = []

for log in raw_logs:
    result = pattern.search(log)
    if result is not None:
        record_map = {
            'time': result.groups()[0],
            'thread': result.groups()[1],
            'message': result.groups()[2]
        }
        raw_json_str_list.append(json.dumps(record_map))
        parsed_dicts.append(record_map)

iterations = 20

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    json_str_list = []

    for parsed_dict in parsed_dicts:
        json_str_list.append(json.dumps(parsed_dict))

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("list size:", len(json_str_list))
    print("serialize iteration duration:", iteration_duration, "ms")


print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    deserialized_list = []

    for raw_json_str in raw_json_str_list:
        deserialized_list.append(json.loads(raw_json_str))

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("list size:", len(deserialized_list))
    print("deserialize iteration duration:", iteration_duration, "ms")

print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")
