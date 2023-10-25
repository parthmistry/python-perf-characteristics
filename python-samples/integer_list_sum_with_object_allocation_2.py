from utils import CustomMath
from utils import ElapsedTimeMonitor

iterations = 20
batch_size = 10_000_000

list1 = []
list2 = []

for i in range(0, batch_size):
    list1.append(i)
    list2.append(i + 5)

global_time_monitor = ElapsedTimeMonitor()
iteration_duration_sum = 0

for n in range(0, iterations):
    iteration_time_monitor = ElapsedTimeMonitor()
    iteration_sum = 0
    custom_math = CustomMath()

    for i in range(0, batch_size):
        value1 = list1[i]
        value2 = list2[i]
        iteration_sum += custom_math.do_sum(value1, value2)

    iteration_duration = iteration_time_monitor.get_elapsed_time_millis()
    iteration_duration_sum += iteration_duration

    print("iteration sum:", iteration_sum)
    print("iteration duration:", iteration_duration, "ms")

print("average iteration duration:", (iteration_duration_sum / float(iterations)), "ms")
print("global duration:", global_time_monitor.get_elapsed_time_millis(), "ms")
