from utils import ElapsedTimeMonitor

iterations = 20
batch_size = 10_000_000

a = 5
b = 10

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    iteration_sum = 0

    for i in range(0, batch_size):
        iteration_sum += (i + a + b)

    print("iteration sum:", iteration_sum)
    iteration_duration_sum += iteration_time_monitor.get_elapsed_time_millis()

print("average iteration duration:", (iteration_duration_sum / iterations), "ms")
print("total duration:", global_time_monitor.get_elapsed_time_millis(), "ms")
