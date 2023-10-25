from utils import ElapsedTimeMonitor

iterations = 20
batch_size = 10_000_000

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    iteration_sum = 0

    for i in range(0, batch_size):
        s = str(i)
        iteration_sum += len(s)

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("iteration sum:", iteration_sum)
    print("iteration duration:", iteration_duration, "ms")

print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")

str_list = []

for i in range(0, batch_size):
    str_list.append(str(i))

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    iteration_sum = 0

    for str_value in str_list:
        num = int(str_value)
        iteration_sum += num

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("iteration sum:", iteration_sum)
    print("iteration duration:", iteration_duration, "ms")

print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")
